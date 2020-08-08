/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Aciklama;
import Entity.Model;
import Utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ModelDAO {

    private Model model;
    private ArrayList modelList;
    private MotorDAO motorDao;
    private VitesDAO vitesDao;
    private YakitDAO yakitDao;
    private YilDAO yilDao;
    private FiyatDAO fiyatDao;
    private KasaDAO kasaDao;
    private MarkaDAO markaDao;
    private AciklamaDAO aciklamaDao;

    public AciklamaDAO getAciklamaDao() {
        if(aciklamaDao==null)
            aciklamaDao=new AciklamaDAO();
        return aciklamaDao;
    }

    
    public MarkaDAO getMarkaDao() {
         if(markaDao==null)
            markaDao=new MarkaDAO();
        return markaDao;
    }
    

    public VitesDAO getVitesDao() {
        if(vitesDao==null)
            vitesDao=new VitesDAO();
        return vitesDao;
    }

    public MotorDAO getMotorDao() {
         if(motorDao==null)
            motorDao=new MotorDAO();
        return motorDao;
    }

    public YakitDAO getYakitDao() {
         if(yakitDao==null)
            yakitDao=new YakitDAO();
        return yakitDao;
    }

    public YilDAO getYilDao() {
         if(yilDao==null)
            yilDao=new YilDAO();
        return yilDao;
    }

    public FiyatDAO getFiyatDao() {
         if(fiyatDao==null)
            fiyatDao=new FiyatDAO();
        return fiyatDao;
    }

    public KasaDAO getKasaDao() {
         if(kasaDao==null)
            kasaDao=new KasaDAO();
        return kasaDao;
    }
    
    
    public Model get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from model where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            this.model = new Model(rs.getInt("id"), rs.getString("adi"),rs.getString("renk")
                    ,this.getMarkaDao().get(rs.getInt("marka_id"))
                    ,this.getFiyatDao().get(rs.getInt("fiyat_id"))
                    ,this.getKasaDao().get(rs.getInt("kasa_id"))
                    ,this.getMotorDao().get(rs.getInt("motor_id"))
                    ,this.getVitesDao().get(rs.getInt("vites_id"))
                    ,this.getYilDao().get(rs.getInt("yil_id"))
                    ,this.getYakitDao().get(rs.getInt("yakit_id"))
                    ,this.getAciklamaDao().getModelAciklama(rs.getInt("id"))
            );
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.model;
    }

    public ArrayList<Model> list() {
        this.modelList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from model");
            while (rs.next()) {
                this.modelList.add(new Model(
                     rs.getInt("id")
                    ,rs.getString("adi")
                    ,rs.getString("renk")
                    ,this.getMarkaDao().get(rs.getInt("marka_id"))
                    ,this.getFiyatDao().get(rs.getInt("fiyat_id"))
                    ,this.getKasaDao().get(rs.getInt("kasa_id"))
                    ,this.getMotorDao().get(rs.getInt("motor_id"))
                    ,this.getVitesDao().get(rs.getInt("vites_id"))
                    ,this.getYilDao().get(rs.getInt("yil_id"))
                    ,this.getYakitDao().get(rs.getInt("yakit_id"))
                    ,this.getAciklamaDao().getModelAciklama(rs.getInt("id"))
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.modelList;
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from model");
            rs.next();
            count = rs.getInt("a_count");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public ArrayList<Model> list(int page, int pageSize) {
        this.modelList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from model order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.modelList.add(new Model(
                    rs.getInt("id")
                    ,rs.getString("adi")
                    ,rs.getString("renk")
                    ,this.getMarkaDao().get(rs.getInt("marka_id"))
                    ,this.getFiyatDao().get(rs.getInt("fiyat_id"))
                    ,this.getKasaDao().get(rs.getInt("kasa_id"))
                    ,this.getMotorDao().get(rs.getInt("motor_id"))
                    ,this.getVitesDao().get(rs.getInt("vites_id"))
                    ,this.getYilDao().get(rs.getInt("yil_id"))
                    ,this.getYakitDao().get(rs.getInt("yakit_id"))
                    ,this.getAciklamaDao().getModelAciklama(rs.getInt("id"))
                ));
                System.out.println("-----------------");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.modelList;
    }
    

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from model where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Model a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from model where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Model a) {
        Connection con = ConnectionManager.getConnection();

        
        String sql = "update model set adi=?, renk=?, marka_id=?, fiyat_id=?, kasa_id=?, motor_id=?, vites_id=?, yil_id=?, yakit_id=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setString(2, a.getRenk());
            st.setInt(3, a.getMarka().getId());
            st.setInt(4, a.getFiyat().getId());
            st.setInt(5, a.getKasa().getId());
            st.setInt(6, a.getMotor().getId());
            st.setInt(7, a.getVites().getId());
            st.setInt(8, a.getYil().getId());
            st.setInt(9, a.getYakit().getId());
            st.setInt(10, a.getId());

            st.executeUpdate();
            st.executeUpdate("delete from model_aciklama where model_id=" + a.getId());
            for (Aciklama g : a.getAciklama()) {
                Statement st2 = con.createStatement();
                st2.executeUpdate("insert into model_aciklama(model_id,aciklama_id) values(" + a.getId() + ",'" + g.getId() + "')");

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Model a) {
        Connection con = ConnectionManager.getConnection();
        System.out.println("DAO.ModelDAO.create()"+a);
        String sql = "insert into model (adi,renk,marka_id,fiyat_id,kasa_id,motor_id,vites_id,yil_id,yakit_id) values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getAdi());
            st.setString(2, a.getRenk());
            st.setInt(3, a.getMarka().getId());
            st.setInt(4, a.getFiyat().getId());
            st.setInt(5, a.getKasa().getId());
            st.setInt(6, a.getMotor().getId());
            st.setInt(7, a.getVites().getId());
            st.setInt(8, a.getYil().getId());
            st.setInt(9, a.getYakit().getId());
            st.executeUpdate();
            int kid = 0;
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                kid = rs.getInt(1);

            }
            for (Aciklama g : a.getAciklama()) {
                Statement st2 = con.createStatement();
                st2.executeUpdate("insert into model_aciklama(model_id,aciklama_id) values(+" + kid + ",'" + g.getId() + "')");

            }
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    return (generatedKeys.getInt(1));
                }

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

}
