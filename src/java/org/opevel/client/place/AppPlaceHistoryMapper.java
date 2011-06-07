/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 *
 * @author Administrator
 */
@WithTokenizers({HomePlace.HomePlaceTokenizer.class, AgentProfilePlace.AgentProfileTokenizer.class, UploadListingPlace.UploadListingPlaceTokenizer.class, SearchResultPlace.SearchResultPlaceTokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {

}
