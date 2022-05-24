package org.example.controllers;

import org.example.dao.MerchDAO;
import org.example.dao.UserDAO;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping()
public class StartController {
    private final UserDAO userDAO;
    private final MerchDAO merchDAO;

    @Autowired
    public StartController(UserDAO userDAO,MerchDAO merchDAO) {
        this.merchDAO=merchDAO;
        this.userDAO = userDAO;
    }


    @GetMapping
    public String firstPage() {
        return "index";
    }
    @GetMapping("/2")
    public String secondPage(Model model) {
        model.addAttribute("merchAll", merchDAO.index());
        return "/merch/merchAll";
    }

    @GetMapping("/3")
    public String thirdPage() {
        return "logIn/bunina";
    }
    @GetMapping("/4")
    public String fourPage() {
        return "logIn/stich";
    }

    @GetMapping("/1")
    public String LogIn(@ModelAttribute("user") User user) {

        return "logIn/authorization";
    }
    @PostMapping("/1")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "index";
        ArrayList<User> sad =(ArrayList<User>) userDAO.index();
        for (int i=0; i<sad.size(); i++){
            if ((user.getLogin().equals(sad.get(i).getLogin())) &&
                    (sad.get(i).getPassword().equals(user.getPassword())) &&
                    (sad.get(i).getRole() == 1))
                return "redirect:/3";
            if ((user.getLogin().equals(sad.get(i).getLogin())) &&
                    (sad.get(i).getPassword().equals(user.getPassword())) &&
                    (sad.get(i).getRole() == 2))
                return "redirect:/4";
        }
        return "index";
    }
}