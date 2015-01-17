package ap2014.asgnmnt4.question1;


public class Aircarft implements Runnable, Flight, LandingFlight, TakingOffFlight{
	Airport airport;
	String callsign;
	String flightStatus;
	long start;
	long end;
	long waitTime;
	boolean prepared = false;
	int order;

	public Aircarft (String callsign, String flightStatus){
		this.callsign = callsign;
		this.flightStatus = flightStatus;
	}

	public boolean isPrepared() {
		return prepared;
	}

	public void run(){	
		prepared = true;
		while (!airport.allPrepared()) {

		}
		start = System.nanoTime();
		Runway runway = airport.getRunway();
		synchronized(runway){
			end = System.nanoTime();
			waitTime=end-start;
			if("takingoff".equals(flightStatus)){
				takeOff(runway);
			}
			else {
				land(runway);
			}
		}
		
	}

	@Override
	public void takeOff(Runway runway) {
		
		System.out.println(this.order+"\t"+this.getCallSign()+"\t\t"+this.getStatus()+"\t\t"+this.getWaitTime());
	}


	@Override
	public void land(Runway runway) {
		System.out.println(this.order+"\t"+this.getCallSign()+"\t\t"+this.getStatus()+"\t\t"+this.getWaitTime());
	}

	@Override
	public String getCallSign() {
		return callsign;
	}


	@Override
	public String getStatus() {
		return flightStatus;
	}

	@Override
	public long getWaitTime() {
		return waitTime;
	}

	/**
	 * @param airport the airport to set
	 */
	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

}
