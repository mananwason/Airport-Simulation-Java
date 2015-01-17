/**
 * 
 */
package ap2014.asgnmnt4.question2;

/**
 * @author manish
 *
 */
public abstract class Participant implements Runnable {

	protected String name;
	protected long sheetAccessWaitStartTime;
	protected long taskStartTime;
	protected long taskEndTime;
	protected AnswerSheet answerSheet;
	protected int numWaitInvocations;
	protected String pTypeName;

	/**
	 * 
	 */
	public Participant(String name) {
		this.name = name;
		String pType = getClass().getName();
		
		pTypeName = pType.substring(pType.lastIndexOf('.') + 1);
	}

	@Override
	public long taskTime() {
		return taskEndTime-taskStartTime;
	}

	@Override
	public int numWaitInvocations() {
		return numWaitInvocations;
	}

	@Override
	public long sheetAccessTime() {
		return taskStartTime-sheetAccessWaitStartTime;
	}

	/**
	 * @param answerSheet the answerSheet to set
	 */
	public void setAnswerSheet(AnswerSheet answerSheet) {
		this.answerSheet = answerSheet;
	}

}