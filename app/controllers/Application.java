package controllers;

import play.*;
import play.mvc.*;
import ie.my.playcommon.JsonHelper;
import ie.my.dao.database.NoSQLConnector;
import java.util.*;

import models.*;

public class Application extends Controller {

	/**
	 * index page
	 * no params no returns nothing
	 */
	public static void index() {
		render();
	}

	/**
	 * search result page in JSON format
	 */
	public static void searchresult(){
		NoSQLConnector connector = new NoSQLConnector("test","localhost",27017);
		StringBuffer buff = connector.lookup("city",params.get("city"));
		renderJSON(buff.toString());
	}
    
    
    /**
     * building json object
     * key and value
     */
	public static StringBuffer appendJsonObject(String key, String key_val, String val,String val_val){
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(JsonHelper.appendValues(key,key_val));
		sb.append(JsonHelper.COMMA);
		sb.append(JsonHelper.appendValues(val,val_val));
		sb.append("}");
		return sb;
    }
    
	/**
	 * returns locations JSON object
	 */
    public static void locations(){
       	List<Country>  locs=null;
       	List<City>     cities=null;
       	Country c =  null;
       	String country = params.get("country");
       	
       	StringBuffer sb = new StringBuffer();
       	sb.append("[");
    	try{
    		locs = (List<Country>) ( (country==null) ? Country.findAll() : Country.find("idx='"+country+"'").fetch() );
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	int max = (country == null) ? locs.size() : ((Country) locs.get(0)).cities.size(); 
    	
    	if(country != null)
    		c =  locs.get(0);
    		
    	for(int i=0;i<max;++i){
    		if(country == null){
    			c = locs.get(i);
    			sb.append( appendJsonObject("idx",c.idx,"value",c.country_name) );
    		}
    		else{
       			City ci = c.cities.get(i);
       			sb.append( appendJsonObject("idx",ci.idx,"value",ci.city_name) );
    		}
    			
    		if(i<max-1)
    			sb.append(JsonHelper.COMMA);
    	}
       	sb.append("]");
       	renderJSON(sb.toString());
    }


    public static void display(String id){
    	if(id.equals("search.html"))
    		render("search.html");
    	else if(id.equals("simple.html"))
    		render("simple.html");
	else if(id.equals("main.html"))
		render("main.html");
        else if(id.equals("test.html"))
		render("test.html");
    }
   
}
