/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import org.opevel.client.event.MainSignupEvent.MainSignupEventHandler;

/**
 *
 * @author Administrator
 */
public class MainSignupEvent extends GwtEvent<MainSignupEventHandler> {

    public interface MainSignupEventHandler extends EventHandler {
        void onSignup(MainSignupEvent event);
    }

    public MainSignupEvent() {
    }

    private static Type<MainSignupEventHandler> TYPE;

    public static Type<MainSignupEventHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<MainSignupEventHandler>());
	}

    @Override
    public Type<MainSignupEventHandler> getAssociatedType() {
        return getType();
    }

    @Override
    protected void dispatch(MainSignupEventHandler h) {
        h.onSignup(this);
    }

}
