package schools;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class Municipality {
	private String nome;
	private String provincia;
	private Collection<Branch> sedi= new LinkedList<>();
	private Community comunita;
	public Municipality(String nome, String provincia, Community comunita) {
		super();
		this.nome = nome;
		this.provincia = provincia;
		this.comunita = comunita;
	}
	public String getName() {
		return nome;
	}
	public String getProvince() {
		return provincia;
	}
	public Collection<Branch> getBranches() {
		return sedi;
	}
	public void addSede(Branch s) {
		sedi.add(s);
	}
	public Optional<Community> getCommunity() {
		return Optional.ofNullable(comunita);
	}	
	
}
