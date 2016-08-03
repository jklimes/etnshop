package cz.etn.etnshop.service;

import cz.etn.etnshop.asserts.Asserts;
import cz.etn.etnshop.dao.Product;
import cz.etn.etnshop.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void saveProduct(Product product) {
        assertProduct(product);
        productDao.saveProduct(product);
    }

    @Override
    public void saveProduct(String name, String serialNumber) {
        Product product = new Product();
        product.setName(name);
        product.setSerialNumber(serialNumber);

        assertProduct(product);

        productDao.saveProduct(product);
    }

    @Override
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @Override
    public void deleteProduct(int productId) {
        productDao.deleteProduct(productId);
    }

    @Override
    public void updateProduct(Product product) {
        assertProduct(product);
        productDao.updateProduct(product);
    }

    @Override
    public void updateProduct(int productId, String name) {
        Asserts.notNullOrEmpty(name, "Name is required and must not be null or empty. name='" + name + "'");
        Product product = getProduct(productId);
        Asserts.notNull(product, "Product with id " + productId + " does not exist.");
        product.setName(name);
        productDao.updateProduct(product);
    }

    @Override
    public Product getProduct(int productId) {
        return productDao.get(productId);
    }

    @Override
    public List<Product> findProducts(String pattern) {
        return getProducts().stream()
                .filter(product -> product.getName().contains(pattern) || product.getSerialNumber().contains(pattern))
                .collect(Collectors.toList());
    }

    private void assertProduct(Product p) {
        Asserts.notNull(p, "Product is required and must not be null.");
        Asserts.notNullOrEmpty(p.getName(), "Product name is required and must not be null or empty. name='" +
                p.getName() + "'");
        Asserts.notNullOrEmpty(p.getSerialNumber(), "Product serial number is required and must not be null or empty. name='" +
                p.getSerialNumber()
                + "'");
    }

}
