package com.genmed.genmed.controller;

import com.genmed.genmed.model.Drugs;
import com.genmed.genmed.model.GenericDrug;
import com.genmed.genmed.repository.DrugDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DrugController {

    @Autowired
    private DrugDao drugDao;

    @RequestMapping(value = "/drugs")
    public String viewDrugs(Model model){
        model.addAttribute("drugs", drugDao.listAllDrugs());
        return "drugs";
    }

    @RequestMapping(value = "/gen_drugs")
    public String viewGenDrugs(Model model){
        model.addAttribute("genDrugs", drugDao.listAllGenDrugs());
        return "genDrugs";
    }

    @RequestMapping(value = "/drug/{drug_id}")
    public String viewDrugDetail(Model model, @PathVariable int drug_id){
        Drugs d = drugDao.getDrugByID(drug_id);
        model.addAttribute("drug", d);
        model.addAttribute( "gen", drugDao.getGenDrugByID(d.getGen_id()));
        return "drugDetail";
    }

    @RequestMapping(value = "/gen_drug/{gen_id}")
    public String viewGenDrugDetail(Model model, @PathVariable int gen_id){
        GenericDrug g = drugDao.getGenDrugByID(gen_id);
        model.addAttribute("gen_drug", g);
        model.addAttribute( "comp", drugDao.getGenDrugCompositionByID(gen_id));
        return "genDrugDetail";
    }

}
