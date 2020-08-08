/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.MenseiDAO;
import Entity.Mensei;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "menseiConverter")

public class MenseiConverter implements Converter{

    private MenseiDAO menseiDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getMenseiDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Mensei mensei = (Mensei) value;
        return Integer.toString(mensei.getId());
    }

    public MenseiDAO getMenseiDao() {
        if(menseiDao==null)
            menseiDao =new MenseiDAO();
        return menseiDao;
    }
    
    
}
