package com.pd.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import model.Korisnik;

public class CustomUserDetail implements UserDetails {
	
	private static final long serialVersionUID = 8454563604940445837L;
	
	private Korisnik k;
	
	public Korisnik getK() {
		return k;
	}
	public void setK(Korisnik k) {
		this.k = k;
	}
	public CustomUserDetail(Korisnik k) {
		this.k = k;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		
		String role = k.getUloga() == null? "NijeClan" : k.getUloga().getTip().toString();
		
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authorities;
	}
	@Override
	public String getPassword() {
		return k.getSifra();
	}
	@Override
	public String getUsername() {
		return k.getKorisnickoIme();
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


}
