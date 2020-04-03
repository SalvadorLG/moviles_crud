package com.example.moviles_crud.views.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviles_crud.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_editproduct.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_editproduct#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_editproduct extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText name_product_edit, price_product_edit, code_product_edit, cant_product_edit;
    private Button btn_edit_product;
    private OnFragmentInteractionListener mListener;
    private FragmentEditProductInterface listener;

    public fragment_editproduct() {
        // Required empty public constructor
    }

    public interface FragmentEditProductInterface{
        void EditProduct(String nombre_producto, String precio_producto,String code_prodcuto, String cant_prodcuto, String token, String puntero);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_editproduct.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_editproduct newInstance(String param1, String param2) {
        fragment_editproduct fragment = new fragment_editproduct();
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
        View v = inflater.inflate(R.layout.fragment_editproduct, container, false);
        name_product_edit = (EditText) v.findViewById(R.id.edt_name_product_edit);
        price_product_edit = (EditText) v.findViewById(R.id.edt_price_product_edit);
        code_product_edit = (EditText) v.findViewById(R.id.edt_code_product_edit);
        cant_product_edit = (EditText) v.findViewById(R.id.edt_cant_producto_edit);
        btn_edit_product = (Button) v.findViewById(R.id.btn_edit_product);
        Bundle bundleedit = getArguments();
        System.out.println(bundleedit);
        final String token = bundleedit.getString("token");
        final String puntero = bundleedit.getString("puntero");
        System.out.println("edit- " + token + " - " + puntero);
        btn_edit_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name_product_edit.getText().toString().equals("") || price_product_edit.getText().toString().equals("") ||
                        code_product_edit.getText().toString().equals("") || cant_product_edit.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Campos requeridos",Toast.LENGTH_SHORT).show();
                }else {

                    listener.EditProduct(
                            name_product_edit.getText().toString(),
                            price_product_edit.getText().toString(),
                            code_product_edit.getText().toString(),
                            cant_product_edit.getText().toString(),
                            token, puntero
                    );
                }
            }
        });
        return v;
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
            listener = (FragmentEditProductInterface) activity;
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
