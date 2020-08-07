package app.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String header;

    private String content;

    private String author;

    private String date;

    private String premium;
}
