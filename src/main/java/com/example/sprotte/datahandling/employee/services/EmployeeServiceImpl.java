package com.example.sprotte.datahandling.employee.services;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.device.repository.DeviceRepository;
import com.example.sprotte.datahandling.employee.repository.EmployeeEmployeeRepository;
import com.example.sprotte.datahandling.employee.repository.EmployeeProfileRepository;
import com.example.sprotte.datahandling.employee.repository.EmployeeRoleRepository;
import com.example.sprotte.dto.employee.EmployeeChangePasswordDto;
import com.example.sprotte.dto.employee.UpdateEmployeeDto;
import com.example.sprotte.entity.Device;
import com.example.sprotte.entity.Employee;
import com.example.sprotte.entity.Profile;
import com.example.sprotte.entity.Role;
import com.example.sprotte.errorhandling.Employee.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeEmployeeRepository employeeRepository;

	@Autowired
	EmployeeProfileRepository employeeProfileRepository;

	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	EmployeeRoleRepository roleRepository;

	@Autowired
	EmployeeHelperClass helperClass;

	@Override
	public Employee getEmployeeById(Long employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if(!employee.isPresent())
			throw new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEE_NOT_FOUND);

		return employee.get();
	}

	@Override
	public String deleteEmployeeById(Long employeeId) {
		employeeRepository.deleteById(employeeId);
		return ResponseMessageConstants.EMPLOYEE_SUCCESSFULLY_DELETED;
	}

	@Override
	public Employee updateEmployee(UpdateEmployeeDto dto) {
		return employeeRepository.save(mapUpdateEmployeeDto(dto));
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		Profile profile = findProfileByUsername(username);
		if (profile == null)
			throw new ProfileNotFoundException(ResponseMessageConstants.PROFILE_NOT_FOUND);

		Employee employee = findByProfileId(profile.getId());
		if (employee == null) {
			throw new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEE_NOT_FOUND);
		}

		return employee;
	}

	@Override
	public Employee getEmployeeByFullName(String firstName, String lastName) {
		Optional<Employee> employee = employeeRepository.findByFirstNameAndLastName(firstName, lastName);

		if(!employee.isPresent())
			throw new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEE_NOT_FOUND);

		return employee.get();
	}

	@Override
	public String changePasswordById(EmployeeChangePasswordDto dto) {
		Employee employee = getEmployeeById(dto.getEmployeeId());
		if(employee == null)
			throw new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEES_NOT_FOUND);

		Profile profile = getProfileById(employee.getProfile().getId());
		if(profile == null)
			throw new ProfileNotFoundException(ResponseMessageConstants.PROFILE_NOT_FOUND);

		if(profile.getPassword() != null) {
			if(!checkOldAndNewPassword(profile.getPassword(), dto.getNewPassword())) {
				profile.setPassword(dto.getNewPassword());
				saveProfile(profile);
			} else {
				throw new IllegalPasswordException(ResponseMessageConstants.ILLEGAL_PASSWORD_UPDATE);
			}
		} else {
			throw new NullPointerException();
		}

		return ResponseMessageConstants.PASSWORD_SUCCESSFULLY_UPDATED;
	}

	@Override
	public String changeUsernameById(Long employeeId, String newUsername) {
		Employee employee = getEmployeeById(employeeId);
		if (employee == null)
			throw new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEE_NOT_FOUND);

		Profile profile = getProfileById(employee.getProfile().getId());
		if(profile == null)
			throw new ProfileNotFoundException(ResponseMessageConstants.PROFILE_NOT_FOUND);

		List<Profile> profileList = findAllProfiles();
		if (profileList.size() == 0)
			throw new ProfileNotFoundException(ResponseMessageConstants.PROFILES_NOT_FOUND);

		if(profile.getUsername() != null) {
			if(!checkNewUsernameAlreadyExist(profileList, profile.getUsername()) &&
					!checkOldAndNewUsername(profile.getUsername(), newUsername)) {
				profile.setUsername(newUsername);
				saveProfile(profile);
			} else {
				throw new UsernameAlreadyExistException(ResponseMessageConstants.USER_ALREADY_EXIST);
			}
		} else {
			throw new NullPointerException();
		}

		return ResponseMessageConstants.USERNAME_SUCCESSFULLY_UPDATED;
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = findAllEmployees();
		if (employees.size() > 0) {
			return employees;
		} else {
			throw new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEES_NOT_FOUND);
		}
	}

	@Override
	public void setEmployeeActiveById(Long employeeId, Long deviceId) {
		// Check Employee is active on other device
		helperClass.checkEmployeeIsActiveOnOtherDevice(employeeId);

		// Set Employee active on Device
		Device device = findDeviceById(deviceId);
		if(device != null) {
			device.setActive(true);
			deviceRepository.save(device);
		} else {
			throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);
		}
	}

	@Override
	public void setEmployeeInactiveById(Long employeeId, Long deviceId) {
		// Set Employe inactive on Device
		Device device = deviceRepository.findByIdAndEmployeeId(deviceId,employeeId);
		if(device != null) {
			device.setActive(false);
			deviceRepository.save(device);
		} else {
			throw new DeviceNotFoundException(ResponseMessageConstants.EMPLOYEE_NOT_LOGGED_IN_ON_THAT_DEVICE);
		}
	}

	//Update Role from Employee
	@Override
	public Employee addRoleToEmployee(Long employeeId, String roleName) {
		Employee employee = getEmployeeById(employeeId);
		if(employee == null)
			throw new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEE_NOT_FOUND);

		Role role = roleRepository.findByDescription(roleName);
		if(role == null)
			throw new RoleNotFoundException(ResponseMessageConstants.ROLE_NOT_FOUND);


		if(!employee.getRoles().contains(role)){
			// Set Child in Parent
			employee.getRoles().add(role);

			//Set Parent in Child Entity
			role.getEmployees().add(employee);

			return employeeRepository.save(employee);
		} else {
			throw new IllegalEmployeeRoleException(ResponseMessageConstants.EMPLOYEE_HAS_ALREADY_ROLE);
		}
	}

	//Delete Role from Employee
	@Override
	public Employee deleteRoleFromEmployee(Long employeeId, String roleName) {
		Employee employee = getEmployeeById(employeeId);
		if(employee == null)
			throw new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEE_NOT_FOUND);

		Role role = roleRepository.findByDescription(roleName);
		if(role == null)
			throw new RoleNotFoundException(ResponseMessageConstants.ROLE_NOT_FOUND);

		// Update Parent
		employee.getRoles().removeIf(tempRole -> tempRole.getId() == role.getId());

		// Update Child
		role.getEmployees().removeIf(tempEmployee -> tempEmployee.getId() == employee.getId());

		return employeeRepository.save(employee);
	}

	// Update Max Numbers of Orders from Employee
	@Override
	public String updateMaxNumbersOfOrders(Long employeeId, int maxNumbersOfOrders) {
		Employee employee = getEmployeeById(employeeId);
		if(employee == null)
			throw new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEE_NOT_FOUND);

		employee.setMaxNumbersOfOrders(maxNumbersOfOrders);

		employeeRepository.save(employee);

		return ResponseMessageConstants.UPDATE_MAX_NUMBERS_OF_ORDERS;
	}

	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}

	public List<Profile> findAllProfiles() {
		return employeeProfileRepository.findAll();
	}

	public Profile getProfileById(Long profileId) {
		return employeeProfileRepository.findById(profileId).orElse(null);
	}

	public Profile saveProfile(Profile profile) {
		return employeeProfileRepository.save(profile);
	}

	public boolean checkOldAndNewPassword(String oldPassword, String newPassword) {
		return oldPassword.equals(newPassword);
	}

	public boolean checkOldAndNewUsername(String oldUsername, String newUsername) {
		return oldUsername.equals(newUsername);
	}

	public boolean checkNewUsernameAlreadyExist(List<Profile> profileList, String newUsername) {
		for(Profile excistingProfiles : profileList) {
			if(excistingProfiles.getUsername().equals(newUsername)) {
				return true;
			}
		}
		return false;
	}

	public Profile findProfileByUsername(String username) {
		return employeeProfileRepository.findByUsername(username).orElse(null);
	}

	public Employee findByProfileId(Long profileId) {
		return employeeRepository.findByProfileId(profileId).orElse(null);
	}

	public Device findDeviceById(Long deviceId) {
		return deviceRepository.findById(deviceId).orElse(null);
	}

	public Employee mapUpdateEmployeeDto(UpdateEmployeeDto dto) {
		if(dto.getEmployeeId() != null) {
			Employee employee = getEmployeeById(dto.getEmployeeId());
			if (employee != null) {
				employee.setMaxNumbersOfOrders(dto.getMaxNumbersOfOrders());
				employee.setFirstName(dto.getFirstName());
				employee.setLastName(dto.getLastName());

				return employee;
			} else {
				throw new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEE_NOT_FOUND);
			}
		} else {
			throw new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEE_NOT_FOUND);
		}
	}
}
