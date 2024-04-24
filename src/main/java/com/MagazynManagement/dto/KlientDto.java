package com.MagazynManagement.dto;

public class KlientDto {

    private String imie;

    private String nazwisko;

    private String email;

    private String adres;

    private String telefon;

    private String login;

    private String haslo;

    public KlientDto(String imie, String nazwisko, String email, String adres, String telefon, String login, String haslo) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.adres = adres;
        this.telefon = telefon;
        this.login = login;
        this.haslo = haslo;
    }

    public KlientDto() {
        super();
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public String getAdres() {
        return adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
}
