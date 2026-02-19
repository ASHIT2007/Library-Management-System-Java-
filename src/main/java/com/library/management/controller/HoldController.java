package com.library.management.controller;

import com.library.management.service.BookService;
import com.library.management.service.HoldService;
import com.library.management.service.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/holds")
public class HoldController {

    @Autowired
    private HoldService holdService;
    @Autowired
    private BookService bookService;
    @Autowired
    private LibraryUserService userService;

    @GetMapping("/place")
    public String showPlaceHoldForm(@RequestParam Long bookId, Model model) {
        model.addAttribute("book", bookService.getBookById(bookId).orElseThrow(() -> new RuntimeException("Book not found")));
        model.addAttribute("users", userService.getAllUsers());
        return "holds/place";
    }

    @PostMapping("/place")
    public String placeHold(@RequestParam Long bookId, @RequestParam Long userId) {
        holdService.placeHold(bookId, userId);
        return "redirect:/books";
    }
}
