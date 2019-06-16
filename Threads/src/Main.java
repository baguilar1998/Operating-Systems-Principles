import java.util.*;
public class Main {
	
	public static long time = System.currentTimeMillis();
	public static Queue<Thread> computerLab = new LinkedList<>();
	public static boolean isLabOpen = false;
	public static int capacity = 8;
	//public static int i = 0;
	//public static int randomTime;
	
	public static void main(String [] args) {
		int students = Integer.parseInt(args[0]);
		final int typeAQuestions = 4, typeBQuestions = 3;
		//randomTime = (int)(Math.random()*students);
	
		// Create the teacher object so we can set up the teachers inbox
		// for any questions that students want to email
		Teacher teacher  = new Teacher();
		
		// Let the students enter the lab first
		for(int i = 0;i<students;i++) {
			int numOfAQuestions = (int) (Math.random()*typeAQuestions)+1;
			int numOfBQuestions =(int) (Math.random()*typeBQuestions)+1;
			Student student = new Student(numOfAQuestions, numOfBQuestions, i+1);
			Thread t = new Thread(student);
			//if(randomTime == i) {
				//isLabOpen = true;
			//}
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


/*
	Students enter a lab and they will come with two types of questions
	if they have any. There are only 8 people max that is allowed in the
	lab and any other student that attempts to enter the lab will be 
	kicked out. While in the lab the students can send questions through 
	email which is a type A question. As soon as they have a question A
	they will send it to the teacher immediately and have higher priority 
	and after that will be set back to its default priority and think of
	another question A or move on to question B.
*/