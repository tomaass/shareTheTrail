/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import model.Trail;
import model.TrailType;
import model.User;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author tomaass
 */
@ManagedBean(name="editTrail")
@ViewScoped
public class EditTrailManageBean implements Serializable {
    
    @ManagedProperty(value = "#{user.user}")
    private User owner;
    @Size(min = 1, message = "Jméno musí být vyplněno")
    private String name;
    @Min(value = 0, message="Trail nesmí mít zápornou délku.") 
    private Integer delka = 0;
    private String description;
    private TrailType type;
    
    
    private Trail trail;
    
    @EJB
    private TrailSessionBean trailManager;

   
    public EditTrailManageBean() {
    }
     @PostConstruct
    public void postTrailManage() {
       if (!FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().isEmpty()) {
            Long trailId = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("trailId"));
            trail = trailManager.findTrailById(trailId);
            
            name = trail.getName();
            delka = trail.getDelka();
            description = trail.getDescription();
            type = trail.getType();
            
        }
    }

    public Trail getTrail() {
        return trail;
    }

    public void setTrail(Trail trail) {
        this.trail = trail;
    }
     
     

    public Integer getDelka() {
        return delka;
    }

    public void setDelka(Integer delka) {
        this.delka = delka;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    

    public TrailType getType() {
        return type;
    }

    public void setType(TrailType type) {
        this.type = type;
    }
    
    public void save(List<LatLng> track){
        trail.setDelka(delka);
        trail.setDescription(description);
        trail.setName(name);
        trail.setTrack(track);
        trail.setType(type);
        trailManager.updateTrail(trail);
    }
    
    public String deleteTrail (){
        trailManager.deleteTrail(trail);
        return "editTrails.xhtml";
    }
     
     
}
