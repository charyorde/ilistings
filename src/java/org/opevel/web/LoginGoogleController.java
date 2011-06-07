/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.web;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.opevel.server.dao.UserEntityDAOImpl;
import org.opevel.server.domain.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/auth")
public class LoginGoogleController{

    private static final Logger log = Logger.getLogger(LoginGoogleController.class.getName());

    public LoginGoogleController() {
        log.info("constructing LoginGoogleController");
        //doPost();
    }

    //@RequestMapping(value="/auth/openid/google")
    //@RequestMapping(value="/google", method=RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("received LoginGoogleController request");
        UserService userService = UserServiceFactory.getUserService();
        //Store openid authenticated user into App Engine User Object
        User user = userService.getCurrentUser(); // or req.getUserPrincipal()
        Set<String> attributes = new HashSet();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if(user != null) {
            out.println("Hello <i>" + user.getNickname() + "</i>!");
            out.println("[<a href=\""
                    + userService.createLogoutURL(request.getRequestURI())
                    + "\">sign out</a>]");
        }else {
            //destinationURL should be to another controller that will dispatch request to GWT or jsp
        String googleLoginUrl = userService.createLoginURL(request.getRequestURI(), null, "google.com/accounts/o8/id", attributes);
        //String googleLoginUrl = userService.createLoginURL("google.com/accounts/o8/id", null);
        //String googleLoginUrl = userService.createLoginURL("google.com/accounts/o8/id");
        log.info("Going to Google login URL: " + googleLoginUrl);
        response.sendRedirect(googleLoginUrl);
        }
    }
    //@RequestMapping(value="/google", method=RequestMethod.GET)
    public String doPost() {
        log.info("received LoginGoogleController request");
        return "redirect:index";
    }


    @RequestMapping(method=RequestMethod.GET)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //String callbackURL = buildCallBackURL(request, AuthenticationProvider.GOOGLE);
        //doGet(request, response);
        UserService userService = UserServiceFactory.getUserService();
        //Store openid authenticated user into App Engine User Object
        User user = userService.getCurrentUser(); // or req.getUserPrincipal()
        Set<String> attributes = new HashSet();
        List<UserEntity> emailList = null;

        //user is not null and loggedIn
        if(user != null) {
            emailList = UserEntityDAOImpl.findUserByEmail(user.getEmail());
            return "welcome"; // display welcome.jsp
        }
        else if(user != null && emailList != null) {//findByEmail in UserEntity i.e the user exists

            //userEntity.setUser(user);
            UserEntityDAOImpl.createUserFromOpenID(user); //persist the user in UserEntity
            // on success of the persistence, login the user and redirect to /auth/account (so that the user can select either buyer or agent).
            return "choose"; // display choose.jsp
        }
        // user is null
         else {
            //destinationURL should be to another controller that will dispatch request to GWT or jsp
            String googleLoginUrl = userService.createLoginURL(request.getRequestURI(), null, "google.com/accounts/o8/id", attributes);
            log.info("Going to Google login URL: " + googleLoginUrl);
            response.sendRedirect(googleLoginUrl);
            return null;
        }
        //return null;
    }

    /*@RequestMapping(method=RequestMethod.GET)
    public String handleServiceRequest() {
        log.info("LoginGoogleController. Handling auth request");

        return "redirect:auth/google";
    }*/


    // To be moved to UserEntityDAOImpl
    private Object findByEmail(String email) {
        throw new UnsupportedOperationException("Not yet implemented");
        //use getObjectById() to get the email here
    }
}
