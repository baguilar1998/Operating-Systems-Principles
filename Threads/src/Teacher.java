import java.util.*;
public class Teacher {
	
	public static Queue<Question> questionInbox = new LinkedList<Question>();
	public static Queue<Student> onlineChatQueue = new LinkedList<Student>();
	private long officeHoursStart, onlineSessionStart;
	public static boolean officeHoursEnded, onlineChatSessionEnded;
	
	public Teacher() {
		officeHoursStart = (long) ((Math.random() *2000) + 2000);
		
	}
	

	public synchronized void arriveToOffice() {
		System.out.println("["+Main.currentTime()+"] "+"The professor has arrived to the office their office");
			
	}
	
	public synchronized void answerTypeAQuestions() {
		if(questionInbox.size() != 0) {
			System.out.println("["+Main.currentTime()+"] "+"The professor has " + questionInbox.size() + " questions in his inbox");
			Question q = questionInbox.remove();
			System.out.println("["+Main.currentTime()+"] "+"The professor is answering question from " +
			q.toString());
			try {
				Thread.currentThread().sleep((long) (Math.random()*2000+1000));
				questionInbox.remove();
				System.out.println("["+Main.currentTime()+"] "+"The professor has answered " + q.getStudent().getStudentName() + " question");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * As soon as the office hours start, the teacher
	 * has to stop answering typeA questions. It's okay
	 * if he can't answer all of them
	 */
	public synchronized void startOfficeHours() {
		System.out.println("["+Main.currentTime()+"] "+"The professor has started his office hours");
		officeHoursEnded = false;
	}
	
	/**
	 * Once the online chat session starts, he will answer
	 * type B questions for the first student that arrived 
	 * to the chat session.
	 * 
	 */
	public synchronized void startOnlineChatSession() {
		System.out.println("["+Main.currentTime()+"] "+"The professor has started the online chat session");
	}
	
	public synchronized void chatWithStudent() {
		
	}
	
	/**
	 * If there are no more students are in the online chat.
	 * End the online chat session and answer any remaining
	 * type A questions
	 */
	public synchronized void endOnlineChatSession() {
		
	}
	
	/**
	 * End the office hours
	 */
	public synchronized void endOfficeHours() {
		try {
			Thread.currentThread().sleep(30000);
			officeHoursEnded = true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
