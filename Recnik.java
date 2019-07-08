package recnik;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Recnik {

	private static String adresa;
	private HashMap<String, String> recnik;

	public Recnik(String adr) {
		adresa = adr;
		recnik = new HashMap<String, String>();
	}

	public void popuniRecnik() {

		try (Connection con = DriverManager.getConnection(adresa)) {
			Statement stm = con.createStatement();
			String upit = "select word, definition from entries";
			ResultSet rezultat = stm.executeQuery(upit);
			while (rezultat.next()) {
				recnik.put(rezultat.getString("word").toLowerCase(), rezultat.getString("definition"));
			}

			rezultat.close();
			stm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void prosiriBazu(Knjiga k) {
		try (Connection con = DriverManager.getConnection(adresa)) {
			Statement stm = con.createStatement();
			StringBuilder sb = new StringBuilder();
			sb.append("DROP TABLE \"newWords\"; CREATE TABLE newWords (newWord varchar(50)); Insert INTO newWords (newWord) VALUES");
			for (String s : k.getNoveReci().keySet()) {
				sb.append("(\"").append(s).append("\")");
			}
			sb.append(";");
			stm.executeUpdate(sb.toString());

			stm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, String> getRecnik() {
		return recnik;
	}

}
