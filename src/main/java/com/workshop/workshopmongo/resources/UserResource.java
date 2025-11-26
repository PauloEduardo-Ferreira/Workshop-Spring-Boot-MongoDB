package com.workshop.workshopmongo.resources;

import com.workshop.workshopmongo.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() {
        User juliana = new User("1", "Juliana Ros", "juliana@gmail.com");
        User paulo = new User("2", "Paulo Eduardo", "paulo@gmail.com");
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(juliana, paulo));
        return ResponseEntity.ok().body(list);
    }
}
