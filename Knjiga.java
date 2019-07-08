package recnik;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.TreeMap;

public class Knjiga {

	private static String adresa;
	private static TreeMap<String, Integer> sveReci;
	private TreeMap<String, Integer> noveReci;

	public Knjiga(String adr) {
		adresa = adr;
		sveReci = samoReci();
		noveReci = new TreeMap<String, Integer>();
	}

	public static TreeMap<String, Integer> samoReci() {
		TreeMap<String, Integer> listaReci = new TreeMap<String, Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(adresa));
			String linija = br.readLine();
			while (linija != null) {
				String[] reciLinija = linija.toLowerCase().split("\\W");
				for (int i = 0; i < reciLinija.length; i++) {
					if (reciLinija[i].equals("") || reciLinija[i].equals(" "))
						continue;
					else if (listaReci.containsKey(reciLinija[i])) {
						int x = listaReci.get(reciLinija[i]);
						listaReci.put(reciLinija[i], x + 1);
					} else
						listaReci.put(reciLinija[i], 1);
				}
				linija = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaReci;
	}

	public void pronadjiNoveReci(Recnik r) {
		for (String s : sveReci.keySet()) {
			if (r.getRecnik().containsKey(s))
				continue;
			else if (this.noveReci.containsKey(s)) {
				int br = noveReci.get(s);
				noveReci.put(s, br + 1);
			} else
				noveReci.put(s, 1);

		}
	}

	public TreeMap<Integer, String> sortirajReci() {
		TreeMap<Integer, String> sortirani = new TreeMap<Integer, String>(Collections.reverseOrder());

		for (String s : sveReci.keySet()) {
			sortirani.put(sveReci.get(s), s);
		}
		return sortirani;
	}

	public void stampajTop20() {
		TreeMap<Integer, String> top20 = this.sortirajReci();
		int i = 0;
		for (int s : top20.keySet()) {
			if (i == 20)
				break;
			else
				System.out.println("\"" + top20.get(s) + "\"  broj ponavljanja: " + s);
			i++;
		}
	}

	public void stampajSve() {
		TreeMap<Integer, String> svee = this.sortirajReci();
		for (int s : svee.keySet()) {
			System.out.println(svee.get(s) + " br.ponavljanja: " + s);
		}
	}

	public TreeMap<String, Integer> getNoveReci() {
		return noveReci;
	}

	public TreeMap<String, Integer> getSveReci() {
		return sveReci;
	}

}
