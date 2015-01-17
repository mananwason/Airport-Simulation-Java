package ap2014.asgnmnt4.question2;

public class Student extends Participant {

	public Student(String name) {
		super(name);
	}

	public void writeExam() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		sheetAccessWaitStartTime = System.nanoTime();
		synchronized (answerSheet) {
			System.out.println(pTypeName + ": I have got the sheet");
			try {
				boolean statusCheck = answerSheet.getStatus().ordinal() < ExamStatus.STARTED.ordinal();
				while (statusCheck) {
					numWaitInvocations++;
					System.out.println(pTypeName + ": going to wait: " + numWaitInvocations);
					answerSheet.wait();
					statusCheck = answerSheet.getStatus().ordinal() < ExamStatus.STARTED.ordinal();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				taskStartTime = System.nanoTime();
				System.out.println(pTypeName + ": going to work on sheet");
				writeExam();
				answerSheet.setStatus(ExamStatus.FINISHED);
				taskEndTime = System.nanoTime();
				System.out.println(pTypeName + ": finished");
				answerSheet.notifyAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
