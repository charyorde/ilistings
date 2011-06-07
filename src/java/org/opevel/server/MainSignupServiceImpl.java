/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import javax.jdo.PersistenceManager;

import org.opevel.client.MainSignupService;
import org.opevel.server.domain.PMF;
import org.opevel.server.domain.UserEntity;
import org.opevel.shared.UserEntityDTO;

/**
 *
 * @author Administrator
 */
public class MainSignupServiceImpl extends RemoteServiceServlet implements MainSignupService {
    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    @Override
    public Boolean createUser(UserEntityDTO userEntityDto) {
        UserEntity userentity = new UserEntity();
        userentity.setEmail(userEntityDto.getEmail());
        userentity.setPass(userEntityDto.getPass());
        userentity.setType(userEntityDto.getType());
        boolean added = false;
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(userentity);
            added = true;
        }
        finally {
            pm.close();
        }
        /*try {
            agentjpa.create(agent);
            added = true;
        }
        catch (Exception ex)
        {
            Logger.getLogger(AgentRegisterServiceImpl.class.getName()).
            log(Level.SEVERE, null, ex);
            }*/
        return added;
    }
}
