package ap2014.asgnmnt4.question2;

public class AnswerSheet 
{
	private ExamStatus status;
	private int marks;
	
	public AnswerSheet() {
		this.status = ExamStatus.NOT_STARTED;
		this.marks = 0;
	}

	public void setStatus(ExamStatus status){
		this.status=status;
	}
	
	public ExamStatus getStatus(){
		return status;
	}
	
	public int getMarks(){
		return marks;
	}
	
	public void setMarks(int marks){
		this.marks=marks;
	}
}
