package org.example.controllers;

import org.example.dao.MerchDAO;
import org.example.dao.OrderDAO;
import org.example.dao.UserDAO;
import org.example.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping()
public class StartController {
    private String userLogin;
    private final UserDAO userDAO;
    private final MerchDAO merchDAO;
    private final OrderDAO orderDAO;

    @Autowired
    public StartController(UserDAO userDAO, MerchDAO merchDAO, OrderDAO orderDAO) {
        this.merchDAO = merchDAO;
        this.userDAO = userDAO;
        this.orderDAO = orderDAO;
    }


    @GetMapping("user")
    public String UserPage() {
        return "redirect:/UserIntf";
    }


    @GetMapping("/UserIntf/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("merch", merchDAO.show(id));
        return "merch/merchShow";
    }



    @PostMapping("/user/order/new")
    public String createOrder(@ModelAttribute("order") @Valid Order order,
                              BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors())
            return "people/new";

        orderDAO.save(order, userLogin);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping("/UserIntf")
    public String create(@ModelAttribute("merch") @Valid Merch merch,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        merchDAO.save(merch);
        return "redirect:/UserIntf";
    }

    @GetMapping("/UserIntf")
    public String secondPage(Model model, @ModelAttribute("order") @Valid Order order) {
        model.addAttribute("merchAll", merchDAO.index());
        return "/merch/merchAll";
    }

    @GetMapping("/UserIntf/order")
    public String UserOrder(Model model) {
        model.addAttribute("orderAll", orderDAO.search(userLogin));
        return "/order/orderUser";
    }

    @GetMapping("/MengIntf")
    public String mengPage(Model model, @ModelAttribute("order") @Valid Order order) {
        model.addAttribute("orderAll", orderDAO.index());
        return "/order/orderShow";
    }

    @GetMapping("/IIT")
    public String IIT(Model model, @ModelAttribute("order") @Valid Order order) {
        model.addAttribute("merchAll", merchDAO.search("IIT"));
        return "/merch/merchAll";
    }

    @GetMapping("/ITXT")
    public String ITXT(Model model, @ModelAttribute("order") @Valid Order order) {
        model.addAttribute("merchAll", merchDAO.search("ITXT"));
        return "/merch/merchAll";
    }

    @GetMapping("/IKB")
    public String IKB(Model model, @ModelAttribute("order") @Valid Order order) {
        model.addAttribute("merchAll", merchDAO.search("IKB"));
        return "/merch/merchAll";
    }

    @GetMapping("/IRI")
    public String IRI(Model model, @ModelAttribute("order") @Valid Order order) {
        model.addAttribute("merchAll", merchDAO.search("IRI"));
        return "/merch/merchAll";
    }

    @GetMapping("/General")
    public String GENERAL(Model model, @ModelAttribute("order") @Valid Order order) {
        model.addAttribute("merchAll", merchDAO.search("General"));
        return "/merch/merchAll";
    }

    @GetMapping("/ITU")
    public String ITU(Model model, @ModelAttribute("order") @Valid Order order) {
        model.addAttribute("merchAll", merchDAO.search("ITU"));
        return "/merch/merchAll";
    }

    @GetMapping("/III")
    public String III(Model model, @ModelAttribute("order") @Valid Order order) {
        model.addAttribute("merchAll", merchDAO.search("III"));
        return "/merch/merchAll";
    }

    @GetMapping("/IPTIP")
    public String IPTIP(Model model, @ModelAttribute("order") @Valid Order order) {
        model.addAttribute("merchAll", merchDAO.search("IPTIP"));
        return "/merch/merchAll";
    }


    @GetMapping("UserIntf/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("merch", merchDAO.show(id));
        return "merch/merchEdit";
    }

    @GetMapping("MengIntf/{id}")
    public String editOrder(Model model, @PathVariable("id") int id) {
        model.addAttribute("order", orderDAO.show(id));
        return "order/orderId";
    }

    @PatchMapping("MengIntf/merch/{id}")
    public String update(@ModelAttribute("merch") @Valid Merch merch,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        merchDAO.update(id, merch);
        return "redirect:/MengIntf/merch/{id}";
    }

    @PatchMapping("MengIntf/{id}")
    public String changeOrder(@ModelAttribute("order") @Valid Order order,
                              BindingResult bindingResult,
                              @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        orderDAO.update(id, order);
        return "redirect:/MengIntf";
    }

    /*   @GetMapping("/MengIntf/{id}")
       public String showOrder(@PathVariable("id") int id, Model model) {
           model.addAttribute("order", orderDAO.show(id));
           return "order/orderId";
       }*/
    @PatchMapping("UserIntf/{id}")
    public String updateOrder(@ModelAttribute("order") @Valid Order order,
                              BindingResult bindingResult,
                              @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        orderDAO.update(id, order);
        return "redirect:/UserIntf/MerchAll";
    }


    @DeleteMapping("/MengIntf/merch/{id}")
    public String delete(@PathVariable("id") int id) {
        merchDAO.delete(id);
        return "redirect:/MengIntf/MerchAll";
    }

    @GetMapping("/MengIntf/MerchAll/new")
    public String newMerch(@ModelAttribute("merch") @Valid Merch merch) {

        return "merch/merchNew";
    }

    @GetMapping("/MengIntf/MerchAll")
    public String MerchAll(Model model) {
        model.addAttribute("merchAll", merchDAO.index());
        return "/merch/merchAllMeng";
    }

    @PostMapping("/MengIntf/MerchAll")
    public String createMerch(@ModelAttribute("Merch") @Valid Merch merch,
                              BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors())
            return "people/new";

        merchDAO.save(merch);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/MengIntf/merch/{id}")
    public String showMerch(@PathVariable("id") int id, Model model) {
        model.addAttribute("merch", merchDAO.show(id));
        return "merch/merchShowMeng";
    }

    @GetMapping("/MengIntf/merch/{id}/edit")
    public String editMerch(@PathVariable("id") int id, Model model) {
        model.addAttribute("merch", merchDAO.show(id));
        return "merch/merchEdit";
    }

    @GetMapping()
    public String LogIn(@ModelAttribute("user") User user) {

        return "logIn/login";
    }

    @PostMapping("/authorization")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "index";
        ArrayList<User> people = (ArrayList<User>) userDAO.index();
        for (int i = 0; i < people.size(); i++) {
            if ((user.getLogin().equals(people.get(i).getLogin())) &&
                    (people.get(i).getPassword().equals(user.getPassword())) &&
                    (people.get(i).getRole() == 1)) {
                userLogin = people.get(i).getLogin();
                return "redirect:/user";
            }
            if ((user.getLogin().equals(people.get(i).getLogin())) &&
                    (people.get(i).getPassword().equals(user.getPassword())) &&
                    (people.get(i).getRole() == 2)) {
                userLogin = people.get(i).getLogin();
                return "redirect:/MengIntf";
            }
        }
        return "logIn/loginError";
    }
}

