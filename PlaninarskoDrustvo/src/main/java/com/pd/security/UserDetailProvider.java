package com.pd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pd.repository.KorisnikRepository;
import model.Korisnik;

@Service
public class UserDetailProvider implements UserDetailsService {
	
	@Autowired
	KorisnikRepository kr;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Korisnik k = kr.findByKorisnickoIme(username);
		
		UserDetails ud = new CustomUserDetail(k);
		return ud;
	}
}
