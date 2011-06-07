/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

/**
 *
 * @author Administrator
 */
public class FooterWidget extends Composite {

    private static final String CREDIT = "<a href='http://opevel.com'>Built By Opevel</a>";
    private static final HTML COPYRIGHT = new HTML("&copy; 2011. All Rights Reserved");

    FlowPanel footerPanel = new FlowPanel();

    public FooterWidget() {

        initWidget(footerPanel);
        footerPanel.add(new HTML("<div class='float-left copyright'>" + COPYRIGHT + "</div>"));
        footerPanel.add(new HTML("<div class='float-right credit'>" + CREDIT + "</div>"));
        setStyleName("gwt-footerPanel");
    }


}
