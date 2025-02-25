package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.RoleDTO;
import com.devsuperior.dscommerce.dto.UserInsertDTO;
import com.devsuperior.dscommerce.dto.UserDTO;
import com.devsuperior.dscommerce.entities.Role;
import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.projections.UserDetailsByEmailProjection;
import com.devsuperior.dscommerce.projections.UserDetailsProjection;
import com.devsuperior.dscommerce.repositories.RoleRepository;
import com.devsuperior.dscommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmailService emailService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
        if (result.size() == 0) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = new User();
        user.setEmail(result.get(0).getUsername());
        user.setPassword(result.get(0).getPassword());
        for (UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }
    protected User authenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");
            List<UserDetailsByEmailProjection> projection = repository.getUserByEmail(username);

            User user = new User();
            user.setId(projection.get(0).getId());
            user.setName(projection.get(0).getName());
            user.setEmail(projection.get(0).getEmail());
            user.setPhone(projection.get(0).getPhone());
            user.setBirthDate(projection.get(0).getData());

            for (UserDetailsByEmailProjection userItem : projection) {
                 for (String role : userItem.getRoles()) {
                     user.addRole(new Role(role));
                 }
            }
            return user;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("Email not found");
        }
    }
    @Transactional
    public UserDTO insertUser(UserInsertDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setBirthDate(userDTO.getBirthDate());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        for (RoleDTO roleDTO : userDTO.getRoles()) {
            Role role = roleRepository.getReferenceById(roleDTO.getId());
            user.getRoles().add(role);
        }

        user = repository.save(user);
        return new UserDTO(user);
    }
    @Transactional(readOnly = true)
    public UserDTO getLoggedUser() {
        User user = authenticated();
        return new UserDTO(user);
    }
}
