package cz.etn.etnshop.service;

import cz.etn.etnshop.dao.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {

    @Transactional
    void saveProduct(Product product);

    @Transactional
    void saveProduct(String name, String serialNumber);

    @Transactional(readOnly = true)
    List<Product> getProducts();

    @Transactional
    void deleteProduct(int productId);

    @Transactional
    void updateProduct(Product product);

    @Transactional
    void updateProduct(int productId, String name);

    @Transactional
    Product getProduct(int productId);

    @Transactional(readOnly = true)
    List<Product> findProducts(String pattern);
}
