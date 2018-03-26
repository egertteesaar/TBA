package com.example.TBA.repository;

import com.example.TBA.model.User;
import com.example.TBA.model.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserBean, String>{

    UserBean findByEmail(final String email);

}
