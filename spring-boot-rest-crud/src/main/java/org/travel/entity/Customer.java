package org.travel.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String source;
    private String destination;
    private String packageType;
    private int cost;
    private String address;
    private String phone;
    private String comments;

    Customer(){}

    public Customer(long id, String firstName, String lastName, String source, String destination, String packageType, int cost, String address, String phone, String comments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.source = source;
        this.destination = destination;
        this.packageType = packageType;
        this.cost = cost;
        this.address = address;
        this.phone = phone;
        this.comments = comments;
    }
}
