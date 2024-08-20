package com.csse.waste_management.service;

import com.csse.waste_management.dao.User;

public interface UserService {
    public Boolean isUserExists(String usernameOrEmail);
    public User getUserByUsername(String username);
    public User getUserByEmail(String email);
    public User saveUser(User user);
    public User getUserById(Long id);
    public void deleteUser(User user);
    public void deleteUserById(Long id);
}
