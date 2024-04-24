package com.MagazynManagement.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private Konto konto;

    public CustomUserDetails(Konto konto) {
        this.konto = konto;
    }

    public String getImieKlient(){
        return konto.getKlient().getImie();
    }

    public String getImiePracownik(){
        return konto.getPracownik().getImie();
    }

    public Long getIdPracownika(){return konto.getPracownik().getIdPracownika();};

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(konto.getRola()));
    }

    @Override
    public String getPassword() {
        return konto.getHaslo();
    }

    @Override
    public String getUsername() {
        return konto.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Konto getKonto() {
        return konto;
    }
}
