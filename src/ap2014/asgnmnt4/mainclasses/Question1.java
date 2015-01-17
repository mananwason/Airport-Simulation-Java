package ap2014.asgnmnt4.mainclasses;
import java.util.Random;

import ap2014.asgnmnt4.question1.*;
public class Question1 extends Thread {

	public static void main(String[] args) {
		
		//Setting up Runway
		Random rand = new Random();
		String runwayName = Integer.toString(rand.nextInt(36)+1);

		//Setting up Airport
		Airport igit = new Airport("Indira Gandhi International Airport","New Delhi",runwayName);

		//Printing Airport Details
		System.out.println("Airport Details");
		System.out.println("Name: "+igit.getName()+"\nLocation: "+igit.getLoc()+"\nRunway Name: "+igit.getRunway().getName());
		System.out.println();

		
		//Setting up aeroplanes
		for(int i=0;i<10;i++){
			int CallSign = rand.nextInt(901)+100;
			int status = rand.nextInt(2);
			String flightStatus;
			if(status==1)
				flightStatus = "landing";
			else
				flightStatus = "Taking off";
			igit.addPlane(new Aircarft("AP-"+CallSign, flightStatus));
		}
		
		System.out.println("Flight Details");
		System.out.println("Order\tCall sign\tStatus");

		for(int i=0;i<10;i++){
			System.out.println((i+1)+"\t"+igit.getPlane(i).getCallSign()+"\t\t"+igit.getPlane(i).getStatus());
		}
		
		System.out.println("\n\n\nFlight operation loop starts");
		System.out.println("\nOrder\tCall sign\tStatus\t\t\tWait Time");

		Thread[] planeThreads = new Thread[10];
		for (int i = 0; i < planeThreads.length; i++) {
			planeThreads[i] = new Thread(igit.getPlane(i));
			planeThreads[i].start();
		}

		try {
			for (int i = 0; i < planeThreads.length; i++){
				planeThreads[i].join();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}


	}
}
