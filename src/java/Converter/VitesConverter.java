/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.VitesDAO;
import Entity.Vites;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "vitesConverter")
public class VitesConverter implements Converter{

    private VitesDAO vitesDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getVitesDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Vites vites = (Vites) value;
        return Integer.toString(vites.getId());
    }

    public VitesDAO getVitesDao() {
        if(vitesDao==null)
            vitesDao =new VitesDAO();
        return vitesDao;
    }
    
    
}
