package org.opevel.server;

import org.opevel.client.rpc.RpcService;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import org.opevel.server.domain.PMF;
import org.opevel.server.domain.UserEntity;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RpcServiceImpl extends RemoteServiceServlet implements RpcService {

    private static final Logger log = Logger.getLogger(RpcServiceImpl.class.getName());

	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
     
	/**
	 * init a blobstore url
	 */
	public String getBlobStoreUrl() {
		String url = blobstoreService.createUploadUrl("/upload");
                //test();
                //findUserByEmail("dreyemi@gmail.com");
                //findUserByEmail();
		return url;
	}
	
	/**
	 * get blob info list
	 * 
	 * @param filter
	 * @return
	 */
	/*public BlobData[] getBlobs(BlobDataFilter filter) {
	  
	  BlobInfoJdo db = new BlobInfoJdo();
	  BlobData[] r = db.getBlobs(filter);
	  
    return r;
	}
	
	public boolean deleteBlob(BlobDataFilter filter) {
	  
	  String blobKey = filter.getBlobKey();
	  
	  if (blobKey == null) {
	    return false;
	  }
	  
	  BlobKey blobKeys = new BlobKey(blobKey);
    blobstoreService.delete(blobKeys);
	  
	  return true;
	}*/

	private void test() {
    
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            PreparedQuery pq = datastore.prepare(new Query("__BlobInfo__"));
            int ce = pq.countEntities();
            List<Entity> entList = pq.asList(FetchOptions.Builder.withLimit(1));

            Entity[] entities = null;

            entities = new Entity[entList.size()];
            entList.toArray(entities);

            System.out.println("countEntities: " + ce);
            log.info("countEntities: " + ce);
            
            log.info("The newest record in __BlobInfo__ is " + entList);
        }
	
	private void test2() {
	  
	  DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	  Entity globalStat = datastore.prepare(new Query("__Stat_Total__")).asSingleEntity();
	  Long totalBytes = null;
    Long totalEntities = null;
    if (globalStat != null) {
      totalBytes = (Long) globalStat.getProperty("bytes");
      totalEntities = (Long) globalStat.getProperty("count");
	  }
    
    System.out.println("totalBytes: " + totalBytes + " totalEntities: " + totalEntities);
	}

    public void findUserByEmail() {

        PersistenceManager pm = PMF.get().getPersistenceManager();
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser(); // or req.getUserPrincipal()
        if(user == null) {
            user = new User("dreyemi@gmail.com", null);
        }
        try {
            pm.makePersistent(user);
        }
        finally {
            pm.close();
        }
        log.info("User information is: " + user.toString());
    }

  public List<UserEntity> findUserByEmail(String email) {

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
          //javax.jdo.Query q = pm.newQuery("select email from " + UserEntity.class.getName() + "where email == emailParam order by desc");
          javax.jdo.Query q = pm.newQuery("select email from " + UserEntity.class.getName() + "where email == emailParam order by desc");
          q.declareParameters("String emailParam");
        userentity = (List<UserEntity>) q.execute(email);
        log.info("User information is: " + userentity.toString());
        return userentity;
      } finally {
          pm.close();
      }
  }
	
}
