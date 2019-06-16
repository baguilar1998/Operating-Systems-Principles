
public class Question {
	private long time; 
	private Student student;
	
	public Question(Student s, long t) {
		student = s;
		time = t;
	}
	
	@Override
	public String toString() {
		return student.getStudentName() + " has asked a question at " + time;
	}
	
}
