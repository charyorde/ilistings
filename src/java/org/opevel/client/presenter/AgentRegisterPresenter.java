/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import org.enunes.gwt.mvp.client.presenter.Presenter;

/**
 *
 * @author Administrator
 */
public interface AgentRegisterPresenter extends Presenter<AgentRegisterPresenter.Display> {

    interface Display extends org.enunes.gwt.mvp.client.view.Display {
        HasClickHandlers insertAgent();
    }
}
