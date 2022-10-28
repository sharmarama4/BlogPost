package com.example.springbootblog.application.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

 @OneToMany(mappedBy ="account")
    private List<Post> posts;

 @ManyToMany(fetch = FetchType.EAGER)
 @JoinTable(name="account_authority",
 joinColumns = {@JoinColumn(name="account_id",referencedColumnName ="id")},
         inverseJoinColumns ={@JoinColumn(name="authority_name",referencedColumnName ="name" )} )
 private Set<Authority>authorities=new HashSet<>();



    @Override
    public String toString() {
        return "Account{" +

                ", email='" + email + '\'' +

                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +

                ", authorities=" + authorities +
                '}';
    }
}

