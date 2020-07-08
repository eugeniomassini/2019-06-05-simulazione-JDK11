package it.polito.tdp.crimes.model;

import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

public class Distretto {
	
	private Integer district_id;
	private List<Event> events;
	private LatLng center;
	
	private Double lat;
	private Double log;
	
	public Distretto(Integer district_id, List<Event> events) {
		super();
		this.district_id = district_id;
		this.events = events;
		
		calcolaCentro();
		
		this.center= new LatLng(lat, log);
		
	}
	
	private void calcolaCentro() {
		Double maxLat =0.0;
		Double minLat=0.0;
		Double maxLog =0.0;
		Double minLog =0.0;
		
		for(Event e: this.events) {
			if(e.getGeo_lat()>maxLat) {
				maxLat = e.getGeo_lat();
			}
			
			if(e.getGeo_lon()>maxLog) {
				maxLog = e.getGeo_lon();
			}
			
			if(e.equals(events.get(0))){
				minLat=e.getGeo_lat();
				minLog=e.getGeo_lon();
			}
			
			if(e.getGeo_lat()<minLat) {
				minLat = e.getGeo_lat();
			}
			
			if(e.getGeo_lon()<minLog) {
				minLog = e.getGeo_lon();
			}
		}
		
		this.lat=(minLat+maxLat)/2;
		this.log=(minLog+maxLog)/2;
		
		
	}
	public LatLng getCenter() {
		return center;
	}

	public void setCenter(LatLng center) {
		this.center = center;
	}

	public Integer getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(Integer district_id) {
		this.district_id = district_id;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((district_id == null) ? 0 : district_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distretto other = (Distretto) obj;
		if (district_id == null) {
			if (other.district_id != null)
				return false;
		} else if (!district_id.equals(other.district_id))
			return false;
		return true;
	}
	
	

}
