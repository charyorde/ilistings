/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.web;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpSession;
import org.opevel.server.domain.AgentEntity;
import org.opevel.server.domain.BuyerEntity;
import org.opevel.server.domain.PMF;
import org.opevel.server.domain.UserEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/** 
 *
 * @author Administrator
 */
//@RequestMapping("/signup/account")
public class SignupAccountTypeController extends SimpleFormController {

    private static final Logger log = Logger.getLogger(SignupAccountTypeController.class.getName());

    private AgentEntity  agententity;
    private BuyerEntity buyerentity;

    public void setAgententity(AgentEntity agententity) {
        this.agententity = agententity;
    }

    public void setBuyerentity(BuyerEntity buyerentity) {
        this.buyerentity = buyerentity;
    }
    
    public SignupAccountTypeController() {
        //Initialize controller properties here or 
        //in the Web Application Context

        log.info("Constructor: constructing SignupAccountTypeController");
        //setCommandClass(UserEntity.class);
        //setCommandName("userentity");
        //setValidator(new AccountTypeValidator());
        //setSuccessView("redirect:profile.htm");This is url that will be redirected to at the success of the form at agent.jsp
        //setFormView("agent");This is the real jsp page to fill the form
    }
    
    @Override
    public ModelAndView handleRequestInternal(HttpServletRequest request,
                                  HttpServletResponse response)
                           throws Exception {
       
        log.info(request.toString());
        log.info(response.toString());
        
        if(request.getParameter("type") == null) {
        log.info("handleRequest is called in SignupAccountTypeController. NULL");
        }else {
            log.info("handleRequest is called in SignupAccountTypeController. NOT NULL");
        }
        Map agentData = new HashMap();
        Map buyerData = new HashMap();
        agententity = new AgentEntity();
        BuyerEntity buyerentity = new BuyerEntity();
        agentData.put("agententity", agententity);
        buyerData.put("buyerentity", buyerentity);

        String type = request.getParameter("type");

        if(type.equals("Agent")) {
            log.info("Account type == Agent");
            setCommandClass(AgentEntity.class);
            setCommandName("agententity");
            setSuccessView("agent");
            createUser(request, type);
        return new ModelAndView("agent", agentData);
        }else {
            return new ModelAndView("buyer");
        }
        
    }

    private static Boolean createUser(HttpServletRequest request, Object type) {
        log.info("calling createUser in SignupAccountTypeController");

        String accountType = (String) type;

        UserEntity userentity = new UserEntity();
        userentity.setEmail(request.getParameter("email"));
        userentity.setPass(request.getParameter("pass"));
        userentity.setType(accountType);
        boolean added = false;
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(userentity);
            added = true;
        }
        finally {
            pm.close();
        }
        return added;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request)
                            throws Exception {
        log.info("SignupAccountTypeController formBackingObject is called");
        //retrieve all the request values and make it an object (UserEntity)
        Map requestMap = request.getParameterMap();
        log.info(requestMap.values().toString());
        //user = (UserEntity) requestMap;
        // retrieve the BackingObject Key for the current request and pass it into the URL
        return super.formBackingObject(request);
        //return user;
    }

    /*@Override
    public Map referenceData(HttpServletRequest request, Object command, Errors errors) {
        AgentEntity agententity = (AgentEntity) command;
     * BuyerEntity buyerentity = (BuyerEntity) command;
    }*/
    
    @Override
    protected void doSubmitAction(Object command) throws Exception {
        //throw new UnsupportedOperationException("Not yet implemented");
        log.info("SignupAccountTypeController doSubmitAction is called");
    }

    //Use onSubmit instead of doSubmitAction 
    //when you need access to the Request, Response, or BindException objects
    @Override
    protected ModelAndView onSubmit(
            HttpServletRequest request, 
            HttpServletResponse response, 
            Object command, 
            BindException errors) throws Exception {
        log.info("SignupAccountTypeController onSubmit is called");
        //persist data
        ModelAndView mv = new ModelAndView(getSuccessView());
        //Do something...
        return mv;
    }

    class AgentValidation implements Validator {

        @Override
        public boolean supports(Class type) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void validate(Object o, Errors errors) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }
}