package com.genmed.genmed.controller;


import com.genmed.genmed.model.Address;
import com.genmed.genmed.model.Reviews;
import com.genmed.genmed.model.Shop;
import com.genmed.genmed.repository.AddressDao;
import com.genmed.genmed.repository.ShopDao;
import com.genmed.genmed.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private AddressDao addressDao;

    @RequestMapping("/self/profile")
    public String userProfile(Model model, Principal p) {
        int user_id = userDao.getUserIDByEmailID(p.getName());
        model.addAttribute("user", userDao.getUserDetailByID(user_id));
        model.addAttribute("addresses", addressDao.getAddressesByID(user_id));
        return "userProfile";
    }

    @GetMapping("/self/add_address")
    public String addAddress(Model model, Principal p) {
        model.addAttribute("address", new Address());
        return "userAddress";
    }

    @PostMapping("/self/add_address")
    public String addAddress(@ModelAttribute("address") Address a, Principal p) {
        int address_id = addressDao.saveAddress(a.getPlot_no(), a.getStreet(), a.getCity(), a.getDistrict(), a.getState(), a.getLatitude(), a.getLongitude());
        addressDao.saveAddressOfUser(address_id, a.getAddress_type(), userDao.getUserIDByEmailID(p.getName()));
        return "redirect:/self/profile";
    }


    @GetMapping("/self/orders")
    public String userOrders(Model model, Principal p){
        int user_id = userDao.getUserIDByEmailID(p.getName());
//        model.addAttribute("orders", userDao.getOrdersByUserID(user_id));
        return "userOrders";
    }

    @RequestMapping("/self/order/{order_id}")
    public String userOrderDetail(Model model){
//        model.addAttribute("order", userDao.getOrderByID(order_id));
        return "userOrderDetail";
    }

    @GetMapping("/self/order/{order_id}/add_review")
    public String userOrderAddReview(Model model){
        model.addAttribute("reviews", new Reviews());
        return "addReview";
    }

    @PostMapping("/self/order/{order_id}/add_review")
    public String userOrderAddReview(@ModelAttribute("reviews") Reviews reviews, @PathVariable int order_id) {
        return "redirect:/user/order/"+order_id;
    }

    // Consult before doing this part of the project

    @GetMapping("/self/add_shop")
    public String addShop(Model model, Principal p) {
        model.addAttribute("shop", new Shop());
        return "addShop";
    }

    @PostMapping("/self/add_shop")
    public String addShop(@ModelAttribute("shop") Shop shop, Principal p) {
        return "redirect:/user/profile";
    }

}
