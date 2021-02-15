package com.example.sprotte.dto.employee;

public class EmployeeChangePasswordDto {

    private Long employeeId;
    private String newPassword;

    public EmployeeChangePasswordDto(Long employeeId, String newPassword) {
        this.employeeId = employeeId;
        this.newPassword = newPassword;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
