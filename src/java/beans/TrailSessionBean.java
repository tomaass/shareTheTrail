/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Trail;
import model.TrailType;
import model.User;

/**
 *
 * @author tomaass
 */
@Stateless
public class TrailSessionBean {

    @PersistenceContext
    private EntityManager em;

    public void addTrail(Trail t) {
        em.persist(t);
    }

    public List<Trail> getTrails() {
        TypedQuery<Trail> q = em.createNamedQuery("Trail.findAll", Trail.class);
        return q.getResultList();
    }

    public Trail findTrailById(Long id) {
//        TypedQuery<Trail> q = em.createNamedQuery("Trail.findById", Trail.class);
//        q.setParameter("id", id);
//        return q.getResultList().get(0);
        return em.find(Trail.class, id);
    }

    public List<Trail> findTrailsByType(TrailType type) {
        TypedQuery<Trail> q = em.createNamedQuery("Trail.findByType", Trail.class);
        q.setParameter("type", type);
        return q.getResultList();
    }

    public List<Trail> findTrailsByUser(User u) {
        TypedQuery<Trail> q = em.createNamedQuery("Trail.findByUser", Trail.class);
        q.setParameter("user", u);
        return q.getResultList();
    }

    public void updateTrail(Trail t) {
        em.merge(t);
    }

    public void deleteTrail(Trail t) {
        Trail newt = em.merge(t);
        em.remove(newt);
    }
}
