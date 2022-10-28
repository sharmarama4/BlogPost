package com.example.springbootblog.application.config;

import com.example.springbootblog.application.models.Authority;
import com.example.springbootblog.application.models.Post;
import com.example.springbootblog.application.models.Account;
import com.example.springbootblog.application.repository.AuthorityRepository;
import com.example.springbootblog.application.services.PostService;
import com.example.springbootblog.application.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedDate implements CommandLineRunner {
    @Autowired
    private PostService postService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();
        if (posts.size() == 0) {

            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("user");
            account1.setLastName("user");

            account1.setEmail("user.user@domain.com");
            account1.setPassword("password");


            account2.setFirstName("admin");
            account2.setLastName("admin");
            account2.setEmail("admin.admin@domain.com");
            account2.setPassword("password");


            accountService.save(account1);
            accountService.save(account2);
            Post post1 = new Post();
            post1.setTitle("title of post 1");
            post1.setBody("This is the body post 1");
            post1.setAccount(account1);//


            Post post2 = new Post();
            post2.setTitle("title of post 2");
            post2.setBody("This is the body post 2");
            post2.setAccount(account2);//

            postService.save(post1);
            postService.save(post2);

       /*  Authority user=new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin=new Authority();
            user.setName("ROLE_ADMIN");
            authorityRepository.save(admin);
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);
            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);

        }*/

        }
    }
}
