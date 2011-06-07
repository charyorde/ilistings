/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.web;

import com.google.appengine.api.datastore.PhoneNumber;
import java.text.DateFormat;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Administrator
 */
public class BindingInitializer implements WebBindingInitializer {

    @Override
    public void initBinder(WebDataBinder binder, WebRequest request)
{
       binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
       binder.registerCustomEditor(Long.class,
           new CustomNumberEditor(Long.class, true)); // use this instead?
       binder.registerCustomEditor(PhoneNumber.class,
           new CustomNumberEditor(Long.class, true)); // use this instead?
       //binder.registerCustomEditor(Date.class, new CustomDateEditor(DateFormat.class, true)); // use this instead?
   }


}
