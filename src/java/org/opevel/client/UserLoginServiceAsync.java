/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.opevel.shared.UsersDTO;

/**
 *
 * @author Kayode Odeyemi
 */
public interface UserLoginServiceAsync {
    public void myMethod(String s, AsyncCallback<String> callback);

    //public void login(String email, String password, AsyncCallback<Users> asyncCallback);

    public void loginUser(String e, String p, AsyncCallback<String> callback);
    public void login(String email, String password, AsyncCallback<UsersDTO> asyncCallback);
}
