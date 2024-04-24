package com.MagazynManagement.dto;

public class ZaopatrzenieDto {
    private Long id_zaopatrzenia;
    private String adres;
    private String produkt;
    private int ilosc;
    private int wykonane;

    public ZaopatrzenieDto(Long idZaopatrzenia, String adres, String produkt, int ilosc, int wykonane) {
        id_zaopatrzenia = idZaopatrzenia;
        this.adres = adres;
        this.produkt = produkt;
        this.ilosc = ilosc;
        this.wykonane = wykonane;
    }

    public ZaopatrzenieDto(){
        super();
    }

    public Long getId_zaopatrzenia() {
        return id_zaopatrzenia;
    }

    public void setId_zaopatrzenia(Long id_zaopatrzenia) {
        this.id_zaopatrzenia = id_zaopatrzenia;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getProdukt() {
        return produkt;
    }

    public void setProdukt(String produkt) {
        this.produkt = produkt;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public int getWykonane() {
        return wykonane;
    }

    public void setWykonane(int wykonane) {
        this.wykonane = wykonane;
    }
}
