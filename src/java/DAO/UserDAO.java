/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.User;
import Utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class UserDAO {

    private User user;
    private ArrayList userList;
    private YetkiDAO yetkiDao;

    public YetkiDAO getYetkiDao() {
        if(yetkiDao==null)
            yetkiDao = new YetkiDAO();
        return yetkiDao;
    }
    
    public User get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from user where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            this.user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),this.yetkiDao.get(rs.getInt("yetki_id")));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.user;
    }
  public User get(String username) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from user where username=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();
            this.user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),this.getYetkiDao().get(rs.getInt("yetki_id")));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.user;
    }
    public ArrayList<User> list() {
        this.userList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from user");
            while (rs.next()) {
                this.userList.add(new User(
                        rs.getInt("id"), rs.getString("username"), rs.getString("password"),this.getYetkiDao().get(rs.getInt("yetki_id"))
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.userList;
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from user");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public ArrayList<User> list(int page, int pageSize) {
        this.userList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from user order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.userList.add(new User(
                        rs.getInt("id"), rs.getString("username"), rs.getString("password"),this.getYetkiDao().get(rs.getInt("yetki_id"))
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.userList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from user where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(User a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from user where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(User a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update user set username=?,password=?,yetki_id=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getUsername());
            st.setString(2, a.getPassword());
            st.setInt(3,a.getYetki().getId());
            st.setInt(4, a.getId());

            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void createWithYetki(User a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into user (username,password,yetki_id) values (?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getUsername());
            st.setString(2, a.getPassword());
            st.setInt(3, a.getYetki().getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

        public void create(User a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into user (username,password,yetki_id) values (?,?,2)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getUsername());
            st.setString(2, a.getPassword());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
