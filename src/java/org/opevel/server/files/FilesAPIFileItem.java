/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.channels.Channels;
import org.apache.commons.fileupload.FileItem;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;

/**
 *
 * @author Administrator
 */
public class FilesAPIFileItem implements FileItem {
       private static final long serialVersionUID = 1;

       private String field;
       private String type;
       private boolean formField;
       private String name;

       static private FileService fileService =
FileServiceFactory.getFileService();
       static private BlobstoreService blobstoreService =
BlobstoreServiceFactory.getBlobstoreService();
       private AppEngineFile file = null;

       public FilesAPIFileItem(
                       String fieldName,
                       String contentType,
                       boolean isFormField,
                       String fileName ) throws IOException {
               field = fieldName;
               type = contentType;
               formField = isFormField;
               name = fileName;

               file = fileService.createNewBlobFile( contentType );
       }

       @Override
       public void delete() {
               BlobKey key = getKey();
               if( key != null ) {
                       blobstoreService.delete( key );
                       file = null;
               }
       }

       @Override
       public byte[] get() {
               BlobKey key = getKey();
               if( key == null )
                       return null;
               return  blobstoreService.fetchData( key, 0, getSize() - 1 );
       }

       @Override
       public String getContentType() {
               return type;
       }

       @Override
       public String getFieldName() {
               return field;
       }

       @Override
       public InputStream getInputStream() throws IOException {
               if( file == null )
                       return null;
               FileReadChannel channel = fileService.openReadChannel( file,
false );
               return Channels.newInputStream( channel );
       }

       @Override
       public String getName() {
               return name;
       }

       @Override
       public OutputStream getOutputStream() throws IOException {
               if( file == null )
                       return null;
               return new FilesAPIOutputStream( fileService, file );
       }

       @Override
       public long getSize() {
               BlobKey key = getKey();
               if( key == null )
                       return 0;
               BlobInfo info = new BlobInfoFactory().loadBlobInfo( key );
               if( info == null )
                       return 0;
               return info.getSize();
       }

       @Override
       public String getString() {
               return get().toString();
       }

       @Override
       public String getString( String encoding ) throws
UnsupportedEncodingException {
               return new String( get(), encoding );
       }

       @Override
       public boolean isFormField() {
               return formField;
       }

       @Override
       public boolean isInMemory() {
               return false;
       }

       @Override
       public void setFieldName( String arg0 ) {
               field = arg0;
       }

       @Override
       public void setFormField( boolean arg0 ) {
               formField = arg0;

       }

       @Override
       public void write(File arg0) throws Exception {
       throw new UnsupportedOperationException( "Writing to file is not allowed" );
       }

       public BlobKey getKey() {
               if( file == null )
                       return null;
               return fileService.getBlobKey( file );
       }

}
