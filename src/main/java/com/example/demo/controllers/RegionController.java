package com.example.demo.controllers;


import org.springframework.lang.Nullable;
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

    @GetMapping(value = {"form", "form/{regionId}"})
    public String create(@PathVariable (required = false) Integer regionId, Model model){
        //Object data = rrdao.getById(regionId);
        if(regionId!=null){
            model.addAttribute("region", rrdao.getById(regionId));
        }else{
            model.addAttribute("region", new Region());
        }
        //model.addAttribute("region", new Region());
        return "region/form";
    }

    // @PostMapping("save")
    // public String save(Region region){
    //     boolean result = rrdao.insert(region);
    //     if(result){
    //         return "redirect:/region";
    //     }else{
    //         return "region/form";
    //     }
    //     return "region/form";
    // }

    @PostMapping("save")
    public String save(@Nullable Region region){
       boolean result;
        if(region.getRegionId() == null){
            result = rrdao.insert(region);
        }else{
            result = rrdao.update(region);
        }
        if(result){
            return "redirect:/region";
        }else{
            return "region/form";
        }
    }

    // @GetMapping(value = ("form/{regionId}"))
    // public String change(@PathVariable (required = false) Integer regionId, Model model){
    //     Object data =rrdao.getById(regionId);
    //     model.addAttribute("region", data);
    //     return "region/edit";
    // }

    // @PostMapping("submit")
    // public String submit(Region region){
    //     boolean result = rrdao.update(region);
    //     if(result){
    //         return "redirect:/region";
    //     }else{
    //         return "region/edit";
    //     }
    // }

    // @GetMapping(value = ("delete/{regionId}"))
    // public String del(Model model){

    //     model.addAttribute("region", new Region());
    //     return "region/delete";
    // }

    // @PostMapping("delete")
    // public String delete(Region region){
    //     boolean result = rrdao.update(region);
    //     if(result){
    //         return "redirect:/region";
    //     }else{
    //         return "region/delete";
    //     }
    // }

    //? (Bikin method baru dari method di atas, menambah PathVariable regionId, dan disable if)
    @GetMapping("/delete/{regionId}")
    public String delete(@PathVariable Integer regionId, Region region){
        rrdao.delete(regionId);
        // if(result){
        //     return "redirect:/region";
        // }else{
        //     return "region/delete";
        // }
        return "redirect:/region";
    }
}
