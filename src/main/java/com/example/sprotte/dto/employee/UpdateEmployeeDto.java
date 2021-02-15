package com.example.sprotte.dto.employee;

public class UpdateEmployeeDto {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private int maxNumbersOfOrders;

    public UpdateEmployeeDto(Long employeeId, String firstName, String lastName, int maxNumbersOfOrders) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maxNumbersOfOrders = maxNumbersOfOrders;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMaxNumbersOfOrders() {
        return maxNumbersOfOrders;
    }

    public void setMaxNumbersOfOrders(int maxNumbersOfOrders) {
        this.maxNumbersOfOrders = maxNumbersOfOrders;
    }
}
