package com.example.sprotte.user.service;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.device.repository.DeviceRepository;
import com.example.sprotte.datahandling.employee.services.EmployeeRegistrationServiceImpl;
import com.example.sprotte.dto.employee.EmployeeRegistrationDto;
import com.example.sprotte.entity.Device;
import com.example.sprotte.entity.Employee;
import com.example.sprotte.entity.Profile;
import com.example.sprotte.entity.Role;
import com.example.sprotte.errorhandling.Employee.DeviceNotFoundException;
import com.example.sprotte.errorhandling.Employee.RoleNotFoundException;
import com.example.sprotte.errorhandling.Employee.UsernameAlreadyExistException;
import com.example.sprotte.datahandling.employee.repository.EmployeeEmployeeRepository;
import com.example.sprotte.datahandling.employee.repository.EmployeeProfileRepository;
import com.example.sprotte.datahandling.employee.repository.EmployeeRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class EmployeeRegistrationServiceImplTest {

	@Mock
	EmployeeProfileRepository employeeProfileRepository;

	@Mock
	EmployeeRoleRepository employeeRoleRepository;

	@Mock
	DeviceRepository deviceRepository;

	@Mock
	EmployeeEmployeeRepository employeeRepository;

	@InjectMocks
	EmployeeRegistrationServiceImpl service;

	@Test
	void saveNewEmployeeThrow() {
		List<Long> roleIds = new ArrayList<>();
		roleIds.add(1L);
		EmployeeRegistrationDto dto = new EmployeeRegistrationDto(
				"Fabio", "Sprotte", 3, "FS", "1234", 1, roleIds);

		doThrow(new UsernameAlreadyExistException(ResponseMessageConstants.USER_ALREADY_EXIST)).when(service).saveNewEmployee(dto);

		assertThrows(UsernameAlreadyExistException.class, () -> service.saveNewEmployee(dto));

		then(service).should().saveNewEmployee(dto);
	}

	@Test
	void saveNewEmployee() {
		Employee employee = new Employee();
		List<Long> roleIds = new ArrayList<>();
		roleIds.add(1L);
		EmployeeRegistrationDto dto = new EmployeeRegistrationDto(
				"Fabio", "Sprotte", 3, "FSP", "1234", 1, roleIds);
		given(service.saveNewEmployee(dto)).willReturn(employee);

		//when
		Employee savedEmployee = service.saveNewEmployee(dto);

		//then
		assertThat(savedEmployee).isNotNull();
		then(service).should().saveNewEmployee(any());
	}

	@Test
	void save() {
		Employee employee = new Employee();
		given(employeeRepository.save(any(Employee.class))).willReturn(employee);

		Employee savedEmployee = service.save(new Employee());

		then(employeeRepository).should().save(any(Employee.class));
		assertThat(savedEmployee).isNotNull();
	}

	@Test
	void usernameAlreadyExist() {
		//given
		String username = "FSP";
		Profile profile = new Profile();
		given(employeeProfileRepository.findByUsername(username)).willReturn(Optional.of(profile));

		//when
		boolean foundProfile = service.usernameAlreadyExist(username);

		//then
		assertThat(foundProfile).isTrue();
		then(employeeProfileRepository).should().findByUsername(username);
	}


	@Test
	void mapDtoToEntityObject() {
		// given
		List<Long> roleIds = new ArrayList<>();
		roleIds.add(1L);
		EmployeeRegistrationDto dto = new EmployeeRegistrationDto(
				"Fabio", "Sprotte", 3, "FSP", "1234", 1, roleIds);
		Role role = new Role();
		Device device = new Device();

		given(employeeRoleRepository.findById(dto.getRoleIds().get(0))).willReturn(Optional.of(role));
		given(deviceRepository.findById(dto.getDeviceId())).willReturn(Optional.of(device));

		//when
		Employee employee = service.mapDtoToEntityObject(dto);

		//then
		assertThat(employee).isNotNull();
		then(employeeRoleRepository).should().findById(anyLong());
		then(deviceRepository).should().findById(anyLong());
	}

	@Test
	void mapDtoToEntityObjectThrow() {
		List<Long> roleIds = new ArrayList<>();
		roleIds.add(1L);
		EmployeeRegistrationDto dto = new EmployeeRegistrationDto(
				"Fabio", "Sprotte", 3, "FSP", "1234", 1, roleIds);

		given(employeeRoleRepository.findById(dto.getRoleIds().get(0))).willThrow(new RoleNotFoundException(ResponseMessageConstants.ROLE_NOT_FOUND));
		given(deviceRepository.findById(dto.getDeviceId())).willThrow(new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND));

		assertThrows(RoleNotFoundException.class, () -> service.findRoleById(dto.getRoleIds().get(0)));
		assertThrows(DeviceNotFoundException.class, () -> service.findDeviceById(dto.getDeviceId()));

		//assertThrows(RoleNotFoundException.class, () -> service.mapDtoToEntityObject(dto));

		then(employeeRoleRepository).should().findById(anyLong());
		then(deviceRepository).should().findById(anyLong());
		//then(service).should().mapDtoToEntityObject(any());
	}

}
