package com.genmed.genmed.controller;


import com.genmed.genmed.model.Address;
import com.genmed.genmed.model.Reviews;
import com.genmed.genmed.model.Shop;
import com.genmed.genmed.model.User;
import com.genmed.genmed.repository.AddressDao;
import com.genmed.genmed.repository.OrderDao;
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

    @Autowired
    private OrderDao orderDao;

    @RequestMapping("/self/profile")
    public String userProfile(Model model, Principal p) {
        int user_id = userDao.getUserIDByEmailID(p.getName());
        model.addAttribute("user", userDao.getUserDetailByID(user_id));
        model.addAttribute("addresses", addressDao.getAddressesByUserID(user_id));
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

    @GetMapping("/self/add_phone")
    public String addPhone(Model model, Principal p){
        return "userPhone";
    }

    @PostMapping("/self/add_phone")
    public String addPhone(@RequestParam String phone_no, Principal p){
        int user_id = userDao.getUserIDByEmailID(p.getName());
        userDao.saveUserPhone(phone_no, user_id);
        return "redirect:/self/profile";
    }


    @GetMapping("/self/orders")
    public String userOrders(Model model, Principal p){
        int user_id = userDao.getUserIDByEmailID(p.getName());
        model.addAttribute("orders", userDao.getOrdersByUserID(user_id));
        return "userOrders";
    }

    @RequestMapping("/self/order/{order_id}")
    public String userOrderDetail(Model model, @PathVariable int order_id){
        model.addAttribute("order", orderDao.getOrderByID(order_id));
        return "userOrdersDetail";
    }

    @GetMapping("/self/order/{order_id}/add_review")
    public String userOrderAddReview(Model model, @PathVariable int order_id){
        model.addAttribute("order_id", order_id);
        model.addAttribute("reviews", new Reviews());
        return "userReview";
    }

    @PostMapping("/self/order/{order_id}/add_review")
    public String userOrderAddReview(@ModelAttribute("reviews") Reviews r, @PathVariable int order_id, Principal p) {
        userDao.saveUserReview(r.getComment(), r.getRating(), r.getOrder_id());
        return "redirect:/user/order/"+order_id;
    }

    @GetMapping("/self/add_shop")
    public String addShop(Model model, Principal p) {
        model.addAttribute("shop", new Shop());
        model.addAttribute("address", new Address());
        return "createShop";
    }

    @PostMapping("/self/add_shop")
    public String addShop(@ModelAttribute("shop") Shop s, @ModelAttribute("address") Address a, Principal p) {

        int address_id = addressDao.saveAddress(a.getPlot_no(), a.getStreet(), a.getCity(), a.getDistrict(), a.getState(), a.getLatitude(), a.getLongitude());
        int shop_id = shopDao.save(s.getShop_name(), s.getEmail_id(), s.getLicense(), address_id);
        int user_id = userDao.getUserIDByEmailID(p.getName());

        // Include code for checking if the same address is present in the database

        shopDao.addOwner(user_id, shop_id);
        userDao.changeRoleToShop(user_id);
        return "redirect:/self/shops";
    }

    @RequestMapping("/self/shops")
    public String selfShops(Model model, Principal p){
        User u = userDao.findByUsername(p.getName());
        model.addAttribute("user", u);
        model.addAttribute("shops", userDao.getShopsOfUser(u.getUser_id()));
        return "userShops";
    }

}
