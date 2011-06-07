/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import org.opevel.client.presenter.BuyerProfilePresenter;

/**
 *
 * @author Administrator
 */
public class BuyerProfile extends Composite implements BuyerProfilePresenter.Display {

    private Label label = new Label("Buyerprofile");
    
    public BuyerProfile() {

        initWidget(label);
    }

    
}
