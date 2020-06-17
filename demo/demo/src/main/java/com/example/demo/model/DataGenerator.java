package com.example.demo.model;

public class DataGenerator {


    public FullfilmentCenterContainer fullfilmentCenterContainer=new FullfilmentCenterContainer();

    public DataGenerator() {

    FullfilmentCenter fullfilmentCenter1=new FullfilmentCenter(1,"Mag1","MagPlace1",100);
    fullfilmentCenter1.addProduct(new Item("Prod1 ", SaleState.ZAMOWIONY,46,5));
    fullfilmentCenter1.addProduct(new Item("Prod2 ", SaleState.ZAMOWIONY,20,9));

    FullfilmentCenter fullfilmentCenter2=new FullfilmentCenter(2,"Mag2","MagPlace2",500);
    fullfilmentCenter2.addProduct(new Item("Prod3 ", SaleState.ZAMOWIONY,50,10));
    fullfilmentCenter2.addProduct(new Item("Prod4 ", SaleState.ZAMOWIONY,15,9));
    fullfilmentCenter2.addProduct(new Item("Prod5 ", SaleState.ZAMOWIONY,32,8));

    FullfilmentCenter fullfilmentCenter3=new FullfilmentCenter(3,"Mag3","MagPlace3",250);
    fullfilmentCenter3.addProduct(new Item("Prod6 ", SaleState.ZAMOWIONY,80,10));

    fullfilmentCenterContainer.addCenter(fullfilmentCenter1);
    fullfilmentCenterContainer.addCenter(fullfilmentCenter2);
    fullfilmentCenterContainer.addCenter(fullfilmentCenter3);

    }

}
