/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;


public class Model {

    private int id;
    private String adi;
    private String renk;
    private Marka marka;
    private Fiyat fiyat;
    private Kasa kasa;
    private Motor motor;
    private Vites vites;
    private Yakit yakit;
    private Yil yil;
    private ArrayList<Aciklama> aciklama;

    public Model() {
        aciklama = new ArrayList<>();
    }

    public Model(String adi, String renk, Marka marka, Fiyat fiyat, Kasa kasa, Motor motor, Vites vites, Yil yil,Yakit yakit, ArrayList<Aciklama> aciklama) {
        this.adi = adi;
        this.renk = renk;
        this.marka = marka;
        this.fiyat = fiyat;
        this.kasa = kasa;
        this.motor = motor;
        this.vites = vites;
        this.yil = yil;
        this.yakit = yakit;
        if (aciklama != null) {
            this.aciklama = aciklama;
        } else {
            this.aciklama = new ArrayList<>();
        }
    }

    public Model(int id, String adi, String renk, Marka marka, Fiyat fiyat, Kasa kasa, Motor motor, Vites vites, Yil yil,Yakit yakit, ArrayList<Aciklama> aciklama) {
        this.id = id;
        this.adi = adi;
        this.renk = renk;
        this.marka = marka;
        this.fiyat = fiyat;
        this.kasa = kasa;
        this.motor = motor;
        this.vites = vites;
        this.yil = yil;
        this.yakit = yakit;
        if (aciklama != null) {
            this.aciklama = aciklama;
        } else {
            this.aciklama = new ArrayList<>();
        }
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

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

    public Marka getMarka() {
        return marka;
    }

    public void setMarka(Marka marka) {
        this.marka = marka;
    }

    public Fiyat getFiyat() {
        return fiyat;
    }

    public void setFiyat(Fiyat fiyat) {
        this.fiyat = fiyat;
    }

    public Kasa getKasa() {
        return kasa;
    }

    public void setKasa(Kasa kasa) {
        this.kasa = kasa;
    }

    public Motor getMotor() {
        return motor;
    }

    public Yakit getYakit() {
        return yakit;
    }

    public void setYakit(Yakit yakit) {
        this.yakit = yakit;
    }
    

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Vites getVites() {
        return vites;
    }

    public void setVites(Vites vites) {
        this.vites = vites;
    }

    public Yil getYil() {
        return yil;
    }

    public void setYil(Yil yil) {
        this.yil = yil;
    }

    public ArrayList<Aciklama> getAciklama() {
        return aciklama;
    }

    public void setAciklama(ArrayList<Aciklama> aciklama) {
        this.aciklama = aciklama;
    }
    

    @Override
    public String toString() {
        return "Model{" + "id=" + id + ", adi=" + adi + ", renk=" + renk + ", marka=" + marka + ", fiyat=" + fiyat + ", kasa=" + kasa + ", motor=" + motor + ", vites=" + vites + ", yil=" + yil + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        final Model other = (Model) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
