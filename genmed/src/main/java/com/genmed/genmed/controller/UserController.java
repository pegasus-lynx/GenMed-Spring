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

    @RequestMapping("/user/profile")
    public String userProfile(Model model, Principal p) {
        int user_id = userDao.getUserIDByEmailID(p.getName());
        model.addAttribute("user", userDao.getUserDetailByID(user_id));
        model.addAttribute("addresses", addressDao.getAddressesByID(user_id));
        return "userProfile";
    }

    @GetMapping("/user/add_address")
    public String addAddress(Model model) {
        model.addAttribute("address", new Address());
        return "address";
    }

    @PostMapping("/user/add_address")
    public String addAddress(@ModelAttribute("address") Address address) {
        return "redirect:/user/profile";
    }

    @GetMapping("user/create_shop")
    public String addShop(Model model) {
        model.addAttribute("shop", new Shop());
        return "addShop";
    }

    @PostMapping("/user/add_address")
    public String addShop(@ModelAttribute("shop") Shop shop) {
        return "redirect:/user/profile";
    }

    @GetMapping("/user/orders")
    public String userOrders(Model model){
        int user_id = userDao.getUserIDByEmailID(p.getName());
        model.addAttribute("orders", userDao.getOrdersByUserID(user_id));
        return "userOrders";
    }

    @RequestMapping("/user/order/{order_id}")
    public String userOrderDetail(Model model){
        model.addAttribute("order", userDao.getOrderByID(order_id));
        return "userOrderDetail";
    }

    @GetMapping("/user/order/{order_id}/add_review")
    public String userOrderAddReview(Model model){
        model.addAttribute("reviews", new Reviews());
        return "addReview";
    }

    @PostMapping("/user/order/{order_id}/add_review")
    public String userOrderAddReview(@ModelAttribute("reviews") Reviews reviews, @PathVariable int order_id) {
        return "redirect:/user/order/"+order_id;
    }

}
