package com.leonardo.animepoll.controllers;

import com.leonardo.animepoll.services.ChartService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Controller
@RequestMapping("/chart")
public class ChartController {
    
    private final ChartService service;

    @GetMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("screens/chart");
        modelAndView.addObject("chart", service.getChart());
        return modelAndView;
    }

    @GetMapping("/screenshot")
    public ModelAndView onlyChart(){
        ModelAndView modelAndView = new ModelAndView("screens/onlyChart");
        modelAndView.addObject("chart", service.getChart().subList(0, 10));
        return modelAndView;
    }

}
