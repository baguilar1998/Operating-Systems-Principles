
public class Question {
	
	private long time; 
	private Student student;
	
	public Question(Student s, long t) {
		student = s;
		time = t;
	}
	
	@Override
	public String toString() {
		return student.getStudentName() + " which was sent at " + time + " Eastern Standard Time";
	}
	
	public Student getStudent() {
		return student;
	}
	
	public long getAge() {
		return time;
	}
}
