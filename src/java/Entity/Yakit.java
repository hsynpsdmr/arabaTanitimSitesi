/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


public class Yakit {
    private int id;
    private String adi;
    private int tuketim;
    private int miktar;

    public Yakit() {
    }

    public Yakit(String adi, int tuketim, int miktar) {
        this.adi = adi;
        this.tuketim = tuketim;
        this.miktar = miktar;
    }

    public Yakit(int id, String adi, int tuketim, int miktar) {
        this.id = id;
        this.adi = adi;
        this.tuketim = tuketim;
        this.miktar = miktar;
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

    public int getTuketim() {
        return tuketim;
    }

    public void setTuketim(int tuketim) {
        this.tuketim = tuketim;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    @Override
    public String toString() {
        return "Yakit{" + "id=" + id + ", adi=" + adi + ", tuketim=" + tuketim + ", miktar=" + miktar + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
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
        final Yakit other = (Yakit) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
