package io.Backend.ServerManager.service;

import io.Backend.ServerManager.model.Server;

import java.io.IOException;
import java.util.Collection;
public interface ServerService {
    //create new server
    Server create(Server server);
    //return a collection of servers based on limit
    Collection<Server> list(int limit);
    //return the server given ID
    Server getServer(Long id);
    //updates server based on server object
    Server updateSever(Server server);
    //returns T/F if server was deleted based on ID
    Boolean deleteServer(Long id);
    //pings the server
    Server pingServer(String ipAddress) throws IOException;

}
