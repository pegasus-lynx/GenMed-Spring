package com.genmed.genmed.controller;


import com.genmed.genmed.repository.DrugDao;
import com.genmed.genmed.repository.ShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ApiController {

    @Autowired
    private DrugDao drugDao;

    @Autowired
    private ShopDao shopDao;

    @GetMapping("/api/get/mf_names")
    public @ResponseBody List<String> getMfNames(@RequestParam int is_generic) {
        return drugDao.getMfNameByIsGen(is_generic);
    }

    @GetMapping("/api/get/drug_names")
    public @ResponseBody List<String> getDrugNames(@RequestParam int is_generic, @RequestParam String mf_name) {
        return drugDao.getDrugNameByOther(is_generic, mf_name);
    }

    @GetMapping("/api/get/drug_id")
    public @ResponseBody int getDrugID(@RequestParam int is_generic, @RequestParam String mf_name, @RequestParam String drug_name) {
        return drugDao.getDrugIDByOther(is_generic, mf_name, drug_name);
    }

    @GetMapping("/api/get/batch_nos")
    public @ResponseBody List<String> getBatches(@RequestParam int drug_id) {
        return shopDao.getBatchNo(drug_id);
    }

    @GetMapping("/api/get/batch_id")
    public @ResponseBody int getBatchID(@RequestParam int drug_id, @RequestParam String batch_no) {
        return shopDao.getBatchID(drug_id, batch_no);
    }
}
