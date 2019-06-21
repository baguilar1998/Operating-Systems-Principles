/**
 * A class to hold any data that a
 * question may contain. (Mainly Type A
 * Question Information)
 *
 */
public class Question {
	
	// Instance Variables
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
	
	/**
	 * -----------------------------
	 * Getter Methods for Questions
	 * -----------------------------
	 */
	
	public Student getStudent() {
		return student;
	}
	
	public long getAge() {
		return time;
	}
}
