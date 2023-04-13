package com.esp.gestionusers.dao;

import org.springframework.stereotype.Service;

import com.esp.gestionusers.beans.Utilisateur;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserRepositoryImp {
	private UserRepository UserRepository;
	
	public Utilisateur getLoginAndPassword(String login, String password) {
   return UserRepository.findByUsernameAndPassword(login, password);
	
	}
}
