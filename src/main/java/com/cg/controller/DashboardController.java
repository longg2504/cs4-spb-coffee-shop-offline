package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboards")
public class DashboardController {
    @GetMapping
    public String showDashboard(){
        return "/dashboard/list-product";
    }
}
