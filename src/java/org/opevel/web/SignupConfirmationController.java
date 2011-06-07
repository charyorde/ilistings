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
import org.opevel.server.domain.BuyerEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/** 
 *
 * @author Administrator
 */
@Controller
@RequestMapping("thankyou")
public class SignupConfirmationController {

    private static final Logger log = Logger.getLogger(SignupConfirmationController.class.getName());

    private AgentEntity  agententity;
    private BuyerEntity buyerentity;
    private AgentEntityDAOImpl agententitydao;

    public void setAgententity(AgentEntity agententity) {
        this.agententity = agententity;
    }

    public void setBuyerentity(BuyerEntity buyerentity) {
        this.buyerentity = buyerentity;
    }

    public SignupConfirmationController() {
        //Initialize controller properties here or 
        //in the Web Application Context

        log.info("constructor: SignupConfirmationController");
        //setCommandClass(MyCommand.class);
        //setCommandName("MyCommandName");
        //setSuccessView("successView");
        //setFormView("thankyou");
        this.agententitydao = new AgentEntityDAOImpl();
    }

    /*@Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                  HttpServletResponse response)
                           throws Exception {
        return new ModelAndView("thankyou");

    }*/

    @RequestMapping("{objectId}")
    public ModelAndView handleRequest(@PathVariable Long objectId, ModelMap model) {
        Map data = new HashMap();
        data.put("agententity", agententity);
        //data.put("buyerentity", buyerentity);
        model.addAllAttributes(data);
        ModelAndView mav = new ModelAndView("thankyou", model);
	//mav.addObject(this.agententitydao.findAgentEntity(objectId));
        //mav.addAllObjects(model);
        return mav;
    }

    //@RequestMapping("/thankyou/buyer/{objectId}")
    /*@RequestMapping("/thankyou/{objectId}")
	public ModelAndView ownerHandler(@PathVariable("objectId") int objectId) {
		ModelAndView mav = new ModelAndView("thankyou");
		mav.addObject(this.clinic.loadOwner(ownerId));
		return mav;
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