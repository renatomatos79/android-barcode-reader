package br.com.rmatos.barcodereader.interfaces;

import br.com.rmatos.barcodereader.model.ProductModel;

/**
 * Created by renato on 12/03/17.
 */

public interface OnProductListener {
    void beforeCreate(ProductModel model);
    void barcodeCapture(ProductModel model);
    void cancel();
}
