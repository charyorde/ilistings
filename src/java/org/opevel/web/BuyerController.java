/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.web;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.opevel.server.domain.BuyerEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/** 
 *
 * @author Administrator
 */
@Controller
public class BuyerController{

    private static final Logger log = Logger.getLogger(BuyerController.class.getName());

    private BuyerEntity buyerentity;

    public BuyerEntity getBuyerentity() {
        return buyerentity;
    }

    public void setBuyerentity(BuyerEntity buyerentity) {
        this.buyerentity = buyerentity;
    }

    public BuyerController() {
        //Initialize controller properties here or 
        //in the Web Application Context

        //setCommandClass(BuyerEntity.class);
        //setCommandName("buyerentity");
        //setSuccessView("redirect:profile.htm");
        //setFormView("buyer");
    }

    @RequestMapping(value = "buyer/confirm", method = RequestMethod.GET)
    public ModelAndView handleRequest(ModelMap model) {
        //registers the agententity model for the thank you page
        Map data = new HashMap();
        buyerentity = new BuyerEntity();
        data.put("buyerentity", buyerentity);
        //data.put("buyerentity", buyerentity);
        model.addAllAttributes(data);
        log.info("Data in AgentEntity model is: " + model.toString());
        ModelAndView mav = new ModelAndView("thankyou", model); // calls the thankyou.jsp page
        return mav;
    }
    /*@Override
    protected void doSubmitAction(Object command) throws Exception {
        log.info("AgentController doSubmitAction is called");
        setSuccessView("profile");
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