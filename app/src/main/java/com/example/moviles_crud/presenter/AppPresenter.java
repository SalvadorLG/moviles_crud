package com.example.moviles_crud.presenter;

import com.example.moviles_crud.AppInterface;
import com.example.moviles_crud.model.AppModel;

import org.json.JSONArray;

public class AppPresenter implements AppInterface.Presenter{
    private AppInterface.View view;
    private AppInterface.Model model;
    public AppPresenter(AppInterface.View view){
        this.view = view;
        model = new AppModel(this);
    }
    @Override
    public void getProducts(String token) {
        if(view!=null){
            model.getProducts(token);
        }
    }

    @Override
    public void showProducts(JSONArray array) {
        if(view!=null){
            view.showProducts(array);
        }
    }

    @Override
    public void addProducts(String nombreproducto, String precioproducto, String codeprodcuto, String cantprodcuto, String token) {
        if(view!=null){
            model.addProducts(nombreproducto, precioproducto, codeprodcuto, cantprodcuto, token);
        }
    }

    @Override
    public void showMsgExitoso(String msg, String token) {
        if (view!=null){
            view.showMsgExitoso(msg, token);
        }
    }

    @Override
    public void EditarProducts(String nombre_producto, String precio_producto, String code_prodcuto, String cant_prodcuto, String token, String puntero) {
        if (view!=null){
            model.EditarProducts(nombre_producto, precio_producto, code_prodcuto, cant_prodcuto, token, puntero);
        }
    }

    @Override
    public void deleteProduct(String token, String puntero) {
        if (view!= null){
            model.deleteProduct(token, puntero);
        }
    }

    @Override
    public void refresecar(String msg, String token) {
        if (view!= null){
            view.refresecar(msg, token);
        }
    }
}
