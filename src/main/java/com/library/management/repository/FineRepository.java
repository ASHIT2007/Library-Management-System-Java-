package com.library.management.repository;

import com.library.management.model.Fine;
import com.library.management.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FineRepository extends JpaRepository<Fine, Long> {
    List<Fine> findByUserAndPaid(LibraryUser user, boolean paid);
}
