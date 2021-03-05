package org.java.web.controller;

import org.java.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.java.web.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserController(){};


    @GetMapping("/start")
    public String start(ModelMap modelMap) {
        List<User> userList = userService.usersList();
        modelMap.addAttribute("listUsers", userList);
        return "start";
    }


    @GetMapping("/create")
    public String newUser(Map<String, Object> model) {
        model.put("user", new User());
        return "create";
    }

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.createNewUser(user);
        return "redirect:/user/start";
    }

    @GetMapping("/edit")
    public ModelAndView updateUserForm(@RequestParam(name = "id") long id) {
        ModelAndView modelAndView = new ModelAndView("update");
        User user = userService.readUser(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(value = "/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/user/start";
    }

    @GetMapping("/delete")
    public String deleteUserForm(@RequestParam(name = "id") long id) {
        userService.deleteUser(id);
        return "redirect:/user/start";
    }

}
