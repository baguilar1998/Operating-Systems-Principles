
public class Timer implements Runnable{

	public static int arrivalTime, startOfficeHour, startChatSession, endChatSession, endOfficeHour;
	private Teacher teacher;
	
	public Timer(Teacher t) {
		teacher = t;
		arrivalTime = 4;
		startOfficeHour = 6;
		endChatSession = 10;
		endOfficeHour = 15;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int counter = 0;
		
		while(counter!=arrivalTime) {
			try {
				Thread.currentThread().sleep((long) (Math.random()*2000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter++;
		}
		
		teacher.arriveToOffice();
		
		while(startOfficeHour!=counter) {
			teacher.answerTypeAQuestions();
			try {
				Thread.currentThread().sleep((long) (Math.random()*2000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter++;
		}
		
		teacher.startOfficeHours();
		teacher.startOnlineChatSession();
		
		while(endChatSession != counter) {
			teacher.chatWithStudent();
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			++counter;
		}
		
		teacher.endOnlineChatSession();
		
		while(endOfficeHour != counter) {
			teacher.answerTypeAQuestions();
			try {
				Thread.currentThread().sleep((long) (Math.random()*2000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter++;
		}
		
		teacher.endOfficeHours();
		
		// Could start the chat session at any point or not
		// Lets assume he starts it right away
	}

}
