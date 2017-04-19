package com.registration.reg.web;

import com.registration.reg.model.Role;
import com.registration.reg.model.User;
import com.registration.reg.requestBody.UserRequestBody;
import com.registration.reg.service.RoleService;
import com.registration.reg.service.RoleServiceImpl;
import com.registration.reg.service.SecurityService;
import com.registration.reg.service.UserService;
import com.registration.reg.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "welcome";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "welcome";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "welcome";
    }

    @RequestMapping(value = "/welcome/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
            return "redirect:/welcome";
        }

        return "welcome";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";

    }


    @RequestMapping(value = "/admin/users/users", method = RequestMethod.GET)
    public ModelAndView findAllUsers() {
        List<User> usersList = userService.findAll();
        ModelAndView model = new ModelAndView("/admin/users/users");
        model.addObject("usersList", usersList);

        return model;
    }

    @RequestMapping(value = "/admin/users/userUpdate/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable Long id, Model model) {
        model.addAttribute("userForm", new UserRequestBody());
        model.addAttribute("user", userService.get(id));
        model.addAttribute("roles", roleService.findAll());

        return "/admin/users/userUpdate";
    }


    @RequestMapping(value = "/admin/users/userUpdate/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable Long id, @ModelAttribute("userForm") UserRequestBody userForm, BindingResult bindingResult, ModelMap model) {
        userValidator.validateUpdate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userService.get(id));
            model.addAttribute("roles", roleService.findAll());

            return "/admin/users/userUpdate";
        }

        userService.update(id, userForm);

        return "redirect:/admin/users/users";
    }

}

