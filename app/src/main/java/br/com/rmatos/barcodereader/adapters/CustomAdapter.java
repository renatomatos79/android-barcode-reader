package br.com.rmatos.barcodereader.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.rmatos.barcodereader.interfaces.OnItemSelectedListener;


public abstract class CustomAdapter<T extends Object, H extends Object> extends BaseAdapter {

    private Context context;
    private List<T> list;

    public CustomAdapter(Context ctx, List<T> list)
    {
        this.context = ctx;
        this.list = list;
    }

    public Context getContext(){
        return this.context;
    }

    public List<T> getList(){
        return this.list;
    }

    public abstract int getLayout();

    public abstract H createHolder(T model, int position, View convertView, ViewGroup parent);

    public abstract void updateHolder(H holder, T model, int position, View convertView, ViewGroup parent);

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final T model = list.get(position);
        final H holder;
        if (convertView ==  null)
        {
            convertView = LayoutInflater.from(this.context).inflate(this.getLayout(), null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (context instanceof OnItemSelectedListener){
                        OnItemSelectedListener listener = (OnItemSelectedListener)context;
                        listener.select(model);
                    }
                }
            });
            holder = createHolder(model, position, convertView, parent);
            convertView.setTag(holder);
        } else {
            holder = (H)convertView.getTag();
        }
        updateHolder(holder, model, position, convertView, parent);
        return convertView;
    }
}
