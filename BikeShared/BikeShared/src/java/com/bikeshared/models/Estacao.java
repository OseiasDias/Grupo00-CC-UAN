/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikeshared.models;

import java.io.Serializable;
import java.util.ArrayList;


public class Estacao implements Serializable{
    private int id;
    public String nome;
    private String coordenada;
    private int capacidade;
    private int premio;
    private boolean disponivel;
    private ArrayList<Doca> docas;
    
    public Estacao()
    {
        this("",0,0);
    }
    public Estacao(String coordenada,int capacidade,int premio)
    {
        this.id = 0;
        this.capacidade = capacidade;
        this.coordenada = coordenada;
        this.premio = premio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getPremio() {
        return premio;
    }

    public void setPremio(int premio) {
        this.premio = premio;
    }

    public ArrayList<Doca> getDocas() {
        return docas;
    }

    public void setDocas(ArrayList<Doca> docas) {
        this.docas = docas;
    }
    
    public void setDisponivel(boolean disponivel)
    {
        this.disponivel = disponivel;
    }
    public boolean isDisponivel()
    {
        return this.disponivel;
    }
    
    
    
}
