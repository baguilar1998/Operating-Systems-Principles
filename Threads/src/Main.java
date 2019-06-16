import java.util.*;
public class Main {
	
	public static long time = System.currentTimeMillis();
	public static Queue<Thread> computerLab = new LinkedList<>();
	public static boolean isLabOpen = false;
	public static int capacity = 8;

	
	public static void main(String [] args) {
		int students = Integer.parseInt(args[0]);
		final int typeAQuestions = 4, typeBQuestions = 3;

		// Create the teacher object so we can set up the teachers inbox
		// for any questions that students want to email
		Teacher teacher  = new Teacher();
		
		// Let the students enter the lab first
		for(int i = 0;i<students;i++) {
			int numOfAQuestions = (int) (Math.random()*typeAQuestions)+1;
			int numOfBQuestions =(int) (Math.random()*typeBQuestions)+1;
			Student student = new Student(numOfAQuestions, numOfBQuestions, i+1);
			Thread t = new Thread(student);
			t.start();
		}
		isLabOpen = true;
		// Let the teacher arrive to the office
		Thread t = new Thread(teacher);
		t.start();
	}
	
	public static long currentTime() {
		return System.currentTimeMillis() - time;
	}
}
