package com.example.sprotte.datahandling.employee.services;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.errorhandling.Employee.*;
import com.example.sprotte.errorhandling.EntityExceptionHandler;
import com.example.sprotte.constants.EntityEnums;

public class    EmployeeExceptionHandler extends EntityExceptionHandler {

    @Override
    public void notFoundException(EntityEnums entity) {

        switch (entity) {
            case DEVICE:
                throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);
            case EMPLOYEE:
                throw new EmployeeNotFoundException(ResponseMessageConstants.EMPLOYEE_NOT_FOUND);
            case PROFILE:
                throw new ProfileNotFoundException(ResponseMessageConstants.PROFILES_NOT_FOUND);
            case ROLE:
                throw new RoleNotFoundException(ResponseMessageConstants.ROLE_NOT_FOUND);
            case TOKEN:
                throw new TokenNotFoundException(ResponseMessageConstants.TOKEN_NOT_FOUND);
            case USERNAME:
                throw new UsernameNotFoundException(ResponseMessageConstants.USERNAME_NOT_EXIST);
            default:
                throw new RuntimeException("Unknown Error");
        }
    }

    @Override
    public void illegalException(EntityEnums entity) {
        switch (entity) {
            case EMPLOYEE:
                throw new IllegalEmployeeRoleException(ResponseMessageConstants.EMPLOYEE_HAS_ALREADY_ROLE);
            case PASSWORD:
                throw new IllegalPasswordException(ResponseMessageConstants.ILLEGAL_PASSWORD_UPDATE);

        }
    }

    @Override
    public void objectAlreadyExistException(EntityEnums entity) {
        switch (entity) {
            case ROLE:
                throw new IllegalRoleException(ResponseMessageConstants.ROLE_ALREADY_EXIST);
            case USERNAME:
                throw new UsernameAlreadyExistException(ResponseMessageConstants.USER_ALREADY_EXIST);
            case DEVICE:
                throw new IllegalDeviceException(ResponseMessageConstants.DEVICE_ALREADY_EXIST);
        }
    }

    public void wrongPswException() {
        throw new WrongPasswordException(ResponseMessageConstants.PASSWORD_IS_WRONG);
    }
}
