/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Entity.Dosya;
import Utility.ConnectionManager;


public class DosyaDAO {

    private Dosya dosya = null;
    private ArrayList<Dosya> dosyaList = null;

    public Dosya get(int id) {
        Connection con = ConnectionManager.getConnection();

        try {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from dosya where id=" + id);
            rs.next();
            this.dosya = new Dosya(rs.getInt("id"), rs.getString("adi"), rs.getString("dosya_yolu"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.dosya;
    }

    public ArrayList<Dosya> list() {
        this.dosyaList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from dosya");
            while (rs.next()) {
                this.dosyaList.add(new Dosya(rs.getInt("id"), rs.getString("adi"), rs.getString("dosya_yolu")));
                System.out.println("-----------------");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.dosyaList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            st.executeUpdate("delete from dosya where id=" + id);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Dosya a) {
        Connection con = ConnectionManager.getConnection();

        try {
            Statement st = con.createStatement();
            st.executeUpdate("update dosya set adi='" + a.getAdi() + "', dosya_yolu='" + a.getDosyaYolu() + "' where id=" + a.getId());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Dosya a) {
        Connection con = ConnectionManager.getConnection();
        int kid = 0;
        try {
            Statement st = con.createStatement();
            st.executeUpdate("insert into dosya (adi,dosya_yolu) values ('" + a.getAdi() + "','" + a.getDosyaYolu() + "')", Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                kid = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return kid;
    }
  public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from dosya");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }
    public ArrayList<Dosya> list(int page, int pageSize) {
        this.dosyaList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from dosya order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.dosyaList.add(new Dosya(
                        rs.getInt("id"), rs.getString("adi"), rs.getString("dosya_yolu")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.dosyaList;
    }

}
