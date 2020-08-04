package app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "previous_credits")
public class ClientPreviousCredits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double salary;

    private double totalCredit;

    private double perMonth;

    private String requiredPaymentDate;

    private String clientPaymentDate;

    @ManyToOne
    private ClientInfo clientInfo;
}
