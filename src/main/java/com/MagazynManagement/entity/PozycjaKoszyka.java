package com.MagazynManagement.entity;

public class PozycjaKoszyka {

    private Material material;

    private Integer ilosc;

    public PozycjaKoszyka(Material material, int ilosc) {
        this.material = material;
        this.ilosc = ilosc;
    }

    public Material getMaterial() {
        return material;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }
}
