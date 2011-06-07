/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.presenter;

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
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.enunes.gwt.mvp.client.EventBus;
import org.enunes.gwt.mvp.client.presenter.BasePresenter;
import org.enunes.gwt.mvp.client.presenter.Presenter;
import org.opevel.client.AppController;
import org.opevel.client.MainSignupService;
import org.opevel.client.MainSignupServiceAsync;
import org.opevel.client.event.MainSignupEvent;
import org.opevel.client.presenter.MainSignupPresenter.Display;
import org.opevel.shared.UserEntityDTO;

/**
 *
 * @author Administrator
 */
public class MainSignupPresenterImpl extends BasePresenter<MainSignupPresenter.Display> implements 
        MainSignupPresenter{

    private final Provider<AgentRegisterPresenter> agentRegisterProvider;
    //private final Provider<MainSignupPresenter> mainSignupProvider;
    private Presenter<? extends org.enunes.gwt.mvp.client.view.Display> presenter;
    MainSignupServiceAsync mainSignupServiceAsync = GWT.create(MainSignupService.class);
    UserEntityDTO userEntitydto;
    AsyncCallback<Boolean> callback;
    private AppController appcontrol;

    @Inject
	public MainSignupPresenterImpl(AgentRegisterPresenter agentPresenter,
            Provider<AgentRegisterPresenter> agentRegisterDisplayProvider,
            EventBus eventBus, Display display,
            //MainSignupServiceAsync mainSignupServiceAsync, AsyncCallback<Boolean> callback
            UserEntityDTO userEntitydto, AppController appcontrol) {
		super(eventBus, display);

                this.agentRegisterProvider = agentRegisterDisplayProvider;
                //this.mainSignupServiceAsync = mainSignupServiceAsync;
                this.userEntitydto = userEntitydto;
                this.appcontrol = appcontrol;
                //this.callback = callback;

                //bind agentRegisterPresenter to AgentRegisterView display
                agentPresenter.bind();
                //display.addContent(agentPresenter.getDisplay());
	}

    //Display display;
    /*MainSignupServiceAsync mainSignupServiceAsync;
    MainSignupServiceAsync mainSignupServiceAsync = GWT.create(MainSignupServiceAsync.class);
    SimpleEventBus eventBus;*/

    /*public MainSignupPresenterImpl(Display view) {
        this.display = view;
    }

    public MainSignupPresenterImpl(MainSignupServiceAsync mainSignupServiceAsync,
            SimpleEventBus eventBus, Display display) {
        this.mainSignupServiceAsync = mainSignupServiceAsync;
        this.eventBus = eventBus;
        this.display = display;
        bind();
    }*/

    @Override
    public void bind() {
        super.bind();
        registerHandler(display.signup().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                //eventBus.fireEvent(new MainSignupEvent());
                //userSignupDTO();
                Window.alert("clicked");
            }

        }));

       registerHandler(display.signupRequest().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent ce) {
                eventBus.fireEvent(new MainSignupEvent());
            }

       }));
    }

        public void userSignupDTO() {
            userEntitydto = display.setData();
            mainSignupServiceAsync.createUser(userEntitydto, callback);
            
            new AsyncCallback<Boolean>() {
                @Override
            public void onSuccess(Boolean result) {
                Window.alert("user created successfully");
                //on return(success) create a path and
               //redirect to the path and add the corresponding
            };

            @Override
            public void onFailure(Throwable thrwbl) {
                Window.alert("Error");
            }
            };
            
        }

        /*public List getUserSignupData() {
            return display.setData();
        }*/

    private void switchPresenter(
			Presenter<? extends org.enunes.gwt.mvp.client.view.Display> presenter) {
		if (this.presenter != null) {
                    Window.alert("MainSignupPresenter is not null");
			this.presenter.unbind();
			display.removeContent();
		}

		this.presenter = presenter;

		if (presenter != null) {
                    Window.alert("Presenter is not null");
			display.addContent(presenter.getDisplay());
			this.presenter.bind();
		}
                presenter.unbind();

	}
/*
    @Override
    public void go(HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
    }
*/
    @Override
    public void unbind() {
        super.unbind();
        presenter.unbind();
    }

}
