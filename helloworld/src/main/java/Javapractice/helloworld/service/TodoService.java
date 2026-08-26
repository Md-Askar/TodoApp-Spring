package Javapractice.helloworld.service;

import Javapractice.helloworld.model.Todo;
import Javapractice.helloworld.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepo todorepo;

   public Todo create(Todo todo){
        return todorepo.save(todo);
    }
   public List<Todo> findAll(){
        return todorepo.findAll();
    }
   public Todo findbyid(int id){
       return todorepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
    }
   public Page<Todo>getalltodos(int page, int size){
        Pageable pageable= PageRequest.of(page,size);
        return todorepo.findAll(pageable);
    }
    public void delete(int id){
       todorepo.deleteById(id);
    }
    public Todo update(Todo todo) {

        Todo existing = todorepo.findById(todo.getId())
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        existing.setTitle(todo.getTitle());
        existing.setTrue(todo.isTrue());

        return todorepo.save(existing);
    }


}
