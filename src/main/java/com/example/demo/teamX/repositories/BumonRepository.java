package com.example.demo.teamX.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.teamX.entity.Bumon;

//@Repository
public interface BumonRepository extends JpaRepository<Bumon, String> {
	@Query(value = "select * from bumon_tbl where bumon_cd = :code" , nativeQuery = true)
	public List<Bumon> findBumonCd(@Param("code") String code);
}
