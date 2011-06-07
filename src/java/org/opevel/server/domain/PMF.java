/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.domain;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 *
 * @author Administrator
 */
public class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}
