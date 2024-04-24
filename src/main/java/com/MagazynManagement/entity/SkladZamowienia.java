package com.MagazynManagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "skladzamowienia")
public class SkladZamowienia {

    @EmbeddedId
    private SkladZamowieniaId id;

    @ManyToOne
    @JoinColumn(name = "nrZamowienia", insertable = false, updatable = false)
    private Zamowienie zamowienie;

    @ManyToOne
    @JoinColumn(name = "idProduktu", insertable = false, updatable = false)
    private Material material;

    private int ilosc;

    public SkladZamowienia(SkladZamowieniaId id, Zamowienie zamowienie, Material material, int ilosc) {
        super();
        this.id = id;
        this.zamowienie = zamowienie;
        this.material = material;
        this.ilosc = ilosc;
    }

    public SkladZamowienia() {
        super();
    }

    public SkladZamowieniaId getId() {
        return id;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public Material getMaterial() {
        return material;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setId(SkladZamowieniaId id) {
        this.id = id;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }
}
