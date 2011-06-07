/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.opevel.shared.ListingsEntityDTO;

/**
 *
 * @author Administrator
 */
public interface UploadListingServiceAsync {
    public void myMethod(String s, AsyncCallback<String> callback);

    public void createNewListing(ListingsEntityDTO setNewListingsData, AsyncCallback<Boolean> callback);
}
