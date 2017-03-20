package br.com.rmatos.barcodereader.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by renato on 20/03/17.
 */

public class ProductModel implements Serializable {
    public String Id;
    public String Name;
    public String BarCode;
    public double Price;
    public ProductModel(String id, String name, String barcode, double price){
        this.Id = id;
        this.Name = name;
        this.BarCode = barcode;
        this.Price = price;
    }

}
