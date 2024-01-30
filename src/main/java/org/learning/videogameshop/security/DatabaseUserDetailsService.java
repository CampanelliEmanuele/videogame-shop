package org.learning.videogameshop.security;

import org.learning.videogameshop.model.User;
import org.learning.videogameshop.repository.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

  @Autowired
  private LibraryUserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // cerco il LibraryUser con l'email = a quello username
    Optional<User> libraryUser = repository.findByEmail(username);
    // se lo trovo lo converto in un DatabaseUserDetail e lo restituisco
    if (libraryUser.isPresent()) {
      return new DatabaseUserDetails(libraryUser.get());
    } else {
      // se non lo trovo sollevo un'eccezione UsernameNotFoundException
      throw new UsernameNotFoundException(username);
    }
  }

}
