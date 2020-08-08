/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Motor;
import Utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MotorDAO {

    private Motor motor;
    private ArrayList motorList;

    public Motor get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from motor where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            this.motor = new Motor(rs.getInt("id"), rs.getString("adi"), rs.getInt("hacmi"),rs.getInt("gucu"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.motor;
    }

    public ArrayList<Motor> list() {
        this.motorList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from motor");
            while (rs.next()) {
                this.motorList.add(new Motor(
                        rs.getInt("id"), rs.getString("adi"), rs.getInt("hacmi"),rs.getInt("gucu")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.motorList;
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from motor");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public ArrayList<Motor> list(int page, int pageSize) {
        this.motorList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from motor order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.motorList.add(new Motor(
                        rs.getInt("id"), rs.getString("adi"), rs.getInt("hacmi"),rs.getInt("gucu")
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.motorList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from motor where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Motor a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from motor where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Motor a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update motor set adi=?,hacmi=?,gucu=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getHacmi());
            st.setInt(3, a.getGucu());
            st.setInt(4, a.getId());

            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(Motor a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into motor (adi,hacmi,gucu) values (?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getHacmi());
            st.setInt(3, a.getGucu());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
