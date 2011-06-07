/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import java.util.logging.Logger;
import org.opevel.client.ClientFactory;
import org.opevel.client.place.SearchResultPlace;
import org.opevel.client.view.SearchResultView;
import org.opevel.client.view.SearchResultView.Presenter;
import org.opevel.client.view.SearchResultViewImpl;

/**
 *
 * @author Administrator
 */
public class SearchResultActivity extends AbstractActivity implements Presenter {

    private Logger logger = Logger.getLogger(UploadListingActivity.class.getName());

    private ClientFactory clientFactory;
    private EventBus eventBus;
    //Place place = GWT.create(Place.class);

    private String searchresultPlaceToken;

    public SearchResultActivity(SearchResultPlace searchresultPlace, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        this.searchresultPlaceToken = searchresultPlace.getSearchResultPlaceToken();
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        this.eventBus = eventBus;
        SearchResultView searchResultView = clientFactory.getSearchResultView();
        //uploadListingView.setUploadListingPlaceToken(uploadListingPlaceToken);
        panel.setWidget(searchResultView);
    }

    @Override
    public void parseJSON(String result) {
        logger.info("parsing JSON");
        try {
            JSONValue jsonValue = JSONParser.parseStrict(result);
            // display the parsed response
            new SearchResultViewImpl().displayJSONObject(jsonValue);
        } catch (JSONException e) {
            logger.severe("JSONException occurred while trying to parseJSON");
            new SearchResultViewImpl().displayParseError(result);
        }
    }
}
