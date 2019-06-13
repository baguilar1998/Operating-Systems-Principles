import java.util.*;
public class Main {
	
	public static void main(String [] args) {
		int capacity = 8;//, students = Integer.parseInt(args[0]);
		final int typeAQuestions = 4, typeBQuestions = 3;
		
		Queue<Thread> computerLab = new LinkedList<>();
		
		// Let the students enter the lab first
		for(int i=0;i<3;i++) {
			int numOfAQuestions = (int) (Math.random()*typeAQuestions)+1;
			int numOfBQuestions =(int) (Math.random()*typeBQuestions)+1;
			Student student = new Student(numOfAQuestions, numOfBQuestions, i+1);
			Thread t = new Thread(student);
			computerLab.add(t);
			// student attempts to enter the lab
			// check the lab cap
			// if full then student can't enter lab and you terminate it
			t.start();
		}
	
		// Then let the teacher arrive to the office
		Teacher teacher  = new Teacher();
		Thread t = new Thread(teacher);
		t.start();
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