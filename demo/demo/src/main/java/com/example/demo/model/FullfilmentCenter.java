package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class FullfilmentCenter {

    int id;
    String storage_name;
    String storage_place;
    List<Item> lista;
    double storage_capacity;
    public double temp_capacity;
    double current_fill;


   public FullfilmentCenter(int id, String storage_name, String storage_place, double storage_capacity) {
        this.id=id;
        this.storage_capacity=storage_capacity;
        this.storage_place=storage_place;
        this.storage_name=storage_name;
        lista=new ArrayList<>();
    }

public FullfilmentCenter(){
    lista=new ArrayList<>();              //gdy dodam magazyn bez zadnego produktu to po to by lista nie byla nulllem
};




    public boolean addProduct(Item product) {
        System.out.println("capacity1:" + storage_capacity + "temp1:" +temp_capacity);
        assert product != null;
        if (temp_capacity + product.mass > storage_capacity) {
            System.err.println("Nie ma miejsca w magazynie");
            return false;
        }
        else {
            lista.add(product);
            temp_capacity +=product.mass;
            current_fill=(temp_capacity/storage_capacity)*100;
        }
        System.out.println("capacity:" + storage_capacity + "temp:"+temp_capacity);
        return true;
    }

    public boolean removeProduct(Item product){
        if(product!=null){
            for(Item item:lista){
                if(item.name.equals(product.name)){
                    System.out.println("usuwaniecapacity1:" + storage_capacity + "temp1:" +temp_capacity);
                    lista.remove(item);
                    temp_capacity-=product.mass;
                    current_fill=(temp_capacity/storage_capacity)*100;
                    System.out.println("Product deleted");
                    System.out.println("pousunieciucapacity1:" + storage_capacity + "temp1:" +temp_capacity);
                    return true;
                }
            }

        }
            return false;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLista(List<Item> lista) {
        this.lista = lista;
    }

    public double getCurrent_fill() {
        return current_fill;
    }

    public void setCurrent_fill(double current_fill) {
        this.current_fill = current_fill;
    }

    public double getTemp_capacity() {
        return temp_capacity;
    }

    public void setTemp_capacity(double temp_capacity) {
        this.temp_capacity = temp_capacity;
    }

    public List<Item> getLista() { return lista; }

    public String getStorage_name() {
        return storage_name;
    }

    public String getStorage_place() {
        return storage_place;
    }

    public double getStorage_capacity() {
        return storage_capacity;
    }

    public void setStorage_name(String storage_name) {
        this.storage_name = storage_name;
    }

    public void setStorage_place(String storage_place) {
        this.storage_place = storage_place;
    }

    public void setStorage_capacity(double storage_capacity) {
        this.storage_capacity = storage_capacity;
    }

}
