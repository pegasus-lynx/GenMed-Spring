package com.genmed.genmed.controller;

import com.genmed.genmed.model.*;
import com.genmed.genmed.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class AccountController {

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

    @RequestMapping("/account/{shop_id}/profile")
    public String accountProfile(Model m, Principal p, @PathVariable int shop_id) {

        User u = userDao.findByUsername(p.getName());
        m.addAttribute("user", u);
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";

        Shop s = shopDao.getShopByID(shop_id);
        System.out.println(s.getAddress_id());
        m.addAttribute("shop", s);
        Address a = addressDao.getAddressByID(s.getAddress_id());
        m.addAttribute("address", a);
        return "accountProfile";
    }

    @GetMapping("/account/{shop_id}/add_phone")
    public String addPhone(Model m, Principal p, @PathVariable int shop_id) {

        User u = userDao.findByUsername(p.getName());
        m.addAttribute("user", u);
        m.addAttribute("shop_id", shop_id);
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";
        return "accountPhone";
    }

    @PostMapping("/account/{shop_id}/add_phone")
    public String addPhone(@RequestParam String phone_no, Principal p, @PathVariable int shop_id) {

        User u = userDao.findByUsername(p.getName());
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";

        int user_id = userDao.getUserIDByEmailID(p.getName());
        shopDao.saveShopPhone(phone_no, shop_id);
        return "redirect:/account/"+shop_id+"/profile";
    }

    @RequestMapping("/account/{shop_id}/feedbacks")
    public String viewFeedBacks(Model m, Principal p, @PathVariable int shop_id) {

        User u = userDao.findByUsername(p.getName());
        m.addAttribute("user", u);
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";

        m.addAttribute("reviews", orderDao.getReviewsForShopID(shop_id));
        return "accountFeedbacks";
    }

    @RequestMapping("/account/{shop_id}/inventory")
    public String viewInventory(Model m, Principal p, @PathVariable int shop_id) {

        User u = userDao.findByUsername(p.getName());
        m.addAttribute("user", u);
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";

        m.addAttribute("items" , shopDao.getInventoryByID(shop_id));
        return "accountInventory";
    }

    @GetMapping("/account/{shop_id}/inventory/add")
    public String addInventory(Model m, Principal p, @PathVariable int shop_id) {

        User u = userDao.findByUsername(p.getName());
        m.addAttribute("user", u);
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";

        m.addAttribute("shop_id", shop_id);
        m.addAttribute("drugs", drugDao.listAllDrugs());
        m.addAttribute("item", new ShopInventory());
        return "addInventory";
    }

    @PostMapping("/account/{shop_id}/inventory/add")
    public String addInventory(@ModelAttribute("item") ShopInventory si, Principal p, @PathVariable int shop_id) {

        User u = userDao.findByUsername(p.getName());
        if (!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";

        shopDao.saveInventoryItem(si.getQuantity(), si.getPrice(), si.getBatch_id(), shop_id);
        return "redirect:/account/" + shop_id + "/profile";
    }

    @GetMapping("/account/add_drug")
    public String addDrug(Model m, Principal p) {

        User u = userDao.findByUsername(p.getName());
        if(!"shop".equals(u.getRole()))
            return "redirect:/self/shops";

        m.addAttribute("drug", new Drugs());
        List<GenericDrug> gen_drugs = drugDao.listAllGenDrugs();
        m.addAttribute("gen_drugs", gen_drugs);
        return "addDrug";
    }

    @PostMapping("/account/add_drug")
    public String addDrug( @ModelAttribute("drugs") Drugs d, Principal p){

        User u = userDao.findByUsername(p.getName());
        if(!u.getRole().equals("shop"))
            return "redirect:/self/shops";

        drugDao.saveDrug(d.getName(), d.getMf_name(), d.getIs_generic(), d.getGen_id());
        return "redirect:/self/shops";
    }

    @GetMapping("/account/{shop_id}/add_drug_batch")
    public String addDrugBatch(Model m, Principal p, @PathVariable int shop_id){

        User u = userDao.findByUsername(p.getName());
        if(!"shop".equals(u.getRole()))
            return "redirect:/self/shops";

        m.addAttribute("shop_id", shop_id);
        m.addAttribute("drugBatch", new DrugBatch());
        return "addDrugBatch";
    }

    @PostMapping("/account/{shop_id}/add_drug_batch")
    public String addDrugBatch(@ModelAttribute("drugBatch") DrugBatch db, Principal p, @PathVariable int shop_id){

        User u = userDao.findByUsername(p.getName());
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";

        shopDao.saveDrugBatch(db.getBatch_no(), db.getMfg_date(), db.getExp_date(), db.getDrug_id());
        return "redirect:/account/"+shop_id+"/profile";
    }


//    @GetMapping("/account/{shop_id}/add_owner")
//    public String addOwner(Model m, Principal p, @PathVariable int shop_id){
//
//        User u = userDao.findByUsername(p.getName());
//        m.addAttribute("user", u);
//        if(!shopDao.isOwner(u.getUser_id(), shop_id))
//            return "redirect:/self/shops";
//
//        return "addOwner";
//    }
//
//    @PostMapping("/account/{shop_id}/add_owner")
//    public String addOwner(@RequestParam String email_id, Principal p, @PathVariable int shop_id){
//
//        User u = userDao.findByUsername(p.getName());
//        if(!shopDao.isOwner(u.getUser_id(), shop_id))
//            return "redirect:/self/shops";
//
//        return "redirect:/account/"+shop_id+"/profile";
//    }
//
//    @RequestMapping("/account/{shop_id}/owners")
//    public String accountOwners(Model m, Principal p, @PathVariable int shop_id){
//
//        User u = userDao.findByUsername(p.getName());
//        m.addAttribute("user", u);
//        if(!shopDao.isOwner(u.getUser_id(), shop_id))
//            return "redirect:/self/shops";
//        m.addAttribute("owners", shopDao.getOwnersByShopID(shop_id));
//        return "accountOwners";
//    }

    @RequestMapping("/account/{shop_id}/orders")
    public String accountOrders(Model m, Principal p, @PathVariable int shop_id){

        User u = userDao.findByUsername(p.getName());
        m.addAttribute("user", u);
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";

        m.addAttribute("orders", orderDao.getOrdersByShopID(shop_id));
        return "accountOrders";
    }
}
