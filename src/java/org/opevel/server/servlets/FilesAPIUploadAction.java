/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.servlets;

import org.apache.commons.fileupload.FileItemFactory;
import gwtupload.server.UploadAction;
import org.opevel.server.files.FilesAPIFileItemFactory;

public class FilesAPIUploadAction extends UploadAction {
       private static final long serialVersionUID = 1;

       @Override
       protected FileItemFactory getFileItemFactory( int requestSize ) {
               return new FilesAPIFileItemFactory();
       }

}