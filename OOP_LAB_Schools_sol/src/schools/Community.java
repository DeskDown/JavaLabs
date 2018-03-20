package schools;

import java.util.Collection;
import java.util.LinkedList;

public class Community {
	private String nome;
	private Type tipo;
	private Collection<Municipality> comuni = new LinkedList<>();
	
	public enum Type { MONTANA, COLLINARE };
	
	public Community(String nome, Type tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}
	public String getName() {
		return nome;
	}
	
	public Type getType(){
		return tipo;
	}

	public Collection<Municipality> getMunicipalities() {
		return comuni;
	}
	
	void add(Municipality e) {
		comuni.add(e);
	}
	
}
