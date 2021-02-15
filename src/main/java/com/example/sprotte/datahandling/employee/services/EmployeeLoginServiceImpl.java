package com.example.sprotte.datahandling.employee.services;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.device.repository.DeviceRepository;
import com.example.sprotte.dto.employee.EmployeeLoginDto;
import com.example.sprotte.entity.AccessData;
import com.example.sprotte.entity.Device;
import com.example.sprotte.entity.Employee;
import com.example.sprotte.entity.Profile;
import com.example.sprotte.errorhandling.Employee.*;
import com.example.sprotte.datahandling.employee.repository.EmployeeAccessDataRepository;
import com.example.sprotte.datahandling.employee.repository.EmployeeEmployeeRepository;
import com.example.sprotte.datahandling.employee.repository.EmployeeProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeLoginServiceImpl implements EmployeeLoginService{
	
	@Autowired
	EmployeeProfileRepository employeeProfileRepository;
	
	@Autowired
	EmployeeEmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeAccessDataRepository employeeAccessDataRepository;

	@Autowired
	EmployeeHelperClass helperClass;
	
	@Autowired
	DeviceRepository deviceRepository;

	@Override
	public Employee loginEmployee(EmployeeLoginDto employeeLoginDto) {
		
		Profile profile = findProfileByUsername(employeeLoginDto.getUsername());
		
		if(profile == null)
			throw new UsernameNotFoundException(ResponseMessageConstants.USERNAME_NOT_EXIST);
		
		
		if(proofPassword(profile.getPassword(), employeeLoginDto.getPassword())) {
			
			Employee employee = findByProfileId(profile.getId());
			
			if(employee == null)
				throw new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEE_NOT_FOUND);
			
			// Set Employee active on Device after Login
			updateDeviceEmployeeRelation(employee, employeeLoginDto.getDeviceId());
			
			// Create new Token for Employee during Login
			updateTokenAndTimeStamp(employee.getAccessData().getToken());

			return employeeRepository.save(employee);
		} else {
			throw new WrongPasswordException(ResponseMessageConstants.PASSWORD_IS_WRONG);
		}
	}
	
	
	// Proof given Password , with User password in Database
	public boolean proofPassword(String passwordDB, String passwordDTO) {
		return passwordDTO.equals(passwordDB);
	}
	
	
	//Create New Token and Timestamp for Employee when Login
	public void updateTokenAndTimeStamp(String token) {
		AccessData accessData = findByToken(token);
		if(accessData != null) {
			accessData.createNewTokenAndTimeStamp();

			saveAccessData(accessData);
		} else {
			throw new TokenNotFoundException(ResponseMessageConstants.TOKEN_NOT_FOUND);
		}
	}

	
	// Set Employee active in Device OR add Employee to Device if Employee is not registrated at the given device
	public void updateDeviceEmployeeRelation(Employee employee, Long deviceId) {

		// Check Employee is active on other device
		helperClass.checkEmployeeIsActiveOnOtherDevice(employee.getId());

		boolean isEmployeeRegisteredOnDevice =
				checkEmployeeIsRegisteredOnDevice(employee.getDevices(), deviceId);

		// Employee ist not registered on given Device
		if(!isEmployeeRegisteredOnDevice) {
			Device device = findDeviceById(deviceId);
			
			if(device != null) {
				// Device get Status "Active" because the Employee is logged in and active
				device.setActive(true);

				//Set Child in Parent
				if(!employee.getDevices().contains(device)){
					employee.getDevices().add(device);
				}

				//Set Parent in Child Entity
				device.setEmployee(employee);
			} else {
				throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);
			}
		}
	}

	public boolean checkEmployeeIsRegisteredOnDevice(List<Device> devices, Long givenDeviceId) {
		if(devices.size() > 0)
		{
			for(Device device : devices) {
				if (device.getId().equals(givenDeviceId)) {
					device.setActive(true);
					return true;
				}
			}
		}

		return false;
	}

	public AccessData saveAccessData(AccessData accessData) {
		return employeeAccessDataRepository.save(accessData);
	}

	public AccessData findByToken(String token) {
		return employeeAccessDataRepository.findByToken(token).orElse(null);
	}

	public Device findDeviceById(Long deviceId) {
		return deviceRepository.findById(deviceId).orElse(null);
	}

	public Profile findProfileByUsername(String username) {
		return employeeProfileRepository.findByUsername(username).orElse(null);
	}

	public Employee findByProfileId(Long profileId) {
		return employeeRepository.findByProfileId(profileId).orElse(null);
	}
}
