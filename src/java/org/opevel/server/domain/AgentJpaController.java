/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.domain;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import org.opevel.server.AgentRegisterServiceImpl;
import org.opevel.server.domain.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrator
 */
public class AgentJpaController {

    public AgentJpaController() {
        emf = Persistence.createEntityManagerFactory("transactions-optional");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Agent agent) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(agent);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Agent agent) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            agent = em.merge(agent);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = agent.getId();
                if (findAgent(id) == null) {
                    throw new NonexistentEntityException("The agent with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Agent agent;
            try {
                agent = em.getReference(Agent.class, id);
                agent.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The agent with id " + id + " no longer exists.", enfe);
            }
            em.remove(agent);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Agent> findAgentEntities() {
        return findAgentEntities(true, -1, -1);
    }

    public List<Agent> findAgentEntities(int maxResults, int firstResult) {
        return findAgentEntities(false, maxResults, firstResult);
    }

    private List<Agent> findAgentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Agent as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Agent findAgent(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Agent.class, id);
        } finally {
            em.close();
        }
    }

    public int getAgentCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Agent as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
