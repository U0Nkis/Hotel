package org.example.Repositories;

import org.example.Entities.Invoice;

import java.util.HashMap;
import java.util.Map;

public class InvoiceRepository {
    private final Map<Integer, Invoice> invoiceStorage = new HashMap<>();
    private int nextId = 1;

    // Создание или обновление счета
    public Invoice save(Invoice invoice) {
        if (invoice.getId() == 0) {
            invoice = new Invoice(nextId++, invoice.getBooking(), invoice.getServices());
        }
        invoiceStorage.put(invoice.getId(), invoice);
        return invoice;
    }

    // Поиск по ID
    public Invoice findById(int id) {
        return invoiceStorage.get(id);
    }

    // Получить все счета
    public Map<Integer, Invoice> findAll() {
        return invoiceStorage;
    }

    // Удаление по ID
    public void deleteById(int id) {
        invoiceStorage.remove(id);
    }
}
