package com.mashpy.muktijuddhergolpo;


import java.util.List;
import android.app.Activity;
import android.content.Intent;

import android.graphics.Typeface;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.view.View.OnClickListener;

import android.webkit.WebView;

import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;
import com.mashpy.muktijudhergolpo.R;

public class Liberation extends Activity 
{


	public static int selectedImagePosition = 0;
	private List<Drawable> drawables;
	private TextView txtTitle ;
	private TextView hadith_index_txt;
	private TextView hadith_details_btn;
	private WebView webview;
	public static int zoomFactor = -1;
	public static String[] Hadiths = {"",
	"এক ব্যর্থ অপারেশনের সফলতা ",
	"একজন জননীর গল্প",
	"সূর্যদী গ্রাম - নির্মম গণহত্যা",
	"জমির আলী একজন মুক্তিযোদ্ধা ",
	"আমার দেখা প্রথম মুক্তিযোদ্ধা",
	"একজন বীর মুক্তিযোদ্ধার গল্প",
	"নির্মম বাস্তবতার মুখোমুখি",
	"আমরা ছুটে চলেছি অচেনা গন্তব্যে",
	"কাঁটার মুকূট ",
	"নিভৃত এক আদিবাসী মুক্তিযোদ্ধা",
	"দেশপ্রেমের একাল-সেকাল ",
	"কিশোর যোদ্ধারা",
	"আমাদের গর্ব, আমাদের হতাশা",
	"মুক্তিযুদ্ধে ভীনদেশী বীর",
	"মুক্তিযুদ্ধের অমলিন স্মৃতি",
	"মীরাশের মা",
	"১৯৭১ , জেড ফোর্সের মুক্তিযুদ্ধ",
	"ঘরে না ফেরা সালামের গল্প",
	"পিতৃহারা কিশোরের প্রতিশোধের গল্প",
	"একাত্তরের জননীরা",
	};
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		webview = (WebView) findViewById(R.id.webkit);
		hadith_index_txt= (TextView)findViewById(R.id.hadith_index_txt);
		hadith_details_btn= (TextView)findViewById(R.id.hadith_details_btn);
		 String fontPath = "fonts/solaimanlipi.ttf";
	     Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
	     hadith_details_btn.setTypeface(tf);
	     hadith_details_btn.setText(" বিস্তারিত");
		
		setupUI();
		hadith_details_btn.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	Intent i = new Intent(getApplicationContext(), StoryDetails.class);
				startActivityForResult(i, 100);	
	        }
	    });
		
		
		ImageButton zoomOut = (ImageButton) findViewById(R.id.ZoomOut); // your image button
		zoomOut.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	int c = (int) (100 * webview.getScale());
	        	if(c>75)
	        	{
	        		webview.zoomOut();
	        		zoomFactor = (int) (100 * webview.getScale());
	        	}
	        }
	    });
		ImageButton zoomIn = (ImageButton) findViewById(R.id.ZoomIn); // your image button

	    // click event on your button
		zoomIn.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	int c = (int) (100 * webview.getScale());
	        	if(c<185)
	        	{
	        		webview.zoomIn();
	        		zoomFactor = (int) (100 * webview.getScale());
	        				
	        	}
	        }
	    });
		
		ImageButton gList = (ImageButton) findViewById(R.id.gList); // your image button

	    // click event on your button
		gList.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	
	        		Intent i = new Intent(getApplicationContext(), ListsActivity.class);
					startActivityForResult(i, 100);			
	        	
	        }
	    });
		setSelectedImage(selectedImagePosition);
	}
	

	private void setupUI() {

		
		
		txtTitle= (TextView)findViewById(R.id.textTitle);
		 String fontPath = "fonts/solaimanlipi.ttf";
	     Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
	     txtTitle.setTypeface(tf);
		ImageView leftArrowImageView = (ImageView) findViewById(R.id.left_arrow_imageview);
		ImageView rightArrowImageView = (ImageView) findViewById(R.id.right_arrow_imageview);
		
		
		leftArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(selectedImagePosition>0)
				{
					--selectedImagePosition;
					setSelectedImage(selectedImagePosition);
				}
				
			}
		});

		rightArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(selectedImagePosition<Hadiths.length-2)
				{
					++selectedImagePosition;
					setSelectedImage(selectedImagePosition);
				}
				

			}
		});


		

	}
	private int selectedIndex = 0;
	private void setSelectedImage(int selectedImagePosition) {

		
		selectedIndex = selectedImagePosition;
		txtTitle.setText("লোডিং");
		hadith_index_txt.setText(""+(selectedImagePosition+1));
		myHandler.removeCallbacks(mMyRunnable);
		myHandler.postDelayed(mMyRunnable, 1500);
		
	}
	
	 private Handler myHandler = new Handler();
	 private Runnable mMyRunnable = new Runnable()
	 {
	     @Override
	     public void run()
	     {
	 			try {
	 				
	 				webview.loadUrl("file:///android_asset/gd"+selectedIndex+".html");
	 				webview.requestFocus();
	 				if(zoomFactor!=-1)
	 					webview.setInitialScale(zoomFactor);
	 				txtTitle.setText("* " + Hadiths[selectedIndex+1]+ " * ");
	 			} catch(Exception e)
	 			{
	 				
	 			}
	     }
	  };
	  @Override
	    protected void onActivityResult(int requestCode,
	                                     int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        if(resultCode == 100){
	        	selectedImagePosition = data.getExtras().getInt("index");
	        	setSelectedImage(data.getExtras().getInt("index"));
	        }
	 
	    }
}