package org.opevel.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
//import org.enunes.gwt.mvp.client.EventBus;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import org.opevel.client.activity.AppActivityMapper;
import org.opevel.client.place.AppPlaceHistoryMapper;
import org.opevel.client.place.HomePlace;
import org.opevel.client.resources.ListingsResources;

/**
 * Main entry point.
 *
 * @author Kayode Odeyemi
 */
public class ListingsEntryPoint implements EntryPoint, ValueChangeHandler<String> {

    public static final String LOCAL_HOST = "http://localhost:8888/";
    public static final String REMOTE_HOST = "http://ilistings.appspot.com/";
    private Label historylbl = new Label();

    private Place home = new HomePlace("home");
    private SimplePanel appWidget = new SimplePanel();
    ClientFactory clientFactory = GWT.create(ClientFactory.class);

    Boolean gwtcss = ListingsResources.INSTANCE.css().ensureInjected();
    //private MainWidget mainwidget = new MainWidget();

    //com.google.gwt.event.shared.EventBus geventBus;

    /** 
     * Creates a new instance of MainEntryPoint
     */
    
    /** 
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    @Override
    public void onModuleLoad() {
        /*final Label label = new Label("Hello, GWT!!!");
        final Button button = new Button("Click me!");
        Hyperlink link0 = new Hyperlink("link to register", "user/register.jsp");
        VerticalPanel panel = new VerticalPanel();
        LoginWidget loginwidget = new LoginWidget();
        AgentRegister agentRegisterWidget = new AgentRegister();*/
        /*GWT.log("calling onModuleLoad");
        final Injector ginjector = GWT.create(Injector.class);

        final MainPresenter mainPresenter = ginjector.getMainPresenter();

	mainPresenter.bind();*/
        //setup();
        initServices();
        gwtcss = true;

	EventBus eventBus = clientFactory.getEventBus();
	final PlaceController placeController = clientFactory.getPlaceController();
        //MainWidget mainwidget = clientFactory.getApp();

        ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(appWidget);

        AppPlaceHistoryMapper apphistoryMapper= GWT.create(AppPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(apphistoryMapper);
        historyHandler.register(placeController, eventBus, home);
        
        appWidget.add(historylbl);
        appWidget.setStyleName("main-wrapper");
        RootPanel.get("rootNode").add(appWidget);
        //RootLayoutPanel.get().add(appWidget);
	// Goes to place represented on URL or default place
        historyHandler.handleCurrentHistory();
    }
    
    @Override
    public void onValueChange(ValueChangeEvent<String> vce) {
        historylbl.setText("The current history token is: " + vce.getValue());
    }
    
    /*private void setup() {
        AppController appc = new AppController(eventBus);
        appc.doEvents();
    }*/
    
    protected void initServices() {
        //initialize the AgentProfileService and BuyerProfileService here and set their endpoints
    }
    
}
