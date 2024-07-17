package com.devsuperior.dscommerce.projections;
import java.time.LocalDate;
import java.util.List;

public interface UserDetailsByEmailProjection {
    Long getId();
    String getName();
    String getEmail();
    String getPhone();
    LocalDate getData();
    List<String> getRoles();
}
