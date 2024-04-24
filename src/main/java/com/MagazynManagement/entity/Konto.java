package com.MagazynManagement.entity;

import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
public class Konto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nrKonta;

    @NaturalId(mutable = true)
    private String login;

    private String haslo;

    @OneToOne
    @JoinColumn(name = "id_klienta", nullable = true)
    private Klient klient;

    @OneToOne
    @JoinColumn(name = "idPracownika", nullable = true)
    private Pracownik pracownik;

    private String rola;

    public Konto(String login,
                 String haslo,
                 Klient klient,
                 Pracownik pracownik,
                 String rola) {
        this.login = login;
        this.haslo = haslo;
        this.klient = klient;
        this.pracownik = pracownik;
        this.rola = rola;
    }

    public Konto() {
        super();
    }

    public String getRola() {
        return rola;
    }

    public Long getNrKonta() {
        return nrKonta;
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }

    public Klient getKlient() {
        return klient;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setNrKonta(Long nrKonta) {
        this.nrKonta = nrKonta;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }
}
