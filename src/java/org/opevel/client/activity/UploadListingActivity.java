/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import java.util.logging.Logger;
import org.opevel.client.ClientFactory;
import org.opevel.client.place.UploadListingPlace;
import org.opevel.client.view.UploadListing;
import org.opevel.client.view.UploadListing.Presenter;
import org.opevel.shared.proxy.ListingsEntityProxy;
import org.opevel.shared.service.ListingsRequestFactory;
import org.opevel.shared.service.ListingsRequestFactory.ListingsEntityRequestContext;

/**
 *
 * @author Administrator
 */
public class UploadListingActivity extends AbstractActivity implements Presenter {

    private Logger logger = Logger.getLogger(UploadListingActivity.class.getName());

    private ClientFactory clientFactory;
    private EventBus eventBus;
    //Place place = GWT.create(Place.class);

    private String uploadListingPlaceToken;

    UploadListingActivity(UploadListingPlace uploadListingPlace, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        this.uploadListingPlaceToken = uploadListingPlace.getUploadListingPlaceToken();
    }

    @Override
    public void start(AcceptsOneWidget aow, EventBus eb) {
        this.eventBus = eb;
        UploadListing uploadListingView = clientFactory.getUploadListingView();
        //uploadListingView.setUploadListingPlaceToken(uploadListingPlaceToken);
        aow.setWidget(uploadListingView);
    }

    @Override
    public void goTo(Place place) {
        
    }

    @Override
    public void fireCreateNewListing(ListingsEntityProxy listingsEntity) {
        final ListingsRequestFactory rf = clientFactory.getRequestFactory();
        ListingsEntityRequestContext reqCtx = rf.listingsEntityRequest();
        final ListingsEntityProxy newListings = reqCtx.create(ListingsEntityProxy.class);

        newListings.setTitle(listingsEntity.getTitle());
        newListings.setDescription(listingsEntity.getDescription());
        newListings.setPrice(listingsEntity.getPrice());
        newListings.setManagedby(listingsEntity.getManagedby());
        newListings.setStatus(listingsEntity.getStatus());
        newListings.setLatitude(listingsEntity.getLatitude());
        newListings.setLongitude(listingsEntity.getLongitude());
        
        reqCtx.createNewListing(listingsEntity).fire(new Receiver() {

            @Override
            public void onSuccess(Object v) {
                Window.alert("successfully created");
            }

        });
    }

}
