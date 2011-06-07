/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.opevel.client.UserLoginService;
import org.opevel.server.domain.Users;
import org.opevel.server.domain.UsersJpaController;
import org.opevel.shared.UsersDTO;

/**
 *
 * @author Administrator
 */
public class UserLoginServiceImpl extends RemoteServiceServlet implements UserLoginService {
    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    /*@Override
    public Users login(String email, String password) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //return UsersJpaController.findUsers(Users.getUid());
        //return Users.class;
        UsersJpaController usersjpa = new UsersJpaController();
        return usersjpa.findUsers(email, password);
        //return Users.
    }*/

    public String loginUser(String e, String p) {
        return e + " " + p;
    }

    public UsersDTO login(String email, String password) {
        //throw new UnsupportedOperationException("Not supported yet.");
        UsersJpaController usersjpa = new UsersJpaController();
        //return usersjpa.findUsers(email, password);
        return Users.toDTO(usersjpa.findUsers(email, password));
        //return Users.
    }
}
