package com.dev.grocery_service.mapper;

import com.dev.grocery_service.dto.RoleDto;
import com.dev.grocery_service.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {

    public abstract RoleDto toRoleDto(Role role);

    public abstract Role toRole(RoleDto dto);

}
