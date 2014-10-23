/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author tomaass
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Trail.findAll", query = "SELECT t FROM Trail t"),
    @NamedQuery(name = "Trail.findById", query = "SELECT t FROM Trail t WHERE t.id = :id"),
    @NamedQuery(name = "Trail.findByType", query="SELECT t FROM Trail t WHERE t.type = :type"),
    @NamedQuery(name = "Trail.findByUser",query="SELECT t FROM Trail t WHERE t.owner = :user")
})
public class Trail implements Serializable {
    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @OneToOne
    User owner;
    @NotNull
    private String name;    
    private Integer delka = 0;
    private String description;
    @NotNull
    private TrailType type;
    
    private List<LatLng> track;
    
    
    

    public Trail() {
    }

    public Trail(User owner, String name, TrailType type) {
        this.owner = owner;
        this.name = name;
        this.type = type;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LatLng> getTrack() {
        return track;
    }

    public void setTrack(List<LatLng> track) {
        this.track = track;
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

   

    public TrailType getType() {
        return type;
    }

    public void setType(TrailType type) {
        this.type = type;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trail)) {
            return false;
        }
        Trail other = (Trail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Trail[ id=" + id + " ]";
    }
}
