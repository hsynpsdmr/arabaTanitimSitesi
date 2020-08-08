/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Aciklama;
import Utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author furkankykc
 */
public class AciklamaDAO {

    private Aciklama aciklama;
    private ArrayList aciklamaList;

    public Aciklama get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from aciklama where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                this.aciklama = new Aciklama(rs.getInt("id"), rs.getString("adi"), rs.getString("icerik"));
            else
                this.aciklama = null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.aciklama;
    }

    public ArrayList<Aciklama> list() {
        this.aciklamaList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from aciklama");
            while (rs.next()) {
                this.aciklamaList.add(new Aciklama(
                        rs.getInt("id"), rs.getString("adi"), rs.getString("icerik")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.aciklamaList;
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from aciklama");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }
    

    public ArrayList<Aciklama> list(int page, int pageSize) {
        this.aciklamaList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from aciklama order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.aciklamaList.add(new Aciklama(
                        rs.getInt("id"), rs.getString("adi"), rs.getString("icerik")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.aciklamaList;
    }
     public ArrayList<Aciklama> getModelAciklama(int k) {
        this.aciklamaList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from model_aciklama where model_id=" + k);
            while (rs.next()) {
                this.aciklamaList.add(this.get(rs.getInt("aciklama_id")));
                System.out.println("-----------------");
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.aciklamaList;
    }


    public void delete(int id) {
        System.out.println("=======deleted========aciklama"+id);
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from aciklama where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Aciklama a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from aciklama where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Aciklama a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update aciklama set adi=?,icerik=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setString(2, a.getIcerik());
            st.setInt(3, a.getId());

            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(Aciklama a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into aciklama (adi,icerik) values (?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setString(2, a.getIcerik());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
