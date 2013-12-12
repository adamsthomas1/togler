package com.example.togler;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {

	// Initialize image switcher objects
	private ImageSwitcher topImageSwitcher;
	private ImageSwitcher bottomImageSwitcher;
	private ImageSwitcher footwearImageSwitcher;
	//private ProgressDialog mWaitingDialog;
	
	String topsURL = "https://s3.amazonaws.com/togler/topsfiletest.txt";
	String bottomsURL = "https://s3.amazonaws.com/togler/bottomsfiletest.txt";
	String footwearURL = "https://s3.amazonaws.com/togler/footwearfiletest.txt";
	
	private ProgressDialog progressDialog;


	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {

			// Wardrobe initializations for default wardrobes
			final Wardrobe tops = new Wardrobe(1);
			final Wardrobe bottoms = new Wardrobe(2);
			final Wardrobe footwear = new Wardrobe(3);

			Thread t = new Thread(new Runnable() {
				ProgressDialog progressDialog;
	            @Override
	            public void run() {
	            	// build every wardrobe from online txt file
	                tops.buildFromFile(topsURL);
	                bottoms.buildFromFile(bottomsURL);
	                footwear.buildFromFile(footwearURL);
	            }});
			t.start();
			progressDialog = ProgressDialog.show(MainActivity.this, "",
                    "Loading pictures...");
			try {
				t.join();
				progressDialog.dismiss();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            
            //Log.v("TOP WARDROBE after other wardrobes:", "article 0(color, style, url): " + tops.getArticle(0).getColor() + " " + tops.getArticle(0).getStyle() + " " + tops.getArticle(0).getURL());
            // list all articles of clothing
//            Log.v("TOP WARDROBE:", "article 0(color, style, url): " + tops.getArticle(0).getColor() + " " + tops.getArticle(0).getStyle() + " " + tops.getArticle(0).getURL());
//            Log.v("TOP WARDROBE:", "article 1(color, style, url): " + tops.getArticle(1).getColor() + " " + tops.getArticle(1).getStyle() + " " + tops.getArticle(1).getURL());
//            Log.v("BOTTOM WARDROBE:", "article 0(color, style, url): " + bottoms.getArticle(0).getColor() + " " + bottoms.getArticle(0).getStyle() + " " + bottoms.getArticle(0).getURL());
//            Log.v("BOTTOM WARDROBE:", "article 1(color, style, url): " + bottoms.getArticle(1).getColor() + " " + bottoms.getArticle(1).getStyle() + " " + bottoms.getArticle(1).getURL());
//            Log.v("FOOTWEAR WARDROBE:", "article 0(color, style, url): " + footwear.getArticle(0).getColor() + " " + footwear.getArticle(0).getStyle() + " " + footwear.getArticle(0).getURL());
//            Log.v("FOOTWEAR WARDROBE:", "article 1(color, style, url): " + footwear.getArticle(1).getColor() + " " + footwear.getArticle(1).getStyle() + " " + footwear.getArticle(1).getURL());
//            
            
            // get The view references 
            topImageSwitcher = (ImageSwitcher) findViewById(R.id.topImageSwitcher);
            bottomImageSwitcher = (ImageSwitcher) findViewById(R.id.bottomImageSwitcher);
            footwearImageSwitcher = (ImageSwitcher) findViewById(R.id.footwearImageSwitcher);
            
            
            // TOPS IMAGE SWITCHER VIEW DECLARATION
            // Set the ViewFactory of the ImageSwitcher that will create ImageView object when asked
            topImageSwitcher.setFactory(new ViewFactory() {
                public View makeView() {
                    // TODO Auto-generated method stub
                        // Create a new ImageView set it's properties 
                        ImageView imageView = new ImageView(getApplicationContext());
                        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
                        return imageView;
                }
            });
            
            
            // BOTTOMS IMAGE SWITCHER VIEW DECLARATION
            // Set the ViewFactory of the ImageSwitcher that will create ImageView object when asked
            bottomImageSwitcher.setFactory(new ViewFactory() {
                public View makeView() {
                    // TODO Auto-generated method stub
                        // Create a new ImageView set it's properties 
                        ImageView imageView = new ImageView(getApplicationContext());
                        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
                        return imageView;
                }
            });
            
            
            // SHOES IMAGE SWITCHER VIEW DECLARATION
            // Set the ViewFactory of the ImageSwitcher that will create ImageView object when asked
            footwearImageSwitcher.setFactory(new ViewFactory() {
                public View makeView() {
                    // TODO Auto-generated method stub
                        // Create a new ImageView set it's properties 
                        ImageView imageView = new ImageView(getApplicationContext());
                        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
                        return imageView;
                }
            });

            
            // Declare the animations and initialize them
            final Animation right_in = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
            final Animation right_out = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
            final Animation left_in = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
            final Animation left_out = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
            
            
		            // Initialize ImageSwitcher with first images
		            
		            // images on file
		            topImageSwitcher.setImageResource(getArticleId(tops.getCurrent()));
		            bottomImageSwitcher.setImageResource(getArticleId(bottoms.getCurrent()));
		            footwearImageSwitcher.setImageResource(getArticleId(footwear.getCurrent()));
		            
		            
		            
		            
		            // TOPS SWIPE
		            // OnSwipeListener for Top ImageSwitcher
		            topImageSwitcher.setOnTouchListener(new OnSwipeTouchListener() {
		                // progress through list
		                public void onSwipeLeft() {
		                	tops.next();
		                	topImageSwitcher.setInAnimation(left_in);
		                	topImageSwitcher.setOutAnimation(left_out);
		                	topImageSwitcher.setImageResource(getArticleId(tops.getCurrent()));
		                }
		                // digress through list
		                public void onSwipeRight() {
		                    tops.prev();
		                    topImageSwitcher.setInAnimation(right_in);
		                	topImageSwitcher.setOutAnimation(right_out);
		                	topImageSwitcher.setImageResource(getArticleId(tops.getCurrent()));
		                }
		                public void onSwipeTop() {
		                	// not used
		                }  
		                public void onSwipeBottom() {
		                	// not used
		                }
		            });       
		            
		            
		            // BOTTOMS  SWIPE
		            // OnSwipeListener for bottom ImageSwitcher
		            bottomImageSwitcher.setOnTouchListener(new OnSwipeTouchListener() {
		                // progress through list
		                public void onSwipeLeft() {
		                	bottoms.next();
		                	bottomImageSwitcher.setInAnimation(left_in);
		                	bottomImageSwitcher.setOutAnimation(left_out);
		                	bottomImageSwitcher.setImageResource(getArticleId(bottoms.getCurrent()));
		                }
		                // digress through list
		                public void onSwipeRight() {
		                	bottoms.prev();
		                	bottomImageSwitcher.setInAnimation(right_in);
		                	bottomImageSwitcher.setOutAnimation(right_out);
		                	bottomImageSwitcher.setImageResource(getArticleId(bottoms.getCurrent()));
		                }
		                // not used
		                public void onSwipeBottom() {
		                }
		                //not used
		                public void onSwipeTop() {
		                }
		            });   
		            
		            
		         	// SHOE SWIPE
		            // OnSwipeListener for bottom ImageSwitcher
		            footwearImageSwitcher.setOnTouchListener(new OnSwipeTouchListener() {
		                // progress through list
		                public void onSwipeLeft() {
		                	footwear.next();
		                	footwearImageSwitcher.setInAnimation(left_in);
		                	footwearImageSwitcher.setOutAnimation(left_out);
		                	//footwearImageSwitcher.setImageDrawable(footwear.getCurrent().getBitmapFromURL(getBaseContext()));
		                	footwearImageSwitcher.setImageResource(getArticleId(footwear.getCurrent()));
		                }
		                // digress through list
		                public void onSwipeRight() {
		                	footwear.prev();
		                	footwearImageSwitcher.setInAnimation(right_in);
		                	footwearImageSwitcher.setOutAnimation(right_out);
		                	//footwearImageSwitcher.setImageDrawable(footwear.getCurrent().getBitmapFromURL(getBaseContext()));
		                	footwearImageSwitcher.setImageResource(getArticleId(footwear.getCurrent()));
		                }
		                // not used
		                public void onSwipeBottom() {
		                }
		                //not used
		                public void onSwipeTop() {
		                }
		            }); 
	            
    }


	// retrieves resource id for image
	private int getArticleId(Article a) {
		String color = a.getColor();
		String style = a.getStyle();
		String url = a.getURL();
		int drawableResourceId = this.getResources().getIdentifier(url, "drawable", this.getPackageName());
		Log.e("GET ARTICLE ID:", "drawable name, color, style = " + url + " " + color + " " + style);
		return drawableResourceId;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
