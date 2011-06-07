/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.web.gwt.server;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
//import org.apache.log4j.Logger;
import com.google.gwt.user.server.rpc.SerializationPolicy;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.opevel.server.domain.AgentEntity;
import org.opevel.shared.GWTSerializer;
import org.opevel.shared.exceptions.InfrastructureException;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Kayode Odeyemi
 * This is where you can use both Servlet and SpringMVC APIs to control requests and handle RPC calls
 */
public class GWTController extends RemoteServiceServlet implements Controller, RemoteService,
        ServletContextAware, ServletConfigAware, GWTSerializer {

    final Logger logger = Logger.getLogger(GWTController.class.getName());

    private AgentEntity agententity;

    private ServletContext servletContext;
    private RemoteService remoteService;
    private Class remoteServiceClass;
    private ThreadLocal perThreadRequest;

    /**
  * Setter for Spring injection of the GWT RemoteService object.
  * @param RemoteService the GWT RemoteService implementation
  * that will be delegated to by
  * the {@code GWTController}.
  */
  public void setRemoteService( RemoteService remoteService )
  {
    this.remoteService = remoteService;
    this.remoteServiceClass = this.remoteService.getClass();
  }

    /**
     * Gets <code>ServletContext</code>.
     */
    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    /**
     * Implementation of <code>ServletContextAware</code>.
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void setPerThreadRequest(ThreadLocal perThreadRequest) {
        this.perThreadRequest = perThreadRequest;
    }
    /**
  * Process the RPC request encoded into the payload string and return a string
  * that encodes either the method return or an exception thrown by it.
  */
    /*@Override
  public String processCall(String payload) throws SerializationException
  {
      logger.info("calling processCall");
    try
    {
       RPCRequest rpcRequest =
           RPC.decodeRequest(payload, this.remoteServiceClass);

       // delegate work to the spring injected service
       return RPC.invokeAndEncodeResponse(this.remoteService,
                                          rpcRequest.getMethod(),
                                          rpcRequest.getParameters(),
                                          rpcRequest.getSerializationPolicy());
    }
    catch (IncompatibleRemoteServiceException e)
    {
      return RPC.encodeResponseForFailure(null, e);
    }

  }*/

     private boolean serializeEverything = false;

    public void setSerializeEverything(boolean serializeEverything) {
        this.serializeEverything = serializeEverything;
    }

    @Override
    public String processCall(String payload)
    throws SerializationException {
        try {

            RPCRequest rpcRequest = RPC.decodeRequest(payload, this
                    .getClass(), this);
            ServerSerializationStreamWriter_1_5_3 writer = getWriter(rpcRequest);


            logger.info("logging RPCRequest getMethod" + rpcRequest.getMethod().toString());
            logger.info("logging RPCRequest getParameters" + rpcRequest.getParameters().toString());

            return RPC1524.invokeAndEncodeResponse(this, rpcRequest
                    .getMethod(), rpcRequest.getParameters(), writer);// This will throw NullPointerException if the RPCRequest info is unavailable

        } catch (IncompatibleRemoteServiceException ex) {
            logger.severe(
                    "An IncompatibleRemoteServiceException was thrown while processing this call." + ex);
            return RPC.encodeResponseForFailure(null, ex);
        } catch (Exception e){
            logger.severe(
                    "An Exception was thrown while processing this call." + e);
            return RPC.encodeResponseForFailure(null, e);
        }
    }

    /**
     * would prefer to call doGetSerializationPolicy() so that we could
     * use the new serializer policies, but not sure how to get the
     * necessary parameters
     *
     * @return
     */
    private ServerSerializationStreamWriter_1_5_3 getWriter(
            RPCRequest rpcRequest) {
        return getWriter(rpcRequest.getSerializationPolicy());
    }

    private ServerSerializationStreamWriter_1_5_3 getWriter() {
        return getWriter(OneFourTenSerializationPolicy.getInstance());
    }

    private ServerSerializationStreamWriter_1_5_3 getWriter(
            SerializationPolicy serializationPolicy) {

        ServerSerializationStreamWriter_1_5_3 writer = new ServerSerializationStreamWriter_1_5_3(
                serializationPolicy);

        writer.setValueWriter(Object.class, new ValueWriter() {
            public void write(ServerSerializationStreamWriter_1_5_3 stream,
                    Object instance) throws SerializationException {
                //stream.writeObject(HibernateFilter.filter(instance));
            }
        });
        return writer;
    }

/**
     * implement GWTSerializer. Used for GWT host pages that want to
     * serialize objects to bootstrap GWT and prevent needing a startup
     * async call.
     */
    public String serializeObject(Object object, Class<?> clazz)
    throws InfrastructureException {

        ServerSerializationStreamWriter_1_5_3 serializer = getWriter();

        try {
            serializer.serializeValue(object, clazz);
        } catch (SerializationException e) {
            throw new InfrastructureException(e);
        }
        String bufferStr = "//OK" + serializer.toString();
        return bufferStr;
    }

    /**
     * Normal GWT Serialization requires that we do a GWT compile to
     * create the serialization whitelist. Unfortunately this means we
     * can't just restart jetty and have this Controller Serialize, unless
     * we do a gwt compile, which slows us down considerably. Solutions is
     * to use our funky laissez faire 1.4.10 (RC1) style serialization
     * policy to serialize everything which means we don't need to
     * recompile all the gwt stuff just to restart jetty.
     *
     * Use the 'serializeEverything' variable which is set differently on
     * test and deployment machines to go to regular 1.5 serialization
     * when deployed.
     */
    @Override
    protected SerializationPolicy doGetSerializationPolicy(
            HttpServletRequest request, String moduleBaseURL,
            String strongName) {
        if (serializeEverything) {
            logger.warning("Using 1.4.10 (RC1) style serializaion.");
            return OneFourTenSerializationPolicy.getInstance();
        } else {
            logger.info("Using Standard Serialization.");
            return super.doGetSerializationPolicy(request, moduleBaseURL,
                    strongName);
        }
    }

   public RemoteService getRemoteService() {
        return remoteService;
    }
 /*
    public Class getRemoteServiceClass() {
        return remoteService.getClass();
    }*/


  
    /**
     * Handles request and delegates to GWT's <code>RemoteServiceServlet.doPost(request, response)</code>.
     * The major method here is the doPost which passes request and response to GWT's RemoteServiceServlet
     * implementing class.
     */
    //@Override
    //@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            logger.info("Processing handleRequest before.");

            logger.info(request.toString());
            //logger.info(request.getParameter("email"));
            //logger.info("RemoteServiceClass is" + getRemoteServiceClass().toString());
            doPost(request, response);
            logger.info("Processing handleRequest after.");

            return null; //response handled by GWT RPC over XmlHttpRequest
            //return new ModelAndView("agent", agentData);
        } catch (Exception e) {
            logger.severe("Handle request error: " + e.getMessage());

            return null;
        }
    }

    @Override

    public void setServletConfig(ServletConfig servletConfig) {

        try {

            super.init(servletConfig);

        } catch (ServletException e) {

            e.printStackTrace();

        }

    }


}
