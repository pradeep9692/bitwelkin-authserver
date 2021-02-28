package com.bitwelkin.secure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

	Optional<Employee> findEmployeeByUsername(String username);
}
