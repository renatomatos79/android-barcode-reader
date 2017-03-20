package br.com.rmatos.barcodereader.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.rmatos.barcodereader.R;
import br.com.rmatos.barcodereader.model.ProductModel;

public class ProductAdapter extends CustomAdapter<ProductModel, ProductAdapter.ProductViewHolder> {

    public ProductAdapter(Context ctx, List<ProductModel> list) {
        super(ctx, list);
    }

    @Override
    public int getLayout() {
        return R.layout.product_view_model;
    }

    @Override
    public ProductViewHolder createHolder(ProductModel model, int position, View convertView, ViewGroup parent) {
        ProductViewHolder holder = new ProductViewHolder();
        holder.imgCode = (ImageView)convertView.findViewById(R.id.imgCode);
        holder.lblCode = (TextView)convertView.findViewById(R.id.lblCode);
        holder.lblName = (TextView)convertView.findViewById(R.id.lblName);
        holder.lblPrice = (TextView)convertView.findViewById(R.id.lblPrice);
        return holder;
    }

    @Override
    public void updateHolder(ProductViewHolder holder, ProductModel model, int position, View convertView, ViewGroup parent){
        holder.lblCode.setText(model.BarCode);
        holder.lblName.setText(model.Name);
        holder.lblPrice.setText("R$ " + String.valueOf(model.Price));
    }

    public static class ProductViewHolder
    {
        ImageView imgCode;
        TextView lblCode;
        TextView lblName;
        TextView lblPrice;
    }
}
