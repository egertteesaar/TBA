package com.example.TBA.repository;

import com.example.TBA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(final String username);

}
