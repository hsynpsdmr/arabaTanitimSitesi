/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.AciklamaDAO;
import Entity.Aciklama;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "aciklamaConverter")
public class AciklamaConverter implements Converter{

    private AciklamaDAO aciklamaDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getAciklamaDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Aciklama aciklama = (Aciklama) value;
        return Integer.toString(aciklama.getId());
    }

    public AciklamaDAO getAciklamaDao() {
        if(aciklamaDao==null)
            aciklamaDao =new AciklamaDAO();
        return aciklamaDao;
    }
    
    
}
