package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import entidad.Autor;
import model.AutorModel;
import util.Conversiones;
import util.Validaciones;
import javax.swing.ImageIcon;

public class FrmCrudAutor extends JInternalFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtFechaNac;
	private JTextField txtFechaReg;
	private JTextField txtNaciona;
	private JTextField txtGrado;
	private JTable table;
	private JButton btnRegistro;
	private JButton btnEliminar;
	private JButton btnActualizar;

	// No se ha seleccionado nada
	int idSeleccionado = 0;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudAutor frame = new FrmCrudAutor();
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
	public FrmCrudAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Actualiza Autor");
		setBounds(100, 100, 949, 678);
		getContentPane().setLayout(null);

		JLabel lblMantenimientoAutor = new JLabel("Mantenimiento Autor");
		lblMantenimientoAutor.setIcon(new ImageIcon("C:\\Users\\abiga\\git\\lp1_2021_1_grupo_05_jueves-aldave\\src\\imagenes\\libro (1).png"));
		lblMantenimientoAutor.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblMantenimientoAutor.setBounds(258, 11, 397, 86);
		getContentPane().add(lblMantenimientoAutor);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(270, 110, 147, 14);
		getContentPane().add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApellido.setBounds(270, 147, 147, 14);
		getContentPane().add(lblApellido);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechaDeNacimiento.setBounds(270, 182, 147, 14);
		getContentPane().add(lblFechaDeNacimiento);

		JLabel lblFechaDeRegistro = new JLabel("Fecha de Registro");
		lblFechaDeRegistro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechaDeRegistro.setBounds(270, 217, 147, 14);
		getContentPane().add(lblFechaDeRegistro);

		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNacionalidad.setBounds(270, 253, 147, 14);
		getContentPane().add(lblNacionalidad);

		JLabel lblGrado = new JLabel("Grado");
		lblGrado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGrado.setBounds(270, 285, 147, 14);
		getContentPane().add(lblGrado);

		txtNombre = new JTextField();
		txtNombre.setBounds(399, 108, 203, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(399, 145, 203, 20);
		getContentPane().add(txtApellido);

		txtFechaNac = new JTextField();
		txtFechaNac.setColumns(10);
		txtFechaNac.setBounds(399, 180, 203, 20);
		getContentPane().add(txtFechaNac);

		txtFechaReg = new JTextField();
		txtFechaReg.setColumns(10);
		txtFechaReg.setBounds(399, 215, 203, 20);
		getContentPane().add(txtFechaReg);

		txtNaciona = new JTextField();
		txtNaciona.setColumns(10);
		txtNaciona.setBounds(399, 251, 203, 20);
		getContentPane().add(txtNaciona);

		txtGrado = new JTextField();
		txtGrado.setColumns(10);
		txtGrado.setBounds(399, 283, 203, 20);
		getContentPane().add(txtGrado);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 388, 821, 249);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "IdAutor", "Nombre", "Apellido",
				"FechaNacimiento", "FechaRegistro", "Nacionalidad", "Grado" }));
		table.getColumnModel().getColumn(3).setPreferredWidth(99);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		scrollPane.setViewportView(table);

		btnRegistro = new JButton("Registro");
		btnRegistro.setIcon(new ImageIcon("C:\\Users\\abiga\\git\\lp1_2021_1_grupo_05_jueves-aldave\\src\\imagenes\\anadir (1).png"));
		btnRegistro.addActionListener(this);
		btnRegistro.setBounds(144, 326, 122, 34);
		getContentPane().add(btnRegistro);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon("C:\\Users\\abiga\\git\\lp1_2021_1_grupo_05_jueves-aldave\\src\\imagenes\\cerrar.png"));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(386, 326, 122, 34);
		getContentPane().add(btnEliminar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon("C:\\Users\\abiga\\git\\lp1_2021_1_grupo_05_jueves-aldave\\src\\imagenes\\actualizado.png"));
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(615, 326, 122, 34);
		getContentPane().add(btnActualizar);

		table = new JTable();
		 table.addMouseListener(this);
		 table.setModel(new DefaultTableModel(
		 new Object[][] {
		 },
		 new String[] {
		 "idAutor", "Nombre", "Apellido", "Fecha de Nacimiento", "Fecha de Registro", "Nacionalidad","Grado"
		 }
		 ));
		 JTableHeader header = table.getTableHeader();
		 header.setReorderingAllowed(false);
		 header.setResizingAllowed(false);
		     
		 table.getColumnModel().getColumn(0).setPreferredWidth(5);
		 table.getColumnModel().getColumn(1).setPreferredWidth(10);
		 table.getColumnModel().getColumn(2).setPreferredWidth(10);
		 table.getColumnModel().getColumn(3).setPreferredWidth(80);
		 table.getColumnModel().getColumn(4).setPreferredWidth(80);
		 table.getColumnModel().getColumn(5).setPreferredWidth(30);
		 
		 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		 centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		 table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		 
		 table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 table.setSelectionBackground(new Color(173, 230, 196));
		 scrollPane.setViewportView(table);
		 
		 label = new JLabel("");
		 label.setIcon(new ImageIcon("C:\\Users\\abiga\\git\\lp1_2021_1_grupo_05_jueves-aldave\\src\\imagenes\\fodo.jpg"));
		 label.setBounds(108, -18, 709, 529);
		 getContentPane().add(label);
		 table.setDefaultEditor(Object.class, null);
		
		
		
		
		lista();

	}

	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnActualizar) {
			btnActualizarActionPerformed(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			btnEliminarActionPerformed(arg0);
		}
		if (arg0.getSource() == btnRegistro) {
			btnRegistroActionPerformed(arg0);
		}
	}

	protected void btnRegistroActionPerformed(ActionEvent arg0) {
		insertar();
	}

	protected void btnEliminarActionPerformed(ActionEvent arg0) {
		eliminar();
	}

	protected void btnActualizarActionPerformed(ActionEvent arg0) {
		actualizar();
	}

	// tabla
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
		busca();
	}
	
	
	
	public void busca() {
		int fila = table.getSelectedRow();
		
		String nom = (String) table.getValueAt(fila, 1).toString();
		String apellido = (String) table.getValueAt(fila, 2).toString();
		String fechaNac = (String) table.getValueAt(fila, 3).toString();
		String fechaRegis = (String) table.getValueAt(fila, 4).toString();
		String nacionalidad = (String) table.getValueAt(fila, 5).toString();
		String grado = (String) table.getValueAt(fila, 6).toString();
		
		
		// Obtenemos el valor del id
		idSeleccionado = (Integer) table.getValueAt(fila, 0);

		txtNombre.setText(nom);
		txtApellido.setText(apellido);
		txtFechaNac.setText(fechaNac);
		txtFechaReg.setText(fechaRegis);
		txtNaciona.setText(nacionalidad);
		txtGrado.setText(grado);
	}
	

	public void lista() {
		AutorModel au = new AutorModel();
		List<Autor> lista = au.listarAutor();
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();

		dtm.setRowCount(0);

		// Recorremos los objetos
		for (Autor x : lista) {
			Object[] f = { x.getIdAutor(), x.getNombre(), x.getApellido(), x.getFechaNaci(), x.getFechaRegis(),
					x.getNacionalidad(), x.getGrado() };
			dtm.addRow(f);
		}
	}

	public void insertar() {
		String nom = txtNombre.getText().trim();
		String apell = txtApellido.getText().trim();
		String fechaN = txtFechaNac.getText().trim();
		Date fechaRegis = Conversiones.getFechaActual();
		String nacionalidad = txtNaciona.getText().trim();
		String grado = txtGrado.getText().trim();

		if (!nom.matches(Validaciones.TEXTO)) {
			
			mensaje("El nombre debe de tener de  2 a 100 caracteres y no acepta numeros");
			
		} else if (!apell.matches(Validaciones.TEXTO)) {
			
			mensaje("El apellido debe de tener de  2 a 100 caracteres y no acepta numeros");
			
		} else if (!nacionalidad.matches(Validaciones.TEXTO)) {
			
			mensaje("La nacionalidad debe de tener de  2 a 100 caracteres y no acepta numeros");
			
		} else if (!fechaN.matches(Validaciones.FECHA)) {
			
			mensaje("Error de formato , escriba Año-Mes-Dia");
			
		} else {
			Autor obj = new Autor();
			obj.setNombre(nom);
			obj.setApellido(apell);
			obj.setFechaNaci(fechaN);
			obj.setFechaRegis(fechaRegis);
			obj.setNacionalidad(nacionalidad);
			obj.setGrado(grado);

			AutorModel a = new AutorModel();
			int s = a.insertarAutor(obj);

			if (s > 0) {
				lista();
				idSeleccionado = 0;
				mensaje("Se ingresó correctamente");
			} else {
				mensaje("Error al registrar");
			}

		}
		lista();

	}

	public void eliminar() {
		
		if (idSeleccionado == -1) {
			mensaje("Seleccione un campo");
		} else {
			AutorModel a = new AutorModel();
			int s = a.eliminarAutor(idSeleccionado);
			if (s > 0) {
				lista();
				idSeleccionado = 0;
				mensaje("Se eliminó correctamente");
			} else {
				mensaje("Error al eliminar");
			}
		}
	}
	
	public void actualizar() {
		
		if (idSeleccionado == -1) {
			
			mensaje("Seleccione un campeonato");
			
		} else {
			String nom = txtNombre.getText().trim();
			String apell = txtApellido.getText().trim();
			String fechaN = txtFechaNac.getText().trim();
			String nacionalidad = txtNaciona.getText().trim();
			Date fechaRegis = Conversiones.getFechaActual();
			String grado = txtGrado.getText().trim();

			Autor obj = new Autor();
			obj.setIdAutor(idSeleccionado);
			obj.setNombre(nom);
			obj.setApellido(apell);
			obj.setFechaNaci(fechaN);
			obj.setFechaRegis(fechaRegis);
			obj.setNacionalidad(nacionalidad);
			obj.setGrado(grado);

			AutorModel a = new AutorModel();
			int s = a.actualizaAutor(obj);

			if (s > 0) {
				lista();
				idSeleccionado = 0;
				mensaje("Se actualizo correctamente");
			} else {
				mensaje("Error al actualizar");
			}
		}
	}


}
