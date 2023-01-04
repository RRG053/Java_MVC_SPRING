package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.daos.RegionDAO;
import com.example.demo.models.Region;
import com.example.demo.tools.DBConnection;

@Controller
@RequestMapping("region")
public class RegionController {
    RegionDAO rrdao = new RegionDAO(DBConnection.getConnection());

    @GetMapping
    public String index(Model model){
        // Object region = new rrdao.getAll();
        model.addAttribute("dataselect", rrdao.getAll());
        // model.addAttribute("division", rrdao.insert(null));
        // model.addAttribute("division", rrdao.update(null));
        // model.addAttribute("division", rrdao.delete(null));
        return "region/ViewGetAllRegion";
    }
    @GetMapping(value = ("form"))
    public String create(Model model){
        model.addAttribute("region", new Region());
        return "region/form";
    }
    @PostMapping("save")
    public String save(Region region){
        boolean result = rrdao.insert(region);
        if(result){
            return "redirect:/region";
        }else{
            return "region/form";
        }

    }
}
