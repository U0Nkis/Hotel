package org.example.Repositories;

import org.example.Entities.Invoice;

import java.util.HashMap;
import java.util.Map;

public class InvoiceRepository {
    private final Map<Integer, Invoice> invoiceStorage = new HashMap<>();
    private int nextId = 1;

    public Invoice save(Invoice invoice) {
        if (invoice.getId() == 0) {
            invoice.setId(nextId++);
        }
        invoiceStorage.put(invoice.getId(), invoice);
        return invoice;
    }

    public Invoice findById(int id) {
        return invoiceStorage.get(id);
    }

    public Map<Integer, Invoice> findAll() {
        return invoiceStorage;
    }

    public void deleteById(int id) {
        invoiceStorage.remove(id);
    }
}
