/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.service;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;

import org.opevel.client.service.UploadListingService;
import org.opevel.server.dao.BlobstoreDAOImpl;
import org.opevel.server.domain.ListingFile;
import org.opevel.server.domain.ListingsEntity;
import org.opevel.server.domain.PMF;
import org.opevel.shared.ListingsEntityDTO;

/**
 *
 * @author Administrator
 */
public class UploadListingServiceImpl extends RemoteServiceServlet implements UploadListingService {

    private static final Logger log = Logger.getLogger(UploadListingServiceImpl.class.getName());

    private Key key;
    private String contentType = "";
    private String fileName = "";
    private Long size;
    private Date creation;

    BlobstoreDAOImpl blobstoredaoimpl = new BlobstoreDAOImpl();

    //List<ListingFile> listingfile = new ArrayList<ListingFile>();

    ListingFile[] listingfileArr = blobstoredaoimpl.getBlob();

    @Override
    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }
    @Override
    public Boolean createNewListing(ListingsEntityDTO listingsEntitydto){
        ListingsEntity listingsentity = new ListingsEntity();
        listingsentity.setTitle(listingsEntitydto.getTitle());
        listingsentity.setDescription(listingsEntitydto.getDescription());
        listingsentity.setPrice(listingsEntitydto.getPrice());
        listingsentity.setAddress(listingsEntitydto.getAddress());
        listingsentity.setManagedby(listingsEntitydto.getManagedby());
        listingsentity.setLatitude(listingsEntitydto.getLatitude());
        listingsentity.setLongitude(listingsEntitydto.getLongitude());

        //ListingFile listingfile = listingsEntitydto.getListingfile();
        ListingFile listingfile = new ListingFile();

        Entity[] entities = blobstoredaoimpl.getBlobstoreEntity();
        log.info("createNewListing(), Entity is " + entities);

        for (int i=0; i < entities.length; i++) {
          long id = entities[i].getKey().getId();
          key = entities[i].getKey();
          contentType = (String) entities[i].getProperty("content_type");
          fileName = (String) entities[i].getProperty("filename");
          size = (Long) entities[i].getProperty("size");
          creation = (Date) entities[i].getProperty("creation");
        }
        /*listingfile.setKeyString(listingsentity.getkey().toString());
        listingfile.setContentType(contentType);
        listingfile.setFilename(fileName);
        listingfile.setSize(size);
        listingfile.setCreation(creation);*/

        //listingsentity.setListingfile(listingfile);

        listingsentity.setBlobKeyString(key.toString());

        boolean added = false;
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(listingsentity);
            added = true;
        }
        finally {
            pm.close();
        }
        return added;
    }
}
