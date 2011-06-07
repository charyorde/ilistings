/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.files;

import gwtupload.server.MemoryFileItemFactory;
import java.io.IOException;
import org.apache.commons.fileupload.FileItem;

public class FilesAPIFileItemFactory extends MemoryFileItemFactory {
       private static final long serialVersionUID = 1;

       public FilesAPIFileItemFactory() {
// Consider that there are no fields in the form being posted whose value can exceed 1024 bytes
               super( 1024 );
       }

       @Override
       public FileItem createItem( String fieldName, String contentType,
boolean isFormField, String fileName ) {
               if( isFormField )
                   return super.createItem( fieldName, contentType, isFormField, fileName );
               try {
                   return new FilesAPIFileItem( fieldName, contentType, isFormField, fileName );
               } catch( IOException x ) {
                       return null;
               }
       }
}
