/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import org.opevel.shared.ListingsEntityDTO;
import org.opevel.shared.proxy.ListingsEntityProxy;

/**
 *
 * @author Administrator
 */
public interface UploadListing extends IsWidget {

    void setPresenter(Presenter presenter);
    void setUploadListingPlaceToken(String uploadListingPlaceToken);

    ListingsEntityDTO setNewListingsData();
    
    //ListingsEntityProxy setNewListingsProxyData();

    public interface Presenter {
        void goTo(Place place);
        void fireCreateNewListing(ListingsEntityProxy listingsEntity);
    }
}
