package org.example.Repositories;

import org.example.Entities.Payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentRepository {
    private final Map<Integer, Payment> paymentStorage = new HashMap<>();
    private int nextId = 1;

    public Payment save(Payment payment) {
        if (payment.getId() == 0) {
            payment.setId(nextId++);
        }
        paymentStorage.put(payment.getId(), payment);
        return payment;
    }

    public Payment findById(int id) {
        return paymentStorage.get(id);
    }

    public Map<Integer, Payment> findAll() {
        return paymentStorage;
    }

    public void deleteById(int id) {
        paymentStorage.remove(id);
    }
}
