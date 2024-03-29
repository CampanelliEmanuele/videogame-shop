package org.learning.videogameshop.security;

import org.learning.videogameshop.model.User;
import org.learning.videogameshop.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DatabaseUserDetails implements UserDetails {

  private Integer id;
  private String username;
  private String password;

  private Set<GrantedAuthority> authorities;

  // costruttore che copia i dati da User e valorizza gli attributi che servono a Spring
  public DatabaseUserDetails(User user) {
    this.id = user.getId();
    this.username = user.getEmail();
    this.password = user.getPassword();
    // itero sui roles del User e li trasformo in GrantedAuthority
    authorities = new HashSet<>();
    for (Role role : user.getRoleSet()) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
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

  @Override
  public boolean isEnabled() {
    return true;
  }

  /* GETTERS AND SETTERS */

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
