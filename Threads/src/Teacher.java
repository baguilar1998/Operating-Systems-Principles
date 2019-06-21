import java.util.*;
public class Teacher {
	
	public static Queue<Question> questionInbox = new LinkedList<Question>();
	public static Queue<Student> onlineChatQueue = new LinkedList<Student>();
	public static boolean officeHoursEnded, onlineChatSessionEnded, didAnswer;
	
	public Teacher() {}
	
	// Simulating the professor arriving to the office
	public synchronized void arriveToOffice() {
		System.out.println("["+Main.currentTime()+"] "
				+"The professor has arrived to the office their office");
	}
	

	// Simulating the professor answering type A questions
	public synchronized void answerTypeAQuestions() {
		// Only answer type A questions if he has any type A questions
		// in his inbox
		if(questionInbox.size() != 0) {
			
			System.out.println("["+Main.currentTime()+"] "+"The professor has " 
					+ questionInbox.size() + " questions in his inbox");
			
			Question q = questionInbox.remove();
			
			System.out.println("["+Main.currentTime()+"] "
					+"The professor is answering a question from " + q.toString());
			
			try {
				// Simulate the professor answering the question by using sleep()
				Thread.currentThread().sleep((long) (Math.random()*2000+1000));
				
				System.out.println("["+Main.currentTime()+"] "+"The professor has answered "
						+ q.getStudent().getStudentName() + " question via email");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	// Simulate the professor starting his office hours
	public synchronized void startOfficeHours() {
		System.out.println("["+Main.currentTime()+"] "
				+"The professor has started his office hours");
		officeHoursEnded = false;
	}
	
	// Simulate the professor starting the online chat session
	public synchronized void startOnlineChatSession() {
		System.out.println("["+Main.currentTime()+"] "
				+"The professor has started the online chat session");
		onlineChatSessionEnded = false;
	}
	
	// Simulate the professor chatting with the student in the
	// chat session
	public synchronized void chatWithStudent() {
		
		// Only chat with the student if there are students
		// waiting to chat with the teacher
		if(onlineChatQueue.size() != 0) {
			
			Student s = onlineChatQueue.remove();
			
			s.setStudentInChat(true);
			
			// Keep chatting with the student until they have no more
			// type B questions to ask
			while(s.getTypeBQuestions() != 0) {
				
				int currentBQuestions = s.getTypeBQuestions();
				
				setAnswer(false);
				
				//Busy wait until the student asks a question
				while(!s.didAskQuestion()) {} 
				
				System.out.println("["+Main.currentTime()+"] "
						+"The professor is answering " + s.getStudentName() + " question");
				try {
					
					// Simulate the professor answering the question by using sleep()
					Thread.currentThread().sleep(3000);
					
					System.out.println("["+Main.currentTime()+"] "
					+"The professor has answered " + s.getStudentName() + " question");
					
					setAnswer(true);
					
					// The professor busy waits until the student notifies him
					// how many questions he has left
					while(currentBQuestions==s.getTypeBQuestions()) {}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
		
	}
	

	// Simulate the professor ending the online chat session
	public synchronized void endOnlineChatSession() {
		System.out.println("["+Main.currentTime()+"] "
				+"The professor has ended the online chat session");
		onlineChatSessionEnded = true; 
	}
	

	// Simulate the professor ending the online office hours
	public synchronized void endOfficeHours() {
		officeHoursEnded = true;
		System.out.println("["+Main.currentTime()+"] "
				+"The professor has ended his office hours and has left the office");
		for(int i = Main.studentArray.length-1; i>=0; i--) {
			Thread t = Main.studentArray[i];
			if(t.isAlive()) {
				try {
					t.interrupt();
				}catch(Exception e) {
				
				}
				break;
			} else {
				continue;
			}
		}
		
	}
	
	/**
	 * ---------------------------
	 * Getters and Setters Methods
	 * ---------------------------
	 */
	public synchronized static boolean didTeacherAnswer() {
		return didAnswer;
	}
	
	public static synchronized void setAnswer(boolean answer) {
		didAnswer = answer;
	}
	
	public synchronized static boolean didChatSessionEnd() {
		return onlineChatSessionEnded;
	}

}
