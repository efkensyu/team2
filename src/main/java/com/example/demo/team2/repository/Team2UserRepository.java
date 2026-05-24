package com.example.demo.team2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.team2.entity.Team2User;

@Repository
public interface Team2UserRepository extends JpaRepository<Team2User, Integer>{
	List<Team2User> findByLoginId(String loginId);
	
	 List<Team2User> findByLoginIdAndPassword(String loginId, String password);
	 boolean existsByLoginIdAndUserIdNot(String loginId, int userId);
}
