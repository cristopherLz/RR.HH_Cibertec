package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import entidad.Usuario;
import model.UsuarioModel;
import util.Validaciones;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class FrmCrudUsuario extends JInternalFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtLogin;
	private JPasswordField txtPassword;
	private JTextField txtCorreo;
	private JTextField txtFecha;
	private JTextField txtDireccion;
	private JTable table;

	// No se ha seleccionado nada
	int idSeleccionado = -1;
	private JButton btnRegistro;
	private JButton btnEliminar;
	private JButton btnActualizar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudUsuario frame = new FrmCrudUsuario();
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
	public FrmCrudUsuario() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Actualiza Usuario");
		setBounds(100, 100, 1041, 700);
		getContentPane().setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(248, 100, 96, 21);
		getContentPane().add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setForeground(Color.BLACK);
		txtNombre.setColumns(10);
		txtNombre.setBounds(354, 102, 214, 20);
		getContentPane().add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setForeground(Color.BLACK);
		txtApellido.setColumns(10);
		txtApellido.setBounds(354, 134, 214, 20);
		getContentPane().add(txtApellido);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setForeground(Color.BLACK);
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblApellido.setBounds(248, 132, 96, 21);
		getContentPane().add(lblApellido);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setForeground(Color.BLACK);
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDni.setBounds(248, 164, 96, 21);
		getContentPane().add(lblDni);

		txtDni = new JTextField();
		txtDni.setForeground(Color.BLACK);
		txtDni.setColumns(10);
		txtDni.setBounds(354, 166, 156, 20);
		getContentPane().add(txtDni);

		txtLogin = new JTextField();
		txtLogin.setForeground(Color.BLACK);
		txtLogin.setColumns(10);
		txtLogin.setBounds(354, 197, 156, 20);
		getContentPane().add(txtLogin);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLogin.setBounds(248, 196, 96, 21);
		getContentPane().add(lblLogin);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(248, 228, 120, 21);
		getContentPane().add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setForeground(Color.BLACK);
		txtPassword.setBounds(354, 228, 156, 20);
		getContentPane().add(txtPassword);

		txtCorreo = new JTextField();
		txtCorreo.setForeground(Color.BLACK);
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(389, 259, 189, 20);
		getContentPane().add(txtCorreo);

		JLabel lblCorreoElectronico = new JLabel("Correo Electronico:");
		lblCorreoElectronico.setForeground(Color.BLACK);
		lblCorreoElectronico.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCorreoElectronico.setBounds(223, 260, 156, 21);
		getContentPane().add(lblCorreoElectronico);

		JLabel lblDireccion = new JLabel("Fecha de Nacimiento");
		lblDireccion.setForeground(Color.BLACK);
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDireccion.setBounds(233, 292, 194, 21);
		getContentPane().add(lblDireccion);

		txtFecha = new JTextField();
		txtFecha.setForeground(Color.BLACK);
		txtFecha.setColumns(10);
		txtFecha.setBounds(413, 294, 165, 20);
		getContentPane().add(txtFecha);

		txtDireccion = new JTextField();
		txtDireccion.setForeground(Color.BLACK);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(343, 327, 246, 20);
		getContentPane().add(txtDireccion);

		JLabel lblDireccion_1 = new JLabel("Direcci\u00F3n:");
		lblDireccion_1.setForeground(Color.BLACK);
		lblDireccion_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDireccion_1.setBounds(248, 325, 96, 21);
		getContentPane().add(lblDireccion_1);

		JLabel lblCrudUsuario = new JLabel("CRUD USUARIO");
		lblCrudUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrudUsuario.setForeground(Color.BLACK);
		lblCrudUsuario.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 21));
		lblCrudUsuario.setBounds(290, 11, 397, 78);
		getContentPane().add(lblCrudUsuario);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 379, 912, 280);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "idUsuario", "nombre", "apellido", "dni",
				"login", "password", "correo", "fechaNacimiento", "direccion" }));
		scrollPane.setViewportView(table);

		btnRegistro = new JButton("REGISTRO");
		btnRegistro.addActionListener(this);
		btnRegistro.setBounds(716, 148, 127, 37);
		getContentPane().add(btnRegistro);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(716, 212, 127, 37);
		getContentPane().add(btnEliminar);

		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(716, 276, 127, 37);
		getContentPane().add(btnActualizar);

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
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(new Color(173, 230, 196));
		scrollPane.setViewportView(table);
		table.setDefaultEditor(Object.class, null);

		lista();

	}

	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnActualizar) {
			handle_btnActualizar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			handle_btnEliminar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnRegistro) {
			handle_btnRegistro_actionPerformed(arg0);
		}
	}

	protected void handle_btnRegistro_actionPerformed(ActionEvent arg0) {
		insertar();
	}

	protected void handle_btnEliminar_actionPerformed(ActionEvent arg0) {
		eliminar();
	}

	protected void handle_btnActualizar_actionPerformed(ActionEvent arg0) {
		actualizar();
	}

	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == table) {
			handle_table_mouseClicked(arg0);
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

	protected void handle_table_mouseClicked(MouseEvent arg0) {
		busca();
	}

	public void busca() {
		int fila = table.getSelectedRow();

		String nom = (String) table.getValueAt(fila, 1).toString();
		String apellido = (String) table.getValueAt(fila, 2).toString();
		String dni = (String) table.getValueAt(fila, 3).toString();
		String login = (String) table.getValueAt(fila, 4).toString();
		String password = (String) table.getValueAt(fila, 5).toString();
		String correo = (String) table.getValueAt(fila, 6).toString();
		String fecha = (String) table.getValueAt(fila, 7).toString();
		String direccion = (String) table.getValueAt(fila, 8).toString();

		// Obtenemos el valor del id
		idSeleccionado = (Integer) table.getValueAt(fila, 0);

		txtNombre.setText(nom);
		txtApellido.setText(apellido);
		txtDni.setText(dni);
		txtLogin.setText(login);
		txtPassword.setText(password);
		txtCorreo.setText(correo);
		txtFecha.setText(fecha);
		txtDireccion.setText(direccion);
	}

	public void lista() {
		UsuarioModel au = new UsuarioModel();
		List<Usuario> lista = au.listaUsuario();
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();

		dtm.setRowCount(0);

		// Recorremos los objetos
		for (Usuario x : lista) {
			Object[] f = { x.getIdUsuario(), x.getNombre(), x.getApellido(), x.getDni(), x.getLogin(), x.getPassword(),
					x.getCorreo(), x.getFechaNacimiento(), x.getDireccion() };
			dtm.addRow(f);
		}
	}

	public void insertar() {
		String nom = txtNombre.getText().trim();
		String apell = txtApellido.getText().trim();
		String dni = txtDni.getText().trim();
		String login = txtLogin.getText().trim();
		String password = String.valueOf(txtPassword.getPassword());
		String correo = txtCorreo.getText().trim();
		String fecha = txtFecha.getText().trim();
		String direccion = txtDireccion.getText().trim();

		if (!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre debe de tener de  2 a 100 caracteres y no acepta numeros");
		} else if (!apell.matches(Validaciones.TEXTO)) {
			mensaje("El apellido debe de tener de  2 a 100 caracteres y no acepta numeros");
		} else if (!dni.matches(Validaciones.DNI)) {
			mensaje("El dni debe de tener de  8 caracteres");
		} else if (!correo.matches(Validaciones.CORREO)) {
			mensaje("revise si su correo es correcto");
		} else if (!fecha.matches(Validaciones.FECHA)) {
			mensaje("Error de formato , escriba Año-Mes-Dia");
		} else {
			Usuario Obj = new Usuario();
			Obj.setNombre(nom);
			Obj.setApellido(apell);
			Obj.setDni(dni);
			Obj.setLogin(login);
			Obj.setPassword(password);
			Obj.setCorreo(correo);
			Obj.setFechaNacimiento(fecha);
			Obj.setDireccion(direccion);

			UsuarioModel u = new UsuarioModel();
			int s = u.insertaUsuario(Obj);

			if (s > 0) {
				mensaje("Se ingreso correctamente");
				lista();
			} else {
				mensaje("Error al registrar");
			}

		}

	}

	public void eliminar() {

		if (idSeleccionado == -1) {
			mensaje("Seleccione un campo");
		} else {
			UsuarioModel u = new UsuarioModel();
			int s = u.eliminaUsuario(idSeleccionado);
			if (s > 0) {
				lista();
				idSeleccionado = -1;
				mensaje("Se elimino correctamente");
			} else {
				mensaje("Error al eliminar");
			}
		}
	}

	public void actualizar() {

		if (idSeleccionado == -1) {

			mensaje("Seleccione un usuario");

		} else {
			String nom = txtNombre.getText().trim();
			String apell = txtApellido.getText().trim();
			String dni = txtDni.getText().trim();
			String login = txtLogin.getText().trim();
			String password = String.valueOf(txtPassword.getPassword());
			String correo = txtCorreo.getText().trim();
			String fecha = txtFecha.getText().trim();
			String direccion = txtDireccion.getText().trim();

			Usuario obj = new Usuario();
			obj.setIdUsuario(idSeleccionado);
			obj.setNombre(nom);
			obj.setApellido(apell);
			obj.setDni(dni);
			obj.setLogin(login);
			obj.setPassword(password);
			obj.setCorreo(correo);
			obj.setFechaNacimiento(fecha);
			obj.setDireccion(direccion);

			UsuarioModel u = new UsuarioModel();
			int s = u.actualizaUsuario(obj);

			if (s > 0) {
				mensaje("Se actualizo correctamente");
				lista();
			} else {
				mensaje("Error al actualizar");
			}
		}
	}

}
