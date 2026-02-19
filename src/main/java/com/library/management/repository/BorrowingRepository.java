package com.library.management.repository;

import com.library.management.model.Borrowing;
import com.library.management.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findByUser(LibraryUser user);
    List<Borrowing> findByStatus(Borrowing.BorrowStatus status);
}
