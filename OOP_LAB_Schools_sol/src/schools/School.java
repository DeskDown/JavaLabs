package schools;

import java.util.Collection;
import java.util.LinkedList;

public class School {
	private String denominazione;
	private String codice;
	private int grado;
	private String descrizione;
	private Collection<Branch> sedi= new LinkedList<>();

	public School(String denominazione, String codice, int grado,
			String descrizione) {
		this.denominazione = denominazione;
		this.codice = codice;
		this.grado = grado;
		this.descrizione = descrizione;
	}

	public String getName() {
		return denominazione;
	}

	public String getCode() {
		return codice;
	}

	public int getGrade() {
		return grado;
	}

	public String getDescription() {
		return descrizione;
	}

	public Collection<Branch> getBranches() {
		return sedi;
	}

	public void addSede(Branch s) {
		sedi.add(s);
	}

}
