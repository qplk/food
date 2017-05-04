package com.registration.reg.web;

import com.registration.reg.model.Order;
import com.registration.reg.model.Role;
import com.registration.reg.model.User;
import com.registration.reg.requestBody.UserRequestBody;
import com.registration.reg.service.*;
import com.registration.reg.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserValidator userValidator;

    User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal().toString().equals("anonymousUser")) {
            return null;
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUsername = userDetails.getUsername();
        return userService.findByUsername(authenticatedUsername);
    }

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

    @RequestMapping(value = "/authenticatedUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity userId(){
        User user = getCurrentUser();

        if (user == null) {
            return null;
        }

        user.setRoles(null);
        Long orderId = 0L;
        Set<Order> orderSet = user.getOrders();
        for(Order item: orderSet){
            orderId = item.getOrderId();
        }
        user.setInformation(orderId.toString());
        user.setOrders(null);
        ResponseEntity responseEntity = new ResponseEntity(user, HttpStatus.OK);

        return responseEntity;
    }


    @RequestMapping(value = "/admin/users/users", method = RequestMethod.GET)
    public ModelAndView findAllUsers() {
        List<User> usersList = userService.findAll();
        ModelAndView model = new ModelAndView("/admin/users/users");
        model.addObject("usersList", usersList);

        return model;
    }

    @RequestMapping(value = "/admin/users/userUpdate/{userId}", method = RequestMethod.GET)
    public String updateUser(@PathVariable Long userId, Model model) {
        model.addAttribute("userForm", new UserRequestBody());
        model.addAttribute("user", userService.get(userId));
        model.addAttribute("roles", roleService.findAll());

        return "/admin/users/userUpdate";
    }


    @RequestMapping(value = "/admin/users/userUpdate/{userId}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable Long userId, @ModelAttribute("userForm") UserRequestBody userForm, BindingResult bindingResult, ModelMap model) {
        userValidator.validateUpdate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userService.get(userId));
            model.addAttribute("roles", roleService.findAll());

            return "/admin/users/userUpdate";
        }

        userService.update(userForm);

        return "redirect:/admin/users/users";
    }

    @RequestMapping(value = "/profile/profile", method = RequestMethod.GET)
    public String profile(Model model) {
        User user = getCurrentUser();

        if (user == null) {
            return "redirect:/welcome/login";
        }

        model.addAttribute("user", user);
        model.addAttribute("formed", orderService.findByUserAndStatus(user.getUserId(), "Formed"));
        model.addAttribute("delivering", orderService.findByUserAndStatus(user.getUserId(), "Delivering"));
        model.addAttribute("delivered", orderService.findByUserAndStatus(user.getUserId(), "Delivered"));

        return "/profile/profile";
    }


    @RequestMapping(value = "/profile/updateInformation", method = RequestMethod.GET)
    public String updateInformation(Model model) {
        User user = getCurrentUser();

        if (user == null) {
            return "redirect:/welcome/login";
        }

        model.addAttribute("user", user);
        model.addAttribute("userForm", new UserRequestBody());

        return "/profile/updateInformation";
    }

    @RequestMapping(value = "/profile/updateInformation", method = RequestMethod.PUT)
    public String updateInformation(@ModelAttribute("userForm") UserRequestBody userForm, BindingResult bindingResult, ModelMap model) {
        User user = getCurrentUser();

        if (user != null) {
            userForm.setUserId(user.getUserId());
        }
        userValidator.validateUpdate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);

            return "/profile/updateInformation";
        }

        userService.update(userForm);

        return "redirect:/profile/profile";
    }


    @RequestMapping(value = "/profile/updatePassword", method = RequestMethod.GET)
    public String updatePassword(Model model) {
        User user = getCurrentUser();

        if (user == null) {
            return "redirect:/welcome/login";
        }

        model.addAttribute("user", user);
        model.addAttribute("passwordForm", new UserRequestBody());

        return "/profile/updatePassword";
    }

    @RequestMapping(value = "/profile/updatePassword", method = RequestMethod.PUT)
    public String updatePassword(@ModelAttribute("passwordForm") UserRequestBody userForm, BindingResult bindingResult, ModelMap model) {
        User user = getCurrentUser();

        if (user != null) {
            userForm.setUserId(user.getUserId());
        }
        userValidator.validatePassword(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            System.out.println("Errors");
            for (ObjectError error: bindingResult.getAllErrors()) {
                System.out.println(error.toString());
            }

            return "/profile/updatePassword";
        }

        userService.update(userForm);

        return "redirect:/profile/profile";
    }

}