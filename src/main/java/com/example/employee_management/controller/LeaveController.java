package com.example.employee_management.controller;

import com.example.employee_management.dto.LeaveRequestDTO;
import com.example.employee_management.dto.LeaveResponseDTO;
import com.example.employee_management.service.LeaveService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    // CREATE LEAVE
    @PostMapping
    public ResponseEntity<LeaveResponseDTO> createLeave(
            @Valid @RequestBody LeaveRequestDTO request) {

        LeaveResponseDTO createdLeave =
                leaveService.createLeave(request);

        return ResponseEntity.status(201).body(createdLeave);
    }

    // GET ALL LEAVES
    @GetMapping
    public List<LeaveResponseDTO> getAllLeaves() {

        return leaveService.getAllLeaves();
    }

    // GET LEAVE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<LeaveResponseDTO> getLeaveById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                leaveService.getLeaveById(id)
        );
    }

    // UPDATE LEAVE
    @PutMapping("/{id}")
    public ResponseEntity<LeaveResponseDTO> updateLeave(
            @PathVariable Long id,
            @Valid @RequestBody LeaveRequestDTO request) {

        LeaveResponseDTO updatedLeave =
                leaveService.updateLeave(id, request);

        return ResponseEntity.ok(updatedLeave);
    }

    // APPROVE LEAVE
    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveResponseDTO> approveLeave(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                leaveService.approveLeave(id)
        );
    }

    // REJECT LEAVE
    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveResponseDTO> rejectLeave(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                leaveService.rejectLeave(id)
        );
    }
}