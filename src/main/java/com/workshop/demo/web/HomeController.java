package com.workshop.demo.web;

import com.workshop.demo.service.CarouselService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CarouselService carouselService;

    public HomeController(CarouselService carouselService) {
        this.carouselService = carouselService;
    }

    @GetMapping("/")
    private String index() {
        return "index";
    }

    @GetMapping("/home")
    private String home(Model model) {
        model.addAttribute("firstImage",carouselService.firstImage());
        model.addAttribute("secondImage",carouselService.secondImage());
        model.addAttribute("thirdImage",carouselService.thirdImage());
        return "home";
    }
}
