package com.singin.singin.Repository.Crud;

import java.util.Optional;

import com.singin.singin.Model.User;

import org.springframework.data.repository.CrudRepository;

/**
 * @author JSBM
 */
public interface UserCrudRepository extends CrudRepository <User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    //Optional<User> findByName(String name);
}
