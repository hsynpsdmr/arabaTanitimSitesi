/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ModelDAO;
import Entity.Model;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "modelController")
@SessionScoped
public class ModelController implements Serializable {

    private List<Model> modelList;
    private ModelDAO modelDao;
    private Model model;
    private String display = "";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public ModelController() {
        this.modelList = new ArrayList<Model>();
        this.modelDao = new ModelDAO();
    }

public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
    

    public ArrayList<Model> search() {
        ArrayList<Model> resultList = new ArrayList<Model>();
        for (Model model : this.modelList) {
            if (model.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(model);
            }
        }

        return resultList;
    }

    public List<Model> getaList() {
        this.modelList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.modelList = this.search();
        }
        System.out.println("|||" + this.modelList);
        return modelList;
    }
    public ModelDAO getaDao() {
        if (this.modelDao == null) {
            this.modelDao = new ModelDAO();
        }
        return modelDao;
    }

    public Model getModel() {
        if (this.model == null) {
            this.model = new Model();
        }
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String create() {

        this.getaDao().create(this.model);
        System.out.println("\\||// == "+this.model.getAciklama());
        clearForm();
        return "model";
    }

    public String updateForm(Model model) {
        this.model = model;
        return "model";
    }

    public void clearForm() {
        this.model = new Model();

    }

    public String update() {
        this.modelDao.update(this.model);
        this.clearForm();
        return "model";
    }

    public String delete(Model model) {
        this.model = model;
        return "confirm_delete";

    }

    public String delete() {
        this.model = model;
        this.getaDao().delete(model.getId());
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
