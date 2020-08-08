/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AciklamaDAO;
import Entity.Aciklama;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "aciklamaController")
@SessionScoped
public class AciklamaController implements Serializable {

    private List<Aciklama> aciklamaList;
    private AciklamaDAO aciklamaDao;
    private Aciklama aciklama;
    private String display = "";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public AciklamaController() {
        this.aciklamaList = new ArrayList<Aciklama>();
        this.aciklamaDao = new AciklamaDAO();
    }
public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
    

    public ArrayList<Aciklama> search() {
        ArrayList<Aciklama> resultList = new ArrayList<Aciklama>();
        for (Aciklama aciklama : this.aciklamaList) {
            if (aciklama.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(aciklama);
            }
        }

        return resultList;
    }

    public List<Aciklama> getaList() {
        this.aciklamaList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.aciklamaList = this.search();
        }
        System.out.println("|||" + this.aciklamaList);
        return aciklamaList;
    }

    public AciklamaDAO getaDao() {
        if (this.aciklamaDao == null) {
            this.aciklamaDao = new AciklamaDAO();
        }
        return aciklamaDao;
    }

    public Aciklama getAciklama() {
        if (this.aciklama == null) {
            this.aciklama = new Aciklama();
        }
        return aciklama;
    }

    public void setAciklama(Aciklama aciklama) {
        this.aciklama = aciklama;
    }

    public String create() {

        this.getaDao().create(this.aciklama);
        clearForm();
        return "aciklama";
    }

    public String updateForm(Aciklama aciklama) {
        this.aciklama = aciklama;
        return "aciklama";
    }

    public void clearForm() {
        this.aciklama = new Aciklama();

    }

    public String update() {
        this.aciklamaDao.update(this.aciklama);
        this.clearForm();
        return "aciklama";
    }

    public String delete(Aciklama aciklama) {
        this.aciklama = aciklama;
        return "confirm_delete";

    }

    public String delete() {
        this.getaDao().delete(this.aciklama.getId());
        
            
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
