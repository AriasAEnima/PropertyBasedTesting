/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.orderCalculator.model;

import java.util.ArrayList;
import java.util.List;
import org.quicktheories.core.Gen;
import org.quicktheories.generators.ArraysDSL;
import org.quicktheories.generators.Generate;
import static org.quicktheories.generators.SourceDSL.*;

/**
 *
 * @author 2109117
 */
public class Generadores {    
    
    public static Gen<Dish> genDishes(){
        return genDishesNames().zip(genDishesTypes(),genDishesPrices(),(n,t,p)-> 
                new Dish(t, p, n));                
    }
    public static Gen<DishType> genDishesTypes(){
        return Generate.enumValues(DishType.class);
    }
    public static Gen<String> genDishesNames(){
        return strings().betweenCodePoints(97,122).ofLengthBetween(5, 10);
    }
    
    public static Gen<Integer> genDishesPrices(){
        return integers().from(1).upToAndIncluding(3000000);
    }
    
    public static Gen<List<Dish>> genListOfOrders(){
        return  lists().of(genDishes()).ofSizeBetween(4, 10);
    }
    
    public static Gen<Order> genOrder(){
        return genListOfOrders().map(od->new Order(od));    
    }
    
}

