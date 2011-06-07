/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

/**
 *
 * @author Administrator
 */
public class BuyerProfilePresenter extends WidgetPresenter {

    //public static final Place PLACE = buyerprofile.cre
    private Place buyerprofileplace;
    
    PlaceRequest buyerprofile = new PlaceRequest("#profile");

    public interface Display extends WidgetDisplay {
        
    }

    @Inject
    public BuyerProfilePresenter(WidgetDisplay display, EventBus eventBus,
            Place buyerprofileplace) {

        super(display, eventBus);
        GWT.log("creating BuyerProfilePresenter");

        this.buyerprofileplace = buyerprofileplace;
        bind();
        createBuyerProfilePlace();
    }

    public PlaceRequest createBuyerProfilePlace() {
        GWT.log("creating Buyer Profile location #profile");
        return buyerprofileplace.createRequest();
    }

    @Override
    protected void onBind() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void onUnbind() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void onRevealDisplay() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
