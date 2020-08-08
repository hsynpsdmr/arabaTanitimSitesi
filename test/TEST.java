/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author furkankykc
 */
import DAO.*;
import Entity.*;

public class TEST {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        Yetki yetki = new Yetki("admin", true, true, true);
        yetki.setId(1);
        User user = new User("root@gmail.com", "1234", yetki);
        YetkiDAO yetkiDAO = new YetkiDAO();
        yetkiDAO.create(yetki);
        userDAO.createWithYetki(user);
    }
}
