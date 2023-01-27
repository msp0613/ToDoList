package com.example.zadanialista;

public class MainModel {
    String id, tytul,opis,czas;
    MainModel(){}
    public MainModel(String id, String tytul,String opis,String czas) {
        this.id=id;
        this.tytul=tytul;
        this.opis=opis;
        this.czas = czas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCzas() {
        return czas;
    }

    public void setCzas(String czas) {
        this.czas = czas;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }
}
