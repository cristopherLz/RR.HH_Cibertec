package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import entidad.Alumno;
import model.AlumnoModel;
import util.Conversiones;
import util.Validaciones;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.List;
import java.awt.event.MouseEvent;

public class FrmCrudAlumno extends JInternalFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblCrudAlumno;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblDni;
	private JLabel lblCorreo;
	private JLabel lblFechaDeNacimiento;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtCorreo;
	private JTextField txtFechaNacimiento;
	private JButton btnRegistrar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblFechaDeRegistro;
	private JTextField txtFechaRegistro;
	
	private int idSeleccionado = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudAlumno frame = new FrmCrudAlumno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmCrudAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Actualiza Alumno");
		setBounds(100, 100, 767, 537);
		getContentPane().setLayout(null);
		
		lblCrudAlumno = new JLabel("CRUD ALUMNO");
		lblCrudAlumno.setIcon(new ImageIcon(FrmCrudAlumno.class.getResource("/imagenes/folder-directory-reload-refresh_108581.png")));
		lblCrudAlumno.setFont(new Font("Arial", Font.BOLD, 18));
		lblCrudAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrudAlumno.setBounds(0, 11, 606, 32);
		getContentPane().add(lblCrudAlumno);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(27, 55, 65, 23);
		getContentPane().add(lblNombre);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(27, 85, 65, 23);
		getContentPane().add(lblApellido);
		
		lblDni = new JLabel("DNI");
		lblDni.setBounds(27, 119, 65, 23);
		getContentPane().add(lblDni);
		
		lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(27, 153, 65, 23);
		getContentPane().add(lblCorreo);
		
		lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setBounds(27, 187, 120, 23);
		getContentPane().add(lblFechaDeNacimiento);
		
		lblFechaDeRegistro = new JLabel("Fecha de Registro");
		lblFechaDeRegistro.setBounds(27, 221, 120, 23);
		getContentPane().add(lblFechaDeRegistro);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(102, 54, 184, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(102, 86, 184, 20);
		getContentPane().add(txtApellido);
		txtApellido.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(102, 120, 96, 20);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(102, 154, 284, 20);
		getContentPane().add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setBounds(157, 188, 129, 20);
		getContentPane().add(txtFechaNacimiento);
		txtFechaNacimiento.setColumns(10);
		
		btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(this);
		
		txtFechaRegistro = new JTextField();
		txtFechaRegistro.setBounds(157, 222, 129, 20);
		getContentPane().add(txtFechaRegistro);
		txtFechaRegistro.setColumns(10);
		btnRegistrar.setIcon(new ImageIcon(FrmCrudAlumno.class.getResource("/imagenes/editar.png")));
		btnRegistrar.setBounds(520, 54, 129, 32);
		getContentPane().add(btnRegistrar);
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(this);
		btnEliminar.setIcon(new ImageIcon(FrmCrudAlumno.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
		btnEliminar.setBounds(520, 114, 129, 32);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setIcon(new ImageIcon(FrmCrudAlumno.class.getResource("/com/sun/javafx/scene/web/skin/Redo_16x16_JFX.png")));
		btnActualizar.setBounds(520, 182, 129, 32);
		getContentPane().add(btnActualizar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 271, 731, 225);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Apellido", "DNI", "Correo", "Fecha de Nacimiento", "Fecha de Registro "
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(95);
		table.getColumnModel().getColumn(2).setPreferredWidth(102);
		table.getColumnModel().getColumn(4).setPreferredWidth(187);
		table.getColumnModel().getColumn(5).setPreferredWidth(116);
		table.getColumnModel().getColumn(6).setPreferredWidth(112);
		
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);
		header.setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(new Color(173, 230, 196));
		table.setDefaultEditor(Object.class, null);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		scrollPane.setViewportView(table);
		
		lista();
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnActualizar) {
			btnActualizarActionPerformed(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			btnEliminarActionPerformed(arg0);
		}
		if (arg0.getSource() == btnRegistrar) {
			btnRegistrarActionPerformed(arg0);
		}
	}
	protected void btnRegistrarActionPerformed(ActionEvent arg0) {
		Inserta();
	}
	protected void btnEliminarActionPerformed(ActionEvent arg0) {
		Elimina();
	}
	protected void btnActualizarActionPerformed(ActionEvent arg0) {
		Actualiza();
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == table) {
			tableMouseClicked(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void tableMouseClicked(MouseEvent arg0) {
		Busca();
	}
	
	public void Busca(){
		 int fila = table.getSelectedRow();
		 
		 idSeleccionado = (Integer)table.getValueAt(fila, 0);
		 String nom = (String)table.getValueAt(fila, 1);
		 String ape = (String)table.getValueAt(fila, 2);
		 String dni = (String)table.getValueAt(fila, 3);
		 String co = (String)table.getValueAt(fila, 4);
		 Date fecn = (Date)table.getValueAt(fila, 5);
		 Date fecr = (Date)table.getValueAt(fila, 6);
		 
		 txtNombre.setText(nom);
		 txtApellido.setText(ape);
		 txtDni.setText(dni);
		 txtCorreo.setText(co);
		 txtFechaNacimiento.setText(String.valueOf(fecn));
		 txtFechaRegistro.setText(String.valueOf(fecr));
		 
	}
	
	
	public void lista() {
		AlumnoModel a = new AlumnoModel();
		List<Alumno> lista = a.listaAlumnoPorNombre(toString());
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		
		for (Alumno x : lista) {
			Object[] f = { x.getIdUsuario(), x.getNombre(), x.getApellido(), x.getDni(), x.getFechaNacimiento(), x.getFechaRegistro()
			};
			dtm.addRow(f);			
		}		
	}
	public void Inserta() {
		
		String nom = txtNombre.getText().trim();
		String ape = txtApellido.getText().trim();
		String dni = txtDni.getText().trim();
		String co = txtCorreo.getText().trim();
		String fecn = txtFechaNacimiento.getText().trim();
		String fecr = txtFechaRegistro.getText().trim();
		
		if(nom.matches(Validaciones.TEXTO)){
		mensaje("El nombre es de 2 a 20 caracteres");
		return;
		}
		
		if(ape.matches(Validaciones.TEXTO)){
			mensaje("El apellido es de 2 a 20 caracteres");
			return;
			}
		
		if(dni.matches(Validaciones.DNI)){
			mensaje("El DNI es de 1 a 8 caracteres");
			return;
			}
		
		if(co.matches(Validaciones.CORREO)){
			mensaje("Ingrese correo correctamente");
			return;
			}
		if(fecn.matches(Validaciones.FECHA)){
			mensaje("Ingrese fecha de nacimiento");
			return;
			}
		if(fecr.matches(Validaciones.FECHA)){
			mensaje("Ingrese fecha de registro");
			return;
			}
		
		
		Alumno obj = new Alumno();
		obj.setNombre(nom);
		obj.setApellido(ape);
		obj.setDni(Integer.parseInt(dni));
		obj.setFechaNacimiento(Conversiones.toFecha(fecn));
		obj.setFechaRegistro(Conversiones.toFecha(fecr));
		
		AlumnoModel a = new AlumnoModel();
		int s = a.insertaAlumno(obj);
		
		if (s > 0 ){
		mensaje("Se inserto correctamente"); 
		lista();
		LimpiarCajasTexto();
		}else {
			mensaje("Error al ingresar");
		}
		
		
		
	}
	private void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	
	private void LimpiarCajasTexto(){
		txtNombre.setText("");
		txtApellido.setText("");
		txtDni.setText("");
		txtCorreo.setText("");
		txtFechaNacimiento.setText("");
		txtFechaRegistro.setText("");
		txtNombre.requestFocus();
	}

	public void Actualiza(){
		
		if (idSeleccionado == -1) {
		mensaje("Seleccione una fila");
		}else {
			String nom = txtNombre.getText().trim();
			String ape = txtApellido.getText().trim();
			String dni = txtDni.getText().trim();
			String co = txtCorreo.getText().trim();
			String fecn = txtFechaNacimiento.getText().trim();
			String fecr = txtFechaRegistro.getText().trim();
			
			if(nom.matches(Validaciones.TEXTO)){
			mensaje("El nombre es de 2 a 20 caracteres");
			return;
			}
			
			if(ape.matches(Validaciones.TEXTO)){
				mensaje("El apellido es de 2 a 20 caracteres");
				return;
				}
			
			if(dni.matches(Validaciones.DNI)){
				mensaje("El DNI es de 1 a 8 caracteres");
				return;
				}
			
			if(co.matches(Validaciones.CORREO)){
				mensaje("Ingrese correo correctamente");
				return;
				}
			if(fecn.matches(Validaciones.FECHA)){
				mensaje("Ingrese fecha de nacimiento");
				return;
				}
			if(fecr.matches(Validaciones.FECHA)){
				mensaje("Ingrese fecha de registro");
				return;
				}
			
			
			Alumno obj = new Alumno();
			obj.setIdUsuario(idSeleccionado);
			obj.setNombre(nom);
			obj.setApellido(ape);
			obj.setDni(Integer.parseInt(dni));
			obj.setFechaNacimiento(Conversiones.toFecha(fecn));
			obj.setFechaRegistro(Conversiones.toFecha(fecr));
			
			AlumnoModel a = new AlumnoModel();
			int s = a.actualizaAlumno(obj);
			
			if (s > 0 ){
			mensaje("Se actualizó correctamente"); 
			lista();
			LimpiarCajasTexto();
			idSeleccionado = -1;
			}else {
				mensaje("Error al actualizar");
			}
			
			
		}
		
	}
	public void Elimina(){
		
		if (idSeleccionado == -1){
		mensaje("Seleccione una fila");
		}else {
			AlumnoModel a = new AlumnoModel();
			int s = a.eliminaAlumno(idSeleccionado);
			if(s>0){
				lista();
				mensaje("Se elimino correctamente");
			}else {
				mensaje("Error al eliminar");
				
			}
			
		}
	}
}
