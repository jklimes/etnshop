package cz.etn.etnshop.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao implements ProductDao {

    @Override
    public void saveProduct(Product product) {
        persist(product);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getProducts() {
        Query query = getSession().createQuery("select p from Product p order by p.id");
        return (List<Product>) query.list();
    }

    @Override
    public void deleteProduct(int productId) {
        Query query = getSession().createQuery("delete from Product where id = :id");
        query.setInteger("id", productId);
        query.executeUpdate();
    }


    @Override
    public void updateProduct(Product product) {
        getSession().update(product);
    }

    @Override
    public Product get(int id) {
        return (Product) getSession().get(Product.class, id);
    }


}
