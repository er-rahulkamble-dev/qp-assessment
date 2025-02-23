package com.dev.grocery_service.repository;

import com.dev.grocery_service.dto.UserDto;
import com.dev.grocery_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles LEFT JOIN FETCH u.orders")
    List<User> findAllUsersWithRolesAndOrders();
}
