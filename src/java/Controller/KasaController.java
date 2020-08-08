/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.KasaDAO;
import Entity.Kasa;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "kasaController")
@SessionScoped
public class KasaController implements Serializable {

    private List<Kasa> kasaList;
    private KasaDAO kasaDao;
    private Kasa kasa;
    private String display = "";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public KasaController() {
        this.kasaList = new ArrayList<Kasa>();
        this.kasaDao = new KasaDAO();
    }
public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
    

    public ArrayList<Kasa> search() {
        ArrayList<Kasa> resultList = new ArrayList<Kasa>();
        for (Kasa kasa : this.kasaList) {
            if (kasa.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(kasa);
            }
        }

        return resultList;
    }

    public List<Kasa> getaList() {
        this.kasaList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.kasaList = this.search();
        }
        System.out.println("|||" + this.kasaList);
        return kasaList;
    }

    public KasaDAO getaDao() {
        if (this.kasaDao == null) {
            this.kasaDao = new KasaDAO();
        }
        return kasaDao;
    }

    public Kasa getKasa() {
        if (this.kasa == null) {
            this.kasa = new Kasa();
        }
        return kasa;
    }

    public void setKasa(Kasa kasa) {
        this.kasa = kasa;
    }

    public String create() {

        this.getaDao().create(this.kasa);
        clearForm();
        return "kasa";
    }

    public String updateForm(Kasa kasa) {
        this.kasa = kasa;
        return "kasa";
    }

    public void clearForm() {
        this.kasa = new Kasa();

    }

    public String update() {
        this.kasaDao.update(this.kasa);
        this.clearForm();
        return "kasa";
    }

    public String delete(Kasa kasa) {
        this.kasa = kasa;
        return "confirm_delete";

    }

    public String delete() {
        this.kasa = kasa;
        this.getaDao().delete(kasa.getId());
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
