package cz.etn.etnshop.dao;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductDao {

    @Transactional(readOnly = false)
    void saveProduct(Product product);

    @Transactional(readOnly = true)
    List<Product> getProducts();

    @Transactional(readOnly = false)
    void deleteProduct(int productId);

    @Transactional(readOnly = false)
    void updateProduct(Product product);

    Product get(int id);
}
