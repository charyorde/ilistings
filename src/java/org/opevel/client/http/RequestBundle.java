/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.http;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class RequestBundle {

    /*private static final String GOOGLE = "Google";
    private static final String YAHOO = "Yahoo";
    private static final String FACEBOOK = "Facebook";
    private static final String TWITTER = "Twitter";*/

    public RequestBundle() {
    }

    public void sendOpenIdRequest(String provider) {

        RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, GWT.getHostPageBaseURL() + provider);
        try {
            rb.sendRequest(null, new RequestCallback() {

                @Override
                public void onResponseReceived(Request rqst, Response rspns) {

                }

                @Override
                public void onError(Request rqst, Throwable thrwbl) {

                }
            });
        } catch (RequestException ex) {
            Logger.getLogger(RequestBundle.class.getName()).info("RequestException from OpenIdRequest");
        }


}
    //auth/openid/google
    public String getGoogleOpenIdURL(String provider) {
        return "auth/openid/google";
    }

    public String getGoogleOpenIdURL() {
        //return "auth/openid/google";
        return "/auth";
    }

    public String getFacebookOpenIdURL() {
        return "";
    }
    public String getYahooOpenIdURL() {
        return "";
    }
    //return RequestBuilder.GET, provider
}
