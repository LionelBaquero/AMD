package com.example.lio.misestudiantes;

/**
 * Created by lio on 15/02/18.
 */

public class Student {
    private int numero;
    private String nombre,grupo, caract;


    public Student(int numero, String nombre, String grupo, String caract){
      this.numero=numero;
      this.nombre=nombre;
      this.grupo=grupo;
      this.caract=caract;
    }

    public Student(){

    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getCaract() {
        return caract;
    }

    public void setCaract(String caract) {
        this.caract = caract;
    }

    public String toString(){
        return numero+" - "+nombre+" - Grupo: "+grupo+" - Caracterización: "+caract;
    }
}
