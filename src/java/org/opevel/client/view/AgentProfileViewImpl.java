/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

/**
 *
 * @author Administrator
 */
public class AgentProfileViewImpl extends Composite implements AgentProfileView {

    private Label label = new Label("Buyerprofile");
    private Presenter listener;

    private String profileToken;

    public AgentProfileViewImpl() {

        initWidget(label);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.listener = presenter;
    }

    @Override
    public void setToken(String profileToken) {
        this.profileToken = profileToken;
    }

}
