/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.web;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.opevel.server.dao.AgentEntityDAOImpl;
import org.opevel.server.domain.AgentEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/** 
 *
 * @author Administrator
 */
@Controller
public class AgentController {

    private static final Logger log = Logger.getLogger(AgentController.class.getName());

    private AgentEntity agententity;
    private AgentEntityDAOImpl agententitydao ;

    /**
     * @return the agententity
     */
    public AgentEntity getAgententity() {
        return agententity;
    }

    /**
     * @param agententity the agententity to set
     */
    public void setAgententity(AgentEntity agententity) {
        this.agententity = agententity;
    }

    public AgentController() {
        //Initialize controller properties here or 
        //in the Web Application Context
        log.info("AgentController is called");
        this.agententitydao = new AgentEntityDAOImpl();
        //setCommandClass(AgentEntity.class);
        //setCommandName("agententity");
        //setSuccessView("redirect:thankyou.htm");
        //setFormView("agent");
    }
    

    @RequestMapping(value = "agent/confirm", method = RequestMethod.GET)
    public ModelAndView handleRequest(ModelMap model) {
        //registers the agententity model for the thank you page
        Map data = new HashMap();
        data.put("agententity", agententity);
        //data.put("buyerentity", buyerentity);
        model.addAllAttributes(data);
        log.info("Data in AgentEntity model is: " + model.toString());
        ModelAndView mav = new ModelAndView("thankyou", model); // calls the thankyou.jsp page
        return mav;
    }

    @RequestMapping(value = "agent/confirm/{objectId}", method = RequestMethod.GET)
    public ModelAndView displayConfirm(@PathVariable String objectId, ModelMap model, @ModelAttribute AgentEntity agententity) {
        //registers the agententity model for the thank you page
        //Map data = new HashMap();
        //data.put("agententity", agententity);
        //data.put("buyerentity", buyerentity);
        //model.addAllAttributes(data);
        agententity = new AgentEntity();
        model.addAttribute("agententity", agententity);
        ModelAndView mav = new ModelAndView("thankyou", model); // calls the thankyou.jsp page
        mav.addObject(AgentEntityDAOImpl.findAgentEntityByString(objectId));
        log.info(AgentEntityDAOImpl.findAgentEntityByString(objectId).toString());
        return mav;
    }

    @RequestMapping(method=RequestMethod.POST)
    public String processSubmit(@ModelAttribute AgentEntity agententity) {
        //validate form, store data in the database and pass parameters to the url
        this.agententitydao.createNewAgent(agententity);
        return "redirect:confirm/" + agententity.getId(); //call HttpServletResponse.sendRedirect(). Redirects to an existing url
    }
    /*@Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Map agententityData = new HashMap();
        agententityData.put("agententity", agententity);
        setSuccessView(getSuccessView());
        return new ModelAndView("thankyou", agententityData);
     }*/

    /*@Override
    protected void doSubmitAction(Object command) throws Exception {
        //throw new UnsupportedOperationException("Not yet implemented");
        log.info("AgentController doSubmitAction is called");
        setSuccessView("thankyou");
    }*/


    /*@Override
    protected Object formBackingObject(HttpServletRequest request)
                            throws Exception {
        log.info("AgentController formBackingObject is called");
        //retrieve all the request values and make it an object (UserEntity)
        Map requestMap = request.getParameterMap();
        log.info(requestMap.values().toString());
        //user = (UserEntity) requestMap;
        // retrieve the BackingObject Key for the current request and pass it into the URL
        return super.formBackingObject(request);
        //return user;
    }*/
    
    //Use onSubmit instead of doSubmitAction 
    //when you need access to the Request, Response, or BindException objects
    /*
    @Override
    protected ModelAndView onSubmit(
            HttpServletRequest request, 
            HttpServletResponse response, 
            Object command, 
            BindException errors) throws Exception {
        ModelAndView mv = new ModelAndView(getSuccessView());
        //Do something...
        return mv;
    }
    */

}