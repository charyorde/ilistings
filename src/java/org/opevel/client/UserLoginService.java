/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.opevel.shared.UsersDTO;

/**
 *
 * @author Administrator
 */
@RemoteServiceRelativePath("userloginservice")
public interface UserLoginService extends RemoteService {
    public String myMethod(String s);

    public String loginUser(String e, String p);
    
    public UsersDTO login(String email, String password);
}
