package com.example.sprotte.user.service;

import com.example.sprotte.datahandling.employee.services.RoleServiceImpl;
import com.example.sprotte.entity.Role;
import com.example.sprotte.datahandling.employee.repository.EmployeeRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    EmployeeRoleRepository roleRepository;

    @InjectMocks
    RoleServiceImpl service;

    @Test
    void findByDescription() {
        String roleDescription = "ADMIN";
        Role role = new Role();
        given(roleRepository.findByDescription(roleDescription)).willReturn(role);

        Role foundRole = service.findByDescription(roleDescription);

        assertThat(foundRole).isNotNull();
        then(roleRepository).should().findByDescription(anyString());
    }
}