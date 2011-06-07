/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

/**
 *
 * @author Administrator
 */
public interface HomeView extends IsWidget {

    void setToken(String homeToken);
    HasClickHandlers showAgentProfileView();

    interface Presenter {
        void goTo(Place place);
    }
    
    void setPresenter(Presenter p);

    abstract HasClickHandlers doSearch();
}
