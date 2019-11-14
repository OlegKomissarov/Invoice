package by.bsu.invoice.service;

import by.bsu.invoice.entity.Invoice;
import java.sql.Date;

import java.util.List;

public interface InvoiceService extends Service<Invoice> {
    List<Invoice> getByUserId(Integer userId);

    void create(Date createdDate, String title, Integer invoiceNumber, Integer userId, Integer isArchival);

    void delete(Integer id);

//    List<Invoice> getByTitleAndUserId(String title, Integer userId);
}
