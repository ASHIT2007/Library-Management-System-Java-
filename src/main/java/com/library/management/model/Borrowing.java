package com.library.management.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private LibraryUser user;

    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate actualReturnDate;
    
    @Enumerated(EnumType.STRING)
    private BorrowStatus status;

    public enum BorrowStatus {
        BORROWED, RETURNED, OVERDUE
    }
}
