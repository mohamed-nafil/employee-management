package com.example.employee_management.service;

import com.example.employee_management.dto.LeaveRequestDTO;
import com.example.employee_management.dto.LeaveResponseDTO;
import com.example.employee_management.entity.Employee;
import com.example.employee_management.entity.Leave;
import com.example.employee_management.exception.ResourceNotFoundException;
import com.example.employee_management.repository.EmployeeRepository;
import com.example.employee_management.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveService(
            LeaveRepository leaveRepository,
            EmployeeRepository employeeRepository) {

        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    // CREATE LEAVE
    public LeaveResponseDTO createLeave(LeaveRequestDTO request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        validateDates(request.getStartDate(), request.getEndDate());

        Leave leave = new Leave();

        leave.setEmployee(employee);
        leave.setLeaveType(request.getLeaveType());
        leave.setStartDate(request.getStartDate());
        leave.setEndDate(request.getEndDate());
        leave.setReason(request.getReason());
        leave.setStatus("Pending");

        Leave savedLeave = leaveRepository.save(leave);

        return convertToResponseDTO(savedLeave);
    }

    // GET ALL LEAVES
    public List<LeaveResponseDTO> getAllLeaves() {

        return leaveRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    // GET LEAVE BY ID
    public LeaveResponseDTO getLeaveById(Long id) {

        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Leave not found"));

        return convertToResponseDTO(leave);
    }

    // UPDATE LEAVE
    public LeaveResponseDTO updateLeave(
            Long id,
            LeaveRequestDTO request) {

        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Leave not found"));

        if (!"Pending".equals(leave.getStatus())) {
            throw new IllegalArgumentException(
                    "Only pending leave requests can be updated");
        }

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        validateDates(request.getStartDate(), request.getEndDate());

        leave.setEmployee(employee);
        leave.setLeaveType(request.getLeaveType());
        leave.setStartDate(request.getStartDate());
        leave.setEndDate(request.getEndDate());
        leave.setReason(request.getReason());

        Leave updatedLeave = leaveRepository.save(leave);

        return convertToResponseDTO(updatedLeave);
    }

    // APPROVE LEAVE
    public LeaveResponseDTO approveLeave(Long id) {

        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Leave not found"));

        leave.setStatus("Approved");

        Leave updatedLeave = leaveRepository.save(leave);

        return convertToResponseDTO(updatedLeave);
    }

    // REJECT LEAVE
    public LeaveResponseDTO rejectLeave(Long id) {

        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Leave not found"));

        leave.setStatus("Rejected");

        Leave updatedLeave = leaveRepository.save(leave);

        return convertToResponseDTO(updatedLeave);
    }

    // DATE VALIDATION
    private void validateDates(
            java.time.LocalDate startDate,
            java.time.LocalDate endDate) {

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException(
                    "End date cannot be before start date");
        }
    }

    // ENTITY → RESPONSE DTO
    private LeaveResponseDTO convertToResponseDTO(Leave leave) {

    Long employeeId = null;
    String employeeName = null;

    if (leave.getEmployee() != null) {
        employeeId = leave.getEmployee().getId();
        employeeName = leave.getEmployee().getName();
    }

    return new LeaveResponseDTO(
            leave.getId(),
            employeeId,
            employeeName,
            leave.getLeaveType(),
            leave.getStartDate(),
            leave.getEndDate(),
            leave.getReason(),
            leave.getStatus()
    );
}
}