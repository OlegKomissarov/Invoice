package by.bsu.invoice.repository;

import by.bsu.invoice.entity.Product;

import java.util.List;

public interface ProductRepository extends Repository<Product> {

    List<Product> getBySellerId(long sellerId);
}
