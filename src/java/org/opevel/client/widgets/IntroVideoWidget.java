/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 * @author Administrator
 */
public class IntroVideoWidget extends Composite {

    private Label lbl = new Label("IntroVideoWidget");
    private HorizontalPanel hp = new HorizontalPanel();

    public IntroVideoWidget() {
        hp.add(lbl);
        hp.setStylePrimaryName("gwt-IntroVideoWrapper");
        initWidget(hp);
    }
}
