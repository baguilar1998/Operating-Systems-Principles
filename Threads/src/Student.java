import java.util.*;
public class Student implements Runnable{

	private int typeAQuestions, typeBQuestions, studentID; 
	
	public Student(int a, int b, int id) {
		typeAQuestions = a;
		typeBQuestions = b;
		studentID = id;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Student " + studentID + " has entered in the computer lab");
	}

	/**
	 * Students can ask the amount of typeA questions
	 * that they need. As soon as they are done, they
	 * go on ahead and start asking their type B questions
	 */
	private void askTypeAQuestions() {
		
	}
	
	/**
	 * Students can ask the amount of typeB questions
	 * that they need.
	 */
	private void askTypeBQuestions() {
		
	}
	
	/**
	 * As soon as they are done, they
	 * just simply browse the internet until the online
	 * office hours end
	 */
	private void browseInternet() {
		
	}
}
