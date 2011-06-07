/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.view;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author Administrator
 */
public interface SearchResultView extends IsWidget {

    public void displayJSONObject(JSONValue jsonValue);

    public void displayParseError(String responseText);

    public void displayError(String errorType, String errorMessage);

    void addResultItems(Widget w, JSONValue jsonvalue);

    void parseJSON(String result);

    public interface Presenter {

        void parseJSON(String result);
    }
}
