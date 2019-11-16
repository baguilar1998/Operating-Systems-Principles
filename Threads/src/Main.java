import java.util.*;
public class Main {
	
	public static long time = System.currentTimeMillis();
	// Used to keep track of how many students are in the 
	// computer lab
	public static Queue<Thread> computerLab = new LinkedList<>();
	// Used to keep track all alive student threads
	public static Student currentStudents[];
	// Holds all student threads
	public static Student [] studentArray;
	
	// Busy wait variable
	public static boolean isLabOpen = false;
	// Default value of capacity
	public static int capacity = 8;

	
	public static void main(String [] args) {
		int students = Integer.parseInt(args[0]);
		final int typeAQuestions = 4, typeBQuestions = 3;

		Teacher teacher  = new Teacher();
		studentArray = new Student[students];
		currentStudents = new Student[students];
		
		for(int i = 0;i<students;i++) {
			int numOfAQuestions = (int) (Math.random()*typeAQuestions)+1;
			int numOfBQuestions =(int) (Math.random()*typeBQuestions)+1;
			Student student = new Student(numOfAQuestions, numOfBQuestions, i+1);
			studentArray[i] = student;
			student.start();
		}
		
		isLabOpen = true;
		Timer t = new Timer(teacher);
		Thread timer = new Thread(t);
		timer.start();
	}
	
	public static long currentTime() {
		return System.currentTimeMillis() - time;
	}
}
