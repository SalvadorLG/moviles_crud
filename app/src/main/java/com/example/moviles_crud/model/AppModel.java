package com.example.moviles_crud.model;
import android.content.Context;
import android.content.Intent;

import com.example.moviles_crud.AppInterface;
import com.example.moviles_crud.CredentialsInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import cz.msebera.android.httpclient.Header;

public class AppModel implements AppInterface.Model {
    private AppInterface.Presenter presenter;
    String URL = "http://192.168.137.156:8000/users/productos/";
    public AppModel(AppInterface.Presenter presenter){
        this.presenter = presenter;
    }
    @Override
    public void getProducts(String token) {
        //String URL = "http://192.168.137.156:8000/users/productos/";
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token " + token);
        client.get(URL, new AsyncHttpResponseHandler() {
             @Override
             public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                 String results;
                if(statusCode == 200){
                    try {
                        JSONObject arr =new JSONObject(new String(responseBody));
                        results = arr.getString("results");
                        System.out.println(results);
                        JSONArray jarr = new JSONArray(results);
                        presenter.showProducts(jarr);
                        //for(int i = 0; i < jarr.length(); ++i) {
                        //    JSONObject jobj = jarr.getJSONObject(i);
                        //    System.out.println(jobj);
                        //}

                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
             }

             @Override
             public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

             }
        });
    }

    @Override
    public void addProducts(String nombreproducto, String precioproducto, String codeprodcuto, String cantprodcuto, final String token) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token " + token);
        System.out.println("addproduct: "+token);
        RequestParams params = new RequestParams();
        params.put("producto",nombreproducto);
        params.put("precio",precioproducto);
        params.put("cantidadTotal",cantprodcuto);
        params.put("codigo",codeprodcuto);
        client.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 201){
                    System.out.println("addproduct 201: "+token);
                    String t = "EL producto ha sido agregado exitosamente";
                    presenter.showMsgExitoso(t, token);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
