/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import org.enunes.gwt.mvp.client.EventBus;
import org.opevel.client.event.MainSignupEvent;

/**
 *
 * @author Administrator
 */
public class AppController {

    /*private final EventBus eventBus;

    public AppController() {
    }

    public AppController(EventBus eventBus) {
        this.eventBus = eventBus;
        //doEvents();
    }*/


    public static final String LOCAL_HOST = "http://localhost:8888/";
    public static final String REMOTE_HOST = "http://ilistings.appspot.com/";

    /**
     *
     * Switch between localhost for testing &
     */
    public static String getRelativeURL(String url) {
        String realModuleBase;

        if (GWT.isScript()) {

            // Log.debug("ModuleBaseURL: " + GWT.getModuleBaseURL());

            String moduleBase = GWT.getModuleBaseURL();

            // Use for Deployment to production server
            //
            realModuleBase = REMOTE_HOST;

            // Use to test compiled browser locally
            //
            if (moduleBase.indexOf("localhost") != -1) {
                // Log.debug("Testing. Using Localhost");
                realModuleBase = LOCAL_HOST;
            }

        } else {
            // realModuleBase = GWT.getModuleBaseURL();

            // This is the URL for GWT Hosted mode
            //
            realModuleBase = LOCAL_HOST;
        }

        return realModuleBase + url;
    }


  public void go() {
      History.fireCurrentHistoryState();
  }

    /*void doEvents() {
         eventBus.addHandler(MainSignupEvent.getType(),
				new MainSignupEvent.MainSignupEventHandler() {
                                        @Override
					public void onSignup(MainSignupEvent event) {
                                                //removeLoginForm();
                                                //send a GWT-RPC or RequestFactory to the server.
                                                //on return(success) create a path and
                                                //redirect to the path and add the corresponding
                                                //view to it.
                                                //changeView
                                            userSignupRequest();
					}
				});
    }
    
    public void userSignupRequest() {
        Window.alert("userSignupRequest Request is called");
        History.newItem("signup");
        History.addValueChangeHandler(this);
        History.fireCurrentHistoryState();

    }

    @Override
    public void onValueChange(ValueChangeEvent<String> vce) {
        String token = vce.getValue();
        if(token.equals("signup")) {
            Window.alert("token is signup");
        }
    }*/

}

