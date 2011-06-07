/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import org.opevel.client.service.SearchService;
import org.opevel.client.service.SearchServiceAsync;
import org.opevel.client.util.RPCCall;

/**
 *
 * @author Administrator
 */
public class Searcher {

    private ClientFactory clientFactory;

    private SearchServiceAsync searchservice = GWT.create(SearchService.class);;

    public SearchServiceAsync getSearchservice() {
        return clientFactory.getSearchService();
    }

    public Searcher() {
    }



    public void doSearch() {
        GWT.log("got here");
        System.out.println("system.out.println: got here");
    // make RPC call
    //searchListingsService();
    // call the respective place

        new RPCCall<ArrayList<Object>>() {
      @Override protected void callService(AsyncCallback<ArrayList<Object>> cb) {
        searchservice.searchListingsService(cb);
      }

      @Override public void onSuccess(ArrayList<Object> result) {
        /*friendSummaries = result;
        sortFriendSummaryDTO();
        display.setData(toStringList(friendSummaries));
        selectedRows = display.getSelectedRows();
        fireFriendListChangeEvent();*/
          Window.alert("worked");
          //call the respective place
      }

      @Override public void onFailure(Throwable caught) {
        Window.alert("Something went wrong: " + caught.getMessage());
      }
    };

    }

}
