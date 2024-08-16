package model;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class Customer {

    private String id;
    private String title;
    private String name;
    private String address;
    private String contactNumber;

    public Customer(String id, String title, String name, String address, String contactNumber) {
        this.id = id;
        this.name = title+name;
        this.address = address;
        this.contactNumber = contactNumber;
    }


    public Customer(String id, String name, String address, String contactNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
    }
}
