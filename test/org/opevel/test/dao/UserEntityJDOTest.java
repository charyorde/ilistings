/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.test.dao;

import com.appenginefan.toolkit.unittests.BaseTest;
import javax.jdo.PersistenceManager;
import org.junit.Test;
import org.opevel.server.dao.UserEntityDAOImpl;
import org.opevel.server.domain.UserEntity;

/**
 *
 * @author Administrator
 */
public class UserEntityJDOTest extends BaseTest {


   @Test
    public void testFindUserByEmail(String email) {

        UserEntityDAOImpl userentityDAO = new UserEntityDAOImpl();
        email = "dreyemi@gmail.com";
        PersistenceManager manager = newPersistenceManager();
        UserEntity expectedemail = manager.getObjectById(UserEntity.class, email);
        assertEquals(expectedemail, userentityDAO.findUserByEmail(email));
        manager.close();
        fail("The test case is a prototype.");
    }
}
