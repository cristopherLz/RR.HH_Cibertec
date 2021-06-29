package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import entidad.Libro;
import model.LibroModel;
import util.Validaciones;

public class FrmCrudLibro extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private JTextField txtTitulo;
	private JTextField txtAño;
	private JTextField txtSerie;
	private JTable table;
	private JTextField txtCategoria;
    
	private int idSeleccionado = -1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudLibro frame = new FrmCrudLibro();
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
	@SuppressWarnings("serial")
	public FrmCrudLibro() {
		setFrameIcon(new ImageIcon(FrmCrudLibro.class.getResource("/imagenes/libro-de-contactos.png")));
		getContentPane().setBackground(SystemColor.inactiveCaption);
		getContentPane().setLayout(null);

		JLabel lblCrudLibro = new JLabel("CRUD LIBRO");
		lblCrudLibro.setIcon(new ImageIcon(FrmCrudLibro.class.getResource("/imagenes/libro (1).png")));
		lblCrudLibro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrudLibro.setFont(new Font("Times New Roman", Font.BOLD, 31));
		lblCrudLibro.setBounds(0, 11, 601, 75);
		getContentPane().add(lblCrudLibro);

		JLabel lblNewLabel = new JLabel("Titulo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setIcon(new ImageIcon(FrmCrudLibro.class.getResource("/imagenes/correct_icon_155572.png")));
		lblNewLabel.setBounds(32, 118, 122, 39);
		getContentPane().add(lblNewLabel);

		JLabel lblAo_1 = new JLabel("A\u00F1o:");
		lblAo_1.setIcon(new ImageIcon(FrmCrudLibro.class.getResource("/imagenes/correct_icon_155572.png")));
		lblAo_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblAo_1.setBounds(32, 183, 122, 39);
		getContentPane().add(lblAo_1);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setIcon(new ImageIcon(FrmCrudLibro.class.getResource("/imagenes/correct_icon_155572.png")));
		lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblCategoria.setBounds(32, 246, 177, 39);
		getContentPane().add(lblCategoria);

		JLabel lblAo = new JLabel("Serie:");
		lblAo.setIcon(new ImageIcon(FrmCrudLibro.class.getResource("/imagenes/correct_icon_155572.png")));
		lblAo.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblAo.setBounds(32, 309, 122, 39);
		getContentPane().add(lblAo);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(164, 125, 233, 32);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);

		txtAño = new JTextField();
		txtAño.setColumns(10);
		txtAño.setBounds(164, 183, 233, 32);
		getContentPane().add(txtAño);

		txtSerie = new JTextField();
		txtSerie.setColumns(10);
		txtSerie.setBounds(164, 316, 233, 32);
		getContentPane().add(txtSerie);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnRegistrarJButton(arg0);
			}
		});
		btnRegistrar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnRegistrar.setIcon(new ImageIcon(FrmCrudLibro.class.getResource("/imagenes/Save_37110 (1).png")));
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegistrar.setBounds(424, 110, 167, 57);
		getContentPane().add(btnRegistrar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnEliminarJButton(e);
			}
		});
		btnEliminar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnEliminar.setIcon(new ImageIcon(FrmCrudLibro.class.getResource("/imagenes/trash-can_115312.png")));
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEliminar.setBounds(424, 175, 167, 57);
		getContentPane().add(btnEliminar);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnActualizarJButton(e);
			}
		});
		btnActualizar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnActualizar.setIcon(
				new ImageIcon(FrmCrudLibro.class.getResource("/imagenes/folder-directory-reload-refresh_108581.png")));
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnActualizar.setBounds(424, 238, 167, 57);
		getContentPane().add(btnActualizar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnLimpiarJButton(e);
			}
		});
		btnLimpiar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnLimpiar.setIcon(new ImageIcon(FrmCrudLibro.class.getResource("/imagenes/new_page_document_16676.png")));
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLimpiar.setBounds(424, 301, 167, 57);
		getContentPane().add(btnLimpiar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 377, 559, 204);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedTableJTable(arg0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mouseEnteredTableJTable(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mouseExitedTableJTable(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				mousePressedTableJTable(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedTableJTable(e);
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID Libro", "Titulo", "A\u00F1o de Creacion", "Categoria", "Serie" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(table);

		txtCategoria = new JTextField();
		txtCategoria.setColumns(10);
		txtCategoria.setBounds(198, 246, 199, 32);
		getContentPane().add(txtCategoria);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Actualiza Libro");
		setBounds(100, 100, 625, 622);
		
		JTableHeader header = table.getTableHeader();
		 header.setReorderingAllowed(false);
		 header.setResizingAllowed(false);
		 
		 table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 table.setSelectionBackground(new Color(173, 230, 196));
		 table.setDefaultEditor(Object.class, null);
		 //centrar
		 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		 centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		 table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		 //tamaño
		 table.getColumnModel().getColumn(0).setPreferredWidth(5);
		 table.getColumnModel().getColumn(1).setPreferredWidth(50);
		 table.getColumnModel().getColumn(2).setPreferredWidth(20);
		 table.getColumnModel().getColumn(3).setPreferredWidth(50);
		 table.getColumnModel().getColumn(4).setPreferredWidth(30);
		 //color
		 table.setSelectionBackground(Color.GREEN);
		lista();
	}

	protected void actionPerformedBtnRegistrarJButton(ActionEvent arg0) {
		inserta();
	}

	protected void actionPerformedBtnEliminarJButton(ActionEvent e) {
		elimina();
	}

	protected void actionPerformedBtnActualizarJButton(ActionEvent e) {
		actualiza();	
	}

	protected void actionPerformedBtnLimpiarJButton(ActionEvent e) {
		limpiarCajasTexto();
	}

	protected void mouseClickedTableJTable(MouseEvent arg0) {
		busca();
	}

	protected void mouseEnteredTableJTable(MouseEvent e) {
	}

	protected void mouseExitedTableJTable(MouseEvent e) {
	}

	protected void mousePressedTableJTable(MouseEvent e) {
	}

	protected void mouseReleasedTableJTable(MouseEvent e) {
	}

	public void lista() {
		LibroModel m = new LibroModel();
		List<Libro> lista = m.listarLibro();

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);

		for (Libro x : lista) {
			Object[] fila = { x.getIdLibro(), x.getTitulo(), x.getAnio(),
					x.getCategoria(), x.getSerie() };

			dtm.addRow(fila);
		}
	}

	public void busca() {
     int fila= table.getSelectedRow();
     
   idSeleccionado=(Integer)table.getValueAt(fila, 0);
    String tit=(String)table.getValueAt(fila, 1);
    String anio=(String)table.getValueAt(fila, 2);
    String cat=(String)table.getValueAt(fila, 3);
    String serie=(String)table.getValueAt(fila, 4);
    
    txtTitulo.setText(tit);
    txtAño.setText(anio);
    txtCategoria.setText(cat);
    txtSerie.setText(serie);
	}

	public void inserta() {
		String tit = txtTitulo.getText();
		String anio = txtAño.getText();
		String cat = txtCategoria.getText();
		String serie = txtSerie.getText();

		if (!tit.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El titulo es de 2 a 20 Caracteres entre números y letras");
			return;
		}
		if (!anio.matches(Validaciones.NUMERO)) {
			mensaje("Ingrese el año solo números maximo hasta 4 caracteres");
			return;
		}
		if (!cat.matches(Validaciones.TEXTO)) {
			mensaje("Ingrese la categoria de 2 a 20 caracteres");
			return;
		}
		if (!serie.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("Ingrese de 2 a 20 caracteres entre número y letras");
			return;
		}
		Libro obj = new Libro();
		obj.setTitulo(tit);
		obj.setAnio(anio);
		obj.setCategoria(cat);
		obj.setSerie(serie);

		LibroModel m = new LibroModel();
		int s = m.insertaLibro(obj);
		if (s > 0) {
			mensaje("Se inserto Correctamente");
			lista();
			limpiarCajasTexto();
		} else {
			mensaje("Error al Insertar");
		}
	}

	private void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);

	}

	public void actualiza() {
		  if(idSeleccionado ==-1) {
		    	 mensaje("Seleccione una fila");    
	}else {
		String tit = txtTitulo.getText();
		String anio = txtAño.getText();
		String cat = txtCategoria.getText();
		String serie = txtSerie.getText();

		if (!tit.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("Ingrese de 2 a 20 caracteres entre números y letras");
			return;
		}
		if (!anio.matches(Validaciones.NUMERO)) {
			mensaje("Ingrese el año solo números maximo hasta 4 caracteres");
			return;
		}
		if (!cat.matches(Validaciones.TEXTO)) {
			mensaje("Ingrese la categoria de 2 a 20 caracteres");
			return;
		}
		if (!serie.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("Ingrese de 2 a 20 caracteres entre números y letras");
			return;
		}
		Libro obj = new Libro();
		obj.setIdLibro(idSeleccionado);
		obj.setTitulo(tit);
		obj.setAnio(anio);
		obj.setCategoria(cat);
		obj.setSerie(serie);

		LibroModel m = new LibroModel();
		int s = m.actualizaLibro(obj);
		if (s > 0) {
			mensaje("Se actualizo Correctamente");
			lista();
			limpiarCajasTexto();
		} else {
			mensaje("Error al actualizar");
		}
	}
	}
	public void elimina() {
     if(idSeleccionado ==-1) {
    	 mensaje("Seleccione una fila");
     }else {
    	 LibroModel m = new LibroModel();
       int s = m.eliminarLibro(idSeleccionado);
       if (s > 0) {
			mensaje("Se elimino Correctamente");
			lista();
			limpiarCajasTexto();
			idSeleccionado=-1;
		} else {
			mensaje("Error al Eliminar");
		}
     }
	}

	void limpiarCajasTexto() {

		txtTitulo.setText("");
		txtAño.setText("");
		txtCategoria.setText("");
		txtSerie.setText("");
		txtTitulo.requestFocus();

	}
}