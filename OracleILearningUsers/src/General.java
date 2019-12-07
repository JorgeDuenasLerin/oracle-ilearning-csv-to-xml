import java.util.ArrayList;

public class General {
	public static String CREAR_MODIFICAR = "2"; // Si existe actualiza
	public static String MANAGER = "jorge_duenas";
	public static String ESCUELA = "JDLC";
	
	public static String AÑO = "19_20";
	public static String CREATION_DATE = "2019-09-01 00:00:00";
	
	public static String [][] CURSOS = {
				{"Fundamentos de Java", "JFoS_esp", "Year X"},
				{"Fundamentos de bases de datos", "DFoS_esp", "Year R"}
			};
	
	public static int CURSO_NAME_INDEX = 2;
	public static int CURSO_VALUE_INDEX = 1;
	
	public static String getPassword(Persona persona) {
		return persona.getNombre().toLowerCase() + "cambiala";
	}
	
	public static String getFileContent(ArrayList<Persona> listado) {
		String xml = "<?xml version=\"1.0\" encoding=\"WINDOWS-1252\"?>\n" + 
				"\n" + 
				"<enterprise xmlns=\"http://www.imsglobal.org/xsd/imsep_rootv1p01\"\n" + 
				"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" + 
				"xsi:schemaLocation=\"http://www.imsglobal.org/xsd/imsep_rootv1p01 http://ilearning.oracle.com/ilearn/en/admin/dtd/imsep_rootv1p01.xsd\">\n" + 
				"	<properties>\n" + 
				"		<datasource>Development</datasource>\n" + 
				"		<datetime></datetime>\n" + 
				"	</properties>";
		
		for(Persona p : listado) {
			xml += p.getXMLLine();
		}
		
		xml += "</enterprise>";
		
		return xml;
	}
}
