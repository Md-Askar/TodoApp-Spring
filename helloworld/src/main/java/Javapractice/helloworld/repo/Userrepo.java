package Javapractice.helloworld.repo;

import Javapractice.helloworld.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public interface Userrepo extends JpaRepository<User,Integer> {
    Optional<User>findByEmail(String email);
}
