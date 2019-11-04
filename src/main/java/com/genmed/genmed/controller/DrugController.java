package com.genmed.genmed.controller;

import com.genmed.genmed.model.Drugs;
import com.genmed.genmed.model.GenericDrug;
import com.genmed.genmed.repository.DrugDao;
import com.genmed.genmed.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class DrugController {

    @Autowired
    private DrugDao drugDao;

    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/drugs")
    public String viewDrugs(@RequestParam(name = "name", required = false) String name, Model m, Principal p){
        System.out.println(name);
        if( name == null){
            m.addAttribute("drugs", drugDao.listAllDrugs());
        }
        else {
            m.addAttribute("drugs", drugDao.listDrugsWithNameLike(name));
        }
        m.addAttribute("user_email", p.getName());
        return "drugs";
    }

    @GetMapping(value = "/gen_drugs")
    public String viewGenDrugs(@RequestParam(name = "name", required = false) String name, Model m, Principal p){
        System.out.println(name);
        if( name == null ){
            m.addAttribute("drugs", drugDao.listAllGenDrugs());
        }
        else {
            m.addAttribute("drugs", drugDao.listGenDrugsWithNameLike(name));
        }        m.addAttribute("user_email", p.getName());
        return "genDrugs";
    }

    @RequestMapping(value = "/drug/{drug_id}")
    public String viewDrugDetail(Model m, Principal p, @PathVariable int drug_id){
        Drugs d = drugDao.getDrugByID(drug_id);
        m.addAttribute("drug", d);
        m.addAttribute("user_email", p.getName());
        m.addAttribute( "gen", drugDao.getGenDrugByID(d.getGen_id()));
        return "drugDetail";
    }

    @RequestMapping(value = "/gen_drug/{gen_id}")
    public String viewGenDrugDetail(Model m, Principal p, @PathVariable int gen_id){
        GenericDrug g = drugDao.getGenDrugByID(gen_id);
        m.addAttribute("gen_drug", g);
        m.addAttribute("user_email", p.getName());
        m.addAttribute( "comp", drugDao.getGenDrugCompositionByID(gen_id));
        return "genDrugDetail";
    }

}
