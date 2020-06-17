package com.example.demo.tests;


import com.example.demo.controller.Controller;

import com.example.demo.model.FullfilmentCenter;
import com.example.demo.model.Item;
import com.example.demo.model.SaleState;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(Controller.class)
@ComponentScan({"com.example.demo"})
public class ControllerTests {

    @Autowired
    private MockMvc mvc;

    //Przesylany item w testach OK
    Item item=new Item("Prod1 ", SaleState.ZAMOWIONY,46,5);
    //Przesylany item w testach ERROR
    Item itemWrong=new Item("wrongItem ", SaleState.ZAMOWIONY,20,4);

    //mapowanie potrzebne do mapowania obiektu na JSONA
    ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();


    //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    @Test
    void zapisCsvTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/product/csv");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("Products saved", result.getResponse().getContentAsString());
    }


    @Test
    public void zapisCsvTest2() throws Exception {
        mvc.perform(get("/api/product/csv"))
                .andExpect(content().string("Products saved"));
    }

    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

    @Test
    public void sredniaOcenaTest() throws Exception {

        String requestJson=ow.writeValueAsString(item);

        mvc.perform(get("/api/product/id/rating").contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void sredniaOcenaErrorTest() throws Exception {

        String requestJson=ow.writeValueAsString(itemWrong);

        mvc.perform(get("/api/product/id/rating").contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(content().string("Not found provided item "));
    }

    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

    @Test
    public void dodajOceneTest() throws Exception {

        String requestJson=ow.writeValueAsString(item);

        mvc.perform(post("/api/rating/9").param("ocena","9").contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(content().string("Rate added"));
    }

    @Test
    public void dodajOceneErrorTest() throws Exception {

        String requestJson=ow.writeValueAsString(itemWrong);

        mvc.perform(post("/api/rating/9").param("ocena","9").contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

    @Test
    public void zwracaProcentoweZapelnienieTest() throws Exception {

        mvc.perform(get("/api/fulfillment/id/fill/1").param("idCenter","1"))
                .andExpect(content().string("66.0"));
    }

    @Test
    public void zwracaProcentoweZapelnienieErrorTest() throws Exception {

        //wywolanie dla nieistniejacego magazynu o podanym ID
        mvc.perform(get("/api/fulfillment/id/fill/20").param("idCenter","20"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void zwracaWszystkieMagazynyTest() throws Exception {

        mvc.perform(get("/api//fulfillment"))
                .andExpect(status().isOk());
    }

    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

    @Test
    public void dodajMagazynTest() throws Exception {

        String requestJson=ow.writeValueAsString(new FullfilmentCenter(10,"Magazyn10","Czarzyzna",500));

        mvc.perform(post("/api//fulfillment").contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

    @Test
    public void dodajProduktTest() throws Exception {

        String requestJson=ow.writeValueAsString(new Item("ItemAddedTest", SaleState.ZAMOWIONY,1,5));

        mvc.perform(post("/api/product/1").param("idCenter","1").contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(content().string("Item added"));
    }

    @Test
    public void dodajProduktErrorTest() throws Exception {

        String requestJson=ow.writeValueAsString(new Item("ItemAddedTest", SaleState.ZAMOWIONY,50,5));
        //dodanie produktu do nieistniejacego magazynu o podanym ID
        mvc.perform(post("/api/product/20").param("idCenter","20").contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isNotFound());
    }

    //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

    @Test
    public void usunProduktTest() throws Exception {

        String requestJson=ow.writeValueAsString(new Item("Prod3 ", SaleState.ZAMOWIONY,50,10));

        mvc.perform(delete("/api/product/id/2").param("idCenter","2").contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(content().string("Item deleted"));
    }

    @Test
    public void usunProduktErrorTest() throws Exception {

        String requestJson=ow.writeValueAsString(item);

        mvc.perform(delete("/api/product/id/20").param("idCenter","20").contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isNotFound());
    }

    //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

    @Test
    public void usunMagazynTest() throws Exception {

        mvc.perform(delete("/api/fulfillment/id/Mag3").param("centerName","Mag3"))
                .andExpect(status().isOk());
    }

    @Test
    public void usunMagazynErrorTest() throws Exception {

        mvc.perform(delete("/api/fulfillment/id/wrongName").param("wrongName","Adrian"))
                .andExpect(content().string("Not found magazine of such a name"));
    }

    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

    @Test
    public void zwracaWszystkieProduktyTest() throws Exception {

        mvc.perform(get("/api/fulfillment/id/products/1").param("idCenter","1"))
                .andExpect(status().isOk());
    }

    @Test
    public void zwracaWszystkieProduktyErrorTest() throws Exception {

        mvc.perform(get("/api/fulfillment/id/products/20").param("idCenter","20"))
                .andExpect(content().string("Not found magazine of such an ID"));
    }

    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''


    }





