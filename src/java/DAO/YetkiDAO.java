/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Yetki;
import Utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class YetkiDAO {

    private Yetki yetki;
    private ArrayList yetkiList;

    public Yetki get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from yetki where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            this.yetki = new Yetki(rs.getInt("id"), rs.getString("adi"),rs.getBoolean("admin"),rs.getBoolean("moderator"),rs.getBoolean("normal"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.yetki;
    }

    public ArrayList<Yetki> list() {
        this.yetkiList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from yetki");
            while (rs.next()) {
                this.yetkiList.add(new Yetki(
                        rs.getInt("id"), rs.getString("adi"),rs.getBoolean("admin"),rs.getBoolean("moderator"),rs.getBoolean("normal")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.yetkiList;
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from yetki");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public ArrayList<Yetki> list(int page, int pageSize) {
        this.yetkiList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from yetki order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.yetkiList.add(new Yetki(
                        rs.getInt("id"), rs.getString("adi"),rs.getBoolean("admin"),rs.getBoolean("moderator"),rs.getBoolean("normal")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.yetkiList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from yetki where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Yetki a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from yetki where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Yetki a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update yetki set adi=? admin=? moderator=? normal=?  where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setBoolean(2, a.isAdmin());
            st.setBoolean(3, a.isModerator());
            st.setBoolean(4, a.isNormal());
            st.setInt(5, a.getId());

            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(Yetki a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into yetki (adi,admin,moderator,normal) values (?,?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setBoolean(2, a.isAdmin());
            st.setBoolean(3, a.isModerator());
            st.setBoolean(4, a.isNormal());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
