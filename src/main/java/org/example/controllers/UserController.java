package org.example.controllers;

import org.example.models.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
public class UserController {

    @Autowired
    private ApplicationContext applicationContext;

    private UserService userService;
    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/check-config", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String checkConfig() {
        return applicationContext.containsBean("getTransactionManager")
                ? "JpaTransactionManager найден"
                : "JpaTransactionManager НЕ найден";
    }



    @GetMapping(value = "/users", produces = "text/plain;charset=UTF-8")
    public String getUsers (Model model) {
        List<User> userList = userService.showAllUsers();
        model.addAttribute("users", userList);
        return "users";
    }

    @GetMapping("/users/new")
    public String newUser() {
        return "add-user";
    }

    @PostMapping(value = "/users-add", produces = "text/plain;charset=UTF-8")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("age") int age) {

        User user = new User(name, age);
        userService.addUser(user);

        return "redirect:/users";
    }

    @PostMapping(value = "/users/delete", produces = "text/plain;charset=UTF-8")
    public String deleteUser(@RequestParam("id") long id) {
        userService.removeUser(id);

        return "redirect:/users";
    }
}
