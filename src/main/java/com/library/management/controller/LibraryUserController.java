package com.library.management.controller;

import com.library.management.model.LibraryUser;
import com.library.management.service.LibraryUserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class LibraryUserController {

    @Autowired
    private LibraryUserService userService;
    @Autowired
    private com.library.management.service.BorrowingService borrowingService;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new LibraryUser());
        return "users/add";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("user") LibraryUser user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/add";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        LibraryUser user = userService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid @ModelAttribute("user") LibraryUser user, BindingResult result) {
        if (result.hasErrors()) {
            return "users/edit";
        }
        user.setId(id);
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/profile/{id}")
    public String viewProfile(@PathVariable("id") Long id, Model model) {
        LibraryUser user = userService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        model.addAttribute("borrowings", borrowingService.getAllBorrowings().stream().filter(b -> b.getUser().getId().equals(id)).toList());
        return "users/profile";
    }
}
