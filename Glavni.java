package recnik;

public class Glavni {

		public static void main(String[] args) {
			String adresaRecnik = "jdbc:sqlite:C:\\Users\\Teona\\Downloads\\Dictionary.db";
			String adresaKnjiga = "C:\\Users\\Teona\\Downloads\\knjiga";

			Recnik r = new Recnik(adresaRecnik);
			r.popuniRecnik();
			Knjiga k = new Knjiga(adresaKnjiga);

			k.pronadjiNoveReci(r);

			System.out.println("Broj novih reci: " + k.getNoveReci().size());
			System.out.println("Broj svih reci: " + k.getSveReci().size());

			System.out.println("\n Top 20 reci: ");
			k.stampajTop20();

			System.out.println("\n Sve reci + broj ponavljanja: \n");
			k.stampajSve();

			r.prosiriBazu(k);

		}
	}