/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikeshared.Dao;

import com.bikeshared.models.Ciclista;
import com.bikeshared.models.Doca;
import com.bikeshared.models.Estacao;
import com.bikeshared.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class EstacaoDao {
    private Connection connection = null;

    public EstacaoDao(Connection conn) {
        this.connection = conn;
    }
    
    public int create(Estacao estacao)throws SQLException
    {
        int id = 0;
        Statement stmt = null;
        try{
            String query = "INSERT INTO estacoes (coordenada,capacidade,premio) VALUES(?, ?, ?);";
            PreparedStatement pstmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, estacao.getCoordenada());
            pstmt.setInt(2, estacao.getCapacidade());
            pstmt.setInt(3, estacao.getPremio());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating estacoes failed, no rows affected.");
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
    
    public Estacao find(int idEstacao)throws SQLException
    {
        Statement stmt = null;
        Estacao estacao = null;
        try{
            String query = "SELECT * FROM estacoes WHERE id = ? ;";
            PreparedStatement pstmt = this.connection.prepareStatement(query);
            pstmt.setInt(1, idEstacao);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                estacao = new Estacao(rs.getString("coordenada"),rs.getInt("capacidade"),
                                rs.getInt("premio"));
                estacao.setId(rs.getInt("id"));
            }
        }catch(SQLException e){
            throw new SQLException("Error on Database:"+e.getMessage());
        }
        finally{
            this.connection.close();
        }
        return estacao;
    }
    
    public ArrayList<Estacao> all()throws SQLException
    {
        Statement stmt = null;
        ArrayList<Estacao> estacoes = new ArrayList<>();
        Estacao estacao;
        try{
            String query = "SELECT * FROM estacoes ;";
            PreparedStatement pstmt = this.connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                estacao = new Estacao(rs.getString("coordenada"),rs.getInt("capacidade"),
                                rs.getInt("premio"));
                estacao.setId(rs.getInt("id"));
                estacao.nome = rs.getString("nome");
                estacoes.add(estacao);
            }
        }catch(SQLException e){
            throw new SQLException("Error on Database:"+e.getMessage());
        }
        finally{
            this.connection.close();
        }
        return estacoes;
    }
    public ArrayList<Estacao> filter(int premio)throws SQLException
    {
        Statement stmt = null;
        ArrayList<Estacao> estacoes = new ArrayList<>();
        Estacao estacao;
        try{
            String query = "SELECT * FROM estacoes WHERE premio >= ?;";
            PreparedStatement pstmt = this.connection.prepareStatement(query);
            pstmt.setInt(1, premio);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                estacao = new Estacao(rs.getString("coordenada"),rs.getInt("capacidade"),
                                rs.getInt("premio"));
                estacao.setId(rs.getInt("id"));
                estacoes.add(estacao);
            }
        }catch(SQLException e){
            throw new SQLException("Error on Database:"+e.getMessage());
        }
        finally{
            this.connection.close();
        }
        return estacoes;
    }
     public ArrayList<Doca> getDocas(int idEstacao)throws SQLException
    {
        Statement stmt = null;
        ArrayList<Doca> docas = new ArrayList<>();
        Doca doca;
        try{
            String query = "SELECT * FROM docas where id_estacao=?;";
            PreparedStatement pstmt = this.connection.prepareStatement(query);
            pstmt.setInt(1, idEstacao);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                doca = new Doca(rs.getInt("id"),rs.getBoolean("estado"));
                docas.add(doca);
            }
        }catch(SQLException e){
            throw new SQLException("Error on Database:"+e.getMessage());
        }
        finally{
            this.connection.close();
        }
        return docas;
    }
     public int addDoca(int idEstacao)throws SQLException
    {
        Statement stmt = null;
        Doca doca;
        int id=0;
        try{
            String query = "INSERT INTO docas(estado,id_estacao) VALUES(?, ?);";
            PreparedStatement pstmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, 1);
            pstmt.setInt(2, idEstacao);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating estacoes failed, no rows affected.");
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
     
    public int trancarDoca(int idDoca)throws SQLException
    {
        Statement stmt = null;
        int id=0;
        try{
            String query = "UPDATE docas SET estado=1 WHERE id = ?;";
            PreparedStatement pstmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, idDoca);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating estacoes failed, no rows affected.");
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
    
    public int destrancarDoca(int idDoca)throws SQLException
    {
        Statement stmt = null;
        int id=0;
        try{
            String query = "UPDATE docas SET estado=0 WHERE id = ?;";
            PreparedStatement pstmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, idDoca);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating estacoes failed, no rows affected.");
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
    
    // TODO
    public int getLevantamentos(int idEstacao)
    {
        return 1;
    }
    
    // TODO
    public int getEntregas(int idEstacao)
    {
        return 1;
    }

    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
