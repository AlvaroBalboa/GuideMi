package com.guidemi.services;

import com.guidemi.entities.Route;
import org.springframework.data.repository.CrudRepository;

import java.util.List;



public interface RouteRepo extends CrudRepository<Route,Integer> {
    List findByUserId(int userId);
}
