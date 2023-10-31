package io.Backend.ServerManager.model;

import io.Backend.ServerManager.enumeration.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Server {
    @Id
    @GeneratedValue( strategy = AUTO)
    private Long id;
    //this makes ip address unique in the DB, will throw exception
    @Column(unique = true)
    @NotEmpty(message = "IP address cant be empty")
    private String ipAddress;
    private String name;
    private String type;
    private String memory;
    private String imageURL;
    private Status status;


}
