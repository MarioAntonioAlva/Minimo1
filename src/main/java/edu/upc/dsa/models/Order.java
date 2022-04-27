package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<ProdCant> ListaP = new ArrayList<ProdCant>();
    private String ID;

    public Order(){}

    public Order(List<ProdCant> ListaP, String ID){
        this.ListaP = ListaP;
        this.ID = ID;
    }
    public void addProdCant(Product product,int quant){
        ListaP.add(new ProdCant(product,quant));
    }

    public List<ProdCant> getListaP() {
        return ListaP;
    }

    public void setListaP(List<ProdCant> listaP) {
        ListaP = listaP;
    }

    public String getID() {
        return ID;
    }

    public void setUser(String ID) {
        this.ID = ID;
    }
}
