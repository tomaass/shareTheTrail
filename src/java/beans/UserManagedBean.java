/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import model.User;

/**
 *
 * @author tomaass
 */ 
@ManagedBean (name="user")
@SessionScoped
public class UserManagedBean implements Serializable {
    @Pattern(regexp=".+@.+\\.[a-z]+", message="Neplatný email")
    private String email;
    @Size(min=6,message="heslo musí mít alespoň šest znaků")
    private String password;
    private String name;
    private String newpassword;
    
    private Boolean logged;
    //private Long id;
    
    
    private User user;
    
    @EJB
    private UserSessionBean userManager;

   
    

    public Boolean getLogged() {
        return logged;
    }
    
    

    public String getEmail() {  
        return email;
    }
    
    public void signOut(){
        //email = "";
        name = "";
        password = "";
        user = new User();
        logged = false;
        
    }
    
    public void signIn(){
        try{
        user = userManager.findUserByEmail(email);
        //System.out.print("Nalezen user: "+user.toString());
        if(user.getPassword().equals(password)){
            name = user.getName();
            logged = true;
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Byl jste přihlášen"));            
        }else{
           FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Nesprávné heslo")); 
        }
        
    } catch(Exception e){
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Email nenalezen"));
    }
    }
    public String addUser(){
        user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);        
        logged = true;
        try{
        user.setId( userManager.addUser(user));
        }catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Uživatel s tímto emailem již existuje"));
        }
        return "";
    }
    
    public String editUser(){
       user.setEmail(email);
       user.setName(name);
       user.setPassword(password);
       userManager.updateUser(user);
       return "index.xhtml";       
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }  

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    /** Creates a new instance of UserManagedBean */
    public UserManagedBean() {
        logged = false;
    }
}
