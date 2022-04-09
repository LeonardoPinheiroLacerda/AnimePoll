package com.leonardo.animepoll.controllers;

import com.leonardo.animepoll.services.AnimeService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Controller
public class IndexController {
    
    private final AnimeService service;

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("screens/index");
        modelAndView.addObject("animes", service.findAll());
        return modelAndView;
    }

}
