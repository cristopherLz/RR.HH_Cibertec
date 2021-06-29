package entidad;

import java.util.Date;

public class Autor {
	
	private int idAutor;
	
	private String nombre;
	
	private String apellido;
	
	private String fechaNaci;
	
	private Date fechaRegis;
	
	private String nacionalidad;
	
	private String grado;
	
	
	
	
	

	public int getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getFechaNaci() {
		return fechaNaci;
	}
	public void setFechaNaci(String fechaNaci) {
		this.fechaNaci = fechaNaci;
	}
	public Date getFechaRegis() {
		return fechaRegis;
	}
	public void setFechaRegis(Date fechaRegis) {
		this.fechaRegis = fechaRegis;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}



}