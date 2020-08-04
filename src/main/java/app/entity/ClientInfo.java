package app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "client_info")
public class ClientInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;

    @OneToMany(mappedBy = "clientInfo")
    private List<ClientPreviousCredits> credits;
}
