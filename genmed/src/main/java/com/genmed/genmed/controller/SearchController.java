package com.genmed.genmed.controller;

import com.genmed.genmed.model.Shop;
import com.genmed.genmed.repository.DrugDao;
import com.genmed.genmed.repository.ShopDao;
import com.genmed.genmed.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class SearchController {

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DrugDao drugDao;

    @GetMapping("/search")
    public String searchShopByMed(@RequestParam(name = "drug_id", required = false) Integer drug_id, Model m, Principal p){
        m.addAttribute("user_email", p.getName());
        m.addAttribute("drugs", drugDao.listAllDrugs());
        if( drug_id==null ){
            m.addAttribute("shops", shopDao.getAllShops());
        }
        else{
            m.addAttribute("shops", shopDao.getShopsHavingDrug(drug_id));
        }
        return "search";
    }
}
