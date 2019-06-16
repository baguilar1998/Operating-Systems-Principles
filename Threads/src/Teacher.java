import java.util.*;
public class Teacher implements Runnable{
	public static Queue<Question> questionInbox = new LinkedList<Question>();
	public static Queue<Student> onlineChatQueue = new LinkedList<Student>();
	private Timer timer;
	private long officeHoursStart, onlineSessionStart;
	
	public Teacher() {
		officeHoursStart = (long) ((Math.random() *2000) + 2000);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		arriveToOffice();
		startOfficeHours();
	}

	/**
	 * Teacher can arrive to the office and answer
	 * some typeA questions before office hours start.
	 * 
	 * Students can send typeA questions and the teacher could have
	 * not arrived to his office yet
	 */
	public void arriveToOffice() {
		System.out.println("["+Main.currentTime()+"] "+"The professor has arrived to the office their office");
		if(questionInbox.size()!=0) {
			timer = new Timer();
			System.out.println("["+Main.currentTime()+"] "+ "The professor has " + questionInbox.size() + " emails in his inbox");
			/*// Allow the professor to answer some questions before his office hours start
			while(true) {
				System.out.println(questionInbox);
				if (officeHoursStart < Main.currentTime()) break;
			}*/
		}
	}
	
	/**
	 * As soon as the office hours start, the teacher
	 * has to stop answering typeA questions. It's okay
	 * if he can't answer all of them
	 */
	public void startOfficeHours() {
		System.out.println("["+Main.currentTime()+"] "+"The professor has started his office hours");
	}
	
	/**
	 * Once the online chat session starts, he will answer
	 * type B questions for the first student that arrived 
	 * to the chat session.
	 * 
	 */
	public void startOnlineChatSession() {
		
	}
	
	/**
	 * If there are no more students are in the online chat.
	 * End the online chat session and answer any remaining
	 * type A questions
	 */
	public void endOnlineChatSession() {
		
	}
	
	/**
	 * End the office hours
	 */
	public void endOfficeHours() {
		
	}
}
