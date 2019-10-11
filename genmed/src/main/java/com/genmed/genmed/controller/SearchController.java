package com.genmed.genmed.controller;


import com.genmed.genmed.model.Drugs;
import com.genmed.genmed.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    public enum FilterByEnum {
        gen, cus
    }

//    @GetMapping("/search/**")
//    public String search(
//            @RequestParam(value="type", required=true) FilterByEnum type,
//            @RequestParam(value="name", required=true) String med,
//            Model result) {
//
//        List<Drugs> medicines;
//
//        if (type == FilterByEnum.gen){ medicines = searchService.searchByGenName(med); }
//        else if (type == FilterByEnum.cus){ medicines = searchService.searchByCusName(med); }
//        else { medicines = null; }
//
//        result.addAttribute("medicines", medicines);
//
//        return "search";
//    }

    @GetMapping("/drugs/{drug}")
    public String drugDetails ( @PathVariable("drug") Long bookId ) {
        return "search";
    }


}
