package com.example.demo.controller;

import com.example.demo.controller.exeption.CustomExeption;
import com.example.demo.model.FullfilmentCenter;
import com.example.demo.model.Item;
import com.example.demo.service.Servicee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class Controller {


    @Autowired()
    private Servicee servicee;



    //===================================================zapis do CSV
    @GetMapping("/product/csv")
    @ResponseBody
    public ResponseEntity<Object> zapiscsv() throws CustomExeption {
        if(servicee.zapiszproduktyCSV()==true)
            return new ResponseEntity<>("Products saved", HttpStatus.OK);
        else
            throw new CustomExeption("Could not find the path ", HttpStatus.BAD_REQUEST);
    }


    //====================================================srednia ocena produktu
    @GetMapping("/product/id/rating")
    @ResponseBody
    public ResponseEntity<Object> sredniaocena(@RequestBody Item item) throws CustomExeption {
         if (servicee.sredniaocena(item) != -1)
                return new ResponseEntity<>(servicee.sredniaocena(item), HttpStatus.OK);
            else
                throw new CustomExeption("Not found provided item ", HttpStatus.BAD_REQUEST);
    }



    //====================================================ADD rate to a product
    @PostMapping("/rating/{ocena}")
    @ResponseBody
    public ResponseEntity<Object> dodajocene(@PathVariable int ocena, @RequestBody Item item) throws CustomExeption {
            if (ocena < 0)
                throw new CustomExeption("Rate value must be over 0", HttpStatus.BAD_REQUEST);                                                    //usunac potem
            else if (servicee.dodajocene(ocena, item) == true)
                return new ResponseEntity<>("Rate added", HttpStatus.OK);
            else
                throw new CustomExeption("Not found provided item ", HttpStatus.BAD_REQUEST);


    }


    //=====================================================RETURN zapelnienie of MAGAZINES
    @GetMapping("/fulfillment/id/fill/{idCenter}")
    public ResponseEntity<Object> zwracaprocentowezapelnienie(@PathVariable int idCenter) throws CustomExeption {
        if(idCenter<0)
            throw new CustomExeption("Center ID value must be over 0",HttpStatus.BAD_REQUEST);
        else if(servicee.zwracaprocentowezapelnienie(idCenter)!=-1)
            return new ResponseEntity<>(servicee.zwracaprocentowezapelnienie(idCenter), HttpStatus.OK);
        else
            throw new CustomExeption("Not found magazine of such an ID",HttpStatus.NOT_FOUND);
    }



    //=====================================================RETURN ALL MAGAZINES
    @GetMapping("/fulfillment")
    public ResponseEntity<Object> zwracawszystkiemagazyny() {
        return new ResponseEntity<>(servicee.zwracawszystkiemagazyny(), HttpStatus.OK);
    }



    //=====================================================ADD MAGAZINE
    @PostMapping("/fulfillment")
    @ResponseBody
    public ResponseEntity<Object> dodajmagazyn(@RequestBody FullfilmentCenter center){
            servicee.dodajmagazyn(center);
            return new ResponseEntity<>("Center added", HttpStatus.OK);
    }



    //=====================================================ADD PRODUCT TO MAGAZINE WITH CHOSEN ID
    @PostMapping("/product/{idCenter}")
    @ResponseBody
    public ResponseEntity<Object> dodajprodukt(@PathVariable int idCenter, @RequestBody(required = true) Item item) throws CustomExeption {
            if (idCenter < 0)
                throw new CustomExeption("Center ID value must be over 0", HttpStatus.BAD_REQUEST);
            else if (servicee.dodajprodukt(idCenter, item) == true)
                return new ResponseEntity<>("Item added", HttpStatus.OK);
            else
                throw new CustomExeption("Not found magazine of such an ID", HttpStatus.NOT_FOUND);
    }



    //====================================================DELETE PRODUCT FROM MAGAZINE WITH CHOSEN ID
    @DeleteMapping("/product/id/{idCenter}")
    @ResponseBody
    public ResponseEntity<Object> usunprodukt(@PathVariable int idCenter, @RequestBody Item item) throws CustomExeption {
            if (idCenter < 0)
                throw new CustomExeption("Center ID value must be over 0", HttpStatus.BAD_REQUEST);
            else if (servicee.usunprodukt(idCenter, item) == true)
                return new ResponseEntity<>("Item deleted", HttpStatus.OK);
            else
                throw new CustomExeption("Not found item in magazine of such an ID", HttpStatus.NOT_FOUND);
    }


    //==========================================================DELETE CENTER WITH CHOSEN ID FROM CONTAINER
    @DeleteMapping("/fulfillment/id/{centerName}")
    @ResponseBody
    public ResponseEntity<Object> usunmagazyn(@PathVariable String centerName) throws CustomExeption {
        if(centerName=="")
            throw new CustomExeption("Wrong parameter provided",HttpStatus.BAD_REQUEST);
        else if(servicee.usunmagazyn(centerName)==true)
            return new ResponseEntity<>("Center deleted", HttpStatus.OK);
        else
            throw new CustomExeption("Not found magazine of such a name", HttpStatus.NOT_FOUND);
    }



    //=============================================RETURN ALL PRODUCT FROM MAGAZINE WITH CHOSEN ID
    @GetMapping(" ")
    public ResponseEntity<Object> zwracawszystkieprodukty(@PathVariable int idCenter) throws CustomExeption {
        if (idCenter < 0)
            throw new CustomExeption("Center ID value must be over 0", HttpStatus.BAD_REQUEST);
        else if(servicee.zwracawszystkieprodukty(idCenter)!=null)
            return new ResponseEntity<>(servicee.zwracawszystkieprodukty(idCenter), HttpStatus.OK);
        else
            throw new CustomExeption("Not found magazine of such an ID", HttpStatus.NOT_FOUND);
    }
}
