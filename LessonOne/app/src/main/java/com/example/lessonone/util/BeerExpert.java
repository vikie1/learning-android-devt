package com.example.lessonone.util;

import java.util.ArrayList;
import java.util.List;

public class BeerExpert {
    public List<String> getBrands(String color){
        List<String> brands = new ArrayList<>();
        if (color.equals("light")){
            brands.add("White Cap");
            brands.add("White Wine");
        }
        else if (color.equals("amber")){
            brands.add("jacks amber");
            brands.add("red moose");
        }
        else if (color.equals("brown")){
            brands.add("origin");
            brands.add("keg");
        }
        else if (color.equals("dark")){
            brands.add("guinness");
            brands.add("Fuck you now");
        }
        else if (color.isEmpty()){
            throw new NullPointerException();
        }
        else{
            brands.add("you selected nothing");
        }
        return brands;
    }
}
