package org.example.Services;

import org.example.Entities.Service;
import org.example.Repositories.ServiceRepository;

import java.util.Map;

public class ServiceService {
    private final ServiceRepository serviceRepository = new ServiceRepository();

    public Service addService(Service service) {
        return serviceRepository.save(service);
    }

    public Service findServiceById(int id) {
        return serviceRepository.findById(id);
    }

    public Map<Integer, Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public void updateService(int id, Service service) {
        serviceRepository.save(service);
    }

    public void deleteService(int id) {
        serviceRepository.deleteById(id);
    }
}
