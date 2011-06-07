/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.opevel.shared.AgentRegisterDTO;

/**
 *
 * @author Administrator
 */
public interface AgentRegisterServiceAsync {
    public void myMethod(String s, AsyncCallback<String> callback);

    public void createAgent(AgentRegisterDTO agentregisterDTO, AsyncCallback<java.lang.Boolean> asyncCallback);
}
