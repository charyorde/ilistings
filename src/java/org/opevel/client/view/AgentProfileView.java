/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

/**
 *
 * @author Administrator
 */
public interface AgentProfileView extends IsWidget {

    void setPresenter(Presenter presenter);
    void setToken(String profileToken);

    public interface Presenter {
        void goTo(Place place);
    }

}
