import java.util.*;
public class Student implements Runnable{

	private int typeAQuestions, typeBQuestions, studentID; 
	private String studentName;
	private String [] questions;
	private boolean leftChatSession;
	
	public Student(int a, int b, int id) {
		typeAQuestions = a;
		typeBQuestions = b;
		questions = new String[typeAQuestions];
		studentID = id;
		leftChatSession = false;
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
		System.out.println("["+Main.currentTime()+"]"+" Student " + studentID + " is waiting to enter the lab");
		/**
		 * Once all of the students (given in args[0]) has arrived. The lab will open and the first students
		 * that enter the lab before its full will get in. (e.g. Student 1 could be the first student to arrive at the lab, but
		 * he could be the 3rd person to enter the lab). All students busy wait while the lab is not open
		 */
		while(!Main.isLabOpen) {}
		/**
		 * As soon as the lab opens, you only want one student to enter at a time. If two students enter in at the same time
		 * this will cause a data coherence problem (we need to check if the lab is full for every student that tries to enter in)
		 * 
		 */
		try {
			Thread.currentThread().interrupt();
			if(Thread.currentThread().isInterrupted()) {
				// If the lab is full, the student cannot enter in and they are terminated
				if(Main.computerLab.size() == Main.capacity) {
					System.out.println("["+Main.currentTime()+"]"+" Student " + studentID + " cannot enter the lab because it is now full FALSE");
				} else {
					// The student enters in the lab and starts performing their tasks
					Main.computerLab.add(Thread.currentThread());
					System.out.println("["+Main.currentTime()+"]"+" Student " + studentID + " has now entered the lab TRUE");
					askTypeAQuestions();
					if(typeBQuestions != 0) {
						Teacher.onlineChatQueue.add(this);
						// Busy wait either if the student is waiting to chat for the teacher
						// or if the student did not finish chatting with the teacher
						while(!leftChatSession) {}
						askTypeBQuestions();
					}
					// wait until the student is finished with their chat session then cancel
					browseInternet();
					Main.computerLab.remove(this);
					System.out.println("["+Main.currentTime()+"]"+" Student " + studentID + " has left the computer lab");
				}
			}
		}catch (Exception e) {
			// Put an error message here
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
				// Releaes the student from the CPU to allow the student to think about their question
				Thread.currentThread().yield();
				System.out.println("["+Main.currentTime()+"] " + studentName + " figured out their question");
				Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
				System.out.println("["+Main.currentTime()+"] " + studentName + " is emailing the professor their question");
				Thread.currentThread().sleep((long) ((Math.random()*5000)+1000));
				Teacher.questionInbox.add(new Question(this,Main.currentTime()));
				System.out.println("["+Main.currentTime()+"] " + studentName + " has emailed the professor their question");
				Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
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
		// Use the sleep method and let the students browse the internet
		// until the online chat session has ended
		try {
			//
		} catch (Exception e) {
			
		}
	}
}
