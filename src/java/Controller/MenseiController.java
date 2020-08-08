/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MenseiDAO;
import Entity.Mensei;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "menseiController")
@SessionScoped
public class MenseiController implements Serializable {

    private List<Mensei> menseiList;
    private MenseiDAO menseiDao;
    private Mensei mensei;
    private String display = "";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public MenseiController() {
        this.menseiList = new ArrayList<Mensei>();
        this.menseiDao = new MenseiDAO();
    }
public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
    

    public ArrayList<Mensei> search() {
        ArrayList<Mensei> resultList = new ArrayList<Mensei>();
        for (Mensei mensei : this.menseiList) {
            if (mensei.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(mensei);
            }
        }

        return resultList;
    }

    public List<Mensei> getaList() {
        this.menseiList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.menseiList = this.search();
        }
        System.out.println("|||" + this.menseiList);
        return menseiList;
    }
    public MenseiDAO getaDao() {
        if (this.menseiDao == null) {
            this.menseiDao = new MenseiDAO();
        }
        return menseiDao;
    }

    public Mensei getMensei() {
        if (this.mensei == null) {
            this.mensei = new Mensei();
        }
        return mensei;
    }

    public void setMensei(Mensei mensei) {
        this.mensei = mensei;
    }

    public String create() {

        this.getaDao().create(this.mensei);
        clearForm();
        return "mensei";
    }

    public String updateForm(Mensei mensei) {
        this.mensei = mensei;
        return "mensei";
    }

    public void clearForm() {
        this.mensei = new Mensei();

    }

    public String update() {
        this.menseiDao.update(this.mensei);
        this.clearForm();
        return "mensei";
    }

    public String delete(Mensei mensei) {
        this.mensei = mensei;
        return "confirm_delete";

    }

    public String delete() {
        this.mensei = mensei;
        this.getaDao().delete(mensei.getId());
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
