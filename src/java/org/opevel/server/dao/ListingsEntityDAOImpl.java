/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.dao;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import org.opevel.server.domain.ListingsEntity;
import org.opevel.server.domain.PMF;

/**
 *
 * @author Administrator
 */
public class ListingsEntityDAOImpl implements ListingsEntityDAO {

    public static List<ListingsEntity> findAllUsers() {
    PersistenceManager pm = PMF.get().getPersistenceManager();
    try {
      List<ListingsEntity> list = (List<ListingsEntity>) pm.newQuery("select o from ListingsEntity o").execute();
      // force to get all the employees
      list.size();
      return list;
    } finally {
      pm.close();
    }
  }

  public static ListingsEntity findListingsEntity(Long id) {
    if (id == null) {
      return null;
    }
    PersistenceManager pm = PMF.get().getPersistenceManager();
    try {
        Query q = pm.newQuery("select id from " + ListingsEntity.class.getName());
        ListingsEntity listingsentity = (ListingsEntity) q.execute();
        return listingsentity;
    } finally {
      pm.close();
    }
  }

    @Override
    public Boolean createNewListings(ListingsEntity listingsEntity) {
        if(listingsEntity == null) {
            return null;
        }
        // insert the blobkey key into ListingsEntity before persisting
        boolean added = false;
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(listingsEntity);
            added = true;
        }
        finally {
            pm.close();
        }
        return added;
    }

}
