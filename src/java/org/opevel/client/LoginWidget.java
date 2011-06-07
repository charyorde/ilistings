/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opevel.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.opevel.shared.UsersDTO;

public class LoginWidget extends Composite {
	
	Button login = new Button("Login");
	Button cancel = new Button("Cancel");
        Label lblServerReply = new Label();
        TextBox loginBox = new TextBox();
        PasswordTextBox passBox = new PasswordTextBox();
        UserLoginServiceAsync userloginService;


        public static UserLoginServiceAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.
            return GWT.create(UserLoginService.class);
        }

	public LoginWidget() {
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Please login");
		initWidget(dialogBox);
		VerticalPanel container = new VerticalPanel();
		container.setSpacing(4);
		container.add(new Label("Username:"));
		container.add(loginBox);
		container.add(new Label("Password"));
		container.add(passBox);
		
		HorizontalPanel buttons = new HorizontalPanel();
		buttons.setSpacing(12);
		buttons.add(login);
		buttons.add(cancel);
                container.add(lblServerReply);
		container.add(buttons);
		// add user click handler
                final AsyncCallback<UsersDTO> callback = new AsyncCallback<UsersDTO>() {
                    public void onSuccess(UsersDTO result) {
                        Window.alert("onSuccess succeeds");
                        lblServerReply.setText(result.toString());
                    }

                    public void onFailure(Throwable caught) {
                        Window.alert("onFailure fails");
                        lblServerReply.setText("Communication failed");
                    }
                };
		login.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event){
                                //onLoginClick();
                            GWT.log("requesting service");
                                getService().login(loginBox.getText(), passBox.getText(), callback);
                                Window.alert("You are logged in!");
                                //userloginService = (UserLoginServiceAsync) GWT.create(UserLoginService.class);
                                //userloginService.loginUser(loginBox.getText(), passBox.getText(), (AsyncCallback<String>) callback);
                                //userloginService.login(loginBox.getText(), passBox.getText(), (AsyncCallback<UsersDTO>) (UsersDTO) callback);
		}
		});
			dialogBox.setWidget(container);
		}
			
		void onLoginClick() {
				Window.alert("You are logged in!");
		}

			
}