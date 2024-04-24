package com.MagazynManagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "samochoddostawczy")
public class SamochodDostawczy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSamochodu;

    private int maxWaga;

    @ManyToOne
    @JoinColumn(name = "idMagazynu")
    private Magazyn magazyn;

    public SamochodDostawczy(Long idSamochodu, int maxWaga, Magazyn magazyn) {
        super();
        this.idSamochodu = idSamochodu;
        this.maxWaga = maxWaga;
        this.magazyn = magazyn;
    }

    public SamochodDostawczy() {
        super();
    }

    public Long getIdSamochodu() {
        return idSamochodu;
    }

    public int getMaxWaga() {
        return maxWaga;
    }

    public Magazyn getMagazyn() {
        return magazyn;
    }

    public void setIdSamochodu(Long idSamochodu) {
        this.idSamochodu = idSamochodu;
    }

    public void setMaxWaga(int maxWaga) {
        this.maxWaga = maxWaga;
    }

    public void setMagazyn(Magazyn magazyn) {
        this.magazyn = magazyn;
    }
}
