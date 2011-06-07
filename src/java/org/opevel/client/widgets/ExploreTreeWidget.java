/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;

/**
 *
 * @author Kayode Odeyemi
 */
public class ExploreTreeWidget extends Composite {

    private FlowPanel exploreVP = new FlowPanel();

    public ExploreTreeWidget() {
        exploreVP.setStyleName("gwt-ExploreTree");
        exploreVP.add(new HTML("<h2 class='title'>Explore</h2>"));
        exploreVP.add(new Hyperlink("Get Started", " "));
        exploreVP.add(new Hyperlink("Upload properties", " "));
        exploreVP.add(new Hyperlink("See who\'s on Listings", " "));
        exploreVP.add(new Hyperlink("Find properties", ""));
        //exploreHP.add(exploreMenu);
        initWidget(exploreVP);
    }


}
