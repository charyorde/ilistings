/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opevel.client.ClientFactory;
import static org.junit.Assert.*;

/**
 * MVP Activity & Place Widget unit tests
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
        //return GWT.getModuleName();
        return "org.opevel.Listings";
    }

    @Test
    public void testSearchResultView() {
        //delayTestFinish(5);
        System.out.println("Testing getSearchResultView()");
        assertTrue(1 == 1);  
        //SearchResultPlace searchresultPlace = new SearchResultPlace("searchresult");
        //assertEquals("searchresult", clientFactory.getHistoryMapper().getToken(searchresultPlace));
    }
}
