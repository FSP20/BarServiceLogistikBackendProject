package com.example.sprotte.user.service;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.device.repository.DeviceRepository;
import com.example.sprotte.datahandling.employee.services.EmployeeLoginServiceImpl;
import com.example.sprotte.dto.employee.EmployeeLoginDto;
import com.example.sprotte.entity.AccessData;
import com.example.sprotte.entity.Device;
import com.example.sprotte.entity.Employee;
import com.example.sprotte.entity.Profile;
import com.example.sprotte.errorhandling.Employee.*;
import com.example.sprotte.datahandling.employee.repository.EmployeeAccessDataRepository;
import com.example.sprotte.datahandling.employee.repository.EmployeeEmployeeRepository;
import com.example.sprotte.datahandling.employee.repository.EmployeeProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeLoginServiceImplTest {

    @Mock
    EmployeeProfileRepository profileRepository;

    @Mock
    EmployeeEmployeeRepository employeeRepository;

    @Mock
    EmployeeAccessDataRepository accessDataRepository;

    @Mock
    DeviceRepository deviceRepository;

    @InjectMocks
    EmployeeLoginServiceImpl service;

    // Login Employee

    @Test
    void findProfileByUsername() {
        String username = "FS";
        Profile profile = new Profile();
        given(profileRepository.findByUsername(username)).willReturn(Optional.of(profile));

        Profile foundProfile = service.findProfileByUsername(username);

        assertThat(foundProfile).isNotNull();
        then(profileRepository).should().findByUsername(anyString());
    }

    @Test
    void loginEmployeeTest() {
        EmployeeLoginDto dto = new EmployeeLoginDto("FS", "1234", 1L);
        willThrow(new UsernameNotFoundException(ResponseMessageConstants.USERNAME_NOT_EXIST)).
                given(profileRepository).findByUsername(dto.getUsername());

        assertThrows(UsernameNotFoundException.class, () -> service.loginEmployee(dto));



        //then(profileRepository).should().findByUsername(anyString());
    }

    @Test
    void proofPasswordFalse() {
        String passwordDB = "1234";
        EmployeeLoginDto dto = new EmployeeLoginDto("FS", "5678", 1L);

        assertFalse(service.proofPassword(passwordDB, dto.getPassword()));
        //assertThrows(WrongPasswordException.class, () -> service.proofPassword(passwordDB, dto.getPassword()));
        //assertThrows(WrongPasswordException.class, () -> service.loginEmployee(dto));
    }

    @Test
    void proofPasswordTrue() {
        String passwordDB = "1234";
        EmployeeLoginDto dto = new EmployeeLoginDto("FS", "1234", 1L);

        assertTrue(service.proofPassword(passwordDB, dto.getPassword()));
    }

    @Test
    void findEmployeeByProfileId() {
        Long profileId = 1l;
        Employee employee = new Employee();
        given(employeeRepository.findByProfileId(profileId)).willReturn(Optional.of(employee));

        Employee foundEmployee = service.findByProfileId(profileId);

        assertThat(foundEmployee).isNotNull();
        then(employeeRepository).should().findByProfileId(anyLong());
    }

    @Test
    void findEmployeeByProfileIdThrow() {
        Long profileId = 1l;
        willThrow(new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEE_NOT_FOUND)).given(employeeRepository).findByProfileId(profileId);

        assertThrows(EmployeeNotFoundException.class, () -> service.findByProfileId(profileId));

        then(employeeRepository).should().findByProfileId(anyLong());
    }

    // UpdateTokenAndTimeStamp

    @Test
    void updateTokenAndTimeStamp() {
        String token = "b20c7202-68cd-43e8-abf6-a3dba0bed103";
        AccessData accessData = new AccessData();

        given(accessDataRepository.findByToken(token)).willReturn(Optional.of(accessData));
        given(accessDataRepository.save(any(AccessData.class))).willReturn(accessData);

        service.updateTokenAndTimeStamp(token);

        then(accessDataRepository).should().findByToken(anyString());
        then(accessDataRepository).should().save(any(AccessData.class));
    }

    @Test
    void updateTokenAndTimeStampThrow() {
        String token = "b20c7202-68cd-43e8-abf6-a3dba0bed103";

        assertThrows(TokenNotFoundException.class, () -> service.updateTokenAndTimeStamp(token));
    }

    // UpdateDevieEmployeeRelation

    @Test
    void findDeviceById() {
        Long deviceId = 1L;
        Device device = new Device();
        given(deviceRepository.findById(deviceId)).willReturn(Optional.of(device));

        Device foundDevice = service.findDeviceById(deviceId);

        assertThat(foundDevice).isNotNull();
        then(deviceRepository).should().findById(anyLong());
    }

    @Test
    void findDeviceByIdThrow() {
        given(deviceRepository.findById(1L)).willThrow(new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND));

        assertThrows(DeviceNotFoundException.class, () -> service.findDeviceById(1L));

        then(deviceRepository).should().findById(1L);
    }


    @Test
    void checkEmployeeIsRegisteredOnDevice() {

    }

    @Test
    void updateDeviceEmployeeRelation () {

    }
}