package io.Backend.ServerManager.service.implementation;

import io.Backend.ServerManager.enumeration.Status;
import io.Backend.ServerManager.model.Server;
import io.Backend.ServerManager.service.ServerService;
import io.Backend.ServerManager.repository.ServoRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static io.Backend.ServerManager.enumeration.Status.SERVER_DOWN;
import static io.Backend.ServerManager.enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {
    private final ServoRepo serverRepo;

    //public ServerServiceImplementation(ServoRepo serverRepo) {
    //    this.serverRepo = serverRepo;
    //}

    @Override
    public Server create(Server server) {
        log.info("Saving new Server: {}", server.getName());
        server.setImageURL(setServerImageURL());
        return serverRepo.save(server);
    }
    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server getServer(Long id) {
        log.info("Fetching server by ID: {}", id);
        return serverRepo.findById(id).orElseThrow();
    }

    @Override
    public Server updateSever(Server server) {
        log.info("Updating Server: {}", server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean deleteServer(Long id) {
        log.info("Deleting Server by ID: {}", id);
        serverRepo.deleteById(id);
        return TRUE;
    }

    @Override
    public Server pingServer(String ipAddress) throws IOException {
        log.info("Pinging Server IP: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }
    private String setServerImageURL() {
        String[] imageNames = {"image1.png", "image2.png", "image3.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/ServerManager/images/" + imageNames[new Random().nextInt(3)]).toUriString();
    }
}
