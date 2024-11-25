package com.example.Account.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Accounts extends BaseEntity {

    @Column(name = "customerId")
    private long customerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "accountNo")
    private long accountNo;

    @Column(name = "accountType")
    private String accountType;

    @Column(name = "branchAddress")
    private String branchAddress;


}
