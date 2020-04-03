package com.example.moviles_crud;

import org.json.JSONArray;

public interface AppInterface {
    interface View{
        void showProducts(JSONArray array);
        void showMsgExitoso(String msg, String token);
    }

    interface Presenter {
        void getProducts(String token);
        void showProducts(JSONArray array);
        void addProducts(String nombreproducto, String precioproducto, String codeprodcuto, String cantprodcuto, String token);
        void showMsgExitoso(String msg, String token);
    }

    interface Model {
        void getProducts(String token);
        void addProducts(String nombreproducto, String precioproducto, String codeprodcuto, String cantprodcuto, String token);
    }
}
