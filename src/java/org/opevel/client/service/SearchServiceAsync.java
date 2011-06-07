/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public interface SearchServiceAsync {
    public void myMethod(String s, AsyncCallback<String> callback);

    public void searchListingsService(AsyncCallback<ArrayList<Object>> callback);

    public void login(String username, String password, AsyncCallback<Boolean> callback);

    public void serviceLogin(String username, String password, AsyncCallback<String> asyncCallback);
}
