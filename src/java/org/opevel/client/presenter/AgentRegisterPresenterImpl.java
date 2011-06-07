/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.presenter;

import com.google.inject.Inject;
import org.enunes.gwt.mvp.client.EventBus;
import org.enunes.gwt.mvp.client.presenter.BasePresenter;

/**
 *
 * @author Administrator
 */
public class AgentRegisterPresenterImpl extends BasePresenter<AgentRegisterPresenter.Display>
        implements AgentRegisterPresenter {

    @Inject
    public AgentRegisterPresenterImpl(EventBus eventBus, Display display) {
        super(eventBus, display);
    }

    @Override
    public void bind() {
        
        super.bind();
        
    }

}
