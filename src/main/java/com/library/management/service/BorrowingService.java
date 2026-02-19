package com.library.management.service;

import com.library.management.model.Book;
import com.library.management.model.Borrowing;
import com.library.management.model.LibraryUser;
import com.library.management.repository.BookRepository;
import com.library.management.repository.BorrowingRepository;
import com.library.management.repository.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryUserRepository userRepository;

    public List<Borrowing> getAllBorrowings() {
        return borrowingRepository.findAll();
    }

    @Transactional
    public Borrowing borrowBook(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        LibraryUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book not available");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        Borrowing borrowing = new Borrowing();
        borrowing.setBook(book);
        borrowing.setUser(user);
        borrowing.setBorrowDate(LocalDate.now());
        borrowing.setReturnDate(LocalDate.now().plusWeeks(2)); // Default 2 weeks
        borrowing.setStatus(Borrowing.BorrowStatus.BORROWED);

        return borrowingRepository.save(borrowing);
    }

    @Transactional
    public void returnBook(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found"));

        if (borrowing.getStatus() == Borrowing.BorrowStatus.RETURNED) {
            throw new RuntimeException("Book already returned");
        }

        borrowing.setStatus(Borrowing.BorrowStatus.RETURNED);
        borrowing.setActualReturnDate(LocalDate.now());
        borrowingRepository.save(borrowing);

        Book book = borrowing.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
    }

    @Transactional
    public Borrowing renew(Long borrowingId, int weeks) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found"));
        if (borrowing.getStatus() != Borrowing.BorrowStatus.BORROWED) {
            throw new RuntimeException("Cannot renew a non-active borrowing");
        }
        borrowing.setReturnDate(borrowing.getReturnDate().plusWeeks(weeks));
        return borrowingRepository.save(borrowing);
    }
}
