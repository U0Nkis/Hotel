package org.example.Controllers;

import org.example.Entities.Service;
import org.example.Services.ServiceService;

import java.util.Scanner;

public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public void manageServices(Scanner scanner) {
        System.out.println("1. Add Service");
        System.out.println("2. View Service");
        System.out.println("3. Update Service");
        System.out.println("4. Delete Service");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addService(scanner);
                break;
            case 2:
                viewService(scanner);
                break;
            case 3:
                updateService(scanner);
                break;
            case 4:
                deleteService(scanner);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void addService(Scanner scanner) {
        System.out.print("Enter service name: ");
        String name = scanner.nextLine();
        System.out.print("Enter service price: ");
        double price = scanner.nextDouble();

        Service service = new Service(0, name, price);
        serviceService.addService(service);
        System.out.println("Service added: " + service);
    }

    private void viewService(Scanner scanner) {
        System.out.print("Enter service ID: ");
        int id = scanner.nextInt();
        Service service = serviceService.findServiceById(id);
        System.out.println(service != null ? service : "Service not found");
    }

    private void updateService(Scanner scanner) {
        System.out.print("Enter service ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter service name: ");
        String name = scanner.nextLine();
        System.out.print("Enter service price: ");
        double price = scanner.nextDouble();

        Service service = new Service(id, name, price);
        serviceService.updateService(id, service);
        System.out.println("Service updated: " + service);
    }

    private void deleteService(Scanner scanner) {
        System.out.print("Enter service ID: ");
        int id = scanner.nextInt();
        serviceService.deleteService(id);
        System.out.println("Service deleted.");
    }
}
