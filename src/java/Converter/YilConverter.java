/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.YilDAO;
import Entity.Yil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "yilConverter")
public class YilConverter implements Converter{

    private YilDAO yilDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getYilDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Yil yil = (Yil) value;
        return Integer.toString(yil.getId());
    }

    public YilDAO getYilDao() {
        if(yilDao==null)
            yilDao =new YilDAO();
        return yilDao;
    }
    
    
}
