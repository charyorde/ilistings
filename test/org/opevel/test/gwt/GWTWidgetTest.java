/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.test.gwt;

import com.google.gwt.junit.client.GWTTestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opevel.client.ClientFactory;
import org.opevel.client.place.SearchResultPlace;

/**
 *
 * @author Kayode Odeyemi
 */
public class GWTWidgetTest extends GWTTestCase {

    ClientFactory clientFactory;

    @Override
    protected void gwtSetUp() {
        System.out.println("Calling gwtSetup");
    }
    @BeforeClass
    public static void initContainer() {
        System.out.println("calling BeforeClass");
    }
    @Override
    public String getModuleName() {
        //throw new UnsupportedOperationException("TODO: Not implemented.");
        return "org.opevel.Listings";
    }

    @Test
    public void testSearchResultView() {
        System.out.println("Testing getSearchResultView()");
        //assertTrue(1 == 1);
        SearchResultPlace searchresultPlace = new SearchResultPlace("searchresult");
        assertEquals("searchresult", clientFactory.getHistoryMapper().getToken(searchresultPlace));
        fail("NullPointerException");
    }
}
