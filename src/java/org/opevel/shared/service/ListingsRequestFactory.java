package org.opevel.shared.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.RequestFactory;
import com.google.gwt.requestfactory.shared.Service;
import org.opevel.server.dao.ListingsEntityDAOImpl;
import org.opevel.shared.proxy.ListingsEntityProxy;
import org.opevel.server.locator.DaoServiceLocator;
/**
 *
 * @author Administrator
 */
public interface ListingsRequestFactory extends RequestFactory {

    
    @Service(value = ListingsEntityDAOImpl.class, locator = DaoServiceLocator.class)
    interface ListingsEntityRequestContext extends RequestContext {
        Request<Boolean> createNewListing(ListingsEntityProxy listingsEntity);
    }
    ListingsEntityRequestContext listingsEntityRequest();

}
