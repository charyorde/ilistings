/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.widgets;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 *
 * @author Administrator
 */
public class SearchWidget extends Composite {

    private FlowPanel searchPanel = new FlowPanel();
    private TextBox searchBox = new TextBox() ;
    private Button searchButton;

    public SearchWidget() {

        initWidget(searchPanel);
        searchPanel.setStyleName("gwt-searchPanel float_left");
        searchPanel.add(searchBox);

        searchBox.setStyleName("searchbox");
        searchPanel.add(getButton());
    }

    public Button getButton() {
        if(searchButton == null) {
            searchButton = new Button("Search");
        }
        return searchButton;
    }




}
