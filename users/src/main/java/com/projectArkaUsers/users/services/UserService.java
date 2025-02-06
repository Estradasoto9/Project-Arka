package com.projectArkaUsers.users.services;

import com.projectArkaUsers.users.dtos.CreateUserDto;
import com.projectArkaUsers.users.dtos.UpdateUserDto;
import com.projectArkaUsers.users.entities.User;
import com.projectArkaUsers.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // create new user
    public User create(CreateUserDto userDto){
        User newUser = new User();
        newUser.setName(userDto.getName());
        newUser.setEmail(userDto.getEmail());
        newUser.setRole(userDto.getRole());
        newUser.setCreationDate(LocalDateTime.now()); //

        return userRepository.save(newUser); // Save User
    }

    // get all users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // get user for id
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    // update use
    public Optional<User> updateUser(Long id, UpdateUserDto userDto){
        return userRepository.findById(id).map(existingUser -> {
            if (userDto.getName() != null) existingUser.setName(userDto.getName());
            if (userDto.getEmail() != null) existingUser.setEmail(userDto.getEmail());
            if (userDto.getRole() != null) existingUser.setRole(userDto.getRole());

            return userRepository.save(existingUser);
        });
    }

    // delete user
    public boolean deleteUser(Long id){
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
