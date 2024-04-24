package com.MagazynManagement.dto;

public class RozpiskaDto {
    private Long idRozpiski;
    private Long idpracownika;

    public RozpiskaDto(Long idRozpiski, Long idpracownika){
        this.idRozpiski = idRozpiski;
        this.idpracownika = idpracownika;
    }

    public RozpiskaDto(){
        super();
    }

    public Long getIdRozpiski() {
        return idRozpiski;
    }

    public void setIdRozpiski(Long idRozpiski) {
        this.idRozpiski = idRozpiski;
    }

    public Long getIdpracownika() {
        return idpracownika;
    }

    public void setIdpracownika(Long idpracownika) {
        this.idpracownika = idpracownika;
    }
}
