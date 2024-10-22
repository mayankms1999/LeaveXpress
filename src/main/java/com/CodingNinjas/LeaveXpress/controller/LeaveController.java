package com.CodingNinjas.LeaveXpress.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.CodingNinjas.LeaveXpress.dto.LeaveDto;
import com.CodingNinjas.LeaveXpress.exception.LeaveNotFoundException;
import com.CodingNinjas.LeaveXpress.model.LeaveModel;
import com.CodingNinjas.LeaveXpress.service.LeaveService;

@RestController
@RequestMapping("/api")
public class LeaveController {

	@Autowired
	LeaveService leaveService;

	@GetMapping("/leave/{id}")
	@PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
	public LeaveModel leaveRecordById(@PathVariable Long id) throws LeaveNotFoundException {
		return leaveService.leaveRecordById(id);
	}

	@GetMapping("/leave/all")
	@PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
	public List<LeaveModel> allLeavesTakenByEmployee() {
		return leaveService.allLeavesTakenByEmployee();
	}

	@GetMapping("/leave/accepted")
	@PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
	public List<LeaveModel> allAcceptedLeavesTakenByEmployee() {
		return leaveService.allLeavesTakenByEmployee();
	}

	@GetMapping("/leave/rejected")
	@PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
	public List<LeaveModel> allRejectedLeavesTakenByEmployee() {
		return leaveService.allLeavesTakenByEmployee();
	}

	@GetMapping("/leave/status/{id}")
	@PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
	public boolean employeeLeaveStatus(@PathVariable Long id) {
		return leaveService.employeeLeaveStatus(id);
	}

	@PutMapping("/leave/{id}")
	@PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
	public void updateLeaveRequest(@PathVariable Long id, @RequestBody LeaveDto updatedLeave)
			throws LeaveNotFoundException {
		leaveService.updateLeaveRequest(id, updatedLeave);
	}

	@DeleteMapping("/leave/{id}")
	@PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
	public void deleteLeaveRecord(@PathVariable Long id) throws LeaveNotFoundException {
		leaveService.deleteLeaveRecord(id);
	}

	@PostMapping("/leave/apply")
	@PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
	public void leaveApplyByEmployee(@RequestBody LeaveDto leaveRequest) {
		leaveService.leaveApplyByEmployee(leaveRequest);
	}

	@PostMapping("/leave/accept/{id}")
	@PreAuthorize("hasRole('MANAGER')") 
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void leaveAcceptedByManager(@PathVariable Long id) throws LeaveNotFoundException {
		leaveService.leaveAcceptedByManager(id);
	}

	@PostMapping("/leave/reject/{id}")
	@PreAuthorize("hasRole('MANAGER')")
	public void leaveRejectedByManager(@PathVariable Long id) {
		leaveService.leaveRejectedByManager(id);
	}
}
