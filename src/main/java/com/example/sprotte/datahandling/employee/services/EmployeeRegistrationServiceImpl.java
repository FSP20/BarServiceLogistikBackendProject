package com.example.sprotte.datahandling.employee.services;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.device.repository.DeviceRepository;
import com.example.sprotte.dto.employee.EmployeeRegistrationDto;
import com.example.sprotte.entity.*;
import com.example.sprotte.errorhandling.Employee.DeviceNotFoundException;
import com.example.sprotte.errorhandling.Employee.RoleNotFoundException;
import com.example.sprotte.errorhandling.Employee.UsernameAlreadyExistException;
import com.example.sprotte.datahandling.employee.repository.EmployeeEmployeeRepository;
import com.example.sprotte.datahandling.employee.repository.EmployeeProfileRepository;
import com.example.sprotte.datahandling.employee.repository.EmployeeRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmployeeRegistrationServiceImpl implements EmployeeRegistrationService {
	
	@Autowired
	EmployeeEmployeeRepository userRegistrationRepository;
	
	@Autowired
	EmployeeProfileRepository employeeLoginInfoRepository;
	
	@Autowired
	EmployeeRoleRepository employeeRoleRepository;
	
	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	EmployeeHelperClass helperClass;

	@Override
	public Employee saveNewEmployee(EmployeeRegistrationDto employeeRegistrationDto) {
		
		if(usernameAlreadyExist(employeeRegistrationDto.getUsername()))
			throw new UsernameAlreadyExistException(ResponseMessageConstants.USER_ALREADY_EXIST);
			
		Employee employee = mapDtoToEntityObject(employeeRegistrationDto);

		// Save Parent (which will save the Child as well)
		return save(employee);
	}

	
	public boolean usernameAlreadyExist(String username) {
		Optional<Profile> profile = findProfileByUsername(username);
		return profile.isPresent();
	}
	
	public Employee mapDtoToEntityObject(EmployeeRegistrationDto dto) {
		Employee employee = new Employee();

		// Employee Data
		employee.setFirstName(dto.getFirstName());
		employee.setLastName(dto.getLastName());
		employee.setMaxNumbersOfOrders(dto.getMaxNumbersOfOrders());
		
		//New AccessData
		// In Constructor from AccessData, generate automatically a new Token and Timestamp
		AccessData accessData = new AccessData();
					
		// Set Child reference in Parent
		employee.setAccessData(accessData);
		
		// Set Parent reference in Child Entity
		employee.getAccessData().setEmployee(employee);
		
		//New Profile
		Profile profile = new Profile();
		profile.setUsername(dto.getUsername());
		profile.setPassword(dto.getPassword());
		
		// Set Child in Parent
		employee.setProfile(profile);
		
		//Set Parent in Child Entity
		employee.getProfile().setEmployee(employee);
		
		// Role
		for (Long roleId : dto.getRoleIds()) {
			Role role = findRoleById(roleId);
			
			if(role == null)
				throw new RoleNotFoundException(ResponseMessageConstants.ROLE_NOT_FOUND);

			// Set Child in Parent
			employee.getRoles().add(role);

			//Set Parent in Child Entity
			role.getEmployees().add(employee);
		}
		
		// Device
		Device device = findDeviceById(dto.getDeviceId());
		
		if(device != null)
			throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);

		// Check Employee is active on other device
		helperClass.setEmployeeInactiveOnOtherDevice(employee.getId());
		// Device get Status "Active" because when Employee is registrated, the Employee is loged in and active
		device.setActive(true);

		//Set Child in Parent
		employee.getDevices().add(device);

		//Set Parent in Child Entity
		device.setEmployee(employee);

		return employee;
	}

	public Optional<Profile> findProfileByUsername(String username) {
		return employeeLoginInfoRepository.findByUsername(username);
	}

	public Role findRoleById(Long roleId) {
		return employeeRoleRepository.findById(roleId).orElse(null);
	}

	public Device findDeviceById(Long deviceId){
		return deviceRepository.findById(deviceId).orElse(null);
	}

	public Employee save(Employee employee) {
		return userRegistrationRepository.save(employee);
	}
}
