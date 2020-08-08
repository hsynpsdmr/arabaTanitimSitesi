/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.FiyatDAO;
import Entity.Fiyat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "fiyatConverter")
public class FiyatConverter implements Converter{

    private FiyatDAO fiyatDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getFiyatDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Fiyat fiyat = (Fiyat) value;
        return Integer.toString(fiyat.getId());
    }

    public FiyatDAO getFiyatDao() {
        if(fiyatDao==null)
            fiyatDao =new FiyatDAO();
        return fiyatDao;
    }
    
    
}
