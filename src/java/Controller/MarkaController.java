/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MarkaDAO;
import Entity.Marka;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "markaController")
@SessionScoped
public class MarkaController implements Serializable {

    private List<Marka> markaList;
    private MarkaDAO markaDao;
    private Marka marka;
    private String display = "";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public MarkaController() {
        this.markaList = new ArrayList<Marka>();
        this.markaDao = new MarkaDAO();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public ArrayList<Marka> search() {
        ArrayList<Marka> resultList = new ArrayList<Marka>();
        for (Marka marka : this.markaList) {
            if (marka.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(marka);
            }
        }

        return resultList;
    }

    public List<Marka> getaList() {
        this.markaList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.markaList = this.search();
        }
        System.out.println("|||" + this.markaList);
        return markaList;
    }

    public MarkaDAO getaDao() {
        if (this.markaDao == null) {
            this.markaDao = new MarkaDAO();
        }
        return markaDao;
    }

    public Marka getMarka() {
        if (this.marka == null) {
            this.marka = new Marka();
        }
        return marka;
    }

    public void setMarka(Marka marka) {
        this.marka = marka;
    }

    public String create() {

        this.getaDao().create(this.marka);
        clearForm();
        return "marka";
    }

    public String updateForm(Marka marka) {
        this.marka = marka;
        return "marka";
    }

    public void clearForm() {
        this.marka = new Marka();

    }

    public String update() {
        this.markaDao.update(this.marka);
        this.clearForm();
        return "marka";
    }

    public String delete(Marka marka) {
        this.marka = marka;
        return "confirm_delete";

    }

    public String delete() {
        this.marka = marka;
        this.getaDao().delete(marka.getId());
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
