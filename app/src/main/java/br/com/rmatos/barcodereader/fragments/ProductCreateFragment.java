package br.com.rmatos.barcodereader.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.UUID;

import br.com.rmatos.barcodereader.R;
import br.com.rmatos.barcodereader.interfaces.OnItemSelectedListener;
import br.com.rmatos.barcodereader.interfaces.OnProductListener;
import br.com.rmatos.barcodereader.model.ProductModel;

public class ProductCreateFragment extends Fragment {

    private EditText txtName;
    private EditText txtPrice;
    private Button btnCapture;
    private Button btnAdd;
    private ProductModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.product_create_model, container, false);

        this.model = new ProductModel(UUID.randomUUID().toString(), "", "", 0);

        this.txtName = (EditText)layout.findViewById(R.id.txtName);
        this.txtPrice = (EditText)layout.findViewById(R.id.txtPrice);

        this.btnCapture = (Button)layout.findViewById(R.id.btnCapture);
        this.btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof OnProductListener){
                    OnProductListener listener = (OnProductListener)getActivity();
                    listener.afterBarcodeCapture(model);
                }
            }
        });


        this.btnAdd = (Button)layout.findViewById(R.id.btnAdd);
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof OnProductListener){
                    OnProductListener listener = (OnProductListener)getActivity();
                    model.Price = Double.valueOf(txtPrice.getText().toString());
                    model.Name = txtName.getText().toString();
                    listener.beforeCreate(model);
                }
            }
        });

        return layout;
    }

}
