package com.example.demo.model;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class FullfilmentCenterContainer {

    public List<FullfilmentCenter> Centers;


    public FullfilmentCenterContainer(){
        Centers= new ArrayList();
    };


    public boolean addCenter(FullfilmentCenter f1){
    System.out.println(" cneter added");
        return Centers.add(f1);

    }

    public boolean removeCenter(String storage_name){
        for(FullfilmentCenter fullfilmentCenter:Centers){
            if(fullfilmentCenter.getStorage_name().equals(storage_name)) {
                Centers.remove(fullfilmentCenter);
                return true;
            }
        }
        return false;
    }



    public List<FullfilmentCenter> getCenters() {
        return Centers;
    }

}
