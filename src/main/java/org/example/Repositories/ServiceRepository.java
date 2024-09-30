package org.example.Repositories;

import org.example.Entities.Service;

import java.util.HashMap;
import java.util.Map;

public class ServiceRepository {
    private final Map<Integer, Service> serviceStorage = new HashMap<>();
    private int nextId = 1;

    public Service save(Service service) {
        if (service.getId() == 0) {
            service = new Service(nextId++, service.getName(), service.getPrice());
        }
        serviceStorage.put(service.getId(), service);
        return service;
    }

    public Service findById(int id) {
        return serviceStorage.get(id);
    }

    public Map<Integer, Service> findAll() {
        return serviceStorage;
    }

    public void deleteById(int id) {
        serviceStorage.remove(id);
    }
}
