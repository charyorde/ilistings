/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.servlets;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.opevel.server.domain.ListingFile;
import org.opevel.server.domain.PMF;
import org.springframework.web.context.ServletContextAware;

/**
 *
 * @author Administrator
 */
public class UploadListingImageServlet extends UploadAction implements ServletContextAware {
   
    private static final long serialVersionUID = 1L;
    
    private static final Logger log = Logger.getLogger(UploadListingImageServlet.class.getName());

  Hashtable<String, String> receivedContentTypes = new Hashtable<String, String>();
  /**
   * Maintain a list with received files and their content types.
   */
  //Hashtable<String, File> receivedFiles = new Hashtable<String, File>();

  //Hashtable<String, ListingFile> receivedFiles = new Hashtable<String, ListingFile>();

  Hashtable<String, FileItemStream> receivedFiles = new Hashtable<String, FileItemStream>();
  
  private ServletContext servletContext;

  private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
  public ServletContext getServletContext(){
      return servletContext;
  }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //here do App Engine compatible post for Blobstore
        HttpSession session = request.getSession();
        //log.info("calling doPost");
        log.info(request.toString());
        // Get a file service
        
        //Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(request);
        //log.info(blobs.toString());

        try {
      ServletFileUpload upload = new ServletFileUpload();
      response.setContentType("multipart/form-data");

      FileItemIterator iterator = upload.getItemIterator(request);
      while (iterator.hasNext()) {
        FileItemStream item = iterator.next();
        InputStream stream = item.openStream();
        log.info("logging stream " + stream.toString());

        if (item.isFormField()) {
          log.warning("Got a form field: " + item.getFieldName());
        } else {
          log.warning("Got an uploaded file: " + item.getFieldName() +
                      ", name = " + item.getName());

          // You now have the filename (item.getName() and the
          // contents (which you can read from stream). Here we just
          // print them back out to the servlet output stream, but you
          // will probably want to do something more interesting (for
          // example, wrap them in a Blob and commit them to the
          // datastore).
          int len;
          byte[] buffer = new byte[100000];
          len = stream.read(buffer, 0, buffer.length);
          ListingFile listingfile = new ListingFile();
          listingfile.setTitle(item.getName());
          listingfile.setImageType(item.getContentType());
          listingfile.setImage(buffer);

          PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(listingfile);
        }
        finally {
            pm.close();
        }
          while (len != -1) {//meaning if len contains bytes of data
              //output what is in the upload file
              response.getOutputStream().write(buffer, 0, len);
          }
        log.info("sending response");
        }
      }
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //here do App Engine compatible get for Blobstore
        log.info("the servlet is now returning");
    }
  // using App Engine FileService
  public String executeAction2(HttpServletRequest request, List<FileItem> sessionFiles) throws UploadActionException {

      String response = "";
      String segment = "";
    int cont = 0;
    for (FileItem item : sessionFiles) {
      if (false == item.isFormField()) {
        cont++;
        try {
            // Get a file service
          FileService fileService = FileServiceFactory.getFileService();

          // Create a new Blob file with mime-type "text/plain"
          AppEngineFile file = fileService.createNewBlobFile("multipart/form-data");

          // Open a channel to write to it
          boolean lock = false;
          FileWriteChannel writeChannel = fileService.openWriteChannel(file, lock);
          //writeChannel.write(ByteBuffer.wrap(fileService.));

          // write the file to the FileWriteChannel
          writeChannel.write(ByteBuffer.wrap(item.get()));


          /// Compose a xml message with the full file information
          response += "<file-" + cont + "-field>" + item.getFieldName() + "</file-" + cont + "-field>\n";
          response += "<file-" + cont + "-name>" + item.getName() + "</file-" + cont + "-name>\n";
          response += "<file-" + cont + "-size>" + item.getSize() + "</file-" + cont + "-size>\n";
          response += "<file-" + cont + "-type>" + item.getContentType() + "</file-" + cont + "type>\n";

        } catch (Exception e) {
          log.info("UploadActionException occurred: " + e.getMessage());
          throw new UploadActionException(e.getMessage());
        }
    }
  }
    return response;
  }
  /**
   * Get the content of an uploaded file.
   */
  @Override
  public void getUploadedFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
      log.info("getting uploaded file");
    
  }

    @Override
    public void setServletContext(ServletContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
