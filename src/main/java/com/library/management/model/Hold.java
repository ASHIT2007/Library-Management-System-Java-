package com.library.management.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Hold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private LibraryUser user;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private HoldStatus status;

    public enum HoldStatus {
        ACTIVE, FULFILLED, CANCELLED
    }
}
