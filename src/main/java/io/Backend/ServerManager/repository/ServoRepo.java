package io.Backend.ServerManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.Backend.ServerManager.model.Server;
//Will allow manipulation of server from app
public interface ServoRepo extends JpaRepository<Server, Long> {
    //does a select based on input of function
    Server findByIpAddress(String ipAddress);


}
