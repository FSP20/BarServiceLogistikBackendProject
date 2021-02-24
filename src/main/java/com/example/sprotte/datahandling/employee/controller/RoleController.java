package com.example.sprotte.datahandling.employee.controller;

import com.example.sprotte.datahandling.employee.services.RoleService;
import com.example.sprotte.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/getroles")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    @PostMapping("/saverole/{role}")
    public Role saveRole(@PathVariable("role") String role) {
        return roleService.saveRole(role);
    }

    @GetMapping("/getrole/{roleid}")
    public Role findRoleById(@PathVariable("roleid") Long roleId) {
        return roleService.findRoleById(roleId);
    }

    @GetMapping("/getrolebyname/{role}")
    public Role findRoleByName(@PathVariable("role") String role) {
        return roleService.findByRoleName(role);
    }

    @PutMapping("/updaterole/{id}/{roleName}")
    public Role updateRole(@PathVariable("id") Long roleId, @PathVariable("roleName") String roleName) {
        return roleService.updateRoleById(roleId, roleName);
    }

    @DeleteMapping("/deleterole/{id}")
    public String deleteRoleById(@PathVariable("id") Long roleId) {
        return roleService.deleteRoleById(roleId);
    }
}
