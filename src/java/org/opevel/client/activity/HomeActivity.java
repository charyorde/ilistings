/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import java.util.logging.Logger;
import org.opevel.client.ClientFactory;
import org.opevel.client.MainWidget;
import org.opevel.client.place.HomePlace;
import org.opevel.client.view.HomeView.Presenter;

/**
 *
 * @author Kayode Odeyemi
 */
public class HomeActivity extends AbstractActivity implements Presenter {

    private static final Logger logger = Logger.getLogger(HomeActivity.class.getName());
    //AgentProfileView
    private ClientFactory clientFactory;
    private EventBus eventBus;
    private Presenter presenter;

    private String homeplaceToken;

    public HomeActivity(HomePlace homePlace, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        this.homeplaceToken = homePlace.getHomeplaceToken();
    }

    @Override
    public void start(AcceptsOneWidget aow, EventBus eb) {
        logger.info("starting HomeActivity");
        //Window.alert("homeActivity is started");
        this.eventBus = eb;
        MainWidget mainWidget = clientFactory.getApp();
        //set mainWidget token
        mainWidget.setToken(homeplaceToken);
        logger.info("homeToken is set to: " + homeplaceToken);
        aow.setWidget(mainWidget.asWidget());
    }

    /**
	 * Ask user before stopping this activity
	 */
	@Override
	public String mayStop() {
		return "Please hold on. This activity is stopping.";
	}

    @Override
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }

}
