package app.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    private int id;

    private String name;

    private String path;

    @ManyToMany(mappedBy = "category")
    private List<Post> post;
}
