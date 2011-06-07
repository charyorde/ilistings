/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.view;

import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class SearchResultViewImpl extends Composite implements SearchResultView {

    private Logger logger = Logger.getLogger(SearchResultViewImpl.class.getName());

    private VerticalPanel searchresultContainer = new VerticalPanel();
    private FlowPanel searchresultPanel = new FlowPanel();

    public SearchResultViewImpl() {
        logger.info(this.toString());
        initWidget(searchresultContainer);
        searchresultContainer.add(searchresultPanel);
        searchresultContainer.setStyleName("gwt-uploadListingFormPanel");
    }

    @Override
    public void displayJSONObject(JSONValue jsonValue) {
        logger.info("SearchResultActivity calling displayJSONObject");
        addResultItems(this.searchresultPanel, jsonValue);
    }

    @Override
    public void displayParseError(String responseText) {
        displayError("Failed to parse JSON response", responseText);
    }

    @Override
    public void displayError(String errorType, String errorMessage) {
        searchresultPanel.add(new HTML(errorType));
        searchresultPanel.add(new HTML(errorMessage));
    }

    @Override
    public Widget asWidget() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addResultItems(Widget w, JSONValue jsonvalue) {
        JSONString jsonString;

        if ((jsonString = jsonvalue.isString()) != null) {
            // Use stringValue instead of toString() because we don't want escaping
            searchresultPanel.add(new HTML("<div>" + jsonString.stringValue() + "</div>"));
            //treeItem.addItem(jsonString.stringValue());
        } else {
            // JSONBoolean, JSONNumber, and JSONNull work well with toString().
            searchresultPanel.add(new HTML(getChildText(jsonvalue.toString())));
        }
    }

    /*
   * Causes the text of child elements to wrap.
   */
  private String getChildText(String text) {
    return "<span style='white-space:normal'>" + text + "</span>";
  }

  public void parseJSON(String result) {
        logger.info("parsing JSON");
        try {
            JSONValue jsonValue = JSONParser.parseStrict(result);
            // display the parsed response
            new SearchResultViewImpl().displayJSONObject(jsonValue);
        } catch (JSONException e) {
            logger.severe("JSONException occurred while trying to parseJSON");
            new SearchResultViewImpl().displayParseError(result);
        }
    }
}
