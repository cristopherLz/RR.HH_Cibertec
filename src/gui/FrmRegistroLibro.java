package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidad.Libro;
import model.LibroModel;
import util.Validaciones;

public class FrmRegistroLibro extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	// CODIGO
	private static final long serialVersionUID = 1L;
	private JTextField txtTitulo;
	private JTextField txtAnio;
	private JTextField txtSerie;
	private JButton btnRegistrar;
	private JTextField txtCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistroLibro frame = new FrmRegistroLibro();
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
	public FrmRegistroLibro() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Libro");
		setBounds(100, 100, 578, 428);
		getContentPane().setLayout(null);

		JLabel lblRegistroDeLibro = new JLabel("REGISTRO DE LIBRO");
		lblRegistroDeLibro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegistroDeLibro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeLibro.setBounds(10, 11, 542, 36);
		getContentPane().add(lblRegistroDeLibro);

		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitulo.setBounds(125, 126, 46, 14);
		getContentPane().add(lblTitulo);

		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAo.setBounds(125, 170, 46, 14);
		getContentPane().add(lblAo);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategoria.setBounds(125, 213, 65, 14);
		getContentPane().add(lblCategoria);

		JLabel lblSerie = new JLabel("Serie");
		lblSerie.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSerie.setBounds(125, 263, 46, 14);
		getContentPane().add(lblSerie);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(213, 123, 213, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);

		txtAnio = new JTextField();
		txtAnio.setColumns(10);
		txtAnio.setBounds(213, 167, 213, 20);
		getContentPane().add(txtAnio);

		txtSerie = new JTextField();
		txtSerie.setColumns(10);
		txtSerie.setBounds(213, 260, 213, 20);
		getContentPane().add(txtSerie);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(199, 322, 136, 23);
		getContentPane().add(btnRegistrar);

		txtCategoria = new JTextField();
		txtCategoria.setColumns(10);
		txtCategoria.setBounds(213, 211, 213, 20);
		getContentPane().add(txtCategoria);

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(arg0);
		}
	}

	protected void actionPerformedBtnRegistrar(ActionEvent arg0) {

		String titulo = txtTitulo.getText().trim();
		String año = txtAnio.getText().trim();
		String categoria = txtCategoria.getText().trim();
		String serie = txtSerie.getText().trim();

		if (!titulo.matches(Validaciones.TEXTO)) {
			mensaje("Error en el titulo");
		} else if (!categoria.matches(Validaciones.TEXTO)) {
			mensaje("Error en la categoria");
		} else {
			Libro obj = new Libro();
			obj.setTitulo(titulo);
			obj.setAnio(año);
			obj.setCategoria(categoria);
			obj.setSerie(serie);

			LibroModel model = new LibroModel();
			int salidaLibro = model.insertaLibro(obj);
			if (salidaLibro > 0) {
				mensaje("Registro exitoso");
			} else {
				mensaje("Registro erróneo");
			}

		}

	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
}
