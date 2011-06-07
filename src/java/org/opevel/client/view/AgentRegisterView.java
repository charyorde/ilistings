/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opevel.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.opevel.client.AgentRegisterService;
import org.opevel.client.AgentRegisterServiceAsync;
import org.opevel.client.presenter.AgentRegisterPresenterImpl;

/**
 *
 * @author Administrator
 */
public class AgentRegisterView extends Composite implements AgentRegisterPresenterImpl.Display{

    Label lblServerReply = new Label();
    Label bnameLabel = new Label("Business name");
    TextBox bnameTextBox = new TextBox();
    Label addressLabel = new Label("Address");
    TextBox addressTextBox = new TextBox();
    Label aboutCoyLabel = new Label("About Company");
    TextArea aboutCoyTextArea = new TextArea();
    Label yearLabel = new Label("Year of Establishment");
    LongBox yearLongBox = new LongBox();
    Button createAgent = new Button("Submit");

    public static AgentRegisterServiceAsync getService() {
            return GWT.create(AgentRegisterService.class);
        }

    public AgentRegisterView() {
        final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Agent Registration");
		initWidget(dialogBox);
        VerticalPanel container = new VerticalPanel();
        container.setSpacing(4);
        container.add(lblServerReply);
        container.add(bnameLabel);
        container.add(bnameTextBox);
        container.add(addressLabel);
        container.add(addressTextBox);
        container.add(aboutCoyLabel);
        container.add(aboutCoyTextArea);
        container.add(yearLabel);
        container.add(yearLongBox);

        HorizontalPanel buttons = new HorizontalPanel();
        buttons.setSpacing(12);
        buttons.add(createAgent);
        container.add(buttons);

        final AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                    public void onSuccess(Boolean result) {
                        Window.alert("onSuccess succeeds");
                        lblServerReply.setText("Account created successfully");
                    }

            @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("onFailure fails");
                        lblServerReply.setText("Communication failed");
                    }
                };
		/*createAgent.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event){
                                //onLoginClick();
                            AgentRegisterDTO agentserviceDTO = new AgentRegisterDTO();
                            agentserviceDTO.setAboutCompany(aboutCoyTextArea.getValue());
                            agentserviceDTO.setAddress(addressTextBox.getValue());
                            agentserviceDTO.setBusinessName(bnameTextBox.getValue());
                            agentserviceDTO.setYearofestablishment(yearLongBox.getValue());
                            GWT.log("requesting service");
                                getService().createAgent(agentserviceDTO, callback);
                                Window.alert("You are logged in!");
		}
		});*/
                dialogBox.setWidget(container);
    }
    
    @Override
    public HasClickHandlers insertAgent() {
        return createAgent;
    }
}