import java.util.*;
public class Teacher {
	
	public static Queue<Question> questionInbox = new LinkedList<Question>();
	public static Queue<Student> onlineChatQueue = new LinkedList<Student>();
	private long officeHoursStart, onlineSessionStart;
	public static boolean officeHoursEnded, onlineChatSessionEnded, didAnswer;
	
	public Teacher() {
		officeHoursStart = (long) ((Math.random() *2000) + 2000);
		
	}
	
	public synchronized static boolean didTeacherAnswer() {
		return didAnswer;
	}
	
	public static synchronized void setAnswer(boolean answer) {
		didAnswer = answer;
	}

	public synchronized void arriveToOffice() {
		System.out.println("["+Main.currentTime()+"] "
				+"The professor has arrived to the office their office");
			
	}
	
	public synchronized static boolean didChatSessionEnd() {
		return onlineChatSessionEnded;
	}
	
	public synchronized void answerTypeAQuestions() {
		
		if(questionInbox.size() != 0) {
			
			System.out.println("["+Main.currentTime()+"] "+"The professor has " 
					+ questionInbox.size() + " questions in his inbox");
			
			Question q = questionInbox.remove();
			
			System.out.println("["+Main.currentTime()+"] "
					+"The professor is answering a question from " + q.toString());
			
			try {
				
				Thread.currentThread().sleep((long) (Math.random()*2000+1000));
				
				questionInbox.remove();
				
				System.out.println("["+Main.currentTime()+"] "+"The professor has answered "
						+ q.getStudent().getStudentName() + " question via email");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	public synchronized void startOfficeHours() {
		System.out.println("["+Main.currentTime()+"] "
				+"The professor has started his office hours");
		officeHoursEnded = false;
	}
	
	public synchronized void startOnlineChatSession() {
		System.out.println("["+Main.currentTime()+"] "
				+"The professor has started the online chat session");
		onlineChatSessionEnded = false;
	}
	
	public synchronized void chatWithStudent() {
		
		if(onlineChatQueue.size() != 0) {
			
			Student s = onlineChatQueue.remove();
			
			s.setStudentInChat(true);
			
			while(s.getTypeBQuestions() != 0) {
				
				int currentBQuestions = s.getTypeBQuestions();
				
				setAnswer(false);
				
				while(!s.didAskQuestion()) {} //busy waits
				
				System.out.println("["+Main.currentTime()+"] "
						+"The professor is answering " + s.getStudentName() + " question");
				try {
					
					Thread.currentThread().sleep(3000);
					
					System.out.println("["+Main.currentTime()+"] "
					+"The professor has answered " + s.getStudentName() + " question");
					
					setAnswer(true);
					
					while(currentBQuestions==s.getTypeBQuestions()) {}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
	}
	

	public synchronized void endOnlineChatSession() {
		System.out.println("["+Main.currentTime()+"] "
				+"The professor has ended the online chat session");
		onlineChatSessionEnded = true;
	}
	
	/**
	 * End the office hours
	 */
	public synchronized void endOfficeHours() {
		officeHoursEnded = true;
		/*try {
			Thread.currentThread().sleep(30000);
			officeHoursEnded = true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
