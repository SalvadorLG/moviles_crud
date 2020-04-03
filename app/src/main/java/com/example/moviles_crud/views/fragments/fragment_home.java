package com.example.moviles_crud.views.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviles_crud.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button add_product;
    private TableLayout table_product;
    private OnFragmentInteractionListener mListener;
    private FragmentHomeInterface listener_home;
    private String Token;
    public fragment_home() {
        // Required empty public constructor
    }

    public interface FragmentHomeInterface{
        void callProducts(String token);
        void addProducts(String token);
        void editProducts(String token,String puntero);
        void deletethis(String token,String puntero);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_home.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_home newInstance(String param1, String param2) {
        fragment_home fragment = new fragment_home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        add_product = (Button) v.findViewById(R.id.btn_add_product);
        table_product = (TableLayout) v.findViewById(R.id.id_table_product);
        Bundle bundle = getArguments();
        Token = bundle.getString("token");
        listener_home.callProducts(Token);

        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("fragment_home: "+Token);
                listener_home.addProducts(Token);

            }
        });
        //Toast.makeText(getContext(), token, Toast.LENGTH_SHORT).show();


        return v;
    }

    public void recargar(){}

    public void msgSuccessful(String msg){
        System.out.println("msgSuccessful"+msg);
        //Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

    }

    public void showListProducts(JSONArray array){
        //String s = array.toString();

        //Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
        for(int i = 0; i < array.length(); ++i) {
            try {
                JSONObject jobj = array.getJSONObject(i);
                String []cadena = {jobj.getString("producto"), jobj.getString("precio")};
                TableRow row = new TableRow(getContext());
                TextView textView;
                for (int j=0; j < 2; j++){
                    System.out.println(cadena[j]);
                    textView = new TextView(getContext());
                    textView.setGravity(Gravity.CENTER_VERTICAL);
                    textView.setPadding(35,15,15,15);
                    textView.setText(cadena[j]);
                    row.addView(textView);
                }
                Button edit = new Button(getContext());
                edit.setText("Edit");
                int id = Integer.parseInt(jobj.getString("id"));
                edit.setId(id);
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("v.getid is:- " + v.getId());
                        String puntero = String.valueOf(v.getId());
                        listener_home.editProducts(Token, puntero);
                    }
                });
                row.addView(edit);
                Button delete = new Button(getContext());
                delete.setText("Delete");
                int id_ = Integer.parseInt(jobj.getString("id"));
                delete.setId(id_);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("v.getid is:- " + v.getId());
                        String puntero = String.valueOf(v.getId());
                        listener_home.deletethis(Token, puntero);
                    }
                });
                row.addView(delete);
                table_product.addView(row);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try {
            listener_home = (fragment_home.FragmentHomeInterface) activity;
        }catch (RuntimeException a){
            throw new RuntimeException(activity.toString()
                    + " must implement FragmentLoginListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
