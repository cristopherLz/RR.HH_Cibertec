package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.Color;
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

import entidad.Libro;
import model.LibroModel;
import util.Validaciones;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmConsultaLibro extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtinicio;
	private JTextField txtfinal;
	private JButton btnFiltrar;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaLibro frame = new FrmConsultaLibro();
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
	public FrmConsultaLibro() {
		getContentPane().setBackground(new Color(255, 204, 102));
		getContentPane().setLayout(null);

		JLabel lblConsultarLibro = new JLabel("Consultar Libro");
		lblConsultarLibro.setIcon(
				new ImageIcon(FrmConsultaLibro.class.getResource("/imagenes/search_book_open_search_locate_6178.png")));
		lblConsultarLibro.setFont(new Font("Segoe Print", Font.PLAIN, 30));
		lblConsultarLibro.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultarLibro.setBounds(0, 11, 816, 76);
		getContentPane().add(lblConsultarLibro);

		JLabel lblNewLabel = new JLabel("Consultar A\u00F1o(Desde)");
		lblNewLabel.setFont(new Font("MV Boli", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 98, 203, 61);
		getContentPane().add(lblNewLabel);

		JLabel lblConsultarAohasta = new JLabel("(Hasta)");
		lblConsultarAohasta.setFont(new Font("MV Boli", Font.BOLD, 16));
		lblConsultarAohasta.setBounds(397, 98, 83, 61);
		getContentPane().add(lblConsultarAohasta);

		txtinicio = new JTextField();
		txtinicio.setBounds(202, 116, 185, 29);
		getContentPane().add(txtinicio);
		txtinicio.setColumns(10);

		txtfinal = new JTextField();
		txtfinal.setColumns(10);
		txtfinal.setBounds(469, 116, 170, 29);
		getContentPane().add(txtfinal);

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBackground(new Color(255, 255, 255));
		btnFiltrar.addActionListener(this);
		btnFiltrar.setIcon(new ImageIcon(FrmConsultaLibro.class.getResource("/imagenes/find_search_locate_6201.png")));
		btnFiltrar.setFont(new Font("Georgia", Font.BOLD, 16));
		btnFiltrar.setBounds(649, 98, 154, 57);
		getContentPane().add(btnFiltrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 170, 796, 245);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(255, 255, 153));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID Libro", "Titulo", "A\u00F1o de Creacion", "Categoria", "Serie" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		
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
		 table.setSelectionBackground(Color.GRAY);
		 
		scrollPane.setViewportView(table);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Libro");
		setBounds(100, 100, 842, 456);

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrarJButton(arg0);
		}
	}

	protected void actionPerformedBtnFiltrarJButton(ActionEvent arg0) {
		String fechaIni = txtinicio.getText();
		String fechaFin = txtfinal.getText();

		if (!fechaIni.matches(Validaciones.NUMERO)) {
			mensaje("Inserte las Fechas que desea Consultar Solo 4 NUMEROS");
			return;
		}

		LibroModel model = new LibroModel();
		List<Libro> lstLibro = model.listaPorAño(fechaIni, fechaFin);

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);

		for (Libro x : lstLibro) {
			Object[] fila = { x.getIdLibro(), x.getTitulo(), x.getAnio(), x.getCategoria(), x.getSerie() };
			dtm.addRow(fila);
		}
	}

	private void mensaje(String msj) {
		JOptionPane.showMessageDialog(this, msj);

	}

	void limpiarCajasTexto() {
		txtinicio.setText("");
		txtfinal.setText("");
		txtinicio.requestFocus();
	}
}
