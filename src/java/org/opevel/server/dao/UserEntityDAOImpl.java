/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.dao;

import com.google.appengine.api.users.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import org.opevel.server.domain.PMF;
import org.opevel.server.domain.UserEntity;

/**
 *
 * @author Administrator
 */
public class UserEntityDAOImpl implements UserEntityDAO {

  private static final Logger log = Logger.getLogger(UserEntityDAOImpl.class.getName());

  private static PersistenceManager pm = PMF.get().getPersistenceManager();


  public static List<UserEntity> findUserByEmail(String email) {

      if(email == null) {
          return null;
      }
     PersistenceManager pm = PMF.get().getPersistenceManager();
     List<UserEntity> userentity = new ArrayList<UserEntity>();
     //email = userentity.getEmail();
      try {
      //user = pm.getObjectById(User.class, user.getEmail());
        /*javax.jdo.Query q = pm.newQuery(UserEntity.class, "email == :email");
	q.setUnique(true);
	userentity = (UserEntity) q.execute(email);*/
          javax.jdo.Query q = pm.newQuery("select email from " + UserEntity.class.getName());
        userentity = (List<UserEntity>) q.execute();
        log.info("User information is: " + userentity.toString());
        return userentity;
      } finally {
          pm.close();
      }
  }

    public static Boolean createUserFromOpenID(User user) {

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setNickname(user.getNickname()); // same as username
        userEntity.setUserid(user.getUserId());

        //persist user;
        boolean added = false;
        try {
            pm.makePersistent(userEntity);
            added = true;
        }
        finally {
            pm.close();
        }
        return added;

    }
    public static List<UserEntity> findAllUsers() {
    PersistenceManager pm = PMF.get().getPersistenceManager();
    try {
      List<UserEntity> list = (List<UserEntity>) pm.newQuery("select o from UserEntity o").execute();
      // force to get all the employees
      list.size();
      return list;
    } finally {
      pm.close();
    }
  }

  public static UserEntity findUserEntity(Long id) {
    if (id == null) {
      return null;
    }
    PersistenceManager pm = PMF.get().getPersistenceManager();
    try {
        Query q = pm.newQuery("select id from " + UserEntity.class.getName());
        UserEntity userentity = (UserEntity) q.execute();
        return userentity;
    } finally {
      pm.close();
    }
  }

    //@Override
    /*public Object findUserByEmail(String email) {
        if (email == null) {
      return null;
    }
    PersistenceManager pm = PMF.get().getPersistenceManager();
    try {
        Query q = pm.newQuery("select email from " + UserEntity.class.getName());
        UserEntity userentity = (UserEntity) q.execute();
        return userentity;
    } finally {
      pm.close();
    }
    }*/

    public void persist() {
    PersistenceManager pm = PMF.get().getPersistenceManager();
    try {
      pm.makePersistent(this);
    } finally {
      pm.close();
    }
  }

}
