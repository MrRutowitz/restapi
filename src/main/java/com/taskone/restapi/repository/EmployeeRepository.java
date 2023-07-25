package com.taskone.restapi.repository;

import com.taskone.restapi.model.Employee;
import com.taskone.restapi.model.EmployeeRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findBySalaryBetween(double minSalary, double maxSalary);

    @Query("select e from Employee e where e.jobposition LIKE %:title%")
    List<Employee> findByTitleOfJobposition(String title);



}
