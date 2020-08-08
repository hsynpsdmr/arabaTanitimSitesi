/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.YakitDAO;
import Entity.Yakit;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "yakitConverter")
public class YakitConverter implements Converter{

    private YakitDAO yakitDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getYakitDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Yakit yakit = (Yakit) value;
        return Integer.toString(yakit.getId());
    }

    public YakitDAO getYakitDao() {
        if(yakitDao==null)
            yakitDao =new YakitDAO();
        return yakitDao;
    }
    
    
}
