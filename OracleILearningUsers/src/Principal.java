import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.transform.stream.StreamSource;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
/**
 * 
 */
import org.apache.commons.cli.ParseException;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.Xslt30Transformer;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;

/**
 * @author Jorge Dueñas Lerín
 *
 */
public class Principal {

	public static void transformData(String stylesheetPath, String csvDataPath) throws SaxonApiException{
		Processor processor = new Processor(false);
        XsltCompiler compiler = processor.newXsltCompiler();
        XsltExecutable stylesheet = compiler.compile(new StreamSource(new File("styles/books.xsl")));
        Serializer out = processor.newSerializer(new File("books.html"));
        out.setOutputProperty(Serializer.Property.METHOD, "html");
        out.setOutputProperty(Serializer.Property.INDENT, "yes");
        Xslt30Transformer trans = stylesheet.load30();
        trans.transform(new StreamSource(new File("data/books.xml")), out);

        System.out.println("Output written to books.html");
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws SaxonApiException 
	 */
	public static void main(String[] args) throws ParseException, SaxonApiException{
		
		final Options options = new Options();
		options.addOption(new Option("d", "debug", false, "Turn on debug."));
		options.addOption(new Option("e", "extract", false, "Turn on extract."));
		options.addOption(new Option("o", "option", true, "Turn on option with argument."));
		
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse( options, args);
		
		transformData("data/plantilla.xml", "");
		
		
		/*
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
		*/
	}

}
