/** 
 * Copyright 2010 Daniel Guermeur and Amy Unruh
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   See http://connectrapp.appspot.com/ for a demo, and links to more information 
 *   about this app and the book that it accompanies.
 */
package org.opevel.client.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import org.enunes.gwt.mvp.client.presenter.Presenter;
import org.opevel.client.view.AgentProfileView;

public interface MainPresenter extends Presenter<MainPresenter.Display> {

    PlaceController getPlaceController();

    interface Display extends org.enunes.gwt.mvp.client.view.Display {

		void addWidget(org.enunes.gwt.mvp.client.view.Display display);

		void addContent(org.enunes.gwt.mvp.client.view.Display display);

		void removeContent();

                AgentProfileView getAgentProfileView();

                HasClickHandlers showAgentProfileView();

                void goTo(Place place);
                
	}
}
