package com.example.togler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import android.util.Log;

public class Wardrobe {
	
	// private variables
	private int type;
	private int cur;
	private ArrayList<Article> articleList;
	
	// default constructor, takes in type parameter and initialized arraylist
	public Wardrobe(int t) {
		cur = 0;
		articleList = new ArrayList<Article>();
		type = t;
	}
	
	
	// another constructor when given an arraylist of articles and type
	public Wardrobe(int t, ArrayList<Article> list) {
		cur = 0;
		type = t;
		articleList = list;
	}
	
	
	// returns article at given index
	public Article getArticle(int index) {
		return articleList.get(index);
	}
	
	
	// iterates through wardrobe to find only articles of a given style, can be null
	// allows for up to 3 styles
	public Wardrobe getArticlesByStyle(String style1, String style2, String style3) {
		// start new list
		ArrayList<Article> listByStyle = new ArrayList<Article>();
		// for each article in current wardrobe, check style
		for (int i = 0; i < articleList.size(); i++) {
			Article a = articleList.get(i);
			// if the right style, add to new list
			String articleStyle = a.getStyle();
			if (articleStyle == style1 || articleStyle == style2 || articleStyle == style3)
				listByStyle.add(a);
		}
		// create new wardrobe with only the articles with desired style
		Wardrobe wardrobeByStyle = new Wardrobe(type, listByStyle);
		return wardrobeByStyle;
	}
	
	
	// Iterates through wardrobe to find only articles of a given color
	// allows for up to 5 colors
	public Wardrobe getArticlesByColor(String color1, String color2, String color3, String color4, String color5) {
		// start new list
		ArrayList<Article> listByColor = new ArrayList<Article>();
		// for each article in current wardrobe, check color
		for (int i = 0; i < articleList.size(); i++) {
			Article a = articleList.get(i);
			String aColor = a.getColor();
			// if the right color, add to new list
			if (aColor == color1 || aColor == color2 || aColor == color3 || aColor == color4 || aColor == color5)
				listByColor.add(a);
		}
		// create new wardrobe with only the articles with desired color(s)
		Wardrobe wardrobeByStyle = new Wardrobe(type, listByColor);
		return wardrobeByStyle;
	}
	
	// retrieve current article of clothing in wardrobe
	public Article getCurrent() {
		return articleList.get(cur);
	}
	
	
	// add article to the wardrobe
	public void addArticle(Article a) {
		articleList.add(a);
	}
	
	// go to next article in wardrobe
	public void next() {
		cur += 1;
		if (cur == articleList.size())
			cur = 0;
	}
	
	// go to prev article in wardrobe
	public void prev() {
		cur -= 1;
		if (cur == -1)
			cur = articleList.size()-1;
	}
	
	public int size() {
		return articleList.size();
	}
	
	public int curIndex() {
		return cur;
	}
	
	// builds the wardrobe from url of text file 
	public void buildFromFile(String urlstring) {
		
		// try to read the file from the internet
				try {
						URL fileUrl = new URL(urlstring);

						BufferedReader in = new BufferedReader(new InputStreamReader(fileUrl.openStream()));

				        Scanner sc = new Scanner(in);
				        int id, type;
				        String style, url, color;
				        while (sc.hasNextLine()) {
				        	// id = sc.nextInt();
				        	type = sc.nextInt();
				        	Log.v("buildFromFile", "type successful: " + type);
				        	style = sc.next();
				        	Log.v("buildFromFile", "style successful: " + style);
				        	color = sc.next();
				        	Log.v("buildFromFile", "color successful: " + color);
				        	url = sc.next();
				        	Log.v("buildFromFile", "url successful: " + url);
				        	if (sc.hasNextLine())
				        		sc.nextLine();
				        	Article a = new Article(color, url, type, style);
				        	Log.e("MAIN - READING INPUT: color:", color);
				        	Log.e("MAIN - READING INPUT: url:", url);
				        	Log.e("MAIN - READING INPUT: type:", type + "");
				        	Log.e("MAIN - READING INPUT: style:", style);
				        	this.addArticle(a);
				        }
				        in.close();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		
	}
}
