package org.example.Repositories;

import org.example.Entities.Payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentRepository {
    private final Map<Integer, Payment> paymentStorage = new HashMap<>();
    private int nextId = 1;

    // Создание или обновление платежа
    public Payment save(Payment payment) {
        if (payment.getId() == 0) {
            payment = new Payment(nextId++, payment.getInvoice(), payment.getPaymentMethod(), payment.isPaid());
        }
        paymentStorage.put(payment.getId(), payment);
        return payment;
    }

    // Поиск по ID
    public Payment findById(int id) {
        return paymentStorage.get(id);
    }

    // Получить все платежи
    public Map<Integer, Payment> findAll() {
        return paymentStorage;
    }

    // Удаление по ID
    public void deleteById(int id) {
        paymentStorage.remove(id);
    }
}
