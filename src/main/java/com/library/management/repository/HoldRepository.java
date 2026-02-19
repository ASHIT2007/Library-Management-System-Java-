package com.library.management.repository;

import com.library.management.model.Hold;
import com.library.management.model.Book;
import com.library.management.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoldRepository extends JpaRepository<Hold, Long> {
    List<Hold> findByBookAndStatus(Book book, Hold.HoldStatus status);
    List<Hold> findByUser(LibraryUser user);
}
