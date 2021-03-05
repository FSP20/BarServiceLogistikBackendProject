package com.example.sprotte.datahandling.employee.services;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.employee.repository.EmployeeRoleRepository;
import com.example.sprotte.entity.Role;
import com.example.sprotte.errorhandling.Employee.IllegalRoleException;
import com.example.sprotte.errorhandling.Employee.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    EmployeeRoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
        List<Role> roles = roleRepository.findAll();
        if(roles.size() == 0)
            throw new RoleNotFoundException(ResponseMessageConstants.ROLE_NOT_FOUND);

        return roles;
    }

    @Override
    public Role saveRole(String roleName) {
        if(roleName == null)
            throw new RuntimeException(ResponseMessageConstants.ROLE_IS_EMPTY);

        // Proof Description Role already exist
        Role role = findRoleByDescription(roleName);
        if(role != null)
            throw new IllegalRoleException(ResponseMessageConstants.ROLE_ALREADY_EXIST);

        role = new Role();
        role.setDescription(roleName);

        return roleRepository.save(role);
    }

    @Override
    public Role findRoleById(Long roleId) {
        Role role = findById(roleId);

        return role;
    }

    @Override
    public Role findByRoleName(String roleDescription) {
        Role role = findRoleByDescription(roleDescription);
        if(role == null)
            throw new RoleNotFoundException(ResponseMessageConstants.ROLE_NOT_FOUND);

        return role;
    }

    @Override
    public Role updateRoleById(Long roleId, String roleName) {
        if(roleName == null)
            throw new RuntimeException(ResponseMessageConstants.ROLE_IS_EMPTY);

        Role role = findById(roleId);

        role.setDescription(roleName);

        return roleRepository.save(role);
    }

    @Override
    public String deleteRoleById(Long roleId) {
        Role role = findById(roleId);

        roleRepository.deleteById(roleId);

        return ResponseMessageConstants.ROLE_SUCCESSFULLY_DELETE;
    }

    public Role findById(Long roleId){
        Role role =  roleRepository.findById(roleId).orElse(null);
        if(role == null)
            throw new RoleNotFoundException(ResponseMessageConstants.ROLE_NOT_FOUND);

        return role;
    }

    public Role findRoleByDescription(String role) {
        return roleRepository.findByDescription(role);
    }
}
