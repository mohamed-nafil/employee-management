package com.example.employee_management.repository;

import com.example.employee_management.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository 
                    extends JpaRepository<Leave, Long> {

}
