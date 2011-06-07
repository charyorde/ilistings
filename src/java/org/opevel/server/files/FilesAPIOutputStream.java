/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.files;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.Channels;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileWriteChannel;

public class FilesAPIOutputStream extends OutputStream {

       private FileWriteChannel channel;
       private OutputStream stream;

       public FilesAPIOutputStream( FileService service, AppEngineFile
file ) throws IOException {
               channel = service.openWriteChannel( file, true );
               stream = Channels.newOutputStream( channel );
       }


       @Override
       public void close() throws IOException {
               stream.close();
               channel.closeFinally();
       }

       @Override
       public void flush() throws IOException {
               stream.flush();
       }

       @Override
       public void write(byte[] b, int off, int len) throws IOException {
               stream.write( b, off, len );
       }

       @Override
       public void write(byte[] b) throws IOException {
               stream.write( b );
       }

       @Override
       public void write(int b) throws IOException {
               stream.write( b );
       }

}