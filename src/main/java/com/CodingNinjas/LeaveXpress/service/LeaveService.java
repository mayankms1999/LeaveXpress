package com.CodingNinjas.LeaveXpress.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.CodingNinjas.LeaveXpress.dto.LeaveDto;
import com.CodingNinjas.LeaveXpress.exception.LeaveNotFoundException;
import com.CodingNinjas.LeaveXpress.model.LeaveModel;
import com.CodingNinjas.LeaveXpress.repository.LeaveRepository;

@Service
public class LeaveService {

	private final LeaveRepository leaveRepository;

	public LeaveService(LeaveRepository leaveRepository) {
		this.leaveRepository = leaveRepository;
	}

	public LeaveModel leaveRecordById(Long id) throws LeaveNotFoundException {

		return leaveRepository.findById(id).orElseThrow(() -> new LeaveNotFoundException(HttpStatus.NOT_FOUND));
	}

	public List<LeaveModel> allLeavesTakenByEmployee() {
		return leaveRepository.findAll();
	}

	public boolean employeeLeaveStatus(Long id) {
		LeaveModel leaveRecord = null;
		try {
			leaveRecord = leaveRecordById(id);
		} catch (LeaveNotFoundException e) {

			e.printStackTrace();
		}
		return leaveRecord.isAccepted();
	}

	public void updateLeaveRequest(Long id, LeaveDto updatedLeave) throws LeaveNotFoundException {
		LeaveModel leaveModel = leaveRecordById(id);
		leaveModel.setType(updatedLeave.getType());
		leaveModel.setStartDate(updatedLeave.getStartDate());
		leaveModel.setEndDate(updatedLeave.getEndDate());
		leaveModel.setDescription(updatedLeave.getDescription());
		leaveRepository.save(leaveModel);
	}

	public void deleteLeaveRecord(Long id) throws LeaveNotFoundException {

		leaveRepository.deleteById(id);
	}

	public void leaveApplyByEmployee(LeaveDto leaveRequest) {
		LeaveModel leaveModel = new LeaveModel();
		leaveModel.setType(leaveRequest.getType());
		leaveModel.setStartDate(leaveRequest.getStartDate());
		leaveModel.setEndDate(leaveRequest.getEndDate());
		leaveModel.setDescription(leaveRequest.getDescription());
		leaveRepository.save(leaveModel);
	}

	public void leaveAcceptedByManager(Long id) throws LeaveNotFoundException {
		LeaveModel leaveModel = leaveRecordById(id);;
		
		leaveModel.setAccepted(true);
	}

	public void leaveRejectedByManager(Long id) {
		LeaveModel leaveModel = leaveRecordById(id);;
		
		leaveModel.setAccepted(false);
	}

}
