/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikeshared.Dao;

import com.bikeshared.models.User;
import com.bikeshared.utils.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {

    public UserDao() {

    }
    private Connection connection = null;

    public UserDao(Connection conn) {
        this.connection = conn;
    }

    public boolean createUsuario(String nome, String email,String senha) {
        PreparedStatement pst = null;
        try {
            Connection conexao = Conexao.getInstance();
            String sql = "INSERT INTO users (username,email,password) VALUES(?, ?, ?)";
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, email);
            pst.setString(3, senha);
            pst.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao tentar Cadastrar" + ex);
        }
       return false;
    }

    public int create(User user) throws SQLException {
        int id = 0;
        Statement stmt = null;
        try {
            String query = "insert into users (username,email,password) values(?, ?, ?)";
            PreparedStatement pstmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new SQLException("Error on Database:" + e.getMessage());
        } finally {
            this.connection.close();
        }
        return id;
    }

    public ArrayList<User> all() throws SQLException {
        Statement stmt = null;
        ArrayList<User> users = new ArrayList<>();
        User user;
        try {
            String query = "SELECT * FROM users ;";
            PreparedStatement pstmt = this.connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("email"),
                        rs.getString("password"));
                user.setId(rs.getInt("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new SQLException("Error on Database:" + e.getMessage());
        } finally {
            this.connection.close();
        }
        return users;
    }

    public User find(int id) throws SQLException {
        PreparedStatement stmt = null;
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE id = ?;";
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("email"),
                        rs.getString("password"));
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error on Database:" + e.getMessage());
        } finally {
            this.connection.close();
        }
        return user;
    }

    public User findUserByEmail(String email) throws SQLException {
        PreparedStatement stmt = null;
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE email = ?;";
            stmt = this.connection.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("email"),
                        rs.getString("password"));
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error on Database:" + e.getMessage());
        } finally {
            this.connection.close();
        }
        return user;
    }

    public int delete(int id) throws SQLException {
        Statement stmt = null;
        try {
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement pstmt = this.connection.prepareStatement(query);
            pstmt.setInt(1, id);
            id = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error on Database:" + e.getMessage());
        } finally {
            this.connection.close();
        }
        return id;
    }
}
