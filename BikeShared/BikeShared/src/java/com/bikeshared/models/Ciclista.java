/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikeshared.models;


public class Ciclista extends User{
    private int id_ciclista;
    private double saldo;
    private boolean tem_bike;
    
    public Ciclista()
    {
        this("","","",0);
    }
    public Ciclista(String username, String email, String password, double saldo)
    {        
        super(username,email,password);
        this.saldo = saldo;
        this.tem_bike = false;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getId_ciclista() {
        return id_ciclista;
    }

    public void setId_ciclista(int id_ciclista) {
        this.id_ciclista = id_ciclista;
    }

    public boolean isTem_bike() {
        return tem_bike;
    }

    public void setTem_bike(boolean tem_bike) {
        this.tem_bike = tem_bike;
    }
    
            
}
