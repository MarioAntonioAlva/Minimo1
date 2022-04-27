package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.ProdCant;
import edu.upc.dsa.models.Product;
import org.junit.Test;
import org.junit.Before;
import java.util.List;
import org.apache.log4j.Logger;

public class ProductManagerTest {
    final static Logger log = Logger.getLogger(ProductManagerTest.class);
    ProductManager pm;
    @Before
    public void addProducts() {
        pm=ProductManagerImpl.getInstance();
        pm.addProduct("pera", 1.5);
        pm.addProduct("pepsi", 2);
        pm.addProduct("cheetos", 5);
        pm.addProduct("agua", 1);
        pm.addProduct("galletas", 4);
        Order o = new Order();
        o.addProdCant(pm.listaProductos().get(0), 2);
        o.addProdCant(pm.listaProductos().get(2), 5);
        pm.addOrder(o, "31567455H");
        Order o2 = new Order();
        o2.addProdCant(pm.listaProductos().get(1), 3);
        o2.addProdCant(pm.listaProductos().get(4), 4);
        pm.addOrder(o2, "57987321K");
    }

    @Test
        public void testproduct(){
        List<Product> l = pm.listaProductos();
        pm.deliverOrder();
        pm.deliverOrder();
        pm.ordenarlistaProductos();

        pm.getListProductsBySells();
        pm.listaOrderporUsuario("31567455H");
    }
}