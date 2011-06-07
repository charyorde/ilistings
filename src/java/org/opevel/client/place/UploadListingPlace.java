/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 *
 * @author Administrator
 */
public class UploadListingPlace extends Place {

    private String uploadListingPlaceToken;

    public UploadListingPlace(String uploadListingPlaceToken) {
        this.uploadListingPlaceToken = uploadListingPlaceToken;
    }

    public String getUploadListingPlaceToken() {
        return uploadListingPlaceToken;
    }

    public static class UploadListingPlaceTokenizer implements PlaceTokenizer<UploadListingPlace> {

        @Override
        public UploadListingPlace getPlace(String place) {
            return new UploadListingPlace(place);
        }

        @Override
        public String getToken(UploadListingPlace p) {
            return p.getUploadListingPlaceToken();
        }

    }
}
