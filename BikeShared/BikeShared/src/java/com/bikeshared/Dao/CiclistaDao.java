/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikeshared.Dao;

import com.bikeshared.models.Ciclista;
import com.bikeshared.models.User;
import com.bikeshared.Dao.UserDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CiclistaDao {
    
    private Connection connection = null;

    public CiclistaDao(Connection conn) {
        this.connection = conn;
    }
    
    public int create(Ciclista ciclista)throws SQLException
    {
        int id = 0;
        Statement stmt = null;
        try{
            String query = "INSERT INTO ciclista (id_user,saldo,tem_bike) VALUES(?, ?, ?);";
            PreparedStatement pstmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, ciclista.getId());
            pstmt.setDouble(2, ciclista.getSaldo());
            pstmt.setInt(3, (ciclista.isTem_bike())? 1 : 0 ) ;
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating ciclist failed, no rows affected.");
            }
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next())
            {
                id = rs.getInt(1);
            }
            
        }catch(SQLException e){
            throw new SQLException("Error on Database:"+e.getMessage());
        }
        finally{
            this.connection.close();
        }
        return id;
    }
    
    public ArrayList<Ciclista> all()throws SQLException
    {
        Statement stmt = null;
        ArrayList<Ciclista> ciclistas = new ArrayList<>();
        Ciclista ciclista;
        try{
            String query = "SELECT C.id ,U.username,U.id as id_user, U.email, U.password, C.saldo,C.tem_bike FROM users U inner join ciclista C"
                    + " ON (U.id = C.id_user) ;";
            PreparedStatement pstmt = this.connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                ciclista = new Ciclista(rs.getString("username"),rs.getString("email"),
                                rs.getString("password"),rs.getDouble("saldo"));
                ciclista.setId(rs.getInt("id_user"));
                ciclista.setId_ciclista(rs.getInt("id"));
                ciclistas.add(ciclista);
            }
        }catch(SQLException e){
            throw new SQLException("Error on Database:"+e.getMessage());
        }
        finally{
//            this.connection.close();
        }
        return ciclistas;
    }
    public Ciclista findByEmail(String email)throws SQLException
    {
        PreparedStatement stmt = null;
        Ciclista ciclista = null;
        try{
            String query = "SELECT U.username,U.password,U.email,C.id,C.saldo,C.tem_bike FROM users U "
                    + "inner join  ciclista C ON C.id_user = U.id WHERE U.email = ?;";
            stmt = this.connection.prepareStatement(query);
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                ciclista = new Ciclista();
                ciclista.setUsername(rs.getString("username"));
                ciclista.setEmail(rs.getString("email"));
                ciclista.setPassword(rs.getString("password"));
                ciclista.setSaldo(rs.getDouble("saldo"));
                ciclista.setTem_bike( (rs.getInt("tem_bike")==1)?true:false );
                ciclista.setId_ciclista(rs.getInt("id"));
            }
        }catch(SQLException e){
            throw new SQLException("Error on Database:"+e.getMessage());
        }
        finally{
//            this.connection.close();
        }
        return ciclista;
    }
     public int delete(int id)throws SQLException
    {
        Statement stmt = null;
        try{
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement pstmt = this.connection.prepareStatement(query);
            pstmt.setInt(1, id);
            id = pstmt.executeUpdate();
        }catch(SQLException e){
            throw new SQLException("Error on Database:"+e.getMessage());
        }
        finally{
            this.connection.close();
        }
        return id;
    }
     
     public int update(Ciclista ciclista)throws SQLException
    {
        int id = 0;
        Statement stmt = null;
        try{
            String query = "UPDATE ciclista  SET saldo = ? , tem_bike = ? WHERE id = ?;";
            PreparedStatement pstmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, ciclista.getSaldo());
            pstmt.setInt(2, (ciclista.isTem_bike())? 1 : 0 ) ;
            pstmt.setInt(3, (ciclista.getId_ciclista())) ;
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating ciclist failed, no rows affected.");
            }
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next())
            {
                id = rs.getInt(1);
            }
            
        }catch(SQLException e){
            throw new SQLException("Error on Database:"+e.getMessage());
        }
        finally{
//            this.connection.close();
        }
        return id;
    }
     
}
