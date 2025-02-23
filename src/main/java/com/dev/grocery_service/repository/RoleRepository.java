package com.dev.grocery_service.repository;

import com.dev.grocery_service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.DoubleStream;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
