package com.example.moviles_crud.presenter;

import com.example.moviles_crud.CredentialsInterface;
import com.example.moviles_crud.model.LoginModel;

public class LoginPresenter implements CredentialsInterface.Presenter{
    private CredentialsInterface.View view;
    private CredentialsInterface.Model model;
    public LoginPresenter(CredentialsInterface.View view){
        this.view = view;
        model = new LoginModel(this);
    }

    @Override
    public void error(String error, int status) {
        if(view!=null){
            view.error(error,status);
        }
    }

    @Override
    public void verificar(String user, String pass) {
        if(view!=null){
            model.verificar(user, pass);
        }
    }

    @Override
    public void login(String token) {
        if(view!=null){
            view.login(token);
        }
    }
}
