/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.UnexpectedException;
import org.opevel.shared.AgentRegisterDTO;

/**
 *
 * @author Administrator
 */
@RemoteServiceRelativePath("agentregisterservice")
public interface AgentRegisterService extends RemoteService {
    public String myMethod(String s);

    public Boolean createAgent(AgentRegisterDTO agentregisterDTO);
}
