/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikeshared.models;

import java.io.Serializable;


public class Doca implements Serializable{
    private int id;
    private boolean estado;
    
    public Doca()
    {
        this(0,true);
    }
    public Doca(int id,boolean estado)
    {
        this.id = id;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
