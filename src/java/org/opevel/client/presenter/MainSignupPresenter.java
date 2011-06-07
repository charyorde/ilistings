/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;
import org.enunes.gwt.mvp.client.presenter.Presenter;
import org.opevel.shared.UserEntityDTO;

/**
 *
 * @author Administrator
 */
public interface MainSignupPresenter extends Presenter<MainSignupPresenter.Display>{

    interface Display extends org.enunes.gwt.mvp.client.view.Display {
        HasClickHandlers signup();
        HasClickHandlers signupRequest();
        //UserEntityDTO setData(UserEntityDTO userEntitydto);
        UserEntityDTO setData();
        HasClickHandlers getTypeSelection();
        @Override
        Widget asWidget();
        void addContent(org.enunes.gwt.mvp.client.view.Display display);

        void removeContent();

        void addWidget(Widget widget);
        void removeWidget(Widget widget);
        void removeContent(org.enunes.gwt.mvp.client.view.Display display);
        void showWidget(Widget widget);
    }
}
