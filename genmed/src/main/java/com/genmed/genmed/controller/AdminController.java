package com.genmed.genmed.controller;

import com.genmed.genmed.model.DrugComp;
import com.genmed.genmed.model.DrugComposition;
import com.genmed.genmed.model.GenericDrug;
import com.genmed.genmed.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class AdminController {

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private DrugDao drugDao;

    @RequestMapping("/admin")
    public String adminHome(Model m, Principal p){
        return "adminHome";
    }

    @GetMapping("/admin/add/generic_drug")
    public String addGenericDrug(Model m, Principal p){
        m.addAttribute("gen_drug", new GenericDrug());
        return "addGenericDrug";
    }

    @PostMapping("/admin/add/generic_drug")
    public String addGenericDrug(@ModelAttribute("gen_drug") GenericDrug g, Principal p){
        drugDao.saveGenDrug(g.getName());
        return "redirect:/admin";
    }

    @GetMapping("/admin/add/drug_component")
    public String addDrugComp(Model m, Principal p){
        m.addAttribute("drug_comp", new DrugComp());
        return "addDrugComp";
    }

    @PostMapping("/admin/add/drug_component")
    public String addDrugComp(@ModelAttribute("drug_comp") DrugComp c, Principal p){
        drugDao.saveDrugComp( c.getComp_name() );
        return "redirect:/admin";
    }

    @GetMapping("/admin/add/composition")
    public String addComposition(Model m, Principal p){
        m.addAttribute("composition", new DrugComposition());
        m.addAttribute("gen_drugs", drugDao.listAllGenDrugs());
        m.addAttribute("drug_comps", drugDao.listAllDrugComp());
        return "addComposition";
    }

    @PostMapping("/admin/add/composition")
    public String addComposition(@ModelAttribute("composition") DrugComposition d, Principal p){
        drugDao.saveDrugComposition(d.getPercent(), d.getComp_id(), d.getGen_id());
        return "redirect:/admin";
    }
}
