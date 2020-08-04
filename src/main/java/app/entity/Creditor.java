package app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "creditor")
public class Creditor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;

    private String login;

    private String password;

    private String role;
}
