package by.bntu.info.config;

import by.bntu.info.model.entity.User;
import by.bntu.info.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(s);
        Set<GrantedAuthority> roles = new HashSet<>();
        if (user.getRole().equals("ROLE_ADMIN")){
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else{
            roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);

    }
}
