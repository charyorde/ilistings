/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.server.rpc.UnexpectedException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;

import org.opevel.client.AgentRegisterService;

import org.opevel.server.domain.AgentEntity;
import org.opevel.server.domain.PMF;

import org.opevel.shared.AgentRegisterDTO;
/**
 *
 * @author Administrator
 */
public class AgentRegisterServiceImpl extends RemoteServiceServlet implements AgentRegisterService {
    @Override
    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    @Override
    public Boolean createAgent(AgentRegisterDTO agentregisterDTO) {
        /*Agent agent = new Agent();
        AgentJpaController agentjpa = new AgentJpaController();
        agent.setId(agentregisterDTO.getId());
        agent.setAboutCompany(agentregisterDTO.getAboutCompany());
        agent.setAddress(agentregisterDTO.getAddress());
        agent.setBusinessName(agentregisterDTO.getBusinessName());
        agent.setYearofestablishment(agentregisterDTO.getYearofestablishment());*/
        AgentEntity agententity = new AgentEntity();
        agententity.setAboutCompany(agentregisterDTO.getAboutCompany());
        agententity.setAddress(agentregisterDTO.getAddress());
        agententity.setBusinessName(agentregisterDTO.getBusinessName());
        agententity.setYearofestablishment(agentregisterDTO.getYearofestablishment());
        boolean added = false;
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(agententity);
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
