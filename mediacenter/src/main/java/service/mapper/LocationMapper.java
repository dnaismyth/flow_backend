package service.mapper;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import mediacenter.TestBaseClass;

import org.apache.log4j.Logger;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;

import entities.RLocation;

public class LocationMapper {
	protected static final Logger logger = Logger.getLogger(LocationMapper.class); 

	private Geocoder geocoder = new Geocoder();
	
	/**
	 * Convert an address to a location (lat, long)
	 * @param address
	 * @return
	 */
	public RLocation toRLocation(String address){
		RLocation rl = null;
		if(address != null){
			rl = new RLocation();
			GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(address).
					getGeocoderRequest();
			GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
			List<GeocoderResult> results = geocoderResponse.getResults();
			rl.setLatitude(results.get(0).getGeometry().getLocation().getLat().floatValue());
			rl.setLongitude(results.get(0).getGeometry().getLocation().getLng().floatValue());
			rl.setAddress(address);
			logger.info("Address:" + results.get(0).getFormattedAddress());
		}
		return rl;
	}
}
