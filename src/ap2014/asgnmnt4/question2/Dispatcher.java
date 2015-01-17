package ap2014.asgnmnt4.question2;

public class Dispatcher extends Participant  {

	public Dispatcher(String name) {
		super(name);
	}

	public void dispatchMarks() {
		System.out.println("Dear Student,\n You have got " + answerSheet.getMarks() + "\nRegards\nUG");
	}

	@Override
	public void run() {
		sheetAccessWaitStartTime = System.nanoTime();
		synchronized (answerSheet) {
			System.out.println(pTypeName + ": I have got the sheet");

			try {
				boolean statusCheck = answerSheet.getStatus().ordinal() < ExamStatus.EVALUATED.ordinal();
				while (statusCheck) {
					numWaitInvocations++;
					System.out.println(pTypeName + ": going to wait : " + numWaitInvocations);
					answerSheet.wait();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					statusCheck = answerSheet.getStatus().ordinal() < ExamStatus.EVALUATED.ordinal();
				}
				taskStartTime = System.nanoTime();
				System.out.println(pTypeName + ": going to work on sheet");
				dispatchMarks();
				answerSheet.setStatus(ExamStatus.MARKS_DISPATCHED);
				System.out.println(pTypeName + ": finished ");
				taskEndTime = System.nanoTime();
				answerSheet.notifyAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
