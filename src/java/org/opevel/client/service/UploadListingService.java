/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.opevel.shared.ListingsEntityDTO;

/**
 *
 * @author Administrator
 */
@RemoteServiceRelativePath("uploadListing")
public interface UploadListingService extends RemoteService {
    public String myMethod(String s);

    public Boolean createNewListing(ListingsEntityDTO listingsEntitydto);

}
