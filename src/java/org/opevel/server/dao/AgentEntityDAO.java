/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.dao;

import org.opevel.server.domain.AgentEntity;

/**
 *
 * @author Administrator
 */
public interface AgentEntityDAO {

    Boolean createNewAgent(AgentEntity agententity);
}
