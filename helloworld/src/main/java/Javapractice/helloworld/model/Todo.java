package Javapractice.helloworld.model;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
public class Todo {

    @Id
    @GeneratedValue

     private   int id;
     private String Title;
    @JsonProperty("isTrue")

    private boolean isTrue;
  }
