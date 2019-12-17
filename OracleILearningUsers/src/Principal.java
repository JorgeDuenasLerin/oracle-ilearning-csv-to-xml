import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
/**
 * 
 */
import org.apache.commons.cli.ParseException;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.QName;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmAtomicValue;
import net.sf.saxon.s9api.XdmValue;
import net.sf.saxon.s9api.Xslt30Transformer;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;

/**
 * @author Jorge Dueñas Lerín
 *
 */
public class Principal {

	private static String TEMPLATE = "data/plantilla.xml";
	
	/***
	 * 
	 * @param csvDataPath
	 * @param anio
	 * @param df_pass
	 * @param tutor
	 * @throws SaxonApiException
	 */
	
	public static void transformData(
			String csvDataPath,
			String anio,
			String df_pass,
			String tutor,
			String extendido
			) throws SaxonApiException {
		
        Processor processor = new Processor(false);
        XsltCompiler compiler = processor.newXsltCompiler();
        XsltExecutable exp = compiler.compile(new StreamSource(
        							Principal.class.getResourceAsStream(TEMPLATE)
        									)
        								);
        
        Serializer out = processor.newSerializer(System.out);
        out.setOutputProperty(Serializer.Property.METHOD, "xml");
        out.setOutputProperty(Serializer.Property.INDENT, "yes");
        Xslt30Transformer trans = exp.load30();
        
        Map<QName, XdmValue> parameters = new HashMap<QName, XdmValue>();
        
        parameters.put(new QName("anio"), new XdmAtomicValue(anio));
        parameters.put(new QName("df_pass"), new XdmAtomicValue(df_pass));
        parameters.put(new QName("tutor"), new XdmAtomicValue(tutor));
        parameters.put(new QName("extendido"), new XdmAtomicValue(extendido));
        
        parameters.put(new QName("csv-uri"), new XdmAtomicValue(csvDataPath));
        
        
		trans.setInitialTemplateParameters(parameters, false);
		
        trans.callTemplate(new QName("main"), out);
	}
	
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws SaxonApiException 
	 */
	public static void main(String[] args) throws SaxonApiException{
		
		final Options options = new Options();
		
		options.addOption(
				Option.builder("d")
					.longOpt("datos")
					.argName("datos")
					.hasArg()
					.desc("Ruta al fichero de datos")
					.required()
					.build()
			);
		
		options.addOption(
				Option.builder("y")
					.longOpt("año")
					.hasArg()
					.argName("año")
					.desc("Prefijo para gestionar varios años de usuarios")
					.required()
					.build()
			);
		
		options.addOption(
				Option.builder("p")
					.longOpt("pass")
					.hasArg()
					.argName("pass")
					.desc("Contraseña por defecto")
					.required()
					.build()
			);
		
		// Para el JDLC: ies_juan_de_la_cierva
		options.addOption(
				Option.builder("t")
					.longOpt("tutor")
					.hasArg()
					.argName("tutor")
					.desc("Usuario tutor en plataforma\nPara el JDLC: ies_juan_de_la_cierva")
					.required()
					.build()
			);
		
		options.addOption(
				Option.builder("e")
					.longOpt("extendido")
					.hasArg()
					.argName("extendido")
					.desc("Todos los cursos! y profesor!!")
					.build()
			);
		
		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine cmd = parser.parse( options, args);
			String file = cmd.getOptionValue("d");
			String año = cmd.getOptionValue("y");
			String pass = cmd.getOptionValue("p");
			String tutor = cmd.getOptionValue("t");
			String extendido = cmd.getOptionValue("e");
			
			transformData(
					file,
					año,
					pass,
					tutor,
					extendido
				);
			
		} catch (ParseException pe) {
			System.out.println("Error en parámetros");
			System.out.println(pe.getMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp( " ", options );
		}
	}

}
