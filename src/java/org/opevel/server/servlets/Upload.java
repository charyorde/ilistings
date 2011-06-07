package org.opevel.server.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpSession;
import org.opevel.server.dao.BlobstoreDAOImpl;
import org.opevel.server.domain.ListingFile;
import org.opevel.server.domain.PMF;

public class Upload extends HttpServlet {

    private static final Logger log = Logger.getLogger(Upload.class.getName());
	// init the blog store service
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    PersistenceManager pm = PMF.get().getPersistenceManager();

    BlobstoreDAOImpl blobstoredaoimpl = new BlobstoreDAOImpl();

    /*public BlobstoreDAOImpl getBlobstoredaoimpl() {
        return blobstoredaoimpl;
    }*/

    /*public Upload(BlobstoreDAOImpl blobstoredaoimpl) {
        this.blobstoredaoimpl = blobstoredaoimpl;
    }*/

    public void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = request.getSession();
            // this has to be used in an rpc call to get the url to be used with this request
            //String url = blobstoreService.createUploadUrl("/upload");

        int len = request.getContentLength();
        int mb = (1024 * 1024) * 1;
        if (len > mb) {
          throw new RuntimeException("Sorry that file is too large. Try < 1024 or 1MB file");
        }

            /*
            Enumeration paramNames = request.getParameterNames();
while(paramNames.hasMoreElements()) {
  String paramName = (String)paramNames.nextElement();
  String[] paramValues = request.getParameterValues(paramName);
  System.out.println(paramName + "=" + paramValues.toString() + "\n");
}
*/

        Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(request);
        BlobKey blobKey = blobs.get("myFile");

        //HashMap<String, Object> listingfile = new HashMap<String, Object>();

        /*List<ListingFile> listingfile = new ArrayList<ListingFile>();

        ListingFile[] listingfileArr = blobstoredaoimpl.getBlob();

        ListingFile lf = new ListingFile();

        log.info("Entities data is" + blobstoredaoimpl.getEntities());
        
        log.info("Data contained in getBlob()" + listingfileArr.toString());
        //ListingFile[] convert = blobstoredaoimpl.convert(blobstoredaoimpl.getEntities());
        listingfile = Arrays.asList(listingfileArr);

        lf.setListingfile(listingfile);

        try {
            pm.makePersistent(lf);
        }
        finally {
            pm.close();
        }*/

        if (blobKey == null) {
                res.sendRedirect("/");
        } else {
                //res.sendRedirect("/serve?blob-key=" + blobKey.getKeyString());
        }

    }

    public void copyTo(BlobKey blobkey, Object o) {
        // query the blobstore to retrieve data
        try {
          DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
          PreparedQuery pq = datastore.prepare(new Query("__BlobInfo__"));
          List<Entity> entList = pq.asList(FetchOptions.Builder.withLimit(1));
        } finally {
            pm.close();
        }
    }

}