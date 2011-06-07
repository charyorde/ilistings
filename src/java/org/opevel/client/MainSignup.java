/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.logging.Logger;
import org.enunes.gwt.mvp.client.view.Display;
import org.opevel.client.http.RequestBundle;
import org.opevel.client.presenter.MainSignupPresenterImpl;
import org.opevel.shared.UserEntityDTO;

/**
 *
 * @author Administrator
 */
public class MainSignup extends Composite implements MainSignupPresenterImpl.Display {

    private Logger logger = Logger.getLogger(MainSignup.class.getName());

    private static final String GOOGLE = "Google";
    private static final String YAHOO = "Yahoo";
    private static final String FACEBOOK = "Facebook";
    private static final String TWITTER = "Twitter";

    FlowPanel signupWrapper = new FlowPanel();
    VerticalPanel signupPanel = new VerticalPanel();
    FlowPanel buttonWrapper = new FlowPanel();
    FormPanel frontSignupFormPanel = new FormPanel();
    private DisclosurePanel openIdDisclosurePanel = new DisclosurePanel();
    
    private Label signupEmailLbl = new Label("Email");
    private TextBox signupemailTextBox = new TextBox();
    
    private Label signupPasswordLbl = new Label("Password");
    private PasswordTextBox signuppasswordTextBox = new PasswordTextBox();
    
    private Label signupListBoxLbl = new Label("I'm a");
    private ListBox typeListBox = new ListBox();
    
    private Button mainSignUpButton = new Button("Sign up");
    
    Hyperlink signuplink = new Hyperlink("Sign up", "signup");
    Button signupButton = new Button("Sign up");

    MainSignupServiceAsync mainSignupServiceAsync = GWT.create(MainSignupService.class);
    SpringSignupServiceAsync springSignupService = GWT.create(SpringSignupService.class);
    
    private RequestBundle rb = new RequestBundle();

    private Widget content;

    public String getSignupemailTextBox() {
        return signupemailTextBox.getValue();
    }

    public String getSignuppasswordTextBox() {
        return signuppasswordTextBox.getValue();
    }

    public String getTypeListBox() {
        return typeListBox.getValue(typeListBox.getSelectedIndex());
    }

    public MainSignup() {

        initWidget(signupWrapper);
        
        signupWrapper.add(frontSignupFormPanel);
        signupWrapper.setStyleName("gwt-signupPanel");
        
        //frontSignupFormPanel.setAction("signup/account");
        frontSignupFormPanel.setAction("register");
        //frontSignupFormPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
        //frontSignupFormPanel.setEncoding(FormPanel.ENCODING_URLENCODED);
        frontSignupFormPanel.setMethod(FormPanel.METHOD_POST);
        frontSignupFormPanel.setWidget(signupPanel);

        signupPanel.add(new HTML("<h2 class='title'>Signup on Listings</h2>"));
        signupPanel.add(signupEmailLbl);
        signupemailTextBox.setName("email");
        signupPanel.add(signupemailTextBox);
        
        signupPanel.add(signupPasswordLbl);
        signuppasswordTextBox.setName("pass");
        signupPanel.add(signuppasswordTextBox);
        
        signupPanel.add(signupListBoxLbl);
        typeListBox.addItem("Agent");
        typeListBox.addItem("Buyer");
        typeListBox.setName("type");
        signupPanel.add(typeListBox);
        
        //button.setSpacing(12);
        //button.add(mainSignUpButton);
        //signupPanel.add(mainSignUpButton);

        signupWrapper.add(buttonWrapper);
        buttonWrapper.add(mainSignUpButton);
        buttonWrapper.add(openIdDisclosurePanel);
        buttonWrapper.setStyleName("loginButtonWrapper");
        //signupWrapper.add(openIdDisclosurePanel);

        openIdDisclosurePanel.setStyleName("gwt-oIdDisclosurePanel");
        openIdDisclosurePanel.setHeader(new HTML("Login with"));
        openIdDisclosurePanel.setContent(new HTML("<div><a href='" + rb.getGoogleOpenIdURL() + "'>GOOGLE</a></div>" +
                "<div>FACEBOOK</div>" +
                "<div>YAHOO</div>" +
                "<div>TWITTER</div>"));
        //signupPanel.add(signupButton);
        
        // call agent or Buyer presenter (go) here to display the widget
        // Add a 'submit' button.
        signup().addClickHandler(new ClickHandler() {
            @Override
          public void onClick(ClickEvent event) {
            logger.info("onClick is called");
            GWT.log("onClick is called" + signupemailTextBox.getValue());
            frontSignupFormPanel.submit();
          }
        });

        final AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
            public void onSuccess(Boolean result) {
                //Window.alert("onSuccess succeeds");
                //statusUpdatelbl.setText("New Listing created successfully");
            }

            public void onFailure(Throwable caught) {
                //Window.alert("onFailure fails");
                //statusUpdatelbl.setText("Communication failed");
            }

        };
        // Add an event handler to the form.
        frontSignupFormPanel.addSubmitHandler(new FormPanel.SubmitHandler() {
            @Override
          public void onSubmit(SubmitEvent event) {
            // This event is fired just before the form is submitted. We can take
            // this opportunity to perform validation.
              //statusUpdatelbl.setText("Uploading...");
              logger.info("onSubmit is called");
              GWT.log("onSubmit is called" + signupemailTextBox.getValue());
              //Window.alert("onSubmit is called" + signupemailTextBox.getValue());
              /*new MainSignup().getSignupemailTextBox();
              new MainSignup().getSignuppasswordTextBox();
              new MainSignup().getTypeListBox();*/
              springSignupService.createUser(setData(), callback);
              //springSignupServiceAsync.myMethod(signupemailTextBox.getValue(), callback);
          }
        });

        frontSignupFormPanel.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
            @Override
          public void onSubmitComplete(SubmitCompleteEvent event) {
            // When the form submission is successfully completed, this event is
            // fired. Assuming the service returned a response of type text/html,
            // we can get the result text here (see the FormPanel documentation for
            // further explanation).
            //Window.alert(event.getResults());
              logger.info("onSubmitComplete is called");
              GWT.log("onSubmitComplete is called" + signupemailTextBox.getValue());
              if(typeListBox.getValue(typeListBox.getSelectedIndex()).equals("Agent")) {
                    dispatchToAgentSignup();
              }
              if(typeListBox.getValue(typeListBox.getSelectedIndex()).equals("Buyer")) {
                  dispatchToBuyerSignup();
                }
              
          }
        });

    }
    
    public void dispatchToAgentSignup() {
        final RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, GWT.getHostPageBaseURL() + "signup/agent");
                    try {
                        rb.sendRequest(null, new RequestCallback(){
                        @Override
                        public void onResponseReceived(Request request, Response response) {
                            if (response.getStatusCode() == 200) {
                                //Window.alert("status code is 200");
                                //rb.getUrl();
                                Window.Location.assign(rb.getUrl());
                            } else {
                                //Window.alert(response.getStatusText());
                            }
                        }

                        @Override
                        public void onError(Request request, Throwable exception) {
                            //Window.alert("onError, request is: "+request.toString());
                        }
                });
                }catch(RequestException re) {
                    logger.severe("RequestException occurred");
                    GWT.log("RequestException occurred");
                }
    }

    public void dispatchToBuyerSignup() {
        final RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, GWT.getHostPageBaseURL() + "signup/buyer");
                    try {
                        rb.sendRequest(null, new RequestCallback(){
                        @Override
                        public void onResponseReceived(Request request, Response response) {
                            if (response.getStatusCode() == 200) {
                                //Window.alert("status code is 200");
                                //rb.getUrl();
                                Window.Location.assign(rb.getUrl());
                            } else {
                                //Window.alert(response.getStatusText());
                            }
                        }

                        @Override
                        public void onError(Request request, Throwable exception) {
                            //Window.alert("onError, request is: "+request.toString());
                        }
                });
                }catch(RequestException re) {
                    logger.severe("RequestException occurred");
                    GWT.log("RequestException occurred");
                }
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public final HasClickHandlers signup() {
        return mainSignUpButton;
    }

    @Override
    public HasClickHandlers getTypeSelection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addContent(Display display) {
        removeContent();
		content = display.asWidget();
		signupPanel.add(display.asWidget());
    }

    @Override
    public void removeContent() {
        if(content != null)
        signupPanel.remove(content);
        else {
            Window.alert("content is null");
        }
    }

    @Override
    public void addWidget(Widget widget) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeWidget(Widget widget) {
        signupPanel.remove(this);
    }

    @Override
    public void showWidget(Widget widget) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeContent(Display display) {
        content = display.asWidget();
        signupPanel.remove(content);
    }

    @Override
    public UserEntityDTO setData() {
        //List<UserEntityDTO> userEntitydtolist = new ArrayList<UserEntityDTO>();
        UserEntityDTO userEntitydto = new UserEntityDTO();
        userEntitydto.setEmail(signupemailTextBox.getValue());
        userEntitydto.setPass(signuppasswordTextBox.getValue());
        userEntitydto.setType(typeListBox.getValue(typeListBox.getSelectedIndex()));
        //userEntitydtolist.add(userEntitydto);
        return userEntitydto;
    }

    @Override
    public HasClickHandlers signupRequest() {
        return signupButton;
    }
}
