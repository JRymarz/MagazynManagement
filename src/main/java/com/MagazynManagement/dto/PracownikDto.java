package com.MagazynManagement.dto;

public class PracownikDto {

    private String imie;

    private String nazwisko;

    private Long idMagazynu;

    private float pensja;

    private String stanowisko;

    private String adres;

    private String telefon;

    private String email;

    private String login;

    private String haslo;

    public PracownikDto(String imie,
                        String nazwisko,
                        Long idMagazynu,
                        float pensja,
                        String stanowisko,
                        String adres,
                        String telefon,
                        String email,
                        String login,
                        String haslo) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.idMagazynu = idMagazynu;
        this.pensja = pensja;
        this.stanowisko = stanowisko;
        this.adres = adres;
        this.telefon = telefon;
        this.email = email;
        this.login = login;
        this.haslo = haslo;
    }

    public String utworzRole(){
        switch (stanowisko){
            case "Administrator":
                return "ADMIN";

            case "Pracownik":
                return "PRACOWNIK";

            case "Manager":
                return "MANAGER";

            default:
                return "NONE";
        }
    }

    public PracownikDto() {
        super();
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public float getPensja() {
        return pensja;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public String getAdres() {
        return adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
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

    public void setPensja(float pensja) {
        this.pensja = pensja;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public Long getIdMagazynu() {
        return idMagazynu;
    }

    public void setIdMagazynu(Long idMagazynu) {
        this.idMagazynu = idMagazynu;
    }
}
