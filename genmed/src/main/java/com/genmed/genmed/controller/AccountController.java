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
        m.addAttribute("user_email", p.getName());
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";

        Shop s = shopDao.getShopByID(shop_id);
        Address a = addressDao.getAddressByID(s.getAddress_id());

        m.addAttribute("shop_id", shop_id);
        m.addAttribute("shops", shopDao.getShopsByUserID(u.getUser_id()));

        m.addAttribute("shop", s);
        m.addAttribute("address", a);
        m.addAttribute("phones", shopDao.getPhoneByID(shop_id));
        return "accountProfile";
    }

    @GetMapping("/account/{shop_id}/add_phone")
    public String addPhone(Model m, Principal p, @PathVariable int shop_id) {

        User u = userDao.findByUsername(p.getName());
        m.addAttribute("user", u);
        m.addAttribute("user_email", p.getName());
        m.addAttribute("shop_id", shop_id);
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";

        m.addAttribute("shop_id", shop_id);
        m.addAttribute("shops", shopDao.getShopsByUserID(u.getUser_id()));
        return "accountPhone";
    }

    @PostMapping("/account/{shop_id}/add_phone")
    public String addPhone(@RequestParam String phone_no, Principal p, @PathVariable int shop_id) {

        User u = userDao.findByUsername(p.getName());
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";
        System.out.println(phone_no);
        int user_id = userDao.getUserIDByEmailID(p.getName());
        shopDao.saveShopPhone(phone_no, shop_id);
        return "redirect:/account/"+shop_id+"/profile";
    }

    @RequestMapping("/account/{shop_id}/feedbacks")
    public String viewFeedBacks(Model m, Principal p, @PathVariable int shop_id) {

        User u = userDao.findByUsername(p.getName());
        m.addAttribute("user", u);
        m.addAttribute("user_email", p.getName());
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";

        m.addAttribute("shop_id", shop_id);
        m.addAttribute("shops", shopDao.getShopsByUserID(u.getUser_id()));
        m.addAttribute("reviews", orderDao.getReviewsForShopID(shop_id));
        return "accountFeedbacks";
    }

    @RequestMapping("/account/{shop_id}/inventory")
    public String viewInventory(Model m, Principal p, @PathVariable int shop_id) {

        User u = userDao.findByUsername(p.getName());
        m.addAttribute("user", u);
        m.addAttribute("user_email", p.getName());
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";

        m.addAttribute("shop_id", shop_id);
        m.addAttribute("shops", shopDao.getShopsByUserID(u.getUser_id()));
        m.addAttribute("items" , shopDao.getInventoryByID(shop_id));
        return "accountInventory";
    }

    @GetMapping("/account/{shop_id}/inventory/add")
    public String addInventory(Model m, Principal p, @PathVariable int shop_id) {

        User u = userDao.findByUsername(p.getName());
        m.addAttribute("user", u);
        m.addAttribute("user_email", p.getName());
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";

        m.addAttribute("shop_id", shop_id);
        m.addAttribute("shops", shopDao.getShopsByUserID(u.getUser_id()));
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
        m.addAttribute("user_email", p.getName());
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
        m.addAttribute("shops", shopDao.getShopsByUserID(u.getUser_id()));
        m.addAttribute("user_email", p.getName());
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

    @RequestMapping("/account/{shop_id}/orders")
    public String accountOrders(Model m, Principal p, @PathVariable int shop_id){

        User u = userDao.findByUsername(p.getName());
        m.addAttribute("user_email", p.getName());
        m.addAttribute("user", u);
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";

        m.addAttribute("shop_id", shop_id);
        m.addAttribute("shops", shopDao.getShopsByUserID(u.getUser_id()));
        m.addAttribute("orders", orderDao.getOrdersByShopID(shop_id));
        return "accountOrders";
    }

    @RequestMapping("/account/{shop_id}/order/{order_id}/mark/cancel")
    public String markCancel(@PathVariable int shop_id, @PathVariable int order_id){
        orderDao.changeOrderStatus(order_id,"cancelled");
        shopDao.refillItems(order_id);
        return "redirect:/account/"+shop_id+"/orders";
    }

    @RequestMapping("/account/{shop_id}/order/{order_id}/mark/processing")
    public String markProcessing(@PathVariable int shop_id, @PathVariable int order_id){
        orderDao.changeOrderStatus(order_id,"processing");
        return "redirect:/account/"+shop_id+"/orders";
    }

    @RequestMapping("/account/{shop_id}/order/{order_id}")
    public String viewShopOrder(Model m, Principal p, @PathVariable int shop_id, @PathVariable int order_id){

        User u = userDao.findByUsername(p.getName());
        m.addAttribute("user_email", p.getName());
        m.addAttribute("user", u);
        if(!shopDao.isOwner(u.getUser_id(), shop_id))
            return "redirect:/self/shops";

        m.addAttribute("shop_id", shop_id);
        m.addAttribute("shops", shopDao.getShopsByUserID(u.getUser_id()));
        m.addAttribute("order", orderDao.getOrderByID(order_id));
        return "viewShopOrder";
    }
}
