/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.MarkaDAO;
import Entity.Marka;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "markaConverter")
public class MarkaConverter implements Converter{

    private MarkaDAO markaDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getMarkaDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Marka marka = (Marka) value;
        return Integer.toString(marka.getId());
    }

    public MarkaDAO getMarkaDao() {
        if(markaDao==null)
            markaDao =new MarkaDAO();
        return markaDao;
    }
    
    
}
