/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MotorDAO;
import Entity.Motor;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "motorController")
@SessionScoped
public class MotorController implements Serializable {

    private List<Motor> motorList;
    private MotorDAO motorDao;
    private Motor motor;
    private String display = "";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public MotorController() {
        this.motorList = new ArrayList<Motor>();
        this.motorDao = new MotorDAO();
    }

public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
    

    public ArrayList<Motor> search() {
        ArrayList<Motor> resultList = new ArrayList<Motor>();
        for (Motor motor : this.motorList) {
            if (motor.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(motor);
            }
        }

        return resultList;
    }

    public List<Motor> getaList() {
        this.motorList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.motorList = this.search();
        }
        System.out.println("|||" + this.motorList);
        return motorList;
    }
    public MotorDAO getaDao() {
        if (this.motorDao == null) {
            this.motorDao = new MotorDAO();
        }
        return motorDao;
    }

    public Motor getMotor() {
        if (this.motor == null) {
            this.motor = new Motor();
        }
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public String create() {

        this.getaDao().create(this.motor);
        clearForm();
        return "motor";
    }

    public String updateForm(Motor motor) {
        this.motor = motor;
        return "motor";
    }

    public void clearForm() {
        this.motor = new Motor();

    }

    public String update() {
        this.motorDao.update(this.motor);
        this.clearForm();
        return "motor";
    }

    public String delete(Motor motor) {
        this.motor = motor;
        return "confirm_delete";

    }

    public String delete() {
        this.motor = motor;
        this.getaDao().delete(motor.getId());
        clearForm();
        return "list";

    }

    public void next() {
        if (page < pageCount) {
            this.page++;
        }
    }

    public void previous() {
        if (page > 1) {
            this.page--;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        try {
            this.pageCount = (int) Math.ceil(this.getaDao().count() / (double) this.pageSize);
        } catch (Exception e) {
            return 1;
        }

        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
