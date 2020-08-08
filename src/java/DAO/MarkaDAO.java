/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Marka;
import Utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MarkaDAO {

    private Marka marka;
    private ArrayList markaList;
    private MenseiDAO menseiDao;
    private DosyaDAO dosyaDao;

    public MenseiDAO getMenseiDao() {
        if (menseiDao == null) {
            menseiDao = new MenseiDAO();
        }
        return menseiDao;
    }

    public DosyaDAO getDosyaDao() {
        if (dosyaDao == null) {
            dosyaDao = new DosyaDAO();

        }
        return dosyaDao;
    }

    public Marka get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from marka where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.marka = new Marka(rs.getInt("id"), rs.getString("adi"), this.getMenseiDao().get(rs.getInt("mensei_id")),this.getDosyaDao().get(rs.getInt("logo_id")));
            } else {
                this.marka = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.marka;
    }

    public ArrayList<Marka> list() {
        this.markaList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from marka");
            while (rs.next()) {
                this.markaList.add(new Marka(
                        rs.getInt("id"), rs.getString("adi"), this.getMenseiDao().get(rs.getInt("mensei_id")),this.getDosyaDao().get(rs.getInt("logo_id"))
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.markaList;
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from marka");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public ArrayList<Marka> list(int page, int pageSize) {
        this.markaList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from marka order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.markaList.add(new Marka(
                        rs.getInt("id"), rs.getString("adi"), this.getMenseiDao().get(rs.getInt("mensei_id")),this.getDosyaDao().get(rs.getInt("logo_id"))
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.markaList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from marka where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Marka a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from marka where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Marka a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update marka set adi=?, mensei_id=?, logo_id=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getMensei().getId());
            st.setInt(3, a.getLogo().getId());
            st.setInt(4, a.getId());

            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(Marka a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into marka (adi,mensei_id,logo_id) values (?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getMensei().getId());
            st.setInt(3, a.getLogo().getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
