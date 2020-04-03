package com.example.moviles_crud;

public interface CredentialsInterface {
    interface View{
        void login(String token);
        void error(String error, int status);
    }
    interface Presenter{
        void error(String error, int status);
        void verificar(String user, String pass);
        void login(String token);
    }
    interface Model{
        void verificar(String user, String pass);
    }
}
