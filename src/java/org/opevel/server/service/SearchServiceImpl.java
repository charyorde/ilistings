/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.opevel.client.service.SearchService;
import org.opevel.shared.exceptions.RESTfulWebServiceException;

/**
 *
 * @author Administrator
 */
public class SearchServiceImpl extends RemoteServiceServlet implements SearchService {

    final Logger logger = Logger.getLogger(SearchServiceImpl.class.getName());

    private DrupalXmlRpcService xmlrpcservice;
    private HttpServletRequest request;
    private HttpServletResponse response;
    String serviceDomain, apiKey, serviceURL;

    HttpSession session;

    public SearchServiceImpl() {
        //logger.info(request.toString());
    }

    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    //place searchListingService here
    @Override
    public ArrayList<Object> searchListingsService() {
        logger.info("called searchListingsService");
        return this.searchListingsService();
    }

    public Boolean login(String username, String password) {
        /*try {
            processPost(request, response);
            logger.info(request.toString());
        } catch (IOException ex) {
            logger.warning("IOException: " + ex);
        } catch (ServletException ex) {
            logger.warning("ServletException: " + ex);
        } catch (SerializationException ex) {
            logger.warning("SerializationException: " + ex);
        }*/

        serviceDomain = "pekuliarproperties";
        apiKey = "918d61b76c51c12aa04542de821bdf17";
        serviceURL = "http://pekuliarproperties?q=services/xmlrpc";
        //username = "root";
        //password = "r00tu$5r";
        logger.info("SearchServiceImpl login data is: " + username + " " + password);
        xmlrpcservice = new DrupalXmlRpcService(serviceDomain, apiKey, serviceURL);
        return xmlrpcservice.Login(username, password);
        //return new DrupalXmlRpcService("pekuliarproperties", "918d61b76c51c12aa04542de821bdf17", "http://pekuliarproperties?q=services/xmlrpc").Login(username, password);
    }

    @Override
    public String serviceLogin(String username, String password) throws RESTfulWebServiceException {
        try {
            URL u = new URL("http://pekuliarproperties/services/rest/user/1");
            HttpURLConnection uc = (HttpURLConnection) u.openConnection();
            uc.setRequestProperty("Content-Type", "application/json");
            uc.setRequestMethod("POST");
            uc.setDoOutput(false);

            Integer status = uc.getResponseCode();
            if (status != 200) {
                    throw (new RESTfulWebServiceException(
                                    "Invalid HTTP response status code " + status + " from web service server."));
            } else {
                logger.info(status.toString());
            }
            logger.info("Making request");
            logger.info(uc.getResponseMessage());
            return uc.getResponseMessage();
    } catch (MalformedURLException e) {
			throw new RESTfulWebServiceException(e.getMessage(), e);
		}catch (IOException e) {
			throw new RESTfulWebServiceException(e.getMessage(), e);
		}
}
}
