/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.YilDAO;
import Entity.Yil;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "yilController")
@SessionScoped
public class YilController implements Serializable {

    private List<Yil> yilList;
    private YilDAO yilDao;
    private Yil yil;
    private String display ="";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public YilController() {
        this.yilList = new ArrayList<Yil>();
        this.yilDao = new YilDAO();
    }

public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
    

    public ArrayList<Yil> search() {
        ArrayList<Yil> resultList = new ArrayList<Yil>();
        for (Yil yil : this.yilList) {
            if (yil.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(yil);
            }
        }

        return resultList;
    }

    public List<Yil> getaList() {
        this.yilList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.yilList = this.search();
        }
        System.out.println("|||" + this.yilList);
        return yilList;
    }

    public YilDAO getaDao() {
        if (this.yilDao == null) {
            this.yilDao = new YilDAO();
        }
        return yilDao;
    }

    public Yil getYil() {
        if (this.yil == null) {
            this.yil = new Yil();
        }
        return yil;
    }

    public void setYil(Yil yil) {
        this.yil = yil;
    }

    public String create() {

        this.getaDao().create(this.yil);
        clearForm();
        return "yil";
    }

    public String updateForm(Yil yil) {
        this.yil = yil;
        return "yil";
    }

    public void clearForm() {
        this.yil = new Yil();

    }

    public String update() {
        this.yilDao.update(this.yil);
        this.clearForm();
        return "yil";
    }

    public String delete(Yil yil) {
        this.yil = yil;
        return "confirm_delete";

    }

    public String delete() {
        this.yil = yil;
        this.getaDao().delete(yil.getId());
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
