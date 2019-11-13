package by.bsu.invoice.service;

import by.bsu.invoice.entity.Invoice;
import java.sql.Date;

import java.util.List;

public interface InvoiceService extends Service<Invoice> {
    List<Invoice> getByUserId(long userId);

    long[] getSellerAndCustomerId(long invoiceId);

    void create(String title,
                  String description,
                  Date paymentDeadline,
                  long sellerId,
                  String customerEmail,
                  long customerId,
                  String[] services,
                  String[] serviceAmount);

    void delete(long id);

    void approve(long id);

    //    List<Invoice> getByTitleAndUserId(String title, long userId);
}
