package org.opevel.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.Window;
import org.opevel.client.place.AppPlaceHistoryMapper;
import org.opevel.client.service.SearchService;
import org.opevel.client.service.SearchServiceAsync;
import org.opevel.client.view.AgentProfileView;
import org.opevel.client.view.AgentProfileViewImpl;
import org.opevel.client.view.SearchResultView;
import org.opevel.client.view.SearchResultViewImpl;
import org.opevel.client.view.UploadListing;
import org.opevel.client.view.UploadListingImpl;
import org.opevel.shared.service.ListingsRequestFactory;

public class ClientFactoryImpl implements ClientFactory
{
    private static final EventBus eventBus = new SimpleEventBus();
    private static final PlaceController placeController = new PlaceController(eventBus);
    private final PlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
    private final ListingsRequestFactory rf = GWT.create(ListingsRequestFactory.class);
    private static final AgentProfileView agentprofileView = new AgentProfileViewImpl();
    private static final UploadListing uploadListingView = new UploadListingImpl();
    private static final SearchResultView searchResultView = new SearchResultViewImpl();
    private static final MainWidget mainwidget = new MainWidget();

    public static final SearchServiceAsync searchService = GWT.create(SearchService.class);

    private static final String REMOTE_HOST = "http://opevel-listings.appspot.com";
    private static final String LOCAL_HOST = "http://localhost:8888/";
    private static final String DEVMODE = "http://127.0.0.1:8888?gwt.codesvr=127.0.0.1:9997";

    public ClientFactoryImpl()
    {
        rf.initialize(eventBus);
    }
    @Override
    public EventBus getEventBus()
    {
            return eventBus;
    }

    @Override
    public AgentProfileView getAgentProfileView()
    {
            return agentprofileView;
    }

    @Override
    public PlaceController getPlaceController()
    {
            return placeController;
    }

    @Override
    public MainWidget getApp() {
        return mainwidget;
    }

    @Override
    public PlaceHistoryMapper getHistoryMapper() {
        return historyMapper;
    }

    @Override
    public UploadListing getUploadListingView() {
        return uploadListingView;
    }

    @Override
    public String getRelativeURL(String url) {
        String realModuleBase = url;

        if (GWT.isScript()) {

            // Log.debug("ModuleBaseURL: " + GWT.getModuleBaseURL());

            String moduleBase = GWT.getModuleBaseURL();

            // Use for Deployment to production server
            //
            realModuleBase = REMOTE_HOST;

            // Use to test compiled browser locally
            //
            if (moduleBase.indexOf("localhost") != -1) {
                // Log.debug("Testing. Using Localhost");
                realModuleBase = LOCAL_HOST;
            }

            String devmode = Window.Location.getParameter("gwt.codesvr=127.0.0.1:9997");

            if(devmode.indexOf("gwt.codesvr=127.0.0.1:9997") != -1) {
                realModuleBase = DEVMODE;
            }

        } else {
            // realModuleBase = GWT.getModuleBaseURL();

            // This is the URL for GWT Hosted mode
            //
            realModuleBase = LOCAL_HOST;
        }

        return realModuleBase;
    }

    @Override
    public ListingsRequestFactory getRequestFactory() {
        return rf;
    }

    @Override
    public SearchServiceAsync getSearchService() {
        return searchService;
    }

    @Override
    public SearchResultView getSearchResultView() {
        return searchResultView;
    }

}
