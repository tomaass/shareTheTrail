/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.User;

/**
 *
 * @author tomaass
 */
@Stateless
@LocalBean
public class UserSessionBean {
    @PersistenceContext
    private EntityManager em;
    
    public Long addUser (User u){
        em.persist(u);
        return findUserByEmail(u.getEmail()).getId();
    }
    
    public User findUserByEmail (String email){
        TypedQuery<User> q = em.createNamedQuery("User.FindByEmail", User.class);
        q.setParameter("email", email);
        return q.getResultList().get(0);
        //TODO vyjimku pro neplatne jmeno
    }
    
    public User findUserById (Long id){
        return em.find(User.class, id);
    }
    
    public void updateUser (User u){
        em.merge(u);
    }

}
