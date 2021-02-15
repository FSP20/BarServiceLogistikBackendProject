package com.example.sprotte.constants;

public class DatabaseConstants {

    //Table Name
    public static final String ACCESS_DATA_TABLE = "accessdata";
    public static final String DEVICE_TABLE = "device";
    public static final String EMPLOYEE_TABLE = "employee";
    public static final String PROFILE_TABLE = "profile";
    public static final String ROLE_TABLE = "role";
    public static final String BAR_TABLE = "bar";
    public static final String WORK_SHIFT_TABLE = "work_shift";
    public static final String BAR_SEGMENT_TABLE = "bar_segment";
    public static final String CONTAINER_CATEGORY_TABLE = "container_category";
    public static final String BAR_CONTAINER_TABLE = "bar_container";

    // Join Table
    public static final String EMPLOYEE_HAS_ROLE = "employee_has_role";
    public static final String EMPLOYEE_HAS_WORK_SHIFT = "employee_has_work_shift";

    // Attribute Name
    // Access Data
    public static final String ID_ACCESS_DATA = "id_accessdata";
    public static final String TOKEN = "token";
    public static final String ACCESS_TIME_STAMP = "access_time_stamp";
    public static final String ACCESS_DATA = "accessData";

    // Device
    public static final String ID_DEVICE = "id_device";
    public static final String DEVICE_NUMBER = "device_number";
    public static final String DEVICE_DESCRIPTION = "description";
    public static final String IS_ACTIVE = "active";

    // Employee
    public static final String ID_EMPLOYEE = "id_employee";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String MAX_NUMBERS_OF_ORDERS = "max_numbers_of_orders";

    // Profile
    public static final String ID_PROFILE = "id_profile";
    public static final String PASSWORD_EMPLOYEE = "password";
    public static final String USERNAME_EMPLOYEE = "username";

    // Role
    public static final String ID_ROLE = "id_role";
    public static final String ROLE_DESCRIPTION = "description";

    // Bar
    public static final String ID_BAR = "id_bar";
    public static final String BAR_NAME = "name";

    // Work Shift
    public static final String ID_WORK_SHIFT = "id_work_shift";
    public static final String WORK_SHIFT_DESCRIPTION = "description";
    public static final String START_TIME = "start_time";
    public static final String END_TIME = "end_time";
    public static final String CALENDAR_WEEK = "calendar_week";
    public static final String DAY = "day";

    // Bar Segment
    public static final String ID_BAR_SEGMENT = "id_bar_segment";
    public static final String BAR_SEGMENT_DESCRIPTION = "description";
    public static final String BAR_SEGMENT_CATEGORY = "category";

    // Container Category
    public static final String ID_CONTAINER_CATEGORY = "id_container_category";
    public static final String CONTAINER_CATEGORY_DESCRIPTION = "description";

    // Bar Container
    public static final String ID_BAR_CONTAINER = "id_bar_container";
    public static final String BAR_CONTAINER_DESCRIPTION = "description";
}
