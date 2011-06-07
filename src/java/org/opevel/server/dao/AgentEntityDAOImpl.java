/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.dao;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import org.opevel.server.domain.AgentEntity;
import org.opevel.server.domain.PMF;

/**
 *
 * @author Administrator
 */
public class AgentEntityDAOImpl implements AgentEntityDAO {

    private static final Logger log = Logger.getLogger(AgentEntityDAOImpl.class.getName());

    private static PersistenceManager pm = PMF.get().getPersistenceManager();

    @Override
    public Boolean createNewAgent(AgentEntity agententity) {
         if(agententity == null) {
            return null;
        }
        // insert the blobkey key into ListingsEntity before persisting
        boolean added = false;
        try {
            pm.makePersistent(agententity);
            added = true;
        }
        finally {
            pm.close();
        }
        return added;
    }

  public static AgentEntity findAgentEntity(String id) {
    if (id == null) {
      return null;
    }
    try {
        Query q = pm.newQuery("select id from " + AgentEntity.class.getName());
        AgentEntity agententity = (AgentEntity) q.execute();
        return agententity;
    } finally {
      pm.close();
    }
  }

  public static List<AgentEntity> findAgentEntityByString(String id) {
    Transaction begin = null;
    List<AgentEntity> agententity = new ArrayList<AgentEntity>();
    if (id == null) {
      return null;
    }
        //begin = begin();
    try {
        Query q = pm.newQuery("select id from " + AgentEntity.class.getName());
        agententity = (List<AgentEntity>) q.execute();
        return agententity;
        //begin.commit();
    } catch(Exception ex) {
        //rollback();
        log.warning(ex.getMessage());
    } 
        return agententity;
  }

  public static List<AgentEntity> findAgentEntityByString2(String id) {
    Transaction begin = null;
    List<AgentEntity> agententity = new ArrayList<AgentEntity>();
    if (id == null) {
      return null;
    }
        //begin = begin();
    try {
        Query q = pm.newQuery("select id from " + AgentEntity.class.getName());
        agententity = (List<AgentEntity>) q.execute();
        agententity.size();
        return agententity;
        //begin.commit();
    } finally {
        pm.close();
    }
  }

  public static List<AgentEntity> findAgentEntityByString3(String id) {
    Transaction begin = null;
    List<AgentEntity> agententity = new ArrayList<AgentEntity>();
    if (id == null) {
      return null;
    }
       Query q = null;
    try {
        q = pm.newQuery("select id from " + AgentEntity.class.getName());
        agententity = (List<AgentEntity>) q.execute();
        pm.detachCopy(agententity);
        return agententity;
        //begin.commit();
    } finally {
        /*if(q!= null){
            q.closeAll();
        }*/
        pm.close();

    }
  }

  public static AgentEntity retrieveAgentEntity(AgentEntity agententity) {
      try {
      Query q = pm.newQuery(AgentEntity.class, "id == :id");
      q.setUnique(true);
      agententity = (AgentEntity) q.execute(agententity.getId());
      return agententity;
      } finally {
          pm.close();
      }
  }
/*  Query q = getPM().newQuery(User.class, "id == :userId");
2	q.setUnique(true);
3	User attachedUser = (User) q.execute(u.getId());*/

  public static AgentEntity fetchAgentEntityById(AgentEntity agententity) {
      try {
      agententity = pm.getObjectById(AgentEntity.class, agententity.getId());
      return agententity;
      } finally {
          pm.close();
      }
  }

  public static AgentEntity fetchAgentEntityById(Long id) {
       if (id == null) {
      return null;
    }
      AgentEntity agententity = new AgentEntity();
      try {
      agententity = pm.getObjectById(AgentEntity.class, agententity.getId());
      return agententity;
      } finally {
          pm.close();
      }
  }


public static Object fetchAgentEntityByKey(Key key) {
    AgentEntity agententity = new AgentEntity();
    key = KeyFactory.createKey(AgentEntity.class.getSimpleName(), agententity.getId());
    Entity entity = null;
        try {
            entity = DatastoreServiceFactory.getDatastoreService().get(key);
        } catch (EntityNotFoundException ex) {
            log.warning("EntityNotFoundException, fetchAgentEntityKey()" + ex);
        }
    return entity;
    }


    /**
     * Begin a new transaction.
     *
     * @return the transaction
     */
    public static Transaction begin() {
      final Transaction tx = pm.currentTransaction();
      tx.begin();
      return tx;
    }

    /**
     * Close the connection to the data store. Clients are expected to guarantee
     * that close will be called. This will also rollback any active
     * transaction.
     */
    public static void rollback() {
      final Transaction tx = pm.currentTransaction();
      if (tx.isActive()) {
        tx.rollback();
      }
    }

    public static void close() {
        pm.close();
    }
}

