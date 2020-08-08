/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.KasaDAO;
import Entity.Kasa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "kasaConverter")

public class KasaConverter implements Converter{

    private KasaDAO kasaDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getKasaDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Kasa kasa = (Kasa) value;
        return Integer.toString(kasa.getId());
    }

    public KasaDAO getKasaDao() {
        if(kasaDao==null)
            kasaDao =new KasaDAO();
        return kasaDao;
    }
    
    
}
