package com.irg.asa.wcs.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.irg.asa.wcs.service.WCService;

@Component(immediate=true, label="WC Service")
@Service
public class WCServiceImpl implements WCService {
	
	private static final Logger log = LoggerFactory.getLogger(WCServiceImpl.class);
	
	public WCServiceImpl() {
	}

	@Override
	public String getJSON(String url, int timeout) {
		try {
	        URL u = new URL(url);
	        HttpURLConnection c = (HttpURLConnection) u.openConnection();
	        c.setRequestMethod("GET");
	        c.setRequestProperty("Content-length", "0");
	        c.setUseCaches(false);
	        c.setAllowUserInteraction(false);
	        c.setConnectTimeout(timeout);
	        c.setReadTimeout(timeout);
	        c.connect();
	        int status = c.getResponseCode();

	        switch (status) {
	            case 200:
	            case 201:
	                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
	                StringBuilder sb = new StringBuilder();
	                String line;
	                while ((line = br.readLine()) != null) {
	                    sb.append(line+"\n");
	                }
	                br.close();
	                return sb.toString();
	        }

	    } catch (MalformedURLException e) {
	    	log.error(e.getMessage(), e);
	    } catch (IOException e) {
	    	log.error(e.getMessage(), e);
	    }
	    return null;
	}

}
