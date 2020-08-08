/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

public class Marka {

    private int id;
    private String adi;
    private Mensei mensei;
    private Dosya logo;

    public Marka() {
    }

    public Marka(String adi, Mensei mensei, Dosya logo) {
        this.adi = adi;
        this.mensei = mensei;
        this.logo = logo;
    }

    public Marka(int id, String adi, Mensei mensei, Dosya logo) {
        this.id = id;
        this.adi = adi;
        this.mensei = mensei;
        this.logo = logo;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public Mensei getMensei() {
        return mensei;
    }

    public void setMensei(Mensei mensei) {
        this.mensei = mensei;
    }

    public Dosya getLogo() {
        return logo;
    }

    public void setLogo(Dosya logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Marka{" + "id=" + id + ", adi=" + adi + ", mensei=" + mensei + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Marka other = (Marka) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
