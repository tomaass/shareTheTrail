/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import javax.faces.context.FacesContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.stream.events.Comment;

import model.Trail;
import model.TrailType;
import model.User;


import org.primefaces.model.map.LatLng;

/**
 *
 * @author tomaass
 */
@ManagedBean(name = "trail")
@RequestScoped
public class TrailManagedBean {
    @ManagedProperty(value = "#{user.user}")
    private User owner;
    @Size(min = 1, message = "Jméno musí být vyplněno")
    private String name;
    @Min(value = 0, message="Trail nesmí mít zápornou délku.") 
    private Integer delka = 0;
    private String description;
    private TrailType type;
    private List<LatLng> track;
    
    private User user;
 
    
   
    @EJB
    private TrailSessionBean trailManager;

    public List<Trail> allTrails() {
        return trailManager.getTrails();
    }

    public Trail getTrailById(Long id) {
        return trailManager.findTrailById(id);
    }

    public Long getOwnerId() {
        return owner.getId();
    }

    public TrailManagedBean() {
    }

   

    public String add(List<LatLng> trc) {
        //System.out.println("Adding trail");
        Trail t = new Trail(owner, name, type);
        t.setTrack(trc);
        t.setDescription(description);
        t.setDelka(delka);
        
        trailManager.addTrail(t);
        return "viewTrails";
    }

    public List<Trail> findByType(int stype) {

        switch (stype) {
            case 1:
                type = TrailType.XC;
                break;
            case 2:
                type = TrailType.FR;
                break;
            case 3:
                type = TrailType.DH;
                break;
            case 4:
                type = TrailType.DIRT;
                break;
            default:
                type = TrailType.FR;
        }
        return trailManager.findTrailsByType(type);
    }    

    public List<Trail> findByUser() {
        return trailManager.findTrailsByUser(owner);
    }


    public Integer getDelka() {
        return delka;
    }

    public void setDelka(Integer delka) {
        this.delka = delka;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<LatLng> getTrack() {
        return track;
    }

    public void setTrack(List<LatLng> track) {
        this.track = track;
    }

    public TrailSessionBean getTrailManager() {
        return trailManager;
    }

    public void setTrailManager(TrailSessionBean trailManager) {
        this.trailManager = trailManager;
    }

    public TrailType getType() {
        return type;
    }

    public void setType(TrailType type) {
        this.type = type;
    }
}
