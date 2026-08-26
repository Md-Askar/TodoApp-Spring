package Javapractice.helloworld.service;


import Javapractice.helloworld.model.User;
import Javapractice.helloworld.repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private Userrepo userrepo;

   public void create( User user){
        userrepo.save(user);
    }
    User getById(int id){
       return userrepo.getById(id);
    }



}
