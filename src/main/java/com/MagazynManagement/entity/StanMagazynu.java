package com.MagazynManagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stanmagazynu")
public class StanMagazynu {

    @EmbeddedId
    private StanMagazynuId id;

    @ManyToOne
    @JoinColumn(name = "idMagazynu", insertable = false, updatable = false)
    private Magazyn magazyn;

    @ManyToOne
    @JoinColumn(name = "idProduktu", insertable = false, updatable = false)
    private Material material;

    private int ilosc;

    @ManyToOne
    @JoinColumn(name = "id_sektora")
    private Sektor sektor;

    public StanMagazynu(StanMagazynuId id, Magazyn magazyn, Material material, int ilosc) {
        super();
        this.id = id;
        this.magazyn = magazyn;
        this.material = material;
        this.ilosc = ilosc;
    }

    public StanMagazynu() {
        super();
    }

    public StanMagazynuId getId() {
        return id;
    }

    public Magazyn getMagazyn() {
        return magazyn;
    }

    public Material getMaterial() {
        return material;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setId(StanMagazynuId id) {
        this.id = id;
    }

    public void setMagazyn(Magazyn magazyn) {
        this.magazyn = magazyn;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public Sektor getSektor() {
        return sektor;
    }

    public void setSektor(Sektor sektor) {
        this.sektor = sektor;
    }
}
