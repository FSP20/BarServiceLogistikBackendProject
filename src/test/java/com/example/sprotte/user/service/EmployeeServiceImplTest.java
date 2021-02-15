package com.example.sprotte.user.service;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.employee.services.EmployeeServiceImpl;
import com.example.sprotte.dto.employee.EmployeeChangePasswordDto;
import com.example.sprotte.entity.Employee;
import com.example.sprotte.entity.Profile;
import com.example.sprotte.errorhandling.Employee.EmployeeNotFoundException;
import com.example.sprotte.errorhandling.Employee.ProfileNotFoundException;
import com.example.sprotte.datahandling.employee.repository.EmployeeEmployeeRepository;
import com.example.sprotte.datahandling.employee.repository.EmployeeProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.atLeastOnce;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    EmployeeEmployeeRepository employeeRepository;

    @Mock
    EmployeeProfileRepository profileRepository;

    @InjectMocks
    EmployeeServiceImpl service;

    @Test
    void getEmployeeById() {
        Long employeeId = 1l;
        Employee employee = new Employee();
        given(employeeRepository.findById(employeeId)).willReturn(Optional.of(employee));

        Employee newEmployee = service.getEmployeeById(employeeId);

        assertThat(newEmployee).isNotNull();
        then(employeeRepository).should().findById(anyLong());
    }

    @Test
    void getEmployeeByIdThrow() {
        Long employeeId = 1L;
        given(employeeRepository.findById(employeeId)).willThrow(new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEE_NOT_FOUND));

        assertThrows(EmployeeNotFoundException.class, () -> service.getEmployeeById(employeeId));

        then(employeeRepository).should().findById(employeeId);
    }

    @Test
    void deleteEmployeeById() {
        Long employeeId = 1l;

        service.deleteEmployeeById(employeeId);

        then(employeeRepository).should(atLeastOnce()).deleteById(employeeId);
    }

    @Test
    void deleteEmployeeByIdThrow() {
        Long employeeId = 1L;
        willThrow(new RuntimeException("boom")).given(employeeRepository).deleteById(employeeId);

        assertThrows(RuntimeException.class, () -> service.deleteEmployeeById(employeeId));

        then(employeeRepository).should().deleteById(anyLong());
    }

    @Test
    void getEmployeeByFullName() {
        String firstName = "Fabio";
        String lastName = "Sprotte";
        Employee employee = new Employee();
        given(employeeRepository.findByFirstNameAndLastName(firstName, lastName)).willReturn(Optional.of(employee));

        Employee foundEmployee = service.getEmployeeByFullName(firstName, lastName);

        assertThat(foundEmployee).isNotNull();
        then(employeeRepository).should().findByFirstNameAndLastName(anyString(), anyString());
    }

    @Test
    void getEmployeeByFullNameThrow() {
        String firstName = "Fabio";
        String lastName = "Sprotte";
        given(employeeRepository.findByFirstNameAndLastName(firstName, lastName)).
                willThrow(new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEE_NOT_FOUND));

        assertThrows(EmployeeNotFoundException.class, () -> service.getEmployeeByFullName(firstName, lastName));

        then(employeeRepository).should().findByFirstNameAndLastName(anyString(), anyString());
    }

    @Test
    void getEmployees() {
        Employee employee = new Employee();
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        given(employeeRepository.findAll()).willReturn(employees);

        List<Employee> foundEmployees = service.getEmployees();

        assertTrue(foundEmployees.size() > 0);
        then(employeeRepository).should().findAll();
    }

    @Test
    void getEmployeesThrow() {
        List<Employee> employees = new ArrayList<>();
        given(employeeRepository.findAll()).willThrow(new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEES_NOT_FOUND));

        assertThrows(EmployeeNotFoundException.class, () -> service.getEmployees());

        then(employeeRepository).should().findAll();
    }

    @Test
    void findAllProfiles() {
        Profile profile = new Profile();
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile);
        given(profileRepository.findAll()).willReturn(profiles);

        List<Profile> foundProfiles = service.findAllProfiles();

        assertTrue(foundProfiles.size() > 0);
        then(profileRepository).should().findAll();
    }

    @Test
    void findAllProfilesThrow() {
        List<Profile> profiles = new ArrayList<>();
        given(profileRepository.findAll()).willThrow(new ProfileNotFoundException(ResponseMessageConstants.PROFILES_NOT_FOUND));

        assertThrows(ProfileNotFoundException.class, () -> service.findAllProfiles());

        then(profileRepository).should().findAll();
    }

    @Test
    void getProfileById() {
        Long profileId = 1L;
        Profile profile = new Profile();
        given(profileRepository.findById(profileId)).willReturn(Optional.of(profile));

        Profile foundProfile = service.getProfileById(profileId);

        assertThat(foundProfile).isNotNull();
        then(profileRepository).should().findById(anyLong());
    }

    @Test
    void getProfileByIdThrow() {
        Long profileId = 1L;
        given(profileRepository.findById(profileId)).willThrow(new ProfileNotFoundException(ResponseMessageConstants.PROFILE_NOT_FOUND));

        assertThrows(ProfileNotFoundException.class, () -> service.getProfileById(profileId));

        then(profileRepository).should().findById(anyLong());
    }

    @Test
    void saveProfile() {
        Profile profile = new Profile();
        given(profileRepository.save(profile)).willReturn(profile);

        Profile savedProfile = service.saveProfile(profile);

        assertThat(savedProfile).isNotNull();
        then(profileRepository).should().save(any(Profile.class));
    }

    @Test
    void saveProfileThrow() {
        Profile profile = new Profile();
        given(profileRepository.save(profile)).willThrow(new RuntimeException("BOOM"));

        assertThrows(RuntimeException.class, () -> service.saveProfile(profile));

        then(profileRepository).should().save(any(Profile.class));
    }

    @Test
    void checkOldAndNewPasswordTrue() {
        String oldPassword = "1234";
        String newPassword = "1234";

        assertTrue(service.checkOldAndNewPassword(oldPassword, newPassword));
    }

    @Test
    void checkOldAndNewPasswordFalse() {
        String oldPassword = "1234";
        String newPassword = "4567";

        assertFalse(service.checkOldAndNewPassword(oldPassword, newPassword));
    }

    @Test
    void checkOldAndNewUsernameTrue() {
        String oldUsername = "FSP";
        String newUsername = "FSP";

        assertTrue(service.checkOldAndNewUsername(oldUsername, newUsername));
    }

    @Test
    void checkOldAndNewUsernameFalse() {
        String oldUsername = "FSP";
        String newUsername = "RSP";

        assertFalse(service.checkOldAndNewUsername(oldUsername, newUsername));
    }

    @Test
    void checkNewUsernameAlreadyExist() {
        String newUsername = "RSP";
        List<Profile> profiles = new ArrayList<>();
        Profile profile1 = new Profile();
        profile1.setUsername("FSP");
        Profile profile2 = new Profile();
        profile2.setUsername("RSP");

        profiles.add(profile1);
        profiles.add(profile2);

        assertTrue(service.checkNewUsernameAlreadyExist(profiles, newUsername));
    }

    @Test
    void checkNewUsernameAlreadyExistThrow() {
        String newUsername = "RSP";
        List<Profile> profiles = new ArrayList<>();
        Profile profile1 = new Profile();
        profile1.setUsername("FSP");
        Profile profile2 = new Profile();
        profile2.setUsername("LSP");

        profiles.add(profile1);
        profiles.add(profile2);

        assertFalse(service.checkNewUsernameAlreadyExist(profiles, newUsername));
    }

    @Test
    void changePasswordById() {
        EmployeeChangePasswordDto dto = new EmployeeChangePasswordDto(1L, "1234");

        String successfullyUpdated = service.changePasswordById(dto);

        assertEquals(successfullyUpdated, ResponseMessageConstants.PASSWORD_SUCCESSFULLY_UPDATED);
    }
}