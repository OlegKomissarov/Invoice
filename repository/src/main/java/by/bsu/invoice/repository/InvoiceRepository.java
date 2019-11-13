package by.bsu.invoice.repository;

import by.bsu.invoice.entity.Invoice;

import java.util.List;

public interface InvoiceRepository extends Repository<Invoice> {

    List<Invoice> getByUserId(long sellerId);

    long[] getSellerAndCustomerId(long id);

    void delete(long id);

    void approve(long id);

//    List<Invoice> getByTitleAndUserId(String title, long userId);
}
