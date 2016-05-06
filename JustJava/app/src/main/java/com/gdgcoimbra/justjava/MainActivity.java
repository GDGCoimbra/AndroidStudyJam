package com.gdgcoimbra.justjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int DEFAULT_PRICE = 2;

    private int mQuantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPricePerCoffee();
    }

    public void onMinusButtonClicked(View view) {
        mQuantity--;
        if(mQuantity == 0) {
            findViewById(R.id.btn_minus).setEnabled(false);
        }

        updateQuantity();
        updatePrice();
    }

    public void onPlusButtonClicked(View view) {
        if(mQuantity == 0) {
            findViewById(R.id.btn_minus).setEnabled(true);
        }

        mQuantity++;
        updateQuantity();
        updatePrice();
    }

    public void onOrderClicked(View view) {
        String name = ((EditText) findViewById(R.id.et_name)).getText().toString();
        boolean hasWhippedCream = ((CheckBox) findViewById(R.id.cb_whipped_cream)).isChecked();
        boolean hasChocolate = ((CheckBox) findViewById(R.id.cb_chocolate)).isChecked();

        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra(Values.EXTRA_NAME, name);
        intent.putExtra(Values.EXTRA_TOPPING_WHIPPED_CREAM, hasWhippedCream);
        intent.putExtra(Values.EXTRA_TOPPING_CHOCOLATE, hasChocolate);
        intent.putExtra(Values.EXTRA_QUANTITY, mQuantity);
        intent.putExtra(Values.EXTRA_PRICE, (DEFAULT_PRICE * mQuantity));

        startActivity(intent);
        finish();
    }

    public void setPricePerCoffee() {
        TextView pricePerCoffee = (TextView) findViewById(R.id.price_per_coffee);
        pricePerCoffee.setText(Utils.getPriceWithLocalCurrency(DEFAULT_PRICE));
    }

    private void updateQuantity() {
        TextView value = (TextView) findViewById(R.id.tv_value);
        value.setText(String.valueOf(mQuantity));
    }

    private void updatePrice() {
        TextView value = (TextView) findViewById(R.id.tv_order);
        value.setText(getString(R.string.order_total, Utils.getPriceWithLocalCurrency(DEFAULT_PRICE * mQuantity)));
    }
}
