package com.gdgcoimbra.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    private String mCoffeeOrderName;
    private String mCoffeeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        TextView details = (TextView) findViewById(R.id.tv_text);
        if(getIntent() == null) {
            details.setText(R.string.order_none);
            return;
        }

        mCoffeeOrder = "";
        if(getIntent().hasExtra(Values.EXTRA_NAME)) {
            mCoffeeOrderName = getIntent().getStringExtra(Values.EXTRA_NAME);
            mCoffeeOrder += getString(R.string.order_summary, mCoffeeOrderName);
        }

        if(getIntent().hasExtra(Values.EXTRA_TOPPING_WHIPPED_CREAM)) {
            boolean whippedCream = getIntent().getBooleanExtra(Values.EXTRA_TOPPING_WHIPPED_CREAM, false);
            mCoffeeOrder += whippedCream ? "\n" + getString(R.string.topping_whipped_cream) : "\n" + getString(R.string.topping_no_whipped_cream);
        }

        if(getIntent().hasExtra(Values.EXTRA_TOPPING_CHOCOLATE)) {
            boolean whippedCream = getIntent().getBooleanExtra(Values.EXTRA_TOPPING_CHOCOLATE, false);
            mCoffeeOrder += whippedCream ? "\n" + getString(R.string.topping_chocolate) : "\n" + getString(R.string.topping_no_chocolate);
        }

        if(getIntent().hasExtra(Values.EXTRA_QUANTITY)) {
            mCoffeeOrder += "\n" + getString(R.string.coffee_quantity) + ": " + getIntent().getIntExtra(Values.EXTRA_QUANTITY, 0);
        }

        if(getIntent().hasExtra(Values.EXTRA_PRICE)) {
            mCoffeeOrder += "\n" + getString(R.string.coffee_price) + ": " + Utils.getPriceWithLocalCurrency(getIntent().getIntExtra(Values.EXTRA_PRICE, 0));
        }

        details.setText(mCoffeeOrder);
    }

    public void onOrderClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_name, mCoffeeOrderName));
        intent.putExtra(Intent.EXTRA_TEXT, mCoffeeOrder);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.order_failed, Toast.LENGTH_SHORT).show();
        }
    }
}
