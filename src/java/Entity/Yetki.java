/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


public class Yetki {
    private int id;
    private String adi;
    private boolean admin;
    private boolean moderator;
    private boolean normal;
    private boolean delete;

    public Yetki() {
    }

    public Yetki(String adi, boolean admin, boolean moderator, boolean normal) {
        this.adi = adi;
        this.admin = admin;
        this.moderator = moderator;
        this.normal = normal;
    }

    public Yetki(int id, String adi, boolean admin, boolean moderator, boolean normal) {
        this.id = id;
        this.adi = adi;
        this.admin = admin;
        this.moderator = moderator;
        this.normal = normal;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isModerator() {
        return moderator;
    }

    public void setModerator(boolean moderator) {
        this.moderator = moderator;
    }

    public boolean isNormal() {
        return normal;
    }

    public void setNormal(boolean normal) {
        this.normal = normal;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "Yetki{" + "id=" + id + ", adi=" + adi + ", create=" + admin + ", read=" + moderator + ", update=" + normal + ", delete=" + delete + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
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
        final Yetki other = (Yetki) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
