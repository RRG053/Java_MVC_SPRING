package com.example.demo.controllers;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.daos.DivisionDAO;
import com.example.demo.models.Division;
import com.example.demo.tools.DBConnection;


@Controller
@RequestMapping("division")
public class DivisionController {
    DivisionDAO dddao = new DivisionDAO(DBConnection.getConnection());

    @GetMapping
    public String index(Model model){
        model.addAttribute("division", dddao.getAll());
        return "division/ViewGetAllDivision";
    }

    @GetMapping(value = {"form", "form/{Id}"})
    public String create(@PathVariable (required = false) Integer Id, Model model){
        //Object data = rrdao.getById(regionId);
        if(Id!=null){
            model.addAttribute("division", dddao.getById(Id));
        }else{
            model.addAttribute("division", new Division());
        }
        //model.addAttribute("region", new Region());
        return "division/form";
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
    public String save(@Nullable Division division){
       boolean result;
        if(division.getId() == null){
            result = dddao.insert(division);
        }else{
            result = dddao.update(division);
        }
        if(result){
            return "redirect:/division";
        }else{
            return "division/form";
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
    @GetMapping("/delete/{Id}")
    public String delete(@PathVariable Integer Id, Division division){
        dddao.delete(Id);
        // if(result){
        //     return "redirect:/region";
        // }else{
        //     return "region/delete";
        // }
        return "redirect:/division";
    }
}


