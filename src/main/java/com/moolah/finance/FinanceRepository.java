package com.moolah.finance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long> {

	List<Finance> findAllByUserIdOrderByIdDesc(Long userId);
}
