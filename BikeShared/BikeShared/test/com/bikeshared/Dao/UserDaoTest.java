/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikeshared.Dao;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GARCIANO GARCIA
 */
public class UserDaoTest {
    
    public UserDaoTest() {
    }

    @Test
    public void testSomeMethod() {
        UserDao test = new UserDao();
        
        if(test.createUsuario("Novais","novais@gmail.com", "1223")){
           System.out.println("Estou vivo");
        }else{
           System.out.println("Estou morto");
        }
    }
    
}
