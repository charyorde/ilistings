package org.opevel.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import org.opevel.client.service.SearchServiceAsync;
import org.opevel.client.view.AgentProfileView;
import org.opevel.client.view.SearchResultView;
import org.opevel.client.view.UploadListing;
import org.opevel.shared.service.ListingsRequestFactory;

public interface ClientFactory
{
	EventBus getEventBus();
	PlaceController getPlaceController();
        PlaceHistoryMapper getHistoryMapper();
        
        AgentProfileView getAgentProfileView();
        UploadListing getUploadListingView();

        MainWidget getApp();

        String getRelativeURL(String url);

        ListingsRequestFactory getRequestFactory();

        SearchServiceAsync getSearchService();

        SearchResultView getSearchResultView();
}
