package app.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;

    private String email;

    @Column(length = 1000)
    private String comment;

    private String date;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(String fullName,String email, String comment, String date, Post post) {
        this.fullName = fullName;
        this.email = email;
        this.comment = comment;
        this.date = date;
        this.post = post;
    }
}
