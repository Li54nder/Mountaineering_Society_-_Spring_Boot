package com.pd.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import model.Korisnik;

public class CustomUserDetail implements UserDetails {

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
		authorities.add(new SimpleGrantedAuthority("ROLE_" + k.getUloga().getTip().toString())); //getTipVracaByte(Enum iz tabele.. VARBINARY)
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
