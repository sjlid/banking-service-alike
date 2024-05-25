package com.eevan.bankingservice.services;

import com.eevan.bankingservice.entities.Employee;
import com.eevan.bankingservice.repositories.EmployeeRepository;
import com.eevan.bankingservice.security.EmployeeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByUsername(username);

        if (employee.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new EmployeeDetails(employee.get());
    }
}
