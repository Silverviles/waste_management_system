package com.csse.waste_management.repository;

import com.csse.waste_management.dao.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
