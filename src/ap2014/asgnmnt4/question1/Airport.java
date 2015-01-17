package ap2014.asgnmnt4.question1;

import java.util.ArrayList;
import java.util.Iterator;



public class Airport{
	ArrayList<Aircarft> planes=new ArrayList<Aircarft>(); 
	Runway runway; 
	String name;
	String location;

	public Airport(String name,String location,String runwayDirection ){
		this.name=name;
		this.location=location;
		this.runway= new Runway(runwayDirection);
	}

	public String getName(){
		return this.name;
	}

	public String getLoc(){
		return this.location;
	}


	public Runway getRunway(){
		return runway;
	}

	public void addPlane(Aircarft plane){
		planes.add(plane);
		plane.setAirport(this);
		plane.setOrder(planes.size());
	}
	
	public Aircarft getPlane(int i){
		return planes.get(i);
	}
	
	public boolean allPrepared() {
		boolean allPrepared = true;
		for (Iterator<Aircarft> iterator = planes.iterator(); iterator.hasNext();) {
			Aircarft plane = iterator.next();
			allPrepared = allPrepared && plane.isPrepared();
		}
		return allPrepared;
	}

}
