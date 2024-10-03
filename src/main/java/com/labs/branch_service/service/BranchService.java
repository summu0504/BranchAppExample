package com.labs.branch_service.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.labs.branch_service.entity.BranchEntity;
import com.labs.branch_service.repo.BranchRepository;
import com.labs.branch_service.response.BranchResponse;

import jakarta.transaction.Transactional;

@Service
public class BranchService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BranchRepository branchRepository;

	@Transactional
	public BranchResponse gerBranches(int id) {
		BranchEntity branch = branchRepository.findById(id).get();
		BranchResponse branchResponse = modelMapper.map(branch, BranchResponse.class);
		return branchResponse;
	}

	@Transactional
	public BranchEntity addBranch(BranchEntity branchEntity) throws Exception {
		// Check if a branch with the same name already exists
		Optional<BranchEntity> existingBranch = branchRepository.findByName(branchEntity.getBranchName());

		if (existingBranch.isPresent()) {
			// Throw an exception or handle the case as per your requirements
			throw new Exception("Branch with the same name already exists");
		}

		// If not found, save the new branch entity
		BranchEntity savedBranch = branchRepository.save(branchEntity);
		return savedBranch;

	}

	@Transactional
	public boolean updateBranch(Integer id, String branch, String status) {
		int rowsUpdated = branchRepository.updateBranch(id, branch, status);
		return rowsUpdated > 0; // Return true if update was successful
	}

	public Optional<BranchEntity> findById(Integer id) {
		return branchRepository.findById(id); // Fetch the updated entity if needed
	}

	@Transactional
	public boolean deletBranch(int id) {
		int rowsDeleted = branchRepository.deleteBranch(id);
		return rowsDeleted > 0;
	}

	public Optional<BranchEntity> deletebyId(Integer id) {
		return branchRepository.findById(id);
	}

}
