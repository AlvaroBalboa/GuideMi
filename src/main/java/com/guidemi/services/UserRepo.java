package com.guidemi.services;

import com.guidemi.entities.Users;
import org.springframework.data.repository.CrudRepository;


public interface UserRepo extends CrudRepository<Users,Integer>{
}
