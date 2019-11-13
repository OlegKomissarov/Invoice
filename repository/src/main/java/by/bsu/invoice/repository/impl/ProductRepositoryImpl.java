package by.bsu.invoice.repository.impl;

import by.bsu.invoice.entity.Product;
import by.bsu.invoice.repository.AbstractRepository;
import by.bsu.invoice.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("productRepository")
public class ProductRepositoryImpl extends AbstractRepository<Product> implements ProductRepository {

    public ProductRepositoryImpl() {
        super.setClazz(Product.class);
    }

    @Override
    public List<Product> getAll() {
        // TODO: add to controller
        // TODO: move to abstract class
        return (List<Product>) entityManager.createNativeQuery("SELECT * FROM product").getResultList();
    }

    @Override
    public List<Product> getBySellerId(long sellerId) {
        // TODO: add to controller
        return (List<Product>) entityManager
                .createNativeQuery("SELECT * FROM product where seller_id=? AND is_archival=0")
                .setParameter(1, sellerId).getResultList();
    }
}
