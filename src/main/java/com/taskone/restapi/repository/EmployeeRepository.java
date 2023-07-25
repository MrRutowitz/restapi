package com.taskone.restapi.repository;

import com.taskone.restapi.entity.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findBySalaryBetween(double minSalary, double maxSalary);

    @Query("select e from Employee e where e.jobposition LIKE %:title%")
    List<Employee> findByTitleOfJobposition(String title);
}
