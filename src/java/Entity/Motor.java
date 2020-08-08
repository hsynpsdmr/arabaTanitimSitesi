/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


public class Motor {
    private int id;
    private String adi;
    private int hacmi;
    private int gucu;

    public Motor() {
    }

    public Motor(String adi, int hacmi, int gucu) {
        this.adi = adi;
        this.hacmi = hacmi;
        this.gucu = gucu;
    }

    public Motor(int id, String adi, int hacmi, int gucu) {
        this.id = id;
        this.adi = adi;
        this.hacmi = hacmi;
        this.gucu = gucu;
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

    public int getHacmi() {
        return hacmi;
    }

    public void setHacmi(int hacmi) {
        this.hacmi = hacmi;
    }

    public int getGucu() {
        return gucu;
    }

    public void setGucu(int gucu) {
        this.gucu = gucu;
    }

    

    @Override
    public String toString() {
        return "Motor{" + "id=" + id + ", adi=" + adi + ", tuketim=" + hacmi + ", miktar=" + gucu + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
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
        final Motor other = (Motor) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
   
}
