package br.com.rmatos.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.zxing.common.StringUtils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.UUID;

import br.com.rmatos.barcodereader.fragments.ProductCreateFragment;
import br.com.rmatos.barcodereader.fragments.ProductListFragment;
import br.com.rmatos.barcodereader.interfaces.OnProductListener;
import br.com.rmatos.barcodereader.model.ProductModel;

public class MainActivity extends AppCompatActivity implements OnProductListener {

    private ArrayList<ProductModel> products;
    private ProductCreateFragment createFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.createFragment = null;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFragment = new ProductCreateFragment();
                replaceFragment(createFragment);
            }
        });

        this.products = fakeList();

        createProductList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createProductList(){
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataSource",products);

        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(bundle);

        replaceFragment(fragment);

        this.createFragment = null;
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.customLayout, fragment);
        ft.commit();
    }

    public ArrayList<ProductModel> fakeList(){
        ArrayList<ProductModel> result = new ArrayList<ProductModel>();
        result.add(new ProductModel(UUID.randomUUID().toString(), "Keyboard", "1212121212222", 24.99));
        result.add(new ProductModel(UUID.randomUUID().toString(), "Mouse", "2323234343231", 89.99));
        result.add(new ProductModel(UUID.randomUUID().toString(), "Notebook", "90909099989898", 1499.99));
        result.add(new ProductModel(UUID.randomUUID().toString(), "Printer", "0909878787654", 239.99));
        return result;
    }

    @Override
    public void beforeCreate(ProductModel model) {
        this.products.add(model);
        createProductList();
    }

    @Override
    public void barcodeCapture(ProductModel model) {
        IntentIntegrator intent = new IntentIntegrator(MainActivity.this);
        intent.initiateScan();
    }

    @Override
    public void cancel() {
        createProductList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            String barcode = result.getContents();
            if (barcode != null && !"".equals(barcode)){
                createFragment.setBarCode(barcode);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
