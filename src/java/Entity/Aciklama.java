/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


public class Aciklama {
    private int id;
    private String adi;
    private String icerik;

    public Aciklama() {
    }

    public Aciklama(String adi, String icerik) {
        this.adi = adi;
        this.icerik = icerik;
    }

    public Aciklama(int id, String adi, String icerik) {
        this.id = id;
        this.adi = adi;
        this.icerik = icerik;
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

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    @Override
    public String toString() {
        return "Aciklama{" + "id=" + id + ", adi=" + adi + ", icerik=" + icerik + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id;
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
        final Aciklama other = (Aciklama) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
