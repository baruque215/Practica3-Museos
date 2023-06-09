/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pracitca3eva;

/**
 *
 * @author canmonal
 */
public class RegistroJugador {
    private String nombre;
    private int tiempo;

    public RegistroJugador(String nombre, int tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
    } 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
}
