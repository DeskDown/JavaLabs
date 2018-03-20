package it.polito.po.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import schools.Municipality;
import schools.Community;
import schools.Region;
import schools.School;
import junit.framework.TestCase;

public class TestR3_ReadData extends TestCase {
	
	final static String url;
	static {
		//System.out.println(TestR3_ReadData.class.getResource("."));
		URL resource = TestR3_ReadData.class.getResource("RegionePiemonte.csv");
		if(System.getProperty("os.name").toLowerCase().startsWith("window")){
			File data=new File("data");
			data.mkdirs();
			data.deleteOnExit();
			File outFile = new File(data,"Dati.csv");
			outFile.deleteOnExit();
			System.out.println("Extracting data file...");
			try(InputStream in = resource.openStream();
				FileOutputStream out=new FileOutputStream(outFile)){
				byte[]b = new byte[2048];
				int n=0;
				while((n=in.read(b))!=-1){
					out.write(b,0,n);
				}
			} catch (IOException e) {
				System.err.println(e);
				outFile=null;
			}
			url = (outFile==null?null:outFile.toURI().toString());
		}else{
			url = resource.toString();
		}
	}
	
	Region r ;
	public void setUp() throws IOException{
		r = new Region("Piemonte");
	}
	
	
	public void testCommunity() throws IOException{
		r.readData(url);
		
		Collection<Community> communities = r.getCommunities();
		
		assertEquals("Wrong number of communities found",
					76,communities.size());
		Map<Community.Type,Long> counts = communities.stream().collect(Collectors.groupingBy(Community::getType,Collectors.counting()));
		assertEquals("Wrong numner of hill communities",28,counts.get(Community.Type.COLLINARE).longValue());
		assertEquals("Wrong numner of mountain communities",48,counts.get(Community.Type.MONTANA).longValue());
	}

	public void testMuniciaplities() throws IOException{
		r.readData(url);
		
		Collection<Municipality> municipalities = r.getMunicipalies();
		
		assertEquals("Wrong numeber of municipalities",
					886,municipalities.size());
		long numProvinces = municipalities.stream().map(Municipality::getProvince).distinct().count();
		assertEquals("Wrong number of provinces",8,numProvinces);
	}

	public void testSchools() throws IOException{
		r.readData(url);
		
		Collection<School> schools = r.getSchools();
		
		assertEquals("Wrong number of schools",
					4057,schools.size());
		assertEquals("Wrong number of branches",
					4377,schools.stream().flatMap(s->s.getBranches().stream()).count());
		Map<Integer,Long> gradesCount = schools.stream().collect(Collectors.groupingBy(School::getGrade,Collectors.counting()));
		int[] grades = {1,2,3,4};
		long[] gradeNo = {1654,1414,543,446};
		for(int g : grades){
			assertEquals("The number of school with grade " + g+ " is wrong",
						gradeNo[g-1],gradesCount.get(g).longValue());
		}
	}

}
