package com.thrymr.modules;

import javax.inject.Inject;

import play.Logger;
import play.filters.cors.CORSFilter;
import play.http.HttpFilters;
import play.mvc.EssentialAction;
import play.mvc.EssentialFilter;

public class CustomFilters extends EssentialFilter implements HttpFilters {

	@Override
	public EssentialFilter[] filters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EssentialAction apply(EssentialAction next) {
		// TODO Auto-generated method stub
		return null;
	}

    /*@Inject
    private CORSFilter corsFilter;


    @Override
    public EssentialAction apply(EssentialAction next) {
    	Logger.info("---Filter applied---");
        return corsFilter.asJava().apply(next);
        
    }

    @Override
    public EssentialFilter[] filters() {
    	Logger.info("---Filter applied---");
        EssentialFilter[] result = new EssentialFilter[1];
        result[0] = this;

        return result;
    }*/
}
	   
