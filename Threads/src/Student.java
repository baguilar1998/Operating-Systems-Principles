import java.util.*;
public class Student implements Runnable{

	private int typeAQuestions, typeBQuestions, studentID; 
	private String studentName;
	private String [] questions;

	public Student(int a, int b, int id) {
		typeAQuestions = a;
		typeBQuestions = b;
		questions = new String[typeAQuestions];
		studentID = id;
		setStudentName();
	}
	
	public void setStudentName() {
		studentName = "Student " + studentID;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("["+Main.currentTime()+"]"+" Student " + studentID + " is waiting to enter the lab");
		// STUDENT WAITS UNTIL THE LAB OPENS
		while(!Main.isLabOpen) {}
		// DATA COHERNCE PROBLEM
		if(Main.computerLab.size() == Main.capacity) {
			System.out.println("["+Main.currentTime()+"]"+" Student " + studentID + " cannot enter the lab because it is now full FALSE");
		} else {
			Main.computerLab.add(Thread.currentThread());
			System.out.println("["+Main.currentTime()+"]"+" Student " + studentID + " has now entered the lab TRUE");
			askTypeAQuestions();
			// STUDENT NEEDS TO WAIT UNTIL CHAT SESSION STARTS
			// STUDENT NEEDS
			askTypeBQuestions();
			browseInternet();
		}
	}

	/**
	 * Students can ask the amount of typeA questions
	 * that they need. As soon as they are done, they
	 * go on ahead and start asking their type B questions
	 */
	private void askTypeAQuestions() {
		int counter = 0;
		System.out.println("["+Main.currentTime()+"] " + studentName + " has " + typeAQuestions + " questions that he wants to email the professor about");
		while(typeAQuestions != 0) {
			try {
				System.out.println("["+Main.currentTime()+"] " + studentName + " is figuring out the question they want to ask");
				Thread.currentThread().yield();
				System.out.println("["+Main.currentTime()+"] " + studentName + " figured out their question");
				Thread.currentThread().setPriority(10);
				System.out.println("["+Main.currentTime()+"] " + studentName + " is emailing the professor their question");
				Thread.currentThread().sleep((long) ((Math.random()*5000)+1000));
				System.out.println("["+Main.currentTime()+"] " + studentName + " has emailed the professor their question");
				Thread.currentThread().setPriority(5);
				typeAQuestions--;
			}catch(Exception e) {
				
			}
		}
		System.out.println("["+Main.currentTime()+"] " + studentName + " emailed all possible questions that they needed to email");
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
