/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Yakit;
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
public class YakitDAO {

    private Yakit yakit;
    private ArrayList yakitList;

    public Yakit get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from yakit where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            this.yakit = new Yakit(rs.getInt("id"), rs.getString("adi"), rs.getInt("tuketim"),rs.getInt("miktar"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.yakit;
    }

    public ArrayList<Yakit> list() {
        this.yakitList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from yakit");
            while (rs.next()) {
                this.yakitList.add(new Yakit(
                        rs.getInt("id"), rs.getString("adi"), rs.getInt("tuketim"),rs.getInt("miktar")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.yakitList;
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from yakit");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public ArrayList<Yakit> list(int page, int pageSize) {
        this.yakitList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from yakit order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.yakitList.add(new Yakit(
                        rs.getInt("id"), rs.getString("adi"), rs.getInt("tuketim"),rs.getInt("miktar")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.yakitList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from yakit where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Yakit a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from yakit where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Yakit a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update yakit set adi=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getId());

            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(Yakit a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into yakit (adi,tuketim,miktar) values (?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getTuketim());
            st.setInt(3, a.getMiktar());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
