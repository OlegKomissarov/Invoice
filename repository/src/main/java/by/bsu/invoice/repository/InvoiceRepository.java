package by.bsu.invoice.repository;

import by.bsu.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@org.springframework.stereotype.Repository
public interface InvoiceRepository extends Repository<Invoice> {

    @Modifying
    @Query(value = "UPDATE invoice.invoice SET is_archival=1 WHERE id=?", nativeQuery = true)
    void delete(Integer id);

    @Query(value = "SELECT * from invoice.invoice where user_id=? and is_archival=0", nativeQuery = true)
    List<Invoice> getByUserId(Integer userId);
}
