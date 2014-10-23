/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import model.Comment;
import model.Trail;
import model.User;

/**
 *
 * @author tomaass
 */
@ManagedBean
@RequestScoped
public class CommentManagedBean {

    @EJB
    private CommentSessionBean commentManager;
    
    @NotNull
    private String text;

    
    public void addComment(User u, Trail t){
        Date date = new Date();
        Comment c = new Comment(date, u, text, t);
        commentManager.addComent(c);
        text = "";
    }
    
    public List<Comment> findComments(Long trailId){
        return commentManager.findCommentsByTrail(trailId);
    }

    

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    
    
    
    
    public CommentManagedBean() {
    }
}
