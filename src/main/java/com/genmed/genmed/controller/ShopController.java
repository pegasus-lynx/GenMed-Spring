package com.genmed.genmed.controller;

import com.genmed.genmed.model.Shop;
import com.genmed.genmed.repository.AddressDao;
import com.genmed.genmed.repository.ShopDao;
import com.genmed.genmed.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Null;
import java.security.Principal;

@Controller
public class ShopController {

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AddressDao addressDao;

    @RequestMapping(value = "/shops")
    public String viewShops(@RequestParam(name = "name", required = false) String name, Model m, Principal p){
        if(name==null){
            m.addAttribute("shops", shopDao.getAllShops());
        }
        else{
            m.addAttribute("shops", shopDao.getShopsWithNameLike(name));
        }
        if(p != null) m.addAttribute("user_email", p.getName());
        return "shops";
    }

    @RequestMapping(value = "/shop/{shop_id}")
    public String viewShopDetail(Model m, @PathVariable int shop_id, Principal p){
        Shop s = shopDao.getShopByID(shop_id);
        m.addAttribute("shop", s);
        m.addAttribute("address", addressDao.getAddressByID(s.getAddress_id()));
        m.addAttribute("user_email", p.getName());
        return "shopDetail";
    }

    @RequestMapping(value = "/shop/{shop_id}/inventory")
    public String viewShopInventory(Model m, Principal p, @PathVariable int shop_id){
        m.addAttribute("items", shopDao.getInventoryByID(shop_id));
        m.addAttribute("user_email", p.getName());
        m.addAttribute("user", userDao.findByUsername(p.getName()));
        return "shopInventory";
    }
}
