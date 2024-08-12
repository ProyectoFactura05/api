package org.izpo.apigestionfacturas.jwt;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.izpo.apigestionfacturas.model.entity.User;
import org.izpo.apigestionfacturas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
@Getter
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private User userDetails;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userDetails = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        if(userDetails != null){
            return new org.springframework.security.core.userdetails.User(userDetails.getEmail(),userDetails.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
