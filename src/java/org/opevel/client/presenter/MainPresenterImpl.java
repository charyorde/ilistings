/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.presenter;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import org.enunes.gwt.mvp.client.presenter.BasePresenter;
import org.enunes.gwt.mvp.client.presenter.Presenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.enunes.gwt.mvp.client.EventBus;
import org.opevel.client.AppController;
import org.opevel.client.activity.AppActivityMapper;
import org.opevel.client.event.MainSignupEvent;
import org.opevel.client.place.AgentProfilePlace;
import org.opevel.client.place.AppPlaceHistoryMapper;
import org.opevel.client.view.AgentProfileView;

/**
 *
 * @author Administrator
 */
public class MainPresenterImpl extends BasePresenter<MainPresenter.Display> 
        implements MainPresenter, ValueChangeHandler<String>  {

        private MainPresenter mainp;

        private AgentProfileView.Presenter agentprofilepresenter;

        private String name;

	private Presenter<? extends org.enunes.gwt.mvp.client.view.Display> presenter;
        private final Provider<MainSignupPresenter> mainSignupProvider;
        private com.google.gwt.event.shared.EventBus geventBus;

        private PlaceController placecontroller = new PlaceController(geventBus);

        String url = GWT.getHostPageBaseURL() + "signup";

	@Inject
	public MainPresenterImpl(EventBus eventBus, Display display,
			MainSignupPresenter mainsignupPresenter,
                        Provider<MainSignupPresenter> mainSignupDisplayProvider) {

		super(eventBus, display);

                this.mainSignupProvider = mainSignupDisplayProvider;

		mainsignupPresenter.bind();
		display.addWidget(mainsignupPresenter.getDisplay());

	}

        @Override
        public void bind() {
            super.bind();
            //register Event here
            registerHandler(eventBus.addHandler(MainSignupEvent.getType(),
                    new MainSignupEvent.MainSignupEventHandler() {
                @Override
                public void onSignup(MainSignupEvent event) {
                    final RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, url);
                    try {
                        rb.sendRequest(null, new RequestCallback(){
                        @Override
                        public void onResponseReceived(Request request, Response response) {
                            if (response.getStatusCode() == 200) {
                                Window.alert("status code is 200");
                                //rb.getUrl();
                                Window.Location.assign(rb.getUrl());
                            } else {
                                Window.alert(response.getStatusText());
                            }
                        }

                        @Override
                        public void onError(Request request, Throwable exception) {
                            Window.alert("onError, request is: "+request.toString());
                        }
                });
                }catch(RequestException re) {
                    Logger.getLogger(MainPresenterImpl.class.getName()).log(Level.SEVERE, null, re);
                    GWT.log("RequestException occurred");
                }
                }
            }));

            registerHandler(display.showAgentProfileView().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                //eventBus.fireEvent(new MainSignupEvent());
                getAgentProfileActivity();
            }

        }));
        }

        public void userSignupRequest() {
            Window.alert("userSignupRequest Request is called");
            History.newItem("signup");
            History.addValueChangeHandler(this);
            History.fireCurrentHistoryState();

        }

        public void getAgentProfileActivity() {
            Window.alert("getAgentProfileActivity Request is called");

            /*History.newItem("profile");
            History.addValueChangeHandler(this);
            History.fireCurrentHistoryState();*/
            
            // Start ActivityManager for the main widget with our ActivityMapper
            /*ActivityMapper activityMapper = new AppActivityMapper(mainp);
            ActivityManager activityManager = new ActivityManager(activityMapper, geventBus);
            activityManager.setDisplay((AcceptsOneWidget)mainp.getDisplay().getAgentProfileView().asWidget());

            Place agentprofile = new AgentProfilePlace("profile");
            PlaceController agentprofilePlaceController = mainp.getPlaceController();
            AgentProfilePlaceHistoryMapper agentprofilehistoryMapper= GWT.create(AgentProfilePlaceHistoryMapper.class);
            PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(agentprofilehistoryMapper);
            historyHandler.register(placecontroller, geventBus, agentprofile);*/
            
            placecontroller.goTo(new AgentProfilePlace(name));

            //display.goTo(new AgentProfilePlace(name));

        }

    @Override
    public void onValueChange(ValueChangeEvent<String> vce) {
        String token = vce.getValue();
        if(token.equals("signup")) {
            //Window.alert("token is signup");
            RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, AppController.getRelativeURL("signup"));
            try {
                rb.setCallback(new RequestCallback(){
                @Override
                public void onResponseReceived(Request request, Response response) {
                    if (response.getStatusCode() == 200) {
                        Window.alert("status code is 200");
                    } else {
                        Window.alert(response.getStatusText());
                    }
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    Window.alert("onError, request is: "+request.toString());
                }

            });
                rb.send();
            }catch(RequestException re) {
                Logger.getLogger(MainPresenterImpl.class.getName()).log(Level.SEVERE, null, re);
                GWT.log("RequestException occurred");
            }
        }
    }

        public void removeLoginForm() {
            MainSignupPresenter mainSignupPresenter = mainSignupProvider.get();
            //mainSignupPresenter.unbind();
            display.removeContent();
            //switchPresenter(mainSignupPresenter);
        }

        public void changeView() {
            //construct a GWT request to a path
            //display agentRegister or buyerRegister in that path
            //redirect to either agentRegister view or buyerRegister view
        }
        private void switchPresenter(
			Presenter<? extends org.enunes.gwt.mvp.client.view.Display> presenter) {

		if (this.presenter != null) {
			this.presenter.unbind();
			display.removeContent();
		}

		this.presenter = presenter;

		if (presenter != null) {
			display.addContent(presenter.getDisplay());
			this.presenter.bind();
		}

	}

    @Override
    public void unbind() {
        super.unbind();
        presenter.unbind();
    }

    @Override
    public PlaceController getPlaceController() {
        return placecontroller;
    }


}
