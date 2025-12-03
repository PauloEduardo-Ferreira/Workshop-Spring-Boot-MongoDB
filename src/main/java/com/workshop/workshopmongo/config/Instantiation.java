package com.workshop.workshopmongo.config;

import com.workshop.workshopmongo.domain.Post;
import com.workshop.workshopmongo.domain.User;
import com.workshop.workshopmongo.dto.AuthorDTO;
import com.workshop.workshopmongo.dto.CommentDTO;
import com.workshop.workshopmongo.repository.PostRepository;
import com.workshop.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User jorge = new User(null, "Jorge Brown", "jorge@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(jorge, alex, bob));

        Post post1 = new Post(null, simpleDateFormat.parse("22/05/2025"), "Partiu viagem", "Vou viajar para praia de Santos. Até mais!", new AuthorDTO(jorge));
        Post post2 = new Post(null, simpleDateFormat.parse("24/05/2025"), "Boa tarde", "Hoje o dia está muito quente!", new AuthorDTO(jorge));

        CommentDTO comment1 = new CommentDTO("Boa viagem mano!", simpleDateFormat.parse("22/05/2025"), new AuthorDTO(alex));
        CommentDTO comment2 = new CommentDTO("Aproveite!", simpleDateFormat.parse("23/05/2025"), new AuthorDTO(bob));
        CommentDTO comment3 = new CommentDTO("Tenha uma ótima tarde!", simpleDateFormat.parse("24/05/2025"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().addAll(Arrays.asList(comment3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        jorge.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(jorge);
    }
}
