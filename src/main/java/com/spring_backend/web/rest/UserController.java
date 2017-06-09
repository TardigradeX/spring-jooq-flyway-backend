package com.spring_backend.web.rest;

import com.spring_backend.domain.User;
import com.spring_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@PathVariable("id") long id){
        Optional<User> userOptional = userRepository.getUser(id);

        if(userOptional.isPresent())
            return ResponseEntity.ok(userOptional.get());
        else
            return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity deleteUser(@PathVariable("id") long id){

        if(userRepository.deleteUser(id))
            return ResponseEntity.ok("USER WITH ID:" + id + "DELETED.");
        else
            return ResponseEntity.notFound().build();

    }



}
