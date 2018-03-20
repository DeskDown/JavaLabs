package schools;

public class Branch {
	private int codice;
	private String tipoUnita;
	private String indirizzo;
	private int CAP;
	private String frazione;
	private String telefono;
	private String fax;
	private Municipality comune;
	private School scuola;
	public Branch(int codice, String indirizzo, int cap,
				  Municipality comune, School scuola) {
		super();
		this.codice = codice;
		this.tipoUnita = tipoUnita;
		this.indirizzo = indirizzo;
		CAP = cap;
		this.frazione = frazione;
		this.telefono = telefono;
		this.fax = fax;
		this.comune = comune;
		this.scuola = scuola;
	}
	public int getCode() {
		return codice;
	}
	public String getBranchType() {
		return tipoUnita;
	}
	public String getAddress() {
		return indirizzo;
	}
	public int getCAP() {
		return CAP;
	}
	public String getLocality() {
		return frazione;
	}
	public String getTelephone() {
		return telefono;
	}
	public String getFax() {
		return fax;
	}

	public Municipality getMunicipality(){
		return comune;
	}

	public School getSchool(){
		return scuola;
	}

}
