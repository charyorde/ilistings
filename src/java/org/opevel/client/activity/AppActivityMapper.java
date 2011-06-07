package org.opevel.client.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import org.opevel.client.ClientFactory;
import org.opevel.client.place.AgentProfilePlace;
import org.opevel.client.place.HomePlace;
import org.opevel.client.place.SearchResultPlace;
import org.opevel.client.place.UploadListingPlace;
import org.opevel.client.presenter.MainPresenter;

public class AppActivityMapper implements ActivityMapper {

        private ClientFactory clientFactory;

	/**
	 * AppActivityMapper associates each Place with its corresponding
	 * {@link Activity}
	 * 
	 * @param clientFactory
	 *            Factory to be passed to activities
	 */
	/*public AppActivityMapper(MainPresenter presenter) {
		super();
		this.presenter = presenter;
	}*/

        public AppActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
                //this.presenter = presenter;
	}

	/**
	 * Map each Place to its corresponding Activity. This would be a great use
	 * for GIN.
	 */
	@Override
	public Activity getActivity(Place place) {
		// This is begging for GIN
                if(place instanceof HomePlace)
                    return new HomeActivity((HomePlace) place, clientFactory);
		if (place instanceof AgentProfilePlace)
			return new AgentProfileActivity((AgentProfilePlace) place, clientFactory);
                if (place instanceof UploadListingPlace)
			return new UploadListingActivity((UploadListingPlace) place, clientFactory);
                if (place instanceof SearchResultPlace)
			return new SearchResultActivity((SearchResultPlace) place, clientFactory);
                else
		return null;
	}

}
