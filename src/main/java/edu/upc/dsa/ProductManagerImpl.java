package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.ProdCant;
import edu.upc.dsa.models.Product;

import java.util.*;

import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

public class ProductManagerImpl implements ProductManager {

    private static ProductManager instance;
    protected List<Product> products;

    private Queue<Order> orderQueue;
    HashMap<String, User> userByID;
    protected List<ProdCant> prodcants;
    private List<Order> orders;
    protected List<Order> ordersusuarios ;
    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    private ProductManagerImpl() {
        this.products = new LinkedList<>();
        this.prodcants = new LinkedList<>();
        this.orderQueue = new ArrayDeque<>();
        this.userByID = new HashMap<>();
    }

    public static ProductManager getInstance() {
        if (instance==null) instance = new ProductManagerImpl();
        return instance;
    }
    public int size() {
        int ret = this.products.size();
        logger.info("size " + ret);

        return ret;
    }
    public void addProduct(Product t) {
        logger.info("Nuevo producto: " + t.getName() + " con un coste de " + t.getPrice() + "€");

        this.products.add (t);
        logger.info("El producto ha sido añadido");
    }
    public void addProduct(String name, double price) {
        this.addProduct(new Product(name, price));
    }
    public List<Product> listaProductos() {
        return this.products;
    }

    public List<Product> ordenarlistaProductos() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
        });
        logger.info("Ordenado de menor a mayor precio:");
        for(Product p:this.products)
            logger.info(p.getName());
        logger.info("===================================");
        return this.products;
    }

    public Order addOrder(Order o,String u){
        o.setUser(u);
        orderQueue.add(o);
        logger.info("Se ha añadido un pedido");
        orders.add(o);
        return o;
    }

    @Override
    public void addProdCant(Product o, double u) {

    }

    @Override
    public List<Product> getListProductsBySells(){
        products.sort(Comparator.comparingDouble(Product::getNumSells).reversed());
        logger.info("Lista de productos ordenado por ventas");
        for(Product p:this.products)
            logger.info(p.getName() + " " + p.getNumSells());
        logger.info("===================================");
        return products;
    }

    @Override
    public List<Order> listaOrderporUsuario(String u) {
        for(Order o : this.orders){
            if(o.getID()==u){
                ordersusuarios.add(o);
                logger.info(o.getID() + " " + o.getListaP());
            }
        }
        return this.ordersusuarios;
    }

    public void addProdCant(ProdCant pc) {
        logger.info("new ProdCant " + pc);

        this.prodcants.add (pc);
        logger.info("new ProdCant added");
    }

    public void addProdCant(Product producto, int cantidad){
        this.addProdCant(new ProdCant(producto, cantidad));
    }
    public List<ProdCant> ListaProdCant() {
        return this.prodcants;
    }



    public void deliverOrder() {
        Order o = orderQueue.poll();
        User u = userByID.get(o.getID());
        for (ProdCant lp: o.getListaP())
            products.get(products.indexOf(lp.getProd())).addNumSells(lp.getCantidad());
        logger.info("Se ha servido el pedido con ID: " + o.getID());
    }

}
