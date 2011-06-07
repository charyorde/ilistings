/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.test.dao;

import org.opevel.server.domain.UsersJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.persistence.Persistence;
import org.opevel.server.domain.Users;

/**
 *
 * @author Administrator
 */
public class UsersJpaControllerTest {

    public UsersJpaControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getEntityManager method, of class UsersJpaController.
     */
    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        UsersJpaController instance = new UsersJpaController();
        EntityManager expResult = Persistence.createEntityManagerFactory("ListingsPU").createEntityManager();
        EntityManager result = instance.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class UsersJpaController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Users users = null;
        UsersJpaController instance = new UsersJpaController();
        instance.create(users);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class UsersJpaController.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Users users = null;
        UsersJpaController instance = new UsersJpaController();
        instance.edit(users);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class UsersJpaController.
     */
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        Integer id = null;
        UsersJpaController instance = new UsersJpaController();
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUsersEntities method, of class UsersJpaController.
     */
    @Test
    public void testFindUsersEntities_0args() {
        System.out.println("findUsersEntities");
        UsersJpaController instance = new UsersJpaController();
        List expResult = null;
        List result = instance.findUsersEntities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUsersEntities method, of class UsersJpaController.
     */
    @Test
    public void testFindUsersEntities_int_int() {
        System.out.println("findUsersEntities");
        int maxResults = 0;
        int firstResult = 0;
        UsersJpaController instance = new UsersJpaController();
        List expResult = null;
        List result = instance.findUsersEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUsers method, of class UsersJpaController.
     */
    @Test
    public void testFindUsers() {
        System.out.println("findUsers");
        Integer id = 1;
        UsersJpaController instance = new UsersJpaController();
        //Users expResult = instance.findUsers(id);
        List<Users> users = instance.getEntityManager().createQuery("Select u from Users u").getResultList();
        Users expResult = (Users) users;
        Users result = instance.findUsers(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsersCount method, of class UsersJpaController.
     */
    @Test
    public void testGetUsersCount() {
        System.out.println("getUsersCount");
        UsersJpaController instance = new UsersJpaController();
        int expResult = 0;
        int result = instance.getUsersCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}