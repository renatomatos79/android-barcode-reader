package br.com.rmatos.barcodereader.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.rmatos.barcodereader.adapters.ProductAdapter;
import br.com.rmatos.barcodereader.interfaces.OnItemSelectedListener;
import br.com.rmatos.barcodereader.model.ProductModel;

public class ProductListFragment extends ListFragment {

    private ArrayList<ProductModel> list;
    private ProductAdapter adp;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ArrayList<ProductModel> dataSource = dataSource = (ArrayList<ProductModel>)getArguments().getSerializable("dataSource");
        adp = new ProductAdapter(getActivity(), dataSource);
        setListAdapter(adp);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        Activity activity = getActivity();
        if (activity instanceof OnItemSelectedListener){
            String value = (String)l.getItemAtPosition(position);
            OnItemSelectedListener handler = (OnItemSelectedListener)activity;
            handler.select(value);
        }
    }
}
