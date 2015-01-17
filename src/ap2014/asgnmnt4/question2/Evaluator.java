package ap2014.asgnmnt4.question2;

public class Evaluator extends Participant {
	
	public Evaluator(String name){
		super(name);
	}
		
	public void evaluate() {
		answerSheet.setMarks((int)(Math.random()*100)+1);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		sheetAccessWaitStartTime=System.nanoTime();
		synchronized(answerSheet){
			System.out.println(pTypeName + ": I have got the sheet");

			try{
				boolean statusCheck = answerSheet.getStatus().ordinal() < ExamStatus.FINISHED.ordinal();
				while(statusCheck) {			
					numWaitInvocations++;
					System.out.println(pTypeName + ": going to wait: " + numWaitInvocations);
					answerSheet.wait();
					statusCheck = answerSheet.getStatus().ordinal() < ExamStatus.FINISHED.ordinal();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				taskStartTime=System.nanoTime();
				System.out.println(pTypeName + ": going to work on sheet");
				evaluate();
				answerSheet.setStatus(ExamStatus.EVALUATED);
				System.out.println(pTypeName + ": finished");
				taskEndTime=System.nanoTime();
				answerSheet.notifyAll();
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
