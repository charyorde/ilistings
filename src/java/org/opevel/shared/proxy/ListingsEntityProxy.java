/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.shared.proxy;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;
import org.opevel.server.domain.ListingsEntity;
import org.opevel.server.locator.JDOLocator;

/**
 *
 * @author Administrator
 */
@ProxyFor(value = ListingsEntity.class, locator = JDOLocator.class)
public interface ListingsEntityProxy extends EntityProxy {

    String getTitle();
    void setTitle(String title);
    String getDescription();
    void setDescription(String description);
    Double getPrice();
    void setPrice(Double price);
    String getAddress();
    void setAddress(String address);
    Float getLatitude();
    void setLatitude(Float latitude);
    Float getLongitude();
    void setLongitude(Float longitude);
    String getManagedby();
    void setManagedby(String managedby);
    Boolean getStatus();
    void setStatus(Boolean status);
    /*ListingFile getListingFile();
    void setListingFile(ListingFile listingfile);*/

}
