package com.library.management.controller;

import com.library.management.service.BookService;
import com.library.management.service.BorrowingService;
import com.library.management.service.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @Autowired
    private BookService bookService;

    @Autowired
    private LibraryUserService userService;

    @GetMapping
    public String listBorrowings(Model model) {
        model.addAttribute("borrowings", borrowingService.getAllBorrowings());
        return "borrowings/list";
    }

    @GetMapping("/borrow")
    public String showBorrowForm(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("users", userService.getAllUsers());
        return "borrowings/borrow";
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Long bookId, @RequestParam Long userId) {
        borrowingService.borrowBook(bookId, userId);
        return "redirect:/borrowings";
    }

    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable("id") Long id) {
        borrowingService.returnBook(id);
        return "redirect:/borrowings";
    }

    @GetMapping("/renew/{id}")
    public String renew(@PathVariable("id") Long id, @RequestParam(defaultValue = "2") int weeks) {
        borrowingService.renew(id, weeks);
        return "redirect:/borrowings";
    }
}
