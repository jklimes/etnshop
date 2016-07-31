package cz.etn.etnshop.service;

import cz.etn.etnshop.dao.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {

    @Transactional(readOnly = false)
    void saveProduct(Product product);

    @Transactional(readOnly = true)
    List<Product> getProducts();

    @Transactional(readOnly = false)
    void deleteProduct(int productId);

    @Transactional(readOnly = false)
    void updateProduct(Product product);

    @Transactional(readOnly = false)
    Product getProduct(int productId);

    @Transactional(readOnly = true)
    List<Product> findProducts(String pattern);
}
