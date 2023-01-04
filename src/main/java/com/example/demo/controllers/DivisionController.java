package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.daos.DivisionDAO;
import com.example.demo.tools.DBConnection;


@Controller
@RequestMapping
public class DivisionController {
    DivisionDAO dddao = new DivisionDAO(DBConnection.getConnection());

    @GetMapping("/getalldivision")
    public String index(Model model){
        model.addAttribute("dataselect", dddao.getAll());
        // model.addAttribute("division", dddao.insert(null));
        // model.addAttribute("division", dddao.update(null));
        // model.addAttribute("division", dddao.delete(null));
        return "ViewGetAllDivision";
    }

}

