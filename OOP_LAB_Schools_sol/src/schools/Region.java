package schools;

import it.polito.utility.LineUtils;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;


public class Region {
	private String nome;
	private Collection<School> scuole;
	private Collection<Community> comunita;
	private Collection<Municipality> comuni;
	
	public Region(String nome){
		this.nome = nome;
		scuole=new LinkedList<>();
		comunita=new LinkedList<>();
		comuni=new LinkedList<>();
	}
	
	public String getName(){return nome;}
	
	public Collection<School> getSchools() {
		return scuole;
	}
	
	public Collection<Community> getCommunities() {
		return comunita;
	}
	
	public Collection<Municipality> getMunicipalies() {
		return comuni;
	}
	
	
	// factory methods
	
	public Community newCommunity(String name, Community.Type type){
		Community c = new Community(name, type);
		comunita.add(c);
		return c;
	}

	public Municipality newMunicipality(String nome, String provincia){
		return newMunicipality( nome,  provincia,  null);
	}
	public Municipality newMunicipality(String nome, String provincia, Community comunita){
		Municipality c = new Municipality(nome, provincia, comunita);
		comuni.add(c);
		if(comunita!=null){
			comunita.add(c);
		}
		
		return c;
	}
	
	public School newSchool(String name, String code, int grade,
			String description){
		School s = new School(name, code, grade, description);
		scuole.add(s);
		
		return s;
	}
	
	public Branch newBranch(int regionalCode, Municipality municipality, String indirizzo, int cap,
							School school)	{
		Branch s = new Branch(regionalCode, indirizzo, cap, municipality, school);
		school.addSede(s);
		municipality.addSede(s);
		
		return s;
	}
	
	public void readData(String url) throws IOException {
		// Hint: use LineUtils.loadLinesUrl(url) to load the CSV lines from a URL

		/**
			Provincia,
			Comune,
			Grado Scolastico,
			Descrizione Scuola,
			Cod Sede,
			Cod Scuola,
			Denominazione Scuola,
			Indirizzo e n. civico,
			C.A.P.,
			Comunita Collinare,
			Comunita Montana
		**/
		List<String> lines = LineUtils.loadLinesUrl(url);
		
		if(lines==null) return; 
		
		// extract headers and build the relative map 
		String[] headers = lines.remove(0).split(",");
		Map<String,Integer> h2i = new HashMap<>();
		for(int i=0; i<headers.length; ++i){
			h2i.put(headers[i], i);
		}
		lines
		.forEach( r -> {
			String[] row = r.split(",");
			Community comunita = getComunita(h2i,row);
			Municipality comune = comuni.stream()
					 .filter( c -> row[h2i.get("Comune")].equals(c.getName()))
					 .findFirst()
					 .orElseGet(() -> newMunicipality(row[h2i.get("Comune")],
							 						 row[h2i.get("Provincia")],
							 						 comunita
							 ))
					 ;
			School scuola = scuole.stream()
							.filter( s -> s.getCode().equals(row[h2i.get("Cod Scuola")] ))
							.findFirst()
							.orElseGet( () -> newSchool(row[h2i.get("Denominazione Scuola")],
							                            row[h2i.get("Cod Scuola")],
							                            Integer.parseInt(row[h2i.get("Grado Scolastico")].replaceAll(" *-.*$","")),
											  		    row[h2i.get("Descrizione Scuola")]
									))
							;
			/*Sede sede =*/ newBranch(Integer.parseInt(row[h2i.get("Cod Sede")]),
								  comune,
								  row[h2i.get("Indirizzo e n. civico")],
								  Integer.parseInt(row[h2i.get("C.A.P.")]),
								  scuola
					    );
		});
	}

	private Community getComunita(Map<String,Integer> h2i,String[] row){
		int cci = h2i.get("Comunita Collinare");
		String collinare = (cci>=row.length?"":row[cci]);
		int cmi = h2i.get("Comunita Montana");
		String montana = (cmi>=row.length?"":row[cmi]);
		if(collinare.length()!=0){
			return comunita.stream()
					.filter( c -> c.getName().equals(collinare))
					.findFirst()
					.orElseGet(() -> newCommunity(collinare, Community.Type.COLLINARE) );
		}else
		if(montana.length()!=0){
				return comunita.stream()
						.filter( c -> c.getName().equals(montana))
						.findFirst()
						.orElseGet(() -> newCommunity(montana, Community.Type.MONTANA) );
		}else
			return null;
	}
	
	/**
	 * restituisce una mappa 
	 * contenente come chiave la descrizione e come valore il numero
	 * di scuole corrispondentin a tale descrizione. 
	 * 
	 */
	public Map<String,Long>countSchoolsPerDescription(){
		return scuole.stream()
				.collect(groupingBy( s -> s.getDescription(),counting()))
				;
	}

	
	/**
	 * restituisce una mappa contenente come chiave il 
	 * nome del comune e come valore il numero di sedi 
	 * presenti nel comune.
	 *  
	 * @return
	 */
	public Map<String,Long>countBranchesPerMunicipality(){
		return scuole.stream()
				.map(School::getBranches)
				.flatMap(Collection::stream)
				.collect(groupingBy( s->s.getMunicipality().getName(),counting()))
				;
	}

	
	/**
	 * restituisce una mappa contenente come chiave il nome della 
	 * provincia e come valore la media del numero di sedi per comune.
	 * @return
	 */
	public Map<String,Double>averageBranchesPerMunicipality(){
		return scuole.stream()
				.map(School::getBranches)
				.flatMap(Collection::stream)
				.collect(groupingBy( s->s.getMunicipality().getProvince(), 
						collectingAndThen(groupingBy( s->s.getMunicipality().getName(),counting()),
										   g -> g.values().stream()
										   		.collect(averagingLong(Long::longValue))
										   )
						))
				
				;
	}

	
	/**
	 * restituisce una lista
	 * di stringhe ciascuna nel formato "<i>### - XXXXXX</i>" dove 
	 * <i>###</i> rappresenta il numero di scuole (!non sedi!)
	 * e <i>XXXXXX</i> rappresenta il nome del comune.
	 * Se una scuola ha pi&ugrave; sedi nello stesso comune deve
	 * essere contata una sola volta.
	 * 
	 */
	public Collection<String> countSchoolsPerMunicipality(){
		
//		return scuole.stream()
//				.map(Scuola::getSedi)
//				.flatMap(Collection::stream)
//				.collect(groupingBy( s->s.getComune().getNome(),
//									 mapping(Sede::getScuola,collectingAndThen(toSet(), Set::size))
//									)
//						)
//				.entrySet().stream()
//				.map( e -> e.getValue()+ " - "+e.getKey())
//				.collect(toList())
//				;
		return comuni.stream()
				.collect(TreeMap::new,
						 (a,c) -> a.put(c.getName(),c.getBranches().stream().map(Branch::getSchool).collect(toSet()).size()),
						 (a1,a2) -> a1.putAll(a2)
						)
				.entrySet().stream()
				.map( e -> e.getValue()+ " - "+e.getKey())
				.collect(toList())
				;
	}
	
	/**
	 * restituisce una lista
	 * di stringhe ciascune nel formato "<i>### - XXXXXX</i>" dove
	 * <i>###</i> rappresenta il numero di scuole (!non sedi!)
	 * e <i>XXXXXX</i> rappresenta il nome della comunita.
	 * ordinate in maniera decrescente per numero di scuole.
	 * L'elenco deve contenere solo le scuole che hanno almeno
	 * una sede in un comune sede di comunita montana.
	 * 
	 */
	public List<String> countSchoolsPerCommunity(){
		return scuole.stream()
				.map(School::getBranches)
				.flatMap(Collection::stream)
				.filter( s->s.getMunicipality().getCommunity().isPresent() )
				.collect(groupingBy( s->s.getMunicipality().getCommunity().get().getName(),
									 mapping(Branch::getSchool,collectingAndThen(toSet(), Set::size))
									)
						)
				.entrySet().stream()
				.sorted(comparing(Map.Entry::getValue,reverseOrder()))
				.map( e -> e.getValue()+ " - "+e.getKey())
				.collect(toList())
				;
	}

	
}
