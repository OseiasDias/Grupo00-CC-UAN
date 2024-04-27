/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikeshared.utils;
import java.sql.DriverManager;
import java.sql.Connection;

public class Conexao {
    private static final Connection instance = null;
    
    public static Connection getInstance() throws Exception
    {
        if(instance==null)
        {
            try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                   return DriverManager.getConnection("jdbc:mysql://localhost:3306/bikeshared","starmen","");
            }catch(Exception e)
            {
                throw new Exception("Error on Database:"+e.getMessage());
            }
        }
        return instance;
    }
}
