/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import java.util.logging.Logger;
import org.opevel.client.ClientFactory;
import org.opevel.client.place.AgentProfilePlace;
import org.opevel.client.presenter.MainPresenter;
import org.opevel.client.view.AgentProfileView;

/**
 *
 * @author Administrator
 */
public class AgentProfileActivity extends AbstractActivity implements AgentProfileView.Presenter {

    private Logger logger = Logger.getLogger(AgentProfileActivity.class.getName());

    private MainPresenter presenter;
    private ClientFactory clientFactory;
    private EventBus eventBus;
    //Place place = GWT.create(Place.class);

    private String agentprofileplaceToken;

    /*@Inject
    public AgentProfileActivity(AgentProfilePlace place, MainPresenter presenter) {
        this.presenter = presenter;
        this.agentprofileplace = place.getAgentprofileplace();
    }*/

    @Inject
    public AgentProfileActivity(AgentProfilePlace place, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        this.agentprofileplaceToken = place.getAgentprofileplaceToken();
    }


    @Override
    public void start(AcceptsOneWidget aow, EventBus eb) {
        logger.info("starting AgentProfileActivity");
        //Window.alert("agentprofile activity is received");
        //AgentProfileView agentProfileView = presenter.getDisplay().getAgentProfileView();
        AgentProfileView agentProfileView = clientFactory.getAgentProfileView();
        this.eventBus = eb;
        //set agent profile token;
        agentProfileView.setToken(agentprofileplaceToken);
        logger.info("profileToken is set to: " + agentprofileplaceToken);
        agentProfileView.setPresenter(this);
        aow.setWidget(agentProfileView.asWidget());
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
