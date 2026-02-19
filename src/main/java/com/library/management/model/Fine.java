package com.library.management.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private LibraryUser user;

    @ManyToOne
    @JoinColumn(name = "borrowing_id")
    private Borrowing borrowing;

    private BigDecimal amount;
    private boolean paid;
    private LocalDateTime createdAt;
    private LocalDateTime paidAt;
}
