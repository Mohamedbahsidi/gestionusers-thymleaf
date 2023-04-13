package com.esp.gestionusers.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esp.gestionusers.beans.Utilisateur;

public interface UserRepository  extends JpaRepository<Utilisateur, Integer> {
  Utilisateur findByUsernameAndPassword(String login, String password);
  Utilisateur findByUsername(String login);
}
