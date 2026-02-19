package com.library.management.service;

import com.library.management.model.Borrowing;
import com.library.management.model.Fine;
import com.library.management.model.LibraryUser;
import com.library.management.repository.BorrowingRepository;
import com.library.management.repository.FineRepository;
import com.library.management.repository.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class FineService {

    @Autowired
    private FineRepository fineRepository;
    @Autowired
    private LibraryUserRepository userRepository;
    @Autowired
    private BorrowingRepository borrowingRepository;

    public Fine recordFine(Long userId, Long borrowingId, BigDecimal amount) {
        LibraryUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Borrowing borrowing = borrowingRepository.findById(borrowingId).orElseThrow(() -> new RuntimeException("Borrowing not found"));
        Fine fine = new Fine();
        fine.setUser(user);
        fine.setBorrowing(borrowing);
        fine.setAmount(amount);
        fine.setPaid(false);
        fine.setCreatedAt(LocalDateTime.now());
        return fineRepository.save(fine);
    }

    public Fine markPaid(Long fineId) {
        Fine fine = fineRepository.findById(fineId).orElseThrow(() -> new RuntimeException("Fine not found"));
        fine.setPaid(true);
        fine.setPaidAt(LocalDateTime.now());
        return fineRepository.save(fine);
    }
}
