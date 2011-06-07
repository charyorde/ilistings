/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.dao;

import javax.jdo.PersistenceManager;
import org.opevel.server.domain.PMF;

/**
 *
 * @author Administrator
 */
public class GenericDAO {

    public void persist() {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
          pm.makePersistent(this);
        } finally {
          pm.close();
        }
    }
}
