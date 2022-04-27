package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.ProdCant;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;

import java.util.List;

public interface ProductManager {


    public void addProduct(String name, double price);

    public List<Product> listaProductos();

    public List<Product> ordenarlistaProductos();
    public List<ProdCant> ListaProdCant();
    public void deliverOrder();
    public Order addOrder(Order o,String u);
    public void addProdCant(Product o,double u);

    public List<Product> getListProductsBySells();

    public List<Order> listaOrderporUsuario(String ID);

    public int size();
}