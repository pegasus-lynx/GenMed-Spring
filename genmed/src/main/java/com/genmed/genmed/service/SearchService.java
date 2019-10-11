package com.genmed.genmed.service;

import com.genmed.genmed.model.Drugs;
import com.genmed.genmed.model.Shop;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    public List<Drugs> searchByGenName(String med_name){
        return null;
    };
    public List<Drugs> searchByCusName(String med_name){
        return null;
    };

    public List<Shop> searchShopByCity(String city){
        return null;
    }
    public List<Shop> searchShopByName(String shop_name){
        return null;
    }

    public Drugs searchMedicineDetails(String med_name, String type) { return null; };

}
