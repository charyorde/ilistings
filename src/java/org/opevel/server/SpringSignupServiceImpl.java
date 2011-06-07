/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.opevel.client.SpringSignupService;
import org.opevel.server.domain.AgentEntity;
import org.opevel.server.domain.BuyerEntity;
import org.opevel.server.domain.PMF;
import org.opevel.server.domain.UserEntity;
import org.opevel.shared.UserEntityDTO;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author Administrator
 * This bean does the RPC thing. That is pass in here your functions that you want the server to perform
 */
//@Controller
//@RequestMapping("register")
//public class SpringSignupServiceImpl extends GWTController implements SpringSignupService {
public class SpringSignupServiceImpl extends RemoteServiceServlet implements SpringSignupService {

    final Logger logger = Logger.getLogger(SpringSignupServiceImpl.class.getName());

    private UserEntity userentity;
    private AgentEntity agententity;
    private BuyerEntity buyerentity;

    public void setUserentity(UserEntity userentity) {
        this.userentity = userentity;
    }

    public SpringSignupServiceImpl() {
        logger.info("calling SpringSignupServiceImpl");
    }


    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    /*@RequestMapping(method = RequestMethod.GET)
    public void handle(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("Hello Facebook page");
    }*/
    /*
     * Handle the signup request and dispatch to respective views based on request parameter type
     */
    //@RequestMapping(method = RequestMethod.POST)
    public String handleSignUpRequest(@RequestParam String type, HttpServletRequest req, HttpServletResponse res, ModelMap model) {

        logger.info("calling handleSignupRequest");
        //Map agentData = new HashMap();
        //Map buyerData = new HashMap();
        agententity = new AgentEntity();
        buyerentity = new BuyerEntity();
        //agentData.put("agententity", agententity);
        //buyerData.put("buyerentity", buyerentity);
        if(type.equals("Agent")) {
            model.addAttribute("agententity", agententity);
            //return "agent";
            //return new ModelAndView("agent", model);
            return "redirect:/signup/account";
        }
        else if(type.equals("Buyer")) {
            model.addAttribute("buyerentity", buyerentity);
            //return new ModelAndView("buyer", model);
            return "redirect:/signup/account";
            //return "buyer";
        }

        return null;
    }

    /*
     * GWT-RPC is a POST request already. Doesn't need method=RequestMethod.POST
     */
    @Override
    //@RequestMapping("register")
    public Boolean createUser(UserEntityDTO userEntityDto) {
        logger.info("SpringignupServiceImpl: calling createUser");
        userentity = new UserEntity();
        userentity.setEmail(userEntityDto.getEmail());
        userentity.setPass(userEntityDto.getPass());
        userentity.setType(userEntityDto.getType());
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
}
