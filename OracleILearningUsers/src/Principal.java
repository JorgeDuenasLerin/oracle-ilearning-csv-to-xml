import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Jorge Dueñas Lerín
 *
 */
public class Principal {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String row;
		
		ArrayList<Persona> listado = new ArrayList<Persona>();
		
		BufferedReader csvReader = new BufferedReader(new FileReader("./data/ejemplo.csv"));
		csvReader.readLine(); // Leer línea de cabecera
		while ((row = csvReader.readLine()) != null) {
		    String[] data = row.split(";");
		    Persona p = new Persona(
		    					General.AÑO,
		    					data[0], // OU
		    					data[1], // DNI
		    					data[2], // Apellido
		    					data[3], // Nombre
		    					data[4]  // Correo
		    				);
		    listado.add(p);
		}
		csvReader.close();
		
		System.out.println(General.getFileContent(listado));
	}

}
