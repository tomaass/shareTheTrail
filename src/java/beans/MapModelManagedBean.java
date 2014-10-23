/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;


import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;
import model.Trail;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Polyline;

/**
 *
 * @author tomaass
 */
@ManagedBean
@ViewScoped
public class MapModelManagedBean implements Serializable {

    private MapModel mapModel;
    private Polyline polyline;
    private Integer zoom;
    private LatLng center;
//    @EJB
//    private TrailSessionBean trailManager;

    public MapModelManagedBean() {

        mapModel = new DefaultMapModel();
        polyline = new Polyline();


        polyline.setStrokeWeight(10);
        polyline.setStrokeColor("#FF9900");
        polyline.setStrokeOpacity(0.7);

        zoom = 13;
        center = new LatLng(50.087485, 14.416498);
        
        

        mapModel.addOverlay(polyline);



    }

    public String getCenter() {
        return (center.getLat() + "," + center.getLng());
    }

    public void setCenter(LatLng center) {
        this.center = center;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public void delLastPoint() {
        if (polyline.getPaths().size() > 0) {
            polyline.getPaths().remove(polyline.getPaths().size() - 1);
            mapModel = new DefaultMapModel();
            mapModel.addOverlay(polyline);
        }
    }

    public MapModel getMapModel() {

        return mapModel;
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
        //addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));
        
        polyline.getPaths().add(latlng);
        mapModel = new DefaultMapModel();
        mapModel.addOverlay(polyline);
        setCenter(polyline.getPaths().get(0));

    }

    public Polyline getPolyline() {
        return polyline;
    }

    public MapModel findMapModel(Trail t) {
        polyline.setPaths(t.getTrack());
        mapModel = new DefaultMapModel();
        mapModel.addOverlay(polyline);
        center = t.getTrack().get(0);
        zoom = 15;
        if (!polyline.getPaths().isEmpty())
        setCenter(polyline.getPaths().get(0));
        return mapModel;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public MapModelManagedBean(MapModel mapModel) {
        this.mapModel = mapModel;
    }
}
