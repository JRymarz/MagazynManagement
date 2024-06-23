package com.MagazynManagement.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PozycjaKoszyka that = (PozycjaKoszyka) o;
        return Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(material);
    }
}
