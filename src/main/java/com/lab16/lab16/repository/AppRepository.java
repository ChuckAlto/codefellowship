package com.lab16.lab16.repository;

import com.lab16.lab16.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<AppUser, Long> {


    AppUser findByUsername(String username);

}
