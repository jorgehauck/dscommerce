package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

public class RoleDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String authority;
    public RoleDTO() {}
    public RoleDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }
    public RoleDTO(Role entity) {
        id = entity.getId();
        authority = entity.getAuthority();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
