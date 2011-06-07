/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.TextBox;
import java.util.logging.Logger;
import org.opevel.client.ClientFactory;
import org.opevel.client.ClientFactoryImpl;
import org.opevel.client.place.SearchResultPlace;
import org.opevel.client.service.SearchService;
import org.opevel.client.service.SearchServiceAsync;
import org.opevel.client.view.SearchResultView;
import org.opevel.client.view.SearchResultViewImpl;
import org.opevel.shared.UserEntityDTO;

/**
 * HeaderWidget. Contains SearchWidget.
 * @author Administrator
 */
public class HeaderWidget extends Composite {

    private Logger logger = Logger.getLogger(HeaderWidget.class.getName());

    //private DecoratorPanel menuRoundedPanel = new DecoratorPanel();
    private FlowPanel headerWrapper = new FlowPanel();
    private HorizontalPanel headerLinksPanel = new HorizontalPanel();
    private FlowPanel menuPanel = new FlowPanel();
    private FlowPanel mainNavigation = new FlowPanel();
    private SearchWidget sw = new SearchWidget();
    private HTML logo;
    private DisclosurePanel accountDisclosurePanel = new DisclosurePanel();

    private Anchor profile = new Anchor("Profile");
    private HTML dpHeader;

    private ClientFactory clientFactory = new ClientFactoryImpl();
    private SearchResultView.Presenter presenter;
    
    private static final String REMOTE_HOST = "http://opevel-listings.appspot.com";
    private static final String LOCAL_HOST = "http://localhost:8888/";
    private static final String DEVMODE = "http://127.0.0.1:8888?gwt.codesvr=127.0.0.1:9997";

    public HeaderWidget() {
        headerWrapper.setStyleName("header-group-wrapper");
        headerWrapper.add(new HTML("<div id='logo'>Logo</div>"));
        headerWrapper.add(headerLinksPanel);
        //headerWrapper.add(sw);
        headerWrapper.add(new SearchWidget2());
        
        headerLinksPanel.add(new Hyperlink("Support", " "));
        headerLinksPanel.add(new Hyperlink("FAQs", " "));
        headerLinksPanel.add(new Hyperlink("Contact Us", " "));
        headerLinksPanel.setStylePrimaryName("gwt-headerLinksWrapper");
        //headerWrapper.add(menuRoundedPanel);
        //headerWrapper.add(new HTML("<div class='clear-block'></div>"));
        headerWrapper.add(new HTML("<div class='clearfix'></div>"));
        headerWrapper.add(menuPanel);

        menuPanel.setStylePrimaryName("gwt-menuPanel");
        menuPanel.add(mainNavigation);
        
        mainNavigation.setStyleName("primary-menu");
        mainNavigation.add(new HTML("<a href=" + REMOTE_HOST + ">Home" + "</a>"));

        menuPanel.add(accountDisclosurePanel);

        accountDisclosurePanel.setStylePrimaryName("gwt-accountDisclosurePanel");
        accountDisclosurePanel.setHeader(new HTML("Account"));
        accountDisclosurePanel.setContent(profile);
        initWidget(headerWrapper);
    }

    class SearchWidget2 extends Composite implements SearchResultView.Presenter {

    private FlowPanel searchPanel = new FlowPanel();
    private TextBox searchBox = new TextBox() ;
    private Button searchButton;

    String name = "kayode";
    String username = "root";
    String password = "root";

    private SearchServiceAsync searchservice = GWT.create(SearchService.class);
    private UserEntityDTO user;

    final SearchResultPlace searchresultPlace = new SearchResultPlace("searchresult");

    private String historyToken = clientFactory.getHistoryMapper().getToken(searchresultPlace);

    public SearchServiceAsync getSearchservice() {
        return clientFactory.getSearchService();
    }

    public SearchWidget2() {

        initWidget(searchPanel);
        searchPanel.setStyleName("gwt-searchPanel float_left");
        searchPanel.add(searchBox);

        searchBox.setStyleName("searchbox");
        searchPanel.add(getButton());

        final AsyncCallback<String> callback = new AsyncCallback<String>() {
                @Override
            public void onSuccess(String result) {
                Window.alert(result.toString());
                GWT.log("parsing the JSON response");
                // parse the JSONResponse
                parseJSON(result);
                clientFactory.getPlaceController().goTo(new SearchResultPlace(historyToken));

            }

                @Override
            public void onFailure(Throwable caught) {
                Window.alert("onFailure fails");
            }

        };

        searchButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent ce) {
                //new Searcher().doSearch();
                //clientFactory.getSearchService().myMethod(name, callback);
                //searchservice.login(username, password, callback);
                searchservice.serviceLogin(username, password, callback);
                /*final RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, "http://pekuliarproperties/services/rest/user/1");
                    try {
                        rb.setUser(name);
                        rb.setPassword(password);
                        user = new UserEntityDTO();
                        user.setUserid(name);
                        user.setPass(password);
                        rb.sendRequest(user.toString(), new RequestCallback(){
                        @Override
                        public void onResponseReceived(com.google.gwt.http.client.Request request, com.google.gwt.http.client.Response response) {
                            if (response.getStatusCode() == 200) {
                                Window.alert("status code is 200");
                                logger.info(response.toString());
                            } else {
                                Window.alert(response.getStatusText());
                            }
                        }

                        @Override
                        public void onError(com.google.gwt.http.client.Request request, Throwable exception) {
                            Window.alert("onError, request is: "+request.toString());
                        }
                });
                }catch(RequestException re) {
                    logger.severe("RequestException occurred");
                    GWT.log("RequestException occurred");
                }*/



            }
        });
    }

        public Button getButton() {
            if(searchButton == null) {
                searchButton = new Button("Search");
            }
            return searchButton;
        }

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
    
}
