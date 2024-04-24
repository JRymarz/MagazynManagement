package com.MagazynManagement.service;

import com.MagazynManagement.entity.CustomUserDetails;
import com.MagazynManagement.entity.Konto;
import com.MagazynManagement.repository.KontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private KontoRepository kontoRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Konto konto = kontoRepository.findByLogin(username);
        if (konto == null)
            throw new UsernameNotFoundException("Taki użytkownik nie istnieje");

        return new CustomUserDetails(konto);
    }
}
