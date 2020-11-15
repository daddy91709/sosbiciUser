package com.example.sosbicicletta;

public class Contatti {

    private String Nome;
    private String Telefono;
    private int Foto;

    public Contatti() {
    }

    public Contatti(String nome, String telefono, int foto)
    {
        Nome=nome;
        Telefono=telefono;
        Foto=foto;
    }
//get

    public String getNome() {
        return Nome;
    }

    public String getTelefono() {
        return Telefono;
    }

    public int getFoto() {
        return Foto;
    }


    //set


    public void setNome(String nome) {
        Nome = nome;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public void setFoto(int foto) {
        Foto = foto;
    }
}
