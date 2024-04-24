package com.MagazynManagement.controller;

import com.MagazynManagement.entity.Magazyn;
import com.MagazynManagement.service.MagazynService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MagazynController {

    private final MagazynService magazynService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/placowki")
    public ModelAndView getAllPlacowki(){
        List<Magazyn> list = magazynService.getAllMagazyn();
        return new ModelAndView("placowki", "magazyn", list);
    }
}
