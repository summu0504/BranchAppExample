package com.labs.branch_service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.labs.branch_service.entity.BranchEntity;

import jakarta.transaction.Transactional;

public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {

	@Query("SELECT b FROM BranchEntity b WHERE b.branchName = :branchName AND b.status = 'ENABLE'")
	Optional<BranchEntity> findByName(@Param("branchName") String branchName);

	@Modifying
	@Transactional
	@Query("UPDATE BranchEntity b SET b.branchName = :branch, b.status = :status WHERE b.branchId = :id")
	int updateBranch(@Param("id") Integer id, @Param("branch") String branch, @Param("status") String status);

	@Modifying
	@Transactional
	@Query("DELETE FROM BranchEntity b  WHERE b.branchId = :id")
	int deleteBranch(@Param("id") int id);

}
