/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.dao;

import org.opevel.server.domain.ListingsEntity;

/**
 *
 * @author Administrator
 */
public interface ListingsEntityDAO {

    Boolean createNewListings(ListingsEntity listingsEntity);
}
