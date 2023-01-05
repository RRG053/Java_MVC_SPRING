package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("region", rrdao.getAll());
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

    @GetMapping(value = ("form/{regionId}"))
    public String change(@PathVariable (required = false) Integer id, Model model){
        Object data =rrdao.getById(id);
        model.addAttribute("region", data);
        return "region/edit";
    }
    @PostMapping("submit")
    public String submit(Region region){
        boolean result = rrdao.update(region);
        if(result){
            return "redirect:/region";
        }else{
            return "region/edit";
        }
    }



    @GetMapping(value = ("delete/{regionId}"))
    public String del(Model model){

        model.addAttribute("region", new Region());
        return "region/delete";
    }
    @PostMapping("delete")
    public String delete(Region region){
        boolean result = rrdao.update(region);
        if(result){
            return "redirect:/region";
        }else{
            return "region/delete";
        }
    }
}
