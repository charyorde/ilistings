/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.web;

import java.util.HashMap;
import java.util.Map;
//import org.apache.log4j.Logger;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.opevel.server.domain.AgentEntity;
import org.opevel.server.domain.BuyerEntity;
import org.opevel.server.domain.PMF;
import org.opevel.server.domain.UserEntity;
import org.opevel.shared.UserEntityDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.SessionScope;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
@Controller
//@RequestMapping("/signup")
//public class SignupController extends SimpleFormController {
public class SignupController {

    private static final Logger log = Logger.getLogger(SignupController.class.getName());

    private UserEntity userentity;
    private AgentEntity  agententity;
    private BuyerEntity buyerentity;
    private SessionScope appSess;
    private HttpSession session;

    /*public SignupController() {
        log.info("SignupController got the signup request");
        //setValidator(new SignupValidator());
        setCommandClass(UserEntity.class);
        setCommandName("userentity");
        //setSuccessView("redirect:agent");
        setFormView("signup");
    }*/

    /*@Autowired
    public SignupController(UserEntity userentity) {
        this.userentity = userentity;
    }*/

    public void setUserentity(UserEntity userentity) {
        this.userentity = userentity;
    }
    
    /**
     * @param session the session to set
     
    public void setSession(HttpSession session) {
        this.session = session;
    }*/

    /**
     * @param appSess the appSess to set
     */
    public void setAppSess(SessionScope appSess) {
        this.appSess = appSess;
    }

    @RequestMapping("/signup")
	public ModelAndView signupHandler() {
		//ModelAndView mav = new ModelAndView("signup");
		Map dataMap = new HashMap();
                userentity = new UserEntity();
                dataMap.put("userentity", userentity);
                //model.addAttribute("userentity", userentity);
                return new ModelAndView("signup", dataMap);
	}

    @RequestMapping(value = "agent", method=RequestMethod.GET)
    public ModelAndView doAgent(ModelMap model) {
        AgentEntity agententity = new AgentEntity();
        model.addAttribute("agententity", agententity);
        return new ModelAndView("agent", model);
    }
    
    @RequestMapping(value = "buyer", method=RequestMethod.GET)
    public ModelAndView doBuyer(ModelMap model) {
        BuyerEntity buyerentity = new BuyerEntity();
        model.addAttribute("buyerentity", buyerentity);
        return new ModelAndView("buyer", model);
    }

    @RequestMapping("account")
    public String setupForm(@RequestParam("type") String type, HttpServletRequest request, ModelMap model) {
        //Pet pet = this.clinic.loadPet(petId);
        //model.addAttribute("pet", pet);
        Map agentData = new HashMap();
        Map buyerData = new HashMap();
        agententity = new AgentEntity();
        buyerentity = new BuyerEntity();
        agentData.put("agententity", agententity);
        buyerData.put("buyerentity", buyerentity);
        if(type.equals("Agent")) {
            model.addAttribute("agententity", agententity);
            createUser(request, type);
            return "agent";
        }
        else if(type.equals("Buyer")) {
            model.addAttribute("buyerentity", buyerentity);
            createUser(request, type);
            return "buyer";
        }
        //createUser(request, type);
        return null;
    }

    private static Boolean createUser(HttpServletRequest request, Object type) {
        log.info("calling createUser in SignupController");

        PersistenceManager pm = PMF.get().getPersistenceManager();
        String accountType = (String) type;
        boolean added = false;

        UserEntity userentity = new UserEntity();
        userentity.setEmail(request.getParameter("email"));
        userentity.setPass(request.getParameter("pass"));
        userentity.setType(accountType);
        try {
            pm.makePersistent(userentity);
            added = true;
        }
        finally {
            pm.close();
        }
        return added;
    }
   

    /*@Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {

            log.info("handleRequestInternal is called in SignupController.class");

            log.info("creating session");
            request.getSession();

            Map dataMap = new HashMap();
            userentity = new UserEntity();
            dataMap.put("userentity", userentity);
            return new ModelAndView(getFormView(), dataMap);
    }*/

    /*@Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
        log.info("SignupController onSubmit is called");
        return new ModelAndView(getFormView());
    }*/

    /*@Override
    protected void doSubmitAction(Object command) throws Exception {
        //do some logic here to validate and dispatch to either agent or buyer views
        //setFormView("agent");
        log.info("doSubmitAction is called in SignupController");
    }*/

    /*@Override
    protected Object formBackingObject(HttpServletRequest request)
                            throws Exception {
        log.info("SignupController formBackingObject is called");
        //retrieve all the request values and make it an object (UserEntity)
        Map requestMap = request.getParameterMap();
        log.info(requestMap.values().toString());
        //userentity = (UserEntity) requestMap;
        return super.formBackingObject(request);
        //return userentity;
    }*/

    //@RequestMapping(value="/account", method = RequestMethod.POST)
    public Boolean createUser(UserEntityDTO userEntityDto) {
        log.info("calling createUser in SignupController");
        return true;
        /*UserEntity userentity = new UserEntity();
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
        return added;*/
    }


    //@Override
    /*public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return new ModelAndView("user/register.jsp");
    }*/

    class SignupValidator implements Validator {

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