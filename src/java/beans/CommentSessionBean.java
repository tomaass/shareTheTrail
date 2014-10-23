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
import model.Comment;

/**
 *
 * @author tomaass
 */
@Stateless
@LocalBean
public class CommentSessionBean {

    @PersistenceContext
    private EntityManager em;
    
    public void addComent(Comment c){
        em.persist(c);
    }
    
    public List<Comment> findCommentsByTrail (Long trailId){
        TypedQuery<Comment> q = em.createNamedQuery("Comment.findByTrail", Comment.class);
        q.setParameter("id", trailId);
        return q.getResultList();
    }
}
