/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import org.opevel.client.resources.ListingsResources;

/**
 *
 * @author Administrator
 */
public class WelcomeWidget extends Composite {

    final FlowPanel welcomePanel = new FlowPanel();

    String welcome = ListingsResources.INSTANCE.getWelcomeContent().getText();

    public WelcomeWidget() {

        welcomePanel.add(new HTML(welcome));
        initWidget(welcomePanel);
    }


}
