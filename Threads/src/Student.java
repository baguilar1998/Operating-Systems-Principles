import java.util.*;
public class Student implements Runnable{

	private int typeAQuestions, typeBQuestions, studentID; 
	private String studentName;
	private String [] questions;
	boolean inChat, askedQuestion, isLastQuestion;
	
	public Student(int a, int b, int id) {
		typeAQuestions = a;
		typeBQuestions = b;
		questions = new String[typeAQuestions];
		studentID = id;
		inChat = false;
		isLastQuestion = false;
		setStudentName();
	}
	
	public synchronized void setStudentName() {
		studentName = "Student " + studentID;
	}
	
	public synchronized String getStudentName() {
		return studentName;
	}
	
	public synchronized int getTypeBQuestions() {
		return typeBQuestions;
	}
	
	public synchronized boolean didAskQuestion() {
		return askedQuestion;
	}
	
	public synchronized void setLastQuestion(boolean s) {
		isLastQuestion = s;
	}
	
	public synchronized boolean lastQuestion() {
		return isLastQuestion;
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

	@Override
	public void run() {
		
		System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
				+ " is waiting to enter the lab");

		while(!Main.isLabOpen) {} // busy wait while the lab is not open

		
		if(Main.computerLab.size() == Main.capacity) {
			System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
					+ " cannot enter the lab because it is now full FALSE");
		} else {
			Main.computerLab.add(Thread.currentThread());
			
			System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
					+ " has now entered the lab TRUE");
			
			if(typeAQuestions != 0 && !Teacher.officeHoursEnded) {
				askTypeAQuestions();	
			}
			
			if(typeBQuestions != 0 && !Teacher.officeHoursEnded) {
				
				Teacher.onlineChatQueue.add(this);
				// Busy wait either if the student is waiting to chat for the teacher
				// or if the student did not finish chatting with the teacher
				System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
						+ " is waiting to chat with the professor " +typeBQuestions);
				

				while(!isStudentInChat() && !Teacher.didChatSessionEnd()) {}
				
				if(!Teacher.onlineChatSessionEnded) {
					System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
							+ " has entered the chat");
					while(getTypeBQuestions()!=0) {
						
						//Check if the online chat session is still going
						System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
								+ " has asked a question to the professor in the chat");
						askedQuestion(true);
						while(!Teacher.didTeacherAnswer()) {}
						askedTypeBQuestion();
						askedQuestion(false);
						Teacher.setAnswer(false);
						//System.out.println("THIS STUDENT HAS " + typeBQuestions);
						
					}	
					System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
							+ " has left the chat session");
					//Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
				} else {
					//Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
					System.out.println("["+Main.currentTime()+"]"+" Student " + studentID 
							+ " could not chat with the teacher cause the chat session hours had ended");
				}
			}
					
			// Check if the online office hours are still up
			if (!Teacher.officeHoursEnded) {
				browseInternet();
			}
			
			System.out.println("["+Main.currentTime()+"]"+" Student " 
			+ studentID + " has left the computer lab HE HAS LEFT");
			
			Main.computerLab.remove(this);
		}



	}

	private synchronized void askTypeAQuestions() {
		int counter = 0;
		
		System.out.println("["+Main.currentTime()+"] " + studentName + " has " 
		+ typeAQuestions + " question(s) that he wants to email the professor about");
		
		while(typeAQuestions != 0) {
			
			try {
				
				System.out.println("["+Main.currentTime()+"] " + studentName 
						+ " is figuring out the question that they want to ask");
				
				// Releases the student from the CPU to allow the student to think about their question
				Thread.currentThread().yield();
				
				// Incrreases the priority of the student because they need to send an email
				Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
				System.out.println("["+Main.currentTime()+"] " + studentName
						+ " is emailing the professor their question");
				
				// Simulate the student writing the email and sending it
				Thread.currentThread().sleep((long) ((Math.random()*5000)+1000));
				Teacher.questionInbox.add(new Question(this,Main.currentTime()));
				
				System.out.println("["+Main.currentTime()+"] " + studentName 
						+ " has emailed the professor their question");
				
				Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
				
				typeAQuestions--;
				
			}catch(Exception e) {
				System.out.println("["+Main.currentTime()+"] " + studentName + " has " 
						+ typeAQuestions + " been interrupted!");
			}
		}
		
		System.out.println("["+Main.currentTime()+"] " + studentName 
				+ " emailed the questions that they needed to email");
		
	}//askTypeAQuestions
	

	private synchronized void browseInternet() {
		// Use the sleep method and let the students browse the internet
		// until the online chat session has ended
		System.out.println("["+Main.currentTime()+"] " + studentName 
				+ " is browsing the internet");
		//while(!Teacher.officeHoursEnded) {}
		/*try {
			for(Thread s: Main.computerLab) {
				if (s.isAlive() && s.getId() > Thread.currentThread().getId()) {
					s.join();		
				}
			}
			while(!Teacher.officeHoursEnded) {}
			Thread.currentThread().interrupt();
			//if(Thread.currentThread().isInterrupted())
		} catch (Exception e) {
			
		}*/
	}//browseInternet
}
