import java.util.ArrayList;

public class Database {
	
	private static ArrayList<Client> clients = new ArrayList<Client>();
	
	public Database() {
		
	}
	
	public static void addClient(Client client) {
		clients.add(client);
	}
	
	public static Client getClient(Client client) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).equals(client));
				return clients.get(i);
		}
		return null;
	}
	
	public static Client findClient(String username, String password) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).checkLogin(username)) {
				if (clients.get(i).checkPassword(password))
					return clients.get(i);
			}
		}
		System.out.println("Client not found.");
		return null;
	}

	public static Client findClient(String username) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).checkLogin(username)) {
				return clients.get(i);
			}
		}
		System.out.println("Client not found.");
		return null;
	}
	
	public static Client findClient(String fname, String mname, String lname) {
		for (int i = 0; i < clients.size(); i++) {
			String tfname = clients.get(i).name().getfname();
			String tmname = clients.get(i).name().getmname();
			String tlname = clients.get(i).name().getlname();
			if (fname.equals(tfname) && mname.equals(tmname) && lname.equals(tlname)) {
				return clients.get(i);
			}
		}
		System.out.println("Client not found.");
		return null;
	}
	
	public static boolean checkUserPass(String username, String password) {
		for (int i = 0; i < clients.size(); i++) {
			System.out.println(clients.get(i).login());
			System.out.println(clients.get(i).password());
			if (clients.get(i).checkLogin(username)) {
				if (clients.get(i).checkPassword(password))
					return true;
			}
		}
		return false;
	}
	
	public static String getDailyReport(Date tdate) {
		String s = "";
		for (int i = 0; i < clients.size(); i++) {
			s += "Name: " + clients.get(i).name() + " | Username: " + clients.get(i).login();
			s += "<br/> " + clients.get(i).viewTransactions() + "<br>";
		}
		return s ;
	}

	public static void displayClients() {
		for (int i=0; i < clients.size(); i++) {
			System.out.println(clients.get(i));
		}
	}

	public static String getInDebt() {
		String res = "";
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).hasLoan())
				res+= clients.get(i).name().getfname() + clients.get(i).name().getmname() + clients.get(i).name().getlname() +
				" has taken out a loan.<br/>";
		}
		return res;
	}

}