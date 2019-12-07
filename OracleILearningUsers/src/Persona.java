/**
 * @author Jorge Dueñas Lerín
 *
 */
public class Persona {
	private String año;
	private String unidadOrganizativa;
	private String DNI;
	private String apellidos;
	private String nombre;
	private String correo;
	
	public Persona(
				String año,
				String unidadOrganizativa,
				String DNI,
				String apellidos,
				String nombre,
				String correo
			) {
		this.año = año;
		this.unidadOrganizativa = unidadOrganizativa;
		this.DNI = DNI;
		this.apellidos = apellidos;
		this.nombre = nombre;
		this.correo = correo;
	}
	
	public String getUsuario() {
		return getAño() + "_" + getUnidadOrganizativa() + "_" + getCorreo();
	}
	
	/**
	 * @return the unidadOrganizativa
	 */
	public String getUnidadOrganizativa() {
		return unidadOrganizativa;
	}
	/**
	 * @param unidadOrganizativa the unidadOrganizativa to set
	 */
	public void setUnidadOrganizativa(String unidadOrganizativa) {
		this.unidadOrganizativa = unidadOrganizativa;
	}
	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * @return the dNI
	 */
	public String getDNI() {
		return DNI;
	}
	/**
	 * @param dNI the dNI to set
	 */
	public void setDNI(String dNI) {
		DNI = dNI;
	}

	/**
	 * @return the año
	 */
	public String getAño() {
		return año;
	}

	/**
	 * @param año the año to set
	 */
	public void setAño(String año) {
		this.año = año;
	}
	
	public String getNombreCompleto() {
		return getApellidos() + " " + getNombre();
	}
	
	public String getXMLLine() {
		return "<person recstatus=\"" + General.CREAR_MODIFICAR + "\">" +
				"<sourcedid><source></source><id></id></sourcedid>" +
					"<userid>" + getUsuario() + "</userid>" +
					"<name><fn>" + getNombreCompleto() + "</fn></name>" + 
					"<email>" + getCorreo() + "</email>" + 
					"<extension><Account_Password>" + General.getPassword(this) + "</Account_Password>" +
					"<StartDate>" + General.CREATION_DATE + "</StartDate>" + 
					"<managerUsername>" + General.MANAGER + "</managerUsername>" +
					"<allowUpdate>Y</allowUpdate>" + 
					getXMLCursos() + 
					"</extension></person>"
				;
	}
	
	public String getXMLCursos() {
		String cursosXML = "";
		for(String [] curso: General.CURSOS) {
			cursosXML += "<CusAttr name=\"Job_Role\" value=\"Student\"/>";
			cursosXML += "<CusAttr name=\"School_Name\" value=\"" + General.ESCUELA + "\"/>";
			cursosXML += "<CusAttr name=\"" +
							curso[General.CURSO_NAME_INDEX] + "\" value =\"" +
							curso[General.CURSO_VALUE_INDEX] + "\"/>";
			
		}
		return cursosXML;
	}
}
