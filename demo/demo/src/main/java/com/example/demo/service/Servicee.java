package com.example.demo.service;

import com.example.demo.model.DataGenerator;
import com.example.demo.model.FullfilmentCenter;
import com.example.demo.model.FullfilmentCenterContainer;
import com.example.demo.model.Item;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Service
public class Servicee {

public FullfilmentCenterContainer container;


public Servicee(){
    container=new DataGenerator().fullfilmentCenterContainer;
};


//=======================================ZAPISZ PRODUKTOW DO CSV
 public boolean zapiszproduktyCSV(){

    ArrayList linie = new ArrayList();
    for(FullfilmentCenter fullfilmentCenter:container.getCenters())
    for (Item item : fullfilmentCenter.getLista()) {
        linie.add(item.toString(","));
    }
    try {
        Files.write(Paths.get("productsCSV"), linie);
    }
    catch (IOException ex) { return false ;}

    return true;
}

    //=============================srednia ocena produktu
    public int sredniaocena( Item item){
        int suma=0;
        for(FullfilmentCenter fullfilmentCenter:container.getCenters())
            for(Item item1:fullfilmentCenter.getLista())
                if(item.getName().equals(item1.getName())) {
                    for(int i=0;i<item1.getOceny().size();i++)
                        suma+=item1.getOceny().get(i);

                    return suma/item1.getOceny().size();
                }

        return -1;

    }

    //=============================ADD rate to a product

    public boolean dodajocene( int ocena,  Item item){
        for(FullfilmentCenter fullfilmentCenter:container.getCenters())
            for(Item item1:fullfilmentCenter.getLista())
                if(item.getName().equals(item1.getName())) {
                    item1.getOceny().add(ocena);
                    return true;
                }

        return false;

    }


    //=============================RETURN zapelnienie of MAGAZINES

    public double zwracaprocentowezapelnienie( int idCenter){
        for(FullfilmentCenter fullfilmentCenter:container.getCenters())
            if(fullfilmentCenter.getId()==idCenter) {
                return fullfilmentCenter.getCurrent_fill();
            }
        return -1;
    }

    //=============================RETURN ALL MAGAZINES

    public List<FullfilmentCenter> zwracawszystkiemagazyny(){
        return container.getCenters();
    }


    //=============================ADD MAGAZINE

    public boolean dodajmagazyn( FullfilmentCenter center){
        container.addCenter(center);
        return true;
    }


    //=============================ADD PRODUCT TO MAGAZINE WITH CHOSEN ID

    public boolean dodajprodukt(int idCenter, Item item){
        for(FullfilmentCenter fullfilmentCenter:container.getCenters())
            if(fullfilmentCenter.getId()==idCenter) {
                for(Item itm:fullfilmentCenter.getLista())
                    itm.summary();
                return fullfilmentCenter.addProduct(item);
            }

        return false;

    }

    //============================DELETE PRODUCT FROM MAGAZINE WITH CHOSEN ID

    public boolean usunprodukt( int idCenter, Item item){
        for(FullfilmentCenter fullfilmentCenter:container.getCenters())
            if(fullfilmentCenter.getId()==idCenter) {
                fullfilmentCenter.removeProduct(item);
                return true;
            }
        return false;

    }

    //============================DELETE CENTER WITH CHOSEN ID FROM CONTAINER

    public boolean usunmagazyn(String centerName){
        System.out.println(centerName);
        return container.removeCenter(centerName);
    }

    //=============================RETURN ALL PRODUCT FROM MAGAZINE WITH CHOSEN ID

    public List<Item> zwracawszystkieprodukty( int idCenter){
        for(FullfilmentCenter fullfilmentCenter:container.getCenters())
            if(fullfilmentCenter.getId()==idCenter) {
                return fullfilmentCenter.getLista();
            }
        return null;
    }
}
