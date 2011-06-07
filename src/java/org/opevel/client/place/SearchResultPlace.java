package org.opevel.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 *
 * @author Administrator
 */
public class SearchResultPlace extends Place {

    private String searchresultPlaceToken;

    public SearchResultPlace(String searchresultPlaceToken) {
        this.searchresultPlaceToken = searchresultPlaceToken;
    }

    public String getSearchResultPlaceToken() {
        return searchresultPlaceToken;
    }

     public static class SearchResultPlaceTokenizer implements PlaceTokenizer<SearchResultPlace> {

        @Override
        public SearchResultPlace getPlace(String placetoken) {
            return new SearchResultPlace(placetoken);
        }

        @Override
        public String getToken(SearchResultPlace place) {
            return place.getSearchResultPlaceToken();
        }

     }
}
