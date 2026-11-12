package com.cesul.barber.services;

import com.cesul.barber.dtos.UserCreationDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersService {

    private List<UserCreationDTO> users = new ArrayList<>();

    public UserCreationDTO create(UserCreationDTO user) {
        user.setId(UUID.randomUUID());
        this.users.add(user);

        return user;
    }

    public List<UserCreationDTO> findAll() {
        return this.users;
    }

    public Optional<UserCreationDTO> findById(UUID id) {

    }

    public Optional<UserCreationDTO> update(UUID id, Map<String, String> request) {
        Optional<UserCreationDTO> userOptional = ;
    }

    public Optional<UserCreationDTO> updateAvatar(UUID id, Map<String, String> request) {
        Optional<UserCreationDTO> userOptional = ;
    }

    public boolean delete(UUID id) {

    }

    public boolean clear() {

    }
}
