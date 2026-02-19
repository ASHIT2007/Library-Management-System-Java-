package com.library.management.service;

import com.library.management.model.Book;
import com.library.management.model.Hold;
import com.library.management.model.LibraryUser;
import com.library.management.repository.BookRepository;
import com.library.management.repository.HoldRepository;
import com.library.management.repository.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HoldService {

    @Autowired
    private HoldRepository holdRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryUserRepository userRepository;

    public Hold placeHold(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        LibraryUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Hold hold = new Hold();
        hold.setBook(book);
        hold.setUser(user);
        hold.setCreatedAt(LocalDateTime.now());
        hold.setStatus(Hold.HoldStatus.ACTIVE);
        return holdRepository.save(hold);
    }

    public List<Hold> getActiveHoldsForBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        return holdRepository.findByBookAndStatus(book, Hold.HoldStatus.ACTIVE);
    }
}
