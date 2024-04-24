package com.MagazynManagement.dto;

public class SektorDto {

    private Long idSektora;
    private Long idMaterialu;

    public SektorDto(Long idSektora, Long idMaterialu) {
        this.idSektora = idSektora;
        this.idMaterialu = idMaterialu;
    }

    public SektorDto(){
        super();
    }

    public Long getIdSektora() {
        return idSektora;
    }

    public void setIdSektora(Long idSektora) {
        this.idSektora = idSektora;
    }

    public Long getIdMaterialu() {
        return idMaterialu;
    }

    public void setIdMaterialu(Long idMaterialu) {
        this.idMaterialu = idMaterialu;
    }
}
