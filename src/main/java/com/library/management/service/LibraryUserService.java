package com.library.management.service;

import com.library.management.model.LibraryUser;
import com.library.management.repository.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryUserService {

    @Autowired
    private LibraryUserRepository userRepository;

    public List<LibraryUser> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<LibraryUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public LibraryUser saveUser(LibraryUser user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
