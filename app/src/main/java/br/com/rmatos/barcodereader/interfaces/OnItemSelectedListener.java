package br.com.rmatos.barcodereader.interfaces;

/**
 * Created by renato on 12/03/17.
 */

public interface OnItemSelectedListener<T extends Object> {
    void select(T item);
}
