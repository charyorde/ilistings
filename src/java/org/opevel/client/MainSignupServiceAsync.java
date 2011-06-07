/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.opevel.shared.UserEntityDTO;

/**
 *
 * @author Administrator
 */
public interface MainSignupServiceAsync {
    public void myMethod(String s, AsyncCallback<String> callback);

    public void createUser(UserEntityDTO userEntityDto, AsyncCallback<java.lang.Boolean> asyncCallback);
}
