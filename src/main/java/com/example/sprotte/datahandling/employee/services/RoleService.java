package com.example.sprotte.datahandling.employee.services;

import com.example.sprotte.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();

    Role saveRole(String role);

    Role findRoleById(Long roleId);

    Role findByRoleName(String role);

    Role updateRoleById(Long roleId, String role);

    String deleteRoleById(Long roleId);
}
