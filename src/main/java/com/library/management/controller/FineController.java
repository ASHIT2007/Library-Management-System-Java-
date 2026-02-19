package com.library.management.controller;

import com.library.management.service.BorrowingService;
import com.library.management.service.FineService;
import com.library.management.service.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/fines")
public class FineController {

    @Autowired
    private FineService fineService;
    @Autowired
    private BorrowingService borrowingService;
    @Autowired
    private LibraryUserService userService;

    @GetMapping("/record")
    public String showRecordForm(@RequestParam Long borrowingId, Model model) {
        model.addAttribute("borrowing", borrowingService.getAllBorrowings().stream().filter(b -> b.getId().equals(borrowingId)).findFirst().orElse(null));
        model.addAttribute("users", userService.getAllUsers());
        return "fines/record";
    }

    @PostMapping("/record")
    public String record(@RequestParam Long userId, @RequestParam Long borrowingId, @RequestParam BigDecimal amount) {
        fineService.recordFine(userId, borrowingId, amount);
        return "redirect:/borrowings";
    }

    @PostMapping("/pay/{id}")
    public String pay(@PathVariable("id") Long id) {
        fineService.markPaid(id);
        return "redirect:/borrowings";
    }
}
