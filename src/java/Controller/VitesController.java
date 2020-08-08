/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.VitesDAO;
import Entity.Vites;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "vitesController")
@SessionScoped
public class VitesController implements Serializable {

    private List<Vites> vitesList;
    private VitesDAO vitesDao;
    private Vites vites;
    private String display = "";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public VitesController() {
        this.vitesList = new ArrayList<Vites>();
        this.vitesDao = new VitesDAO();
    }

public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
    

    public ArrayList<Vites> search() {
        ArrayList<Vites> resultList = new ArrayList<Vites>();
        for (Vites vites : this.vitesList) {
            if (vites.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(vites);
            }
        }

        return resultList;
    }

    public List<Vites> getaList() {
        this.vitesList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.vitesList = this.search();
        }
        System.out.println("|||" + this.vitesList);
        return vitesList;
    }

    public VitesDAO getaDao() {
        if (this.vitesDao == null) {
            this.vitesDao = new VitesDAO();
        }
        return vitesDao;
    }

    public Vites getVites() {
        if (this.vites == null) {
            this.vites = new Vites();
        }
        return vites;
    }

    public void setVites(Vites vites) {
        this.vites = vites;
    }

    public String create() {

        this.getaDao().create(this.vites);
        clearForm();
        return "vites";
    }

    public String updateForm(Vites vites) {
        this.vites = vites;
        return "vites";
    }

    public void clearForm() {
        this.vites = new Vites();

    }

    public String update() {
        this.vitesDao.update(this.vites);
        this.clearForm();
        return "vites";
    }

    public String delete(Vites vites) {
        this.vites = vites;
        return "confirm_delete";

    }

    public String delete() {
        this.vites = vites;
        this.getaDao().delete(vites.getId());
        clearForm();
        return "vites";

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
