/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template dosya, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DosyaDAO;
import Entity.Dosya;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;


@ManagedBean(name = "dosyaController")
@SessionScoped
public class DosyaController implements Serializable {

    private List<Dosya> dosyaList;
    private DosyaDAO dosyaDao;
    private Dosya dosya;
    private Part part;
    private String display = "";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public void upload() {
        try (InputStream input = part.getInputStream()) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            String yol = "/resources/images/";
            String webContentRoot = ec.getRealPath(yol);
            Path a = new java.io.File(webContentRoot, part.getSubmittedFileName()).toPath();
            try {
                Files.copy(input, a);
            } catch (Exception ex) {
            }

            this.dosya = new Dosya(part.getSubmittedFileName(), a.toAbsolutePath().toString());
            System.out.println(dosya);
            this.getaDao().create(this.dosya);
        } catch (Exception ex) {
            System.err.print(ex);
        }
    }

    public DosyaController() {
        this.dosyaList = new ArrayList<Dosya>();
        this.dosyaDao = new DosyaDAO();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
    

    public ArrayList<Dosya> search() {
        ArrayList<Dosya> resultList = new ArrayList<Dosya>();
        for (Dosya dosya : this.dosyaList) {
            if (dosya.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(dosya);
            }
        }

        return resultList;
    }

    public List<Dosya> getaList() {
        this.dosyaList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.dosyaList = this.search();
        }
        System.out.println("|||" + this.dosyaList);
        return dosyaList;
    }

    public DosyaDAO getaDao() {
        if (this.dosyaDao == null) {
            this.dosyaDao = new DosyaDAO();
        }
        return dosyaDao;
    }

    public Dosya getDosya() {
        if (this.dosya == null) {
            this.dosya = new Dosya();
        }
        return dosya;
    }

    public void setDosya(Dosya dosya) {
        this.dosya = dosya;
    }

    public String create() {

        this.getaDao().create(this.dosya);
        clearForm();
        return "dosya";
    }

    public String updateForm(Dosya dosya) {
        this.dosya = dosya;
        return "dosya";
    }

    public void clearForm() {
        this.dosya = new Dosya();

    }

    public String update() {
        this.dosyaDao.update(this.dosya);
        this.clearForm();
        return "dosya";
    }

    public String delete(Dosya dosya) {
        this.dosya = dosya;
        return "confirm_delete";

    }

    public String delete() {
        this.dosya = dosya;
        this.getaDao().delete(dosya.getId());
        clearForm();
        return "dosya";

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
