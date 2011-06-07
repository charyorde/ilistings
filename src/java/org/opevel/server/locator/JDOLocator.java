/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.locator;

import com.google.gwt.requestfactory.shared.Locator;
import org.opevel.server.domain.ListingsEntity;
import org.opevel.server.dao.ListingsEntityDAOImpl;

/**
 *
 * @author Administrator
 */
public class JDOLocator extends Locator<ListingsEntity, Long>{

    @Override
    public ListingsEntity create(Class<? extends ListingsEntity> clazz) {
        try
		{
			return clazz.newInstance();
		} catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    @Override
    public ListingsEntity find(Class<? extends ListingsEntity> clazz, Long id) {
        ListingsEntityDAOImpl listingsdao = new ListingsEntityDAOImpl();
        return listingsdao.findListingsEntity(id);
    }

    @Override
    public Class<ListingsEntity> getDomainType() {
        return null;
    }

    @Override
    public Long getId(ListingsEntity t) {
        return t.getkey().getId();
    }

    @Override
    public Class<Long> getIdType() {
        return Long.class;
    }

    @Override
    public Object getVersion(ListingsEntity t) {
        return t.getVersion();
    }

}
