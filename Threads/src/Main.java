import java.util.*;
public class Main {
	
	public static long time = System.currentTimeMillis();
	public static Queue<Thread> computerLab = new LinkedList<>();
	public static Thread [] studentArray;
	public static boolean isLabOpen = false;
	public static int capacity = 8;

	
	public static void main(String [] args) {
		int students = Integer.parseInt(args[0]);
		final int typeAQuestions = 4, typeBQuestions = 3;

		Teacher teacher  = new Teacher();
		studentArray = new Thread[students];
		
		for(int i = 0;i<students;i++) {
			int numOfAQuestions = (int) (Math.random()*typeAQuestions)+1;
			int numOfBQuestions =(int) (Math.random()*typeBQuestions)+1;
			Student student = new Student(numOfAQuestions, numOfBQuestions, i+1);
			Thread t = new Thread(student);
			studentArray[i] = t;
			t.start();
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
