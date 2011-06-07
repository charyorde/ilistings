/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.place;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 *
 * @author Administrator
 */
public class AgentProfilePlace extends Place {

    private String agentprofileplaceToken;

    public AgentProfilePlace() {
        GWT.log("AgentProfilePlace is called");
    }

    public AgentProfilePlace(String token) {
        this.agentprofileplaceToken = token;
    }

    public String getAgentprofileplaceToken() {
        return agentprofileplaceToken;
    }



    public static class AgentProfileTokenizer implements PlaceTokenizer<AgentProfilePlace> {

        @Override
        public AgentProfilePlace getPlace(String place) {
            return new AgentProfilePlace(place);
        }

        @Override
        public String getToken(AgentProfilePlace p) {
            return p.getAgentprofileplaceToken();
        }

    }
}
