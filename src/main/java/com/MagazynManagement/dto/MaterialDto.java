package com.MagazynManagement.dto;

public class MaterialDto {
    private String name;
    private Long idmagazynu;

    public MaterialDto(String name, Long idmagazynu) {
        this.name = name;
        this.idmagazynu = idmagazynu;
    }

    public MaterialDto(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdmagazynu() {
        return idmagazynu;
    }

    public void setIdmagazynu(Long idmagazynu) {
        this.idmagazynu = idmagazynu;
    }
}
