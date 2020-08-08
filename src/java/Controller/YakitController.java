/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.YakitDAO;
import Entity.Yakit;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "yakitController")
@SessionScoped
public class YakitController implements Serializable {

    private List<Yakit> yakitList;
    private YakitDAO yakitDao;
    private Yakit yakit;
    private String display = "";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public YakitController() {
        this.yakitList = new ArrayList<Yakit>();
        this.yakitDao = new YakitDAO();
    }

public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
    

    public ArrayList<Yakit> search() {
        ArrayList<Yakit> resultList = new ArrayList<Yakit>();
        for (Yakit yakit : this.yakitList) {
            if (yakit.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(yakit);
            }
        }

        return resultList;
    }

    public List<Yakit> getaList() {
        this.yakitList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.yakitList = this.search();
        }
        System.out.println("|||" + this.yakitList);
        return yakitList;
    }

    public YakitDAO getaDao() {
        if (this.yakitDao == null) {
            this.yakitDao = new YakitDAO();
        }
        return yakitDao;
    }

    public Yakit getYakit() {
        if (this.yakit == null) {
            this.yakit = new Yakit();
        }
        return yakit;
    }

    public void setYakit(Yakit yakit) {
        this.yakit = yakit;
    }

    public String create() {

        this.getaDao().create(this.yakit);
        clearForm();
        return "yakit";
    }

    public String updateForm(Yakit yakit) {
        this.yakit = yakit;
        return "yakit";
    }

    public void clearForm() {
        this.yakit = new Yakit();

    }

    public String update() {
        this.yakitDao.update(this.yakit);
        this.clearForm();
        return "yakit";
    }

    public String delete(Yakit yakit) {
        this.yakit = yakit;
        return "confirm_delete";

    }

    public String delete() {
        this.yakit = yakit;
        this.getaDao().delete(yakit.getId());
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
