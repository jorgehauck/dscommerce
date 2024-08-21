package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.projections.UserDetailsByEmailProjection;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private Set<RoleDTO> roles = new HashSet<>();
    private List<String> authority = new ArrayList<>();
    public UserDTO() {}
    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
        birthDate = entity.getBirthDate();
        entity.getRoles().forEach(role -> roles.add(new RoleDTO(role)));
    }
    public UserDTO(UserDetailsByEmailProjection projection) {
        id = projection.getId();
        name = projection.getName();
        email = projection.getEmail();
        phone = projection.getPhone();
        birthDate = projection.getData();
        authority = projection.getRoles();
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public Set<RoleDTO> getRoles() {
        return roles;
    }
    public List<String> getAuthority() {
        return authority;
    }
}
