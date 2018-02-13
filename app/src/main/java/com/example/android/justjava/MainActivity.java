/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        /**
         * Check to see if Whipped Cream was ordered
         */
        boolean hasWhippedCream = whippedCreamYesNo();

        /**
         * Check to see if Chocolate was ordered
         */
        boolean hasChocolate = chocolateYesNo();

        /**
         * Get the total price
         */
        int price = calculatePrice();

        /**
         * Get the user name
         */
        String nameIs = getName();

        /**
         * Piece together the order and send it to orderSummary
         */
        String priceMessage = orderSummary(nameIs, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
        }

    /**
     * Create summary of the order.
     * @param price of the order
     * @param addWhippedCream if the user wants whipped cream
     * @return text summary
     */
    private String orderSummary(String nameIs, int price, boolean addWhippedCream, boolean addChocolate){
        String priceMessage = "Name: " +nameIs;
        priceMessage = priceMessage + "\nWants whipped cream? "+ addWhippedCream;
        priceMessage = priceMessage + "\nWants chocolate? "+ addChocolate;
        priceMessage = priceMessage + "\nQuantity: " + quantity;
        priceMessage = priceMessage + "\nTotal = $" + price;
        priceMessage = priceMessage + "\nThank you!";
        return priceMessage;
    }

    /**
     * Get the state of the whipped cream check box
     * @return true or false to submitOrder
     */
    public boolean whippedCreamYesNo () {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        return (hasWhippedCream);
    }

    /**
     * Get the state of the chcolate check box
     * @return true or false to submitOrder
     */
    public boolean chocolateYesNo (){
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        return (hasChocolate);
    }

    /**
     * Get the users name
     * @return name to submitOrder
     */
    private String getName (){
        EditText nameIsEditText = findViewById(R.id.nameField);
        String whatIsName = nameIsEditText.getText() .toString();
        return (whatIsName);
    }

    /**
     * Calculates the price of the order.
     *
     */
    public int calculatePrice() {
        int price= quantity * 5;

        /**
         * call in methods to check what toppings were ordered
         */
        whippedCreamYesNo();
        chocolateYesNo();

        /**
         * Based off toppings ordered, adjust the price
         */
        if (whippedCreamYesNo() && chocolateYesNo()){
            int toppingPrice = quantity * 3;
            return (price + toppingPrice);
        }
        else if (whippedCreamYesNo()){
            int whippedPrice = quantity * 1;
            return (price + whippedPrice);
        }
        else if (chocolateYesNo()){
            int chocoPrice = quantity * 2;
            return (price + chocoPrice);
        }

        /**
         * If no toppings were ordered, return price of just the amount of coffees
         */
        return (price);
    }



    /**
     * Used to increase the number of coffees ordered
     */
    public void increment(View view ) {
        quantity = quantity + 1;
        if (quantity > 100){
            quantity = 100;
        }
        displayQuantity(quantity);
    }

    /**
     *Used to decrease the number of coffees ordered and handle a less than zero issue
     */
    public void decrement(View view ) {
        quantity = quantity - 1;
        if (quantity < 0) {
            quantity = 0;
        }

        displayQuantity(quantity);


    }


    /**
     * This method displays the given quantity value on the screen.
     */
    public void displayQuantity(int howManyOrdered) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + howManyOrdered);
    }

    /**
     * This method displays the given price on the screen. NOT USED NOW

    private void displayPrice(int number) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }*/

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}