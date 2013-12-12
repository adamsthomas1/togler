package com.example.togler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class Article {
	
	private String color;
	private String imageURL;
	private int type;
	private String style;
	
	// individual set methods
	public void setColor(String c) {color = c;}
	public void setImageURL(String url) {imageURL = url;}
	public void setType(int num) {type = num;}
	public void setStyle(String sty) {style = sty;}
	
	// individual get methods
	public String getColor() {return color;}
	public String getURL() {return imageURL;}
	public int getType() {return type;}
	public String getStyle() {return style;}
	
	// primary constructor
	public Article(String c, String url, int num, String sty) {
		color = c;
		imageURL = url;
		type = num;
		style = sty;
	}
	
	// null default constructor
	public Article() {
		color = "";
		imageURL = "";
		type = -1;
		style = "";
	}
	
	
	public Drawable getBitmapFromURL(Context c) {
	    try {
	    	String src = imageURL;
	        URL url = new URL(src);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
	        Drawable draw = new BitmapDrawable(c.getResources(), myBitmap);
	        return draw;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}  
	
	

}
