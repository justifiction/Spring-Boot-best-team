package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Item {
    String name;
    int mass;
    SaleState state;
    List<Integer> oceny;

    public List<Integer> getOceny() {
        return oceny;
    }

    public void setOceny(List<Integer> oceny) {
        this.oceny = oceny;
    }

    public Item(String name, SaleState state, int mass ){
        this.name=name;
        this.state=state;
        this.mass=mass;
        oceny = new ArrayList<>();
    }

    public Item(String name, SaleState state, int mass,int ocena ){
        oceny = new ArrayList<>();
        this.name=name;
        this.state=state;
        this.mass=mass;
        oceny.add(ocena);
    }

    public Item(){
        oceny=new ArrayList<>();
    };

    public String getName() {
        return name;
    }

    public SaleState getState() {
        return state;
    }

    public double getMass() {
        return mass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(SaleState state) {
        this.state = state;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    @Override
    public String toString() {
        return "Name: " + name + ". State: " + state + ". Mass:" + mass;
    }
    public void summary(){
        System.out.println("Name: "+name);
        System.out.println("State: "+state);
        System.out.println("Mass: "+mass);
    }
    public String toString(String separator) {
        String oceny="";
        oceny =oceny+""+1;
        for(int i=0;i<getOceny().size();i++)
            oceny=oceny+","+getOceny().get(i);
        return name + separator + state + separator +
                mass + separator + oceny;
    }



}
