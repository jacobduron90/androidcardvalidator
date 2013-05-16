package com.example.card;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Card extends Activity implements OnClickListener {

	Button submit;
	EditText card;
	EditText month;
	EditText year;
	EditText cvv;
	static ImageView cardim;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_card);
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_header);
		submit = (Button)findViewById(R.id.submit);
		card = (EditText)findViewById(R.id.cardnumber);
		month = (EditText)findViewById(R.id.month);
		year = (EditText)findViewById(R.id.year);
		cvv = (EditText)findViewById(R.id.cvv);
		cardim = (ImageView)findViewById(R.id.cardimage);
		submit.setOnClickListener(this);
		
	
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		cardImageFunction();
		
		
	}
	
	
public void cardImageFunction(){
	
	card.addTextChangedListener(new TextWatcher() {

	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	        // TODO Auto-generated method stub

	    }

	    public void beforeTextChanged(CharSequence s, int start, int count,
	            int after) {
	        // TODO Auto-generated method stub

	    }
	    @Override
	    public void afterTextChanged(Editable s) {
	        // TODO Auto-generated method stub
	        if(s.length() == 0){
	        	cardim.setImageResource(R.drawable.generic_card);
	        }
	        if (s.length() > 0) {
	            String str = card.getText().toString();
	            
	         
	            if (str.length() >= 13 && str.length() <= 16
	                    && str.startsWith("4")) {
	            	cardim.setImageResource(R.drawable.visalogo); 
	            	
	        }else if (str.length() == 16) {
	            // MASTERCARD has length 16
	            // MASTER card starts with 51, 52, 53, 54 or 55
	            
	                int prefix = Integer.parseInt(str.substring(0, 2));
	                if (prefix >= 51 && prefix <= 55) {
	                	cardim.setImageResource(R.drawable.mastercard); 
	                
	                    
	                }
	            
	        } else if (str.length() == 15
	                    && (str.startsWith("34") || str
	                            .startsWith("37"))) {
	        	 // AMEX has length 15
	            // AMEX has prefix 34 or 37
	                cardim.setImageResource(R.drawable.amex_512);
	            
	        }else if (str.length() == 14) {
	        	// DINERSCLUB or CARTEBLANCHE has length 14
	            // DINERSCLUB or CARTEBLANCHE has prefix 300, 301, 302, 303, 304,
	            // 305 36 or 38
	                int prefix = Integer.parseInt(str.substring(0, 3));
	                if ((prefix >= 300 && prefix <= 305)
	                        || str.startsWith("36")
	                        || str.startsWith("38")) {
	                	cardim.setImageResource(R.drawable.diners);
	               
	        } else if (str.length() == 16 && str.startsWith("6011")) {
	             // DISCOVER card has length of 16
		            // DISCOVER card starts with 6011
	        	cardim.setImageResource(R.drawable.discover_512);
	            
	        } else if (str.length() == 16
	                    && (str.startsWith("2014") || str
	                            .startsWith("2149"))) {
	            	 // ENROUTE card has length of 16
		            // ENROUTE card starts with 2014 or 2149
	        	cardim.setImageResource(R.drawable.enroute_card_thumb);
	        	
            } else if ((str.length() == 16 && str.startsWith("3"))
	                    || (str.length() == 15 && (str
	                            .startsWith("2131") || str
	                            .startsWith("1800")))) {
	            	// JCB card has length of 16 or 15
		            // JCB card with length 16 starts with 3
		            // JCB card with length 15 starts with 2131 or 1800
            	cardim.setImageResource(R.drawable.jcb);

	            }
	             
	        }
	    }

	    }
		
	});
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.card, menu);
		return true;
	}
	
	@Override
	public void onClick(View v) {
		try{
		String mo = month.getText().toString();
		String yr = year.getText().toString();
		String cv = cvv.getText().toString();
			if(mo.length() == 2 && yr.length() == 4 && cv.length() != 0){
			Calendar date = Calendar.getInstance();
			date.set(Integer.parseInt(yr), Integer.parseInt(mo)-1, 0);
			Calendar dates = GregorianCalendar.getInstance();
				if(date.after(dates)){
					String cardnu = card.getText().toString();
					if(CardValidator.validate(cardnu) == true){
						theDialog("SUCCESS", "Your card has been added");
						card.setText(null);
						month.setText(null);
						year.setText(null);
						cvv.setText(null);
						
						
					}else if (CardValidator.validate(cardnu) == false){
						theDialog("Wrong info.", "Your card number is incorrect");
						
					}
				}else{
					theDialog("Out of Date", "Your card has expired");
				}
			}else{
					theDialog("Missing Info", "You're Missing Info");
				}
		}catch(Exception e){
			
		}
		
		
	}
	
	
	public void theDialog(String title, String message){
		new AlertDialog.Builder(this)
	    .setTitle(title)
	    .setMessage(message)
	    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // continue with delete
	        }
	     })
	    
	     .show();
	
	}
	
	
	



}
