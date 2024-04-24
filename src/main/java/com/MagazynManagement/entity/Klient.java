package com.MagazynManagement.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
public class Klient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_klienta")
    private Long idKlienta;
    private String imie;
    private String nazwisko;
    private String email;
    private String adres;
    private String telefon;

    @OneToOne(mappedBy = "klient")
    private Konto konto;

    public Klient(String imie, String nazwisko, String email, String adres, String telefon) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.adres = adres;
        this.telefon = telefon;
    }

    public Klient() {
        super();
    }

    public long getIdKlienta() {
        return idKlienta;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    @NaturalId(mutable = true)
    public String getEmail() {
        return email;
    }

    public String getAdres() {
        return adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setIdKlienta(Long idKlienta) {
        this.idKlienta = idKlienta;
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

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }
}
