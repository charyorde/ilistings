/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.opevel.shared.UserEntityDTO;

/**
 *
 * @author Administrator
 */
@RemoteServiceRelativePath("mainsignupservice")
public interface MainSignupService extends RemoteService {
    public String myMethod(String s);

    public Boolean createUser(UserEntityDTO userEntityDto);
}
