package com.example.justnew;

import java.util.ArrayList;
import java.util.List;

public class BeerExpert {
    List<String> getBrands(String color){
        List<String> brands = new ArrayList<>();
        if(color.equals("amber")){
            brands.add("Vick's amber");
            brands.add("white cap");
        }
        else if(color.equals("Light")){
            brands.add("chrome");
            brands.add("cider");
        }
        else if(color.equals("brown")){
            brands.add("guiness");
            brands.add("pilsner");
        }
        else  if(color.equals("oily")){
            brands.add("Origin");
            brands.add("konyagi");
        }
        else brands.add("i don't know everything man!");

        return brands;
    }
}
