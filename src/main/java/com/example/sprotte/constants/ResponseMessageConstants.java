package com.example.sprotte.constants;

public class ResponseMessageConstants {

	// Exception String Employee Services
	public static final String USER_SUCCESSFUL_REGISTRATION = "Successfully Done !";
	public static final String USER_ALREADY_EXIST = "The user with this username, already exist";
	public static final String USERNAME_NOT_EXIST = "The username is not existing";
	public static final String PASSWORD_IS_WRONG = "The password is wrong";
	public static final String SUCCESSFULLY_LOGIN = "Login was successfully";
	public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
	public static final String EMPLOYEES_NOT_FOUND = "No Employees found";
	public static final String DEVICE_NOT_FOUND = "Device not found, please registrate the Device";
	public static final String ROLE_NOT_FOUND = "Role not found, please choose a other Role";
	public static final String TOKEN_NOT_FOUND = "User has no Token, or Token is wrong";
	public static final String PROFILE_NOT_FOUND = "The Employee has no Profile or the Profile wasn't found";
	public static final String PROFILES_NOT_FOUND = "No Profiles found";
	public static final String ILLEGAL_PASSWORD_UPDATE = "Update Password went wrong because old and new password are equal";
	public static final String BAR_NOT_FOUND = "Bar not found, please choose a other Bar";
	public static final String BAR_SEGMENT_NOT_FOUND = "Bar Segment not found, please choose a other Bar Segment";
	public static final String CONTAINER_CATEGORY_NOT_FOUND = "Container Category not found, please choose a other Container Category";
	public static final String BAR_CONTAINER_NOT_FOUND = "Bar Container not found, please choose a other Bar Container";
	public static final String PRODUCT_NOT_FOUND = "Product not found";

	//Employee SCRUD Strings
	public static final String EMPLOYEE_SUCCESSFULLY_DELETED = "Employee successfully deleted";
	public static final String EMPLOYEE_SUCCESSFULLY_UPDATED = "Employee successfully updated";
	public static final String PASSWORD_SUCCESSFULLY_UPDATED = "Password successfully updated";
	public static final String USERNAME_SUCCESSFULLY_UPDATED = "Username successfully updated";
	public static final String EMPLOYEE_NOT_LOGGED_IN_ON_THAT_DEVICE = "Employee is not Logged in on given Device";
	public static final String EMPLOYEE_HAS_ALREADY_ROLE = "Employee has Role already";
	public static final String REMOVE_ROLE_FROM_EMPLOYEE = "Role removed successfully from employee";
	public static final String UPDATE_MAX_NUMBERS_OF_ORDERS = "Updated maxNumbersOfOrders successfully";

	// Role SCRUD Strings
	public static final String ROLE_IS_EMPTY = "Role Object is empty";
	public static final String ROLE_ALREADY_EXIST = "Role already exist";
	public static final String ROLE_SUCCESSFULLY_DELETE = "Role successfully deleted";

	// Device SCRUD Strings
	public static final String DEVICE_IS_EMPTY = "Device Object is empty";
	public static final String DEVICE_ALREADY_EXIST = "Device already exist";
	public static final String DEVICE_SUCCESSFULLY_DELETE = "Device successfully deleted";

	// Bar SCRUD Strings
	public static final String BAR_DTO_IS_EMPTY = "Bar Dto Object is empty";
	public static final String BAR_IS_EMPTY = "Bar Object is empty";
	public static final String BAR_ALREADY_EXIST = "Bar already exist";
	public static final String BAR_SUCCESSFULLY_DELETE = "Bar successfully deleted";
	public static final String REMOVE_DEVICE_FROM_BAR = "Device removed successfully from bar";

	// BarSegment SCRUD Strings
	public static final String BAR_SEGMENT_DTO_IS_EMPTY = "Bar Segment Dto Object is empty";
	public static final String BAR_SEGMENT_IS_EMPTY = "Bar Segment Object is empty";
	public static final String BAR_SEGMENT_ALREADY_EXIST = "Bar Segment already exist";
	public static final String BAR_SEGMENT_MUST_ASSIGNED_TO_BAR = "Bar segment must be assigned to a bar";
	public static final String BAR_SEGMENT_SUCCESSFULLY_DELETE = "Bar Segment successfully deleted";

	// Container Category Strings
	public static final String CONTAINER_CATEGORY_IS_EMPTY = "Container Category Object is empty";
	public static final String CONTAINER_CATEGORY_ALREADY_EXIST = "Container Category already exist";
	public static final String CONTAINER_CATEGORY_SUCCESSFULLY_DELETE = "Container Category successfully deleted";

	// Bar Container Strings
	public static final String BAR_CONTAINER_IS_EMPTY = "Bar Container Object is empty";
	public static final String BAR_CONTAINER_ALREADY_EXIST = "Bar Container already exist";
	public static final String BAR_CONTAINER_MUST_ASSIGNED_TO_BAR_SEGMENT = "Bar Container must be assigned to a bar segment";
	public static final String BAR_CONTAINER_MUST_ASSIGNED_TO_CONTAINER_CATEGORY = "Bar Container must be assigned to a container category";
	public static final String BAR_CONTAINER_SUCCESSFULLY_DELETE = "Bar Container successfully deleted";

	// Product Strings
	public static final String PRODUCT_IS_EMPTY = "Product Object is empty";
	public static final String PRODUCT_ALREADY_EXIST = "Product already exist";
	public static final String PRODUCT_SUCCESSFULLY_DELETE = "Product successfully deleted";
}
