package cz.etn.etnshop.service;

import cz.etn.etnshop.dao.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ActiveProfiles("test")
@ContextConfiguration(locations = {"classpath:appContext/spring-core-config.xml", "classpath:spring-core-config_test.xml"})
public class ProductServiceImplTest {
    @Resource(name = "productService")
    private ProductService productService;
    @Resource
    private SessionFactory sessionFactory;

    private Product createProduct(String name, String serialNumber) {
        Product p = new Product();
        p.setName(name);
        p.setSerialNumber(serialNumber);
        return p;
    }

    private void flushAndClear() {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.flush();
        currentSession.clear();
    }

    private List<Product> createAndSaveProducts(int numberOfProducts) {
        List<Product> products = createProducts(numberOfProducts);
        for (Product product : products) {
            productService.saveProduct(product);
        }
        return products;
    }

    private List<Product> createProducts(int count) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            products.add(createProduct("testSave" + i, "0" + i));
        }
        return products;
    }


    @Test
    public void saveProduct() throws Exception {
        Product product = createProduct("testSave", "00000000001");
        productService.saveProduct(product);

        flushAndClear();

        assertTrue("Product id should greater than zero.", product.getId() > 0);
        Session session = sessionFactory.getCurrentSession();
        assertFalse(session.contains(product));

        Product loadedProduct = (Product) session.load(Product.class, product.getId());
        assertProductByProduct(product, loadedProduct);
    }

    @Test
    public void saveProductWithNameAndSerial() throws Exception {
        String name = "testSave";
        String serialNumber = "00000000001";
        productService.saveProduct(name, serialNumber);

        flushAndClear();

        Session session = sessionFactory.getCurrentSession();

        List<Product> products = (List<Product>) session.createQuery("select p from Product p").list();
        assertEquals(1, products.size());
        Product product = createProduct(name, serialNumber);
        product.setId(products.get(0).getId());
        assertProductByProduct(product, products.get(0));
    }

    private void assertProductByProduct(Product product, Product loadedProduct) {
        assertEquals(product.getId(), loadedProduct.getId());
        assertEquals(product.getName(), loadedProduct.getName());
        assertEquals(product.getSerialNumber(), loadedProduct.getSerialNumber());
    }

    @Test
    public void getProducts() throws Exception {
        final int numberOfProducts = 10;
        List<Product> products = createAndSaveProducts(numberOfProducts);

        flushAndClear();

        List<Product> loadedProducts = productService.getProducts();
        assertEquals(numberOfProducts, loadedProducts.size());
        for (int i = 0; i < numberOfProducts; i++) {
            assertProductByProduct(products.get(i), loadedProducts.get(i));
        }
    }


    @Test
    public void deleteProduct() throws Exception {
        final int numberOfProducts = 10;
        List<Product> products = createAndSaveProducts(numberOfProducts);

        flushAndClear();

        int idToDelete = products.get(0).getId();
        productService.deleteProduct(idToDelete);
        List<Product> loadedProducts = productService.getProducts();
        assertEquals(numberOfProducts - 1, loadedProducts.size());
        assertFalse("loaded products must not contain product with deleted id", loadedProducts.stream()
                .anyMatch(product -> product.getId() == idToDelete));

    }

    @Test
    public void updateProduct() throws Exception {
        List<Product> products = createAndSaveProducts(10);

        flushAndClear();

        Product product = products.get(0);
        product.setName("updatedName");
        product.setSerialNumber("99999999999");
        productService.updateProduct(product);

        flushAndClear();

        List<Product> loadedProducts = productService.getProducts();
        assertProductByProduct(product, loadedProducts.get(0));
    }

    @Test
    public void updateProductNameById() throws Exception {
        List<Product> products = createAndSaveProducts(10);

        flushAndClear();

        Product product = products.get(0);
        product.setName("updatedName");
        productService.updateProduct(product.getId(), product.getName());

        flushAndClear();

        List<Product> loadedProducts = productService.getProducts();

        assertProductByProduct(product, loadedProducts.get(0));
    }

    @Test
    public void findProducts() {
        List<Product> products = createAndSaveProducts(10);

        flushAndClear();

        List<Product> found = productService.findProducts("x");
        assertTrue(found.isEmpty());
        found = productService.findProducts("0");
        assertEquals(10, found.size());
        for (int i = 0; i < products.size(); i++) {
            assertProductByProduct(products.get(i), found.get(i));
        }
        found = productService.findProducts("05");
        assertEquals(1, found.size());
        assertProductByProduct(products.get(5), found.get(0));

    }

}