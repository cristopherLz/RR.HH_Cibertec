package gui;


/**
 * 
 * @author Luis
 *
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entidad.Sala;
import model.SalaModel;

public class FrmConsultaSala extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtInicio;
	private JTextField txtFin;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaSala frame = new FrmConsultaSala();
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
	public FrmConsultaSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Sala");
		setBounds(100, 100, 741, 589);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Consulta de Sala por Piso");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(43, 33, 653, 54);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPiso = new JLabel("Piso(Desde)");
		lblPiso.setBounds(43, 132, 73, 14);
		getContentPane().add(lblPiso);
		
		txtInicio = new JTextField();
		txtInicio.setColumns(10);
		txtInicio.setBounds(145, 129, 109, 20);
		getContentPane().add(txtInicio);
		
		JLabel lblHasta = new JLabel("(Hasta)");
		lblHasta.setBounds(345, 132, 46, 14);
		getContentPane().add(lblHasta);
		
		txtFin = new JTextField();
		txtFin.setColumns(10);
		txtFin.setBounds(441, 129, 109, 20);
		getContentPane().add(txtFin);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pisoIni = txtInicio.getText();
				String pisoFin = txtFin.getText();
				
				if(!pisoIni.matches("[1-5]{1}")) {
					mensaje("El numero de piso  maximo es 5");
					return;
				};
				if(!pisoFin.matches("[1-5]{1}")) {
					mensaje("El numero de piso  maximo es 5");
					return;
				}
				
				double dbPisoInicial = Double.parseDouble(pisoIni);
				double dbPisoFinal = Double.parseDouble(pisoFin);
				
				if(dbPisoInicial > dbPisoFinal) {
					mensaje("El piso Inicial es mayor que el piso Final");
					return;
				}
				
				SalaModel model = new SalaModel();
				List<Sala> lstSala = model.listaPorPiso(dbPisoInicial, dbPisoFinal);
				
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);
				
				for(Sala x : lstSala) {
					Object[] fila = {x.getIdsala(),x.getNumero(),x.getPiso(),x.getCapacidad(),x.getRecursos(),x.getEstado()};
					
					dtm.addRow(fila);
					
					
				}
				
			}
		});
		btnFiltrar.setBounds(607, 128, 89, 23);
		getContentPane().add(btnFiltrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 190, 653, 331);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IdSala", "Numero", "Piso", "Capacidad", "Recursos", "Estado"
			}
		));
		scrollPane.setViewportView(table);


	}
	public void mensaje (String msg)
	{
		JOptionPane.showMessageDialog(this, msg);
	}
}
