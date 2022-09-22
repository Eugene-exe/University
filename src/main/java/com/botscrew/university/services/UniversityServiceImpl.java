package com.botscrew.university.services;

import com.botscrew.university.constants.Degree;
import com.botscrew.university.exception.CustomException;
import com.botscrew.university.model.Department;
import com.botscrew.university.model.Lector;
import com.botscrew.university.repository.LectorRepository;
import com.botscrew.university.repository.DepartmentRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor

public class UniversityServiceImpl implements UniversityService {

    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;


    @Override
    @Transactional
    public String takeHeadOfDepartment(String request) {
        if (request == null || request.equals("")) {
            throw new CustomException("DepartmentName cant be null");
        }

        Department department = departmentRepository.getDepartmentByDepartmentName(request);
        if (department == null) {
            throw new CustomException("Department with name " + request + " not found");
        }
        Lector lector = department.getDepartmentHead();

        if (lector == null) {
            throw new CustomException("Department " + request + " dont have an department head");
        }

        return lector.getName() + " " + lector.getLastName();
    }

    @Override
    public String takeDepartmentStatistics(String request) {
        if (request == null || request.equals("")) {
            throw new CustomException("DepartmentName cant be null");
        }
        Department department = departmentRepository.getDepartmentByDepartmentName(request);
        if (department == null) {
            throw new CustomException("Department with name " + request + " not found");
        }
        Set<Lector> lectors = department.getLectorsOfDepartment();
        long CountAssistants = lectors.stream()
                .filter(a -> a.getDegree().equals(Degree.ASSISTANT))
                .count();
        long CountAssociate = lectors.stream()
                .filter(a -> a.getDegree().equals(Degree.ASSOCIATE_PROFESSOR))
                .count();
        long CountProfessors = lectors.stream()
                .filter(a -> a.getDegree().equals(Degree.PROFESSOR))
                .count();

        return "Answer: " + "assistants - " + CountAssistants + " associate professors - " + CountAssociate + " professors - " + CountProfessors;
    }

    @Override
    public String takeAverageSalary(String request) {
        if (request == null || request.equals("")) {
            throw new CustomException("DepartmentName cant be null");
        }

        Department department = departmentRepository.getDepartmentByDepartmentName(request);

        if (department == null) {
            throw new CustomException("Department with name " + request + " not found");
        }
        List<BigDecimal> salary = department.getLectorsOfDepartment().stream()
                .map(Lector::getSalary)
                .toList();
        BigDecimal sum = salary.stream()
                .map(Objects::requireNonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(new BigDecimal(salary.size()), RoundingMode.CEILING).toString();

    }

    @Override
    public String takeEmployeeCount(String request) {
        Department department = departmentRepository.getDepartmentByDepartmentName(request);
        if (request == null || request.equals("")) {
            throw new CustomException("DepartmentName cant be null");
        }

        if (department == null) {
            throw new CustomException("Department with name " + request + " not found");
        }
        return String.valueOf(department.getLectorsOfDepartment().size());
    }

    @Override
    public String globalSearch(String request) {
        String result = lectorRepository.findAllByNameContainsOrLastNameContains(request, request).toString();
        if(result == null || result.isEmpty() ||result.equals("[]")){
            return "Nothing found ";
        }
        return result;
    }
}
