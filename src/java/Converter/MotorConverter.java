/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.MotorDAO;
import Entity.Motor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "motorConverter")
public class MotorConverter implements Converter{

    private MotorDAO motorDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getMotorDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Motor motor = (Motor) value;
        return Integer.toString(motor.getId());
    }

    public MotorDAO getMotorDao() {
        if(motorDao==null)
            motorDao =new MotorDAO();
        return motorDao;
    }
    
    
}
