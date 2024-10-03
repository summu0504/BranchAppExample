package com.labs.branch_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labs.branch_service.entity.BranchEntity;
import com.labs.branch_service.response.BranchResponse;
import com.labs.branch_service.service.BranchService;

@RestController
@RequestMapping("/branches")
public class BranchController {

	@Autowired
	private BranchService branchService;

	// get branch Rest API using ID
	@GetMapping("get/branch/{id}")
	public ResponseEntity<BranchResponse> getBranch(@PathVariable int id) throws Exception {
		if (id == 0) {
			throw new Exception("Id Should not be 0");
		}
		BranchResponse branchResponse = branchService.gerBranches(id);
		return new ResponseEntity<>(branchResponse, HttpStatus.ACCEPTED);

	}

	// Exception handler method for handling exceptions in this controller
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		// Return a custom error message with 400 Bad Request
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// add new Branch
	@PostMapping("/add/branch")
	public ResponseEntity<String> addBranch(@RequestBody BranchEntity branchEntity) throws Exception {
		if (branchEntity == null) {
			throw new NullPointerException();
		}
		BranchEntity branch = branchService.addBranch(branchEntity);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handledException(NullPointerException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// Update branches
	@PutMapping("/update/branch/{id}/{branch}/{status}")
	public ResponseEntity<String> updateBranch(@PathVariable int id, @PathVariable String branch,
			@PathVariable String status) throws Exception {
		if (id <= 0) {
			throw new IllegalArgumentException("Invalid ID: ID must be greater than 0.");
		}
		if (branch == null || branch.isEmpty()) {
			throw new IllegalArgumentException("Branch name cannot be null or empty.");
		}
		if (status == null || status.isEmpty()) {
			throw new IllegalArgumentException("Status cannot be null or empty.");
		}

		boolean isUpdated = branchService.updateBranch(id, branch, status);
		if (isUpdated) {
			return ResponseEntity.ok("Branch updated successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Branch with id " + id + " not found.");
		}
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	// delete branches
	@DeleteMapping("/delete/branch/{id}")
	public ResponseEntity<String> deleteBranch(@PathVariable int id) throws Exception {
		if (id <= 0) {
			throw new IllegalArgumentException("Invalid ID: ID must be greater than 0.");
		}

		boolean isDeleted = branchService.deletBranch(id);
		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
