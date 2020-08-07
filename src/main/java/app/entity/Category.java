package app.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    private int id;

    private String name;

    private String path;
}
