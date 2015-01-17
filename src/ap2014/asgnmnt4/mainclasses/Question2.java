package ap2014.asgnmnt4.mainclasses;

import ap2014.asgnmnt4.question2.AnswerSheet;
import ap2014.asgnmnt4.question2.Dispatcher;
import ap2014.asgnmnt4.question2.Evaluator;
import ap2014.asgnmnt4.question2.ExamStatus;
import ap2014.asgnmnt4.question2.Participant;
import ap2014.asgnmnt4.question2.Student;

public class Question2 {

	public static void main(String[] args) {
		AnswerSheet answerSheet = new AnswerSheet();
		Participant[] participants = new Participant[3];
		Thread[] threads = new Thread[participants.length];
		
		participants[0] = new Student("Yash");
		participants[1] = new Evaluator("Yash");
		participants[2] = new Dispatcher("UG");

		for (int i = participants.length-1; i >=0; i--) {
			Participant participant = participants[i];
			participant.setAnswerSheet(answerSheet);
			threads[i] = new Thread(participant);
			threads[i].setPriority(Thread.MAX_PRIORITY - i);
			threads[i].start();
		}
		
		try {
			Thread.sleep(500);
			synchronized (answerSheet) {
				answerSheet.setStatus(ExamStatus.STARTED);
				answerSheet.notifyAll();	
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			for (int i = 0; i < participants.length; i++) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("\nTime taken to get access to Answer Sheet");
		for (int i = 0; i < participants.length; i++) {
			Participant participant = participants[i];
			String pType = participant.getClass().getName();
			System.out.println("\t" + pType.substring(pType.lastIndexOf('.') + 1)
					+ "\t "+participant.sheetAccessTime());
		}
		
		System.out.println("\nNumber of Wait Invocations");
		for (int i = 0; i < participants.length; i++) {
			Participant participant = participants[i];
			String pType = participant.getClass().getName();
			System.out.println("\t" + pType.substring(pType.lastIndexOf('.') + 1)
					+ "\t "+participant.numWaitInvocations());
		}
		
		System.out.println("\nTime taken to get access to finish task");
		for (int i = 0; i < participants.length; i++) {
			Participant participant = participants[i];
			String pType = participant.getClass().getName();
			System.out.println("\t" + pType.substring(pType.lastIndexOf('.') + 1)
					+ "\t "+participant.taskTime());
		}
	}
}
