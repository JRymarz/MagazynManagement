package com.MagazynManagement.entity;

import jakarta.persistence.*;
import lombok.Builder;

import javax.naming.AuthenticationException;

@Entity
public class Pracownik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pracownika")
    private Long idPracownika;

    private String imie;

    private String nazwisko;

    @ManyToOne
    @JoinColumn(name = "id_magazynu")
    private Magazyn magazyn;

    private float pensja;

    private String stanowisko;

    private String adres;

    private String telefon;

    private String email;

    public Pracownik(String imie,
                     String nazwisko,
                     Magazyn magazyn,
                     float pensja,
                     String stanowisko,
                     String adres,
                     String telefon,
                     String email) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.magazyn = magazyn;
        this.pensja = pensja;
        this.stanowisko = stanowisko;
        this.adres = adres;
        this.telefon = telefon;
        this.email = email;
    }

    public Pracownik() {
        super();
    }

    public Long getIdPracownika() {
        return idPracownika;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Magazyn getMagazyn() {
        return magazyn;
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

    public String getEmail(){
        return email;
    }

    public void setIdPracownika(Long idPracownika) {
        this.idPracownika = idPracownika;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setMagazyn(Magazyn magazyn) {
        this.magazyn = magazyn;
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

    public void setEmail(String email){
        this.email = email;
    }

    public Long getIdMagazynu(){
        return magazyn.getIdMagazynu();
    }

    public void setIdMagazynu(Long idMagazynu){
        magazyn.setIdMagazynu(idMagazynu);
    }
}
