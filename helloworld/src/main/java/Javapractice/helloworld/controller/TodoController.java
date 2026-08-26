package Javapractice.helloworld.controller;

import Javapractice.helloworld.service.TodoService;
import Javapractice.helloworld.service.UserService;
import Javapractice.helloworld.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
@Autowired
    private TodoService todoservice;

    @PostMapping("/create")
    ResponseEntity<Todo>create(@RequestBody Todo todo){

       return new ResponseEntity<>(todoservice.create(todo), HttpStatus.OK);
    }

    @GetMapping("/find")
    ResponseEntity<Todo>findbyid(@RequestParam int id){
        return new ResponseEntity<>(todoservice.findbyid(id),HttpStatus.OK);
    }
    @GetMapping("/findAll")
    ResponseEntity<List<Todo>>find(){
        return new ResponseEntity<>(todoservice.findAll(),HttpStatus.OK);
    }
    @GetMapping("/findAllpage")
    ResponseEntity<Page<Todo>>findAll(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(todoservice.getalltodos(page,size),HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    ResponseEntity<Void>delete(@RequestParam int id){
        todoservice.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping
    ResponseEntity<Todo>updatetodo(@RequestBody Todo todo){
        return new ResponseEntity<>(todoservice.update(todo),HttpStatus.OK);
    }







}
