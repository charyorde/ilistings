/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.jdo.PersistenceManager;

import org.opevel.server.domain.PMF;
import org.opevel.server.domain.ListingFile;

/**
 *
 * @author Administrator
 */
public class BlobstoreDAOImpl implements BlobstoreDAO {

    private Entity[] entities = null;

    public Entity[] getEntities() {
        return entities;
    }

    public Entity[] getBlobstoreEntity() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        PreparedQuery pq = datastore.prepare(new Query("__BlobInfo__"));
        int ce = pq.countEntities();
        List<Entity> entList = pq.asList(FetchOptions.Builder.withLimit(1));
        
        Entity[] myentities = null;
        myentities = new Entity[entList.size()];
        entList.toArray(myentities);

        return myentities;
    }

    @Override
    public String findBlobById(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ListingFile[] getBlobs(BlobDataFilter filter) {

    PersistenceManager pm = PMF.get().getPersistenceManager();

    
    try {
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      PreparedQuery pq = datastore.prepare(new Query("__BlobInfo__"));
      List<Entity> entList = pq.asList(FetchOptions.Builder.withLimit((int) filter.getRangeFinish()).offset((int) filter.getRangeStart()));

      entities = new Entity[entList.size()];
      entList.toArray(entities);

    } finally {
      pm.close();
    }

    ListingFile[] blobData = convert(entities);

    return blobData;
  }

    public ListingFile[] getBlob() {

    PersistenceManager pm = PMF.get().getPersistenceManager();

    try {
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      PreparedQuery pq = datastore.prepare(new Query("__BlobInfo__"));
      List<Entity> entList = pq.asList(FetchOptions.Builder.withLimit(1));

      entities = new Entity[entList.size()];
      entList.toArray(entities);

    } finally {
      pm.close();
    }

    ListingFile[] blobData = convert(entities);

    return blobData;
  }

  private ListingFile[] convert(Entity[] es) {
    if (es == null || es.length == 0) {
      return null;
    }

    ListingFile[] b = new ListingFile[es.length];
    for (int i=0; i < es.length; i++) {

      Map<String, Object> p = es[i].getProperties();

      long id = es[i].getKey().getId();
      Key key = es[i].getKey();
      String ct = (String) es[i].getProperty("content_type");
      String fn = (String) es[i].getProperty("filename");
      Long size = (Long) es[i].getProperty("size");
      Date creation = (Date) es[i].getProperty("creation");

      b[i] = new ListingFile();
      b[i].setKeyString(key.getName());
      b[i].setContentType(ct);
      b[i].setFilename(fn);
      b[i].setSize(size);
      b[i].setCreation(creation);

    }

    return b;
  }
}
