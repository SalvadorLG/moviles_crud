package com.example.moviles_crud.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.moviles_crud.AppInterface;
import com.example.moviles_crud.CredentialsInterface;
import com.example.moviles_crud.R;
import com.example.moviles_crud.presenter.AppPresenter;
import com.example.moviles_crud.presenter.LoginPresenter;
import com.example.moviles_crud.views.fragments.fragment_login;
import com.example.moviles_crud.views.fragments.fragment_home;
import com.example.moviles_crud.views.fragments.fragment_addproduct;
import org.json.JSONArray;

public class AppView extends AppCompatActivity
        implements fragment_login.FragmentLoginInterface,
        CredentialsInterface.View,
        fragment_home.FragmentHomeInterface,
        AppInterface.View, fragment_addproduct.FragmentAddProductInterface{

    private CredentialsInterface.Presenter presenter_login;
    private AppInterface.Presenter presenter_home;
    private fragment_login fragment_login;
    private fragment_home fragment_home;
    private fragment_addproduct fragment_addproduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment_login = new fragment_login();
        fragment_home = new fragment_home();
        fragment_addproduct = new fragment_addproduct();
        presenter_login = new LoginPresenter(this);
        presenter_home = new AppPresenter(this);
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments,fragment_login).commit();
    }

    @Override
    public void Credentials(String username, String password) {
        presenter_login.verificar(username, password);
    }

    @Override
    public void login(String token) {
        Bundle bundle = new Bundle();
        bundle.putString("token",token);
        fragment_home.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedorFragments,fragment_home);
        transaction.commit();
    }

    @Override
    public void error(String error, int status) {
        fragment_login.msgError(error, status);
    }

    @Override
    public void callProducts(String token) {
        presenter_home.getProducts(token);
    }

    @Override
    public void addProducts(String token) {
        Bundle bundle = new Bundle();
        bundle.putString("token",token);
        fragment_addproduct.setArguments(bundle);
        System.out.println("addProducts ==:> "+ token);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedorFragments,fragment_addproduct);
        transaction.commit();
    }

    @Override
    public void showProducts(JSONArray array) {
        fragment_home.showListProducts(array);
    }

    @Override
    public void showMsgExitoso(String msg, String token) {
        System.out.println("showMsgExitoso:::"+token);
        System.out.println("mensaje"+msg);
        fragment_home.msgSuccessful(msg);
        Bundle bundle = new Bundle();
        bundle.putString("token",token);
        fragment_home.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedorFragments,fragment_home);
        transaction.commit();
    }

    @Override
    public void addProduct(String nombreproducto, String precioproducto, String codeprodcuto, String cantprodcuto, String token) {

        presenter_home.addProducts(nombreproducto, precioproducto, codeprodcuto, cantprodcuto, token);
    }
}
