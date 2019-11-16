import java.util.*;
public class Student extends Thread{

	private int typeAQuestions, typeBQuestions, studentID; 
	private String studentName;
	private static boolean enterLab;
	boolean inChat, askedQuestion, isLastQuestion;
	
	public Student(int a, int b, int id) {
		typeAQuestions = a;
		typeBQuestions = b;
		studentID = id;
		inChat = false;
		isLastQuestion = false;
		enterLab = false;
		setStudentName();
	}
	

	@Override
	public void run() {
		
		System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
				+ " is waiting to enter the lab");

		// All students busy waits while the lab is not
		// open
		while(!Main.isLabOpen || turnToEnterLab()) {} 
		
		// Implementing Mutual Exclusion so only one student can enter 
		// the lab at a time
		setEnterLab(true);
		
		// If the lab is full, the student terminates
		// else the student performs its tasks in the lab
		if(Main.computerLab.size() == Main.capacity) {
			System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
					+ " cannot enter the lab because it is now full");
			setEnterLab(false);
		} else {
			Main.computerLab.add(Thread.currentThread());
			Main.currentStudents[studentID-1] = this;
			setEnterLab(false);
			
			System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
					+ " has now entered the lab");
			
			// If they have any type A questions, students will
			// ask type A questions
			if(typeAQuestions != 0 && !Teacher.officeHoursEnded) {
				askTypeAQuestions();	
			}
			
			// If student have any type B questions, they will chat
			// with the professor
			if(typeBQuestions != 0 && !Teacher.officeHoursEnded) {
				
				// Go into the chat queue
				Teacher.onlineChatQueue.add(this);

				System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
						+ " is waiting to chat with the professor");
				
				// Busy wait while the student is not in the chat and while
				// the chat session hours didnt end
				while(!isStudentInChat() && !Teacher.didChatSessionEnd()) {}
				
				// If the online chat session didnt end, the student will talk to the
				// professor in the chat. Else all students will leave the chat queue
				if(!Teacher.onlineChatSessionEnded) {
					
					System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
							+ " has entered the chat");
					
					System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
							+ " has " + typeBQuestions + " question(s) that he wants to chat about");
					
					while(getTypeBQuestions()!=0) {
						System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
								+ " has asked a question to the professor in the chat");
						askedQuestion(true);
						// Busy wait while the professor did not answer the 
						// student's question
						while(!Teacher.didTeacherAnswer()) {}
						askedTypeBQuestion();
						askedQuestion(false);
						Teacher.setAnswer(false);
					}	
					
					System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
							+ " has left the chat session");
				} else {
					System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
							+ " could not chat with the teacher cause the chat session hours had ended");
				}
			}
					
			// Let the students browse the internet if the office hours
			// are still in session
			if (!Teacher.officeHoursEnded) {
				browseInternet();
			}
			
			System.out.println("["+Main.currentTime()+"]"+" Student " 
			+ studentID + " has left the computer lab");

		}

	}

	// Simulate the student emailing the professor 
	// type A questions
	private void askTypeAQuestions() {
		int counter = 0;
		
		System.out.println("["+Main.currentTime()+"] " + studentName + " has " 
		+ typeAQuestions + " question(s) that he wants to email the professor about");
		
		while(typeAQuestions != 0) {
			
			try {
				
				System.out.println("["+Main.currentTime()+"] " + studentName 
						+ " is figuring out the question that they want to ask");
				
				// Releases the student from the CPU to allow the student to think about their question
				Thread.yield();
				
				// Increases the priority of the student because they need to send an email
				Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
				System.out.println("["+Main.currentTime()+"] " + studentName
						+ " is emailing the professor their question");
				
				// Simulate the student writing the email and sending it
				Thread.sleep((long) ((Math.random()*5000)+1000));
				
				// Send the emailed question to the professor
				Teacher.questionInbox.add(new Question(this,Main.currentTime()));
				
				System.out.println("["+Main.currentTime()+"] " + studentName 
						+ " has emailed the professor their question");
				
				// Set the priority back to it's default value
				Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
				
				typeAQuestions--;
				
			}catch(Exception e) {
				System.out.println("["+Main.currentTime()+"] " + studentName + " has " 
						+ typeAQuestions + " been interrupted!");
			}
		}
		
		System.out.println("["+Main.currentTime()+"] " + studentName 
				+ " emailed the questions that they needed to email");
		
	}
	

	// Simulating the student browsing the enter
	private void browseInternet() {
		// Use the sleep method and let the students browse the internet
		// until the online chat session has ended
		System.out.println("["+Main.currentTime()+"] " + studentName 
				+ " is browsing the internet");
		try {
			
			
			// for loop that will terminate all threads in decreasing order
			// based off of their ID
			for(int i = 0; i<Main.currentStudents.length; i++) {
				Student s = Main.currentStudents[i];
				if(s!=null) {
					if (s.isAlive() && s.getStudentID() > this.getStudentID()) {
						s.join();	
					}	
				}
			}
			
			// Simulate the students browsing the internet
			Thread.sleep(100000);
			
		} catch (InterruptedException e) {
			// Catch the interrupt exception because it will occur
			// when the student is interrupted
		}
	}
	
	/**
	 * ----------------------------------------
	 * Synchronized Getters and Setters Methods
	 * ----------------------------------------
	 */
	public void setStudentName() {
		studentName = "Student " + studentID;
	}
	
	public int getStudentID() {
		return studentID;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public synchronized int getTypeBQuestions() {
		return typeBQuestions;
	}
	
	public synchronized boolean didAskQuestion() {
		return askedQuestion;
	}
	
	public void setEnterLab(boolean enter) {
		enterLab = enter;
	}
	
	public synchronized boolean turnToEnterLab() {
		return enterLab;
	}

	public synchronized boolean isStudentInChat() {
		return inChat;
	}
	
	public synchronized void setStudentInChat(boolean c) {
		inChat = c;
	}
	
	public synchronized void askedQuestion(boolean asked) {
		askedQuestion = asked;
	}
	
	public synchronized void askedTypeBQuestion() {
		typeBQuestions--;
	}
}
