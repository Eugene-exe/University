package com.botscrew.university.services;

public interface UniversityService {
    String takeAverageSalary(String departmentName);
    String takeEmployeeCount(String departmentName);
    String takeDepartmentStatistics(String departmentName);
    String takeHeadOfDepartment(String departmentName);
    String globalSearch(String template);
}
