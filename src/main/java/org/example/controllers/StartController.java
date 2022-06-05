package org.example.controllers;

import org.example.dao.MerchDAO;
import org.example.dao.UserDAO;
import org.example.models.Merch;
import org.example.models.User;
import org.example.repository.MerchEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping()
public class StartController {
    private final UserDAO userDAO;
    private final MerchDAO merchDAO;
    //    @Autowired
    private MerchEntityRepository merchEntityRepository;
   @Autowired
    public StartController(UserDAO userDAO,MerchDAO merchDAO) {
        this.merchDAO=merchDAO;
        this.userDAO = userDAO;
    }
    @GetMapping
    public String firstPage() {
        return "index";
    }

 /*    @GetMapping("222")
    public String qwePage() {
        if (testRepo.findById(5) != null) {
            return "index";
        }
        System.out.println("LOL");
        return "logIn/Kostl";
    }*/

    @GetMapping("222")
    public String qwePage() {
        if (merchEntityRepository.findById(5) != null) {
            return "index";
        }
        System.out.println("LOL");
        return "logIn/Kostl";
    }
    @GetMapping("/2/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("merch", merchDAO.show(id));
        return "merch/merchShow";
    }

    @GetMapping("/2")
    public String secondPage(Model model) {
        model.addAttribute("merchAll", merchDAO.index());
        return "/merch/merchAll";
    }

    /*  @PatchMapping("/2")
    public String update(@ModelAttribute("merch") @Valid Merch merch, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        merchDAO.update(id, merch);
        return "redirect:/2";
    }*/

// 123 попытки сделать кнопки рабочими
    @GetMapping("/123")
    public String fivePage() {
        return "merch/search";
    }

    @PostMapping("/123")
    public String editCustomer(@RequestParam("checkboxName")String[] checkboxValue)
    {
        if(checkboxValue[0] != null)
        {
            System.out.println("checkbox is checked");
            return "redirect:/3";
        }
        else
        {
            System.out.println("checkbox is not checked");
            return "redirect:/4";
        }
    }
    @GetMapping("/3")
    public String thirdPage() {
        return "logIn/bunina";
    }
    @GetMapping("/4")
    public String fourPage() {

        return "logIn/stich";
    }

    @GetMapping("2/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("merch", merchDAO.show(id));
        return "merch/merchEdit";
    }

      @PatchMapping("2/{id}")
    public String update(@ModelAttribute("person") @Valid Merch merch, BindingResult bindingResult,
                         @PathVariable("id") int id) {
          if (bindingResult.hasErrors())
              return "people/edit";

          merchDAO.update(id, merch);
          return "redirect:/2";
      }

    @DeleteMapping("/2/{id}")
    public String delete(@PathVariable("id") int id) {
        merchDAO.delete(id);
        return "redirect:/2";
    }

    @GetMapping("/2/new")
    public String newMerch(@ModelAttribute("merch") Merch merch) {

        return "merch/merchNew";
    }


    @PostMapping("/2")
    public String create(@ModelAttribute("merch") @Valid Merch merch,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

      //  merchDAO.save(merch);
        return "redirect:/2";
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
                return "redirect:/2";
            if ((user.getLogin().equals(sad.get(i).getLogin())) &&
                    (sad.get(i).getPassword().equals(user.getPassword())) &&
                    (sad.get(i).getRole() == 2))
                return "redirect:/4";
        }
        return "index";
    }
}