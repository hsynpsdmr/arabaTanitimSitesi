/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.FiyatDAO;
import Entity.Fiyat;
import Entity.Fiyat;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "fiyatController")
@SessionScoped
public class FiyatController implements Serializable {

    private List<Fiyat> fiyatList;
    private FiyatDAO fiyatDao;
    private Fiyat fiyat;
    private String display = "";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public FiyatController() {
        this.fiyatList = new ArrayList<Fiyat>();
        this.fiyatDao = new FiyatDAO();
    }
public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
    

    public ArrayList<Fiyat> search() {
        ArrayList<Fiyat> resultList = new ArrayList<Fiyat>();
        for (Fiyat fiyat : this.fiyatList) {
            if (fiyat.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(fiyat);
            }
        }

        return resultList;
    }

    public List<Fiyat> getaList() {
        this.fiyatList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.fiyatList = this.search();
        }
        System.out.println("|||" + this.fiyatList);
        return fiyatList;
    }

    public FiyatDAO getaDao() {
        if (this.fiyatDao == null) {
            this.fiyatDao = new FiyatDAO();
        }
        return fiyatDao;
    }

    public Fiyat getFiyat() {
        if (this.fiyat == null) {
            this.fiyat = new Fiyat();
        }
        return fiyat;
    }

    public void setFiyat(Fiyat fiyat) {
        this.fiyat = fiyat;
    }

    public String create() {

        this.getaDao().create(this.fiyat);
        clearForm();
        return "fiyat";
    }

    public String updateForm(Fiyat fiyat) {
        this.fiyat = fiyat;
        return "fiyat";
    }

    public void clearForm() {
        this.fiyat = new Fiyat();

    }

    public String update() {
        this.fiyatDao.update(this.fiyat);
        this.clearForm();
        return "fiyat";
    }

    public String delete(Fiyat fiyat) {
        this.fiyat = fiyat;
        return "confirm_delete";

    }

    public String delete() {
        this.fiyat = fiyat;
        this.getaDao().delete(fiyat.getId());
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
