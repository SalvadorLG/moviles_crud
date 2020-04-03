package com.example.moviles_crud.model;
import com.example.moviles_crud.CredentialsInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;

public class LoginModel implements CredentialsInterface.Model{
    private CredentialsInterface.Presenter presenter;

    public LoginModel(CredentialsInterface.Presenter presenter){
        this.presenter = presenter;
    }
    @Override
    public void verificar(String user, String pass) {
        String URL = "http://192.168.137.156:8000/users/login/";
        if(user.equals("") && pass.equals("")){
            //presenter.error("Campo Vacio");
        }else{
            RequestParams params = new RequestParams();
            params.put("username",user);
            params.put("password",pass);
            new AsyncHttpClient().post(URL, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String str = new String(responseBody);
                    String token;
                    System.out.println(str);

                    try {
                        JSONObject arr =new JSONObject(new String(responseBody));
                        token = arr.getString("token");
                        System.out.println("Token "+token);
                        System.out.println(token);
                        if(statusCode == 200){
                            //Siguiente_Vista(token);
                            presenter.login(token);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    System.out.println("error "+error + " Status: " + statusCode);

                    String str = new String(responseBody);
                    System.out.println("response"+ str);

                    if(statusCode == 400){
                        String TEXT = "El usuario o la contraseña son incorrectos";
                        presenter.error(TEXT, statusCode);
                    }
                    if(statusCode == 401){
                        String TEXT = "El usuario o la contraseña son incorrectos";
                        presenter.error(TEXT, statusCode);
                    }
                    if(statusCode == 404){
                        String TEXT = "Error Not found";
                        presenter.error(TEXT, statusCode);
                    }
                    if(statusCode == 500){
                        String TEXT = "500 Internal Server Error";
                        presenter.error(TEXT, statusCode);
                    }
                }
            });
        }
    }
}
