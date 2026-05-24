package com.example.demo.team2.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.team2.entity.Team2User;
import com.example.demo.team2.form.Team2LoginForm;
import com.example.demo.team2.form.Team2ProfileEditForm;
import com.example.demo.team2.form.Team2RegisterForm;
import com.example.demo.team2.repository.Team2UserRepository;

@Service
public class Team2UsersService {
	@Autowired
    private Team2UserRepository usersRepository;

    // ログイン認証
    public Team2User authenticate(String loginId, String password) {
        List<Team2User> users = usersRepository.findByLoginIdAndPassword(loginId, password);
        if (users.isEmpty()) return null;
        Team2User user = users.get(0);
        if (user.getIsDeleted() == 1) {
        	System.out.println("退会済み");
        	return null;
        }
        return user;
    }

    // loginId重複チェック
    public boolean existsByLoginId(String loginId) {
        return !usersRepository.findByLoginId(loginId).isEmpty();
    }

    // ユーザ登録
    public Team2User register(Team2RegisterForm form) {
        Team2User user = new Team2User();
        user.setLoginId(form.getLoginId());
        user.setPassword(form.getPassword());
        user.setUserName(form.getUserName());
        user.setIsDeleted(0);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return usersRepository.save(user);
    }
    
    //ログイン
    public Team2LoginForm login(Team2RegisterForm form) {
    	Team2LoginForm loginForm = new Team2LoginForm();
    	loginForm.setLoginId(form.getLoginId());
    	return loginForm;
    }

    // userId で取得
    public Team2User findById(int userId) {
        return usersRepository.findById(userId).orElse(null);
    }
    
    //ログインID重複チェック
    public boolean existsByLoginId(String loginId, int userId) {
    	return usersRepository.existsByLoginIdAndUserIdNot(loginId, userId);
    }
    
    //更新
    public void updateProfile(Team2ProfileEditForm form) {
    	Team2User user = usersRepository.findById(form.getUserId()).orElse(null);
    	if (user == null) return;
    	user.setUserName(form.getUserName());
    	user.setLoginId(form.getLoginId());
    	usersRepository.save(user);
    }
    
    //パスワードチェック
    public boolean checkPassword(int userId, String currentPassword) {
    	Team2User user = usersRepository.findById(userId).orElse(null);
    	if (user == null) return false;
    	return user.getPassword().equals(currentPassword);
    }
    
    //パスワード更新
    public void updatePassword(int userId, String newPassword) {
    	Team2User user = usersRepository.findById(userId).orElse(null);
    	if (user == null) return;
    	user.setPassword(newPassword);
    	usersRepository.save(user);
    }
    
    //退会
    public void delete(int userId) {
    	Team2User user = usersRepository.findById(userId).orElse(null);
    	if (user == null) return;
    	user.setIsDeleted(1);
    	user.setUpdatedAt(LocalDateTime.now());
    	usersRepository.save(user);
    }
}
