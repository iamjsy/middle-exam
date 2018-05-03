package kr.ac.jejunu;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest {

    private ProductDao productDao;
    //private ProductDao hallaProductDao;

    @Before
    public void setup() {
        productDao = new ProductDao();
        //hallaProductDao = new ProductDao();
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = productDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        product.setTitle("배과");
        product.setPrice(12000);
        Long id = productDao.insert(product);

        Product insertedUser = productDao.get(id);
        assertThat(insertedUser.getId(), is(id));
        assertThat(insertedUser.getTitle(), is(product.getTitle()));
        assertThat(insertedUser.getPrice(), is(product.getPrice()));
    }
}