package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidad.Autor;
import model.AutorModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import reporte.GeneradorReporte;

public class FrmReporteAutor extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblConsultaAutorPor;
	private JLabel lblFechaInicio;
	private JTextField txtInicio;
	private JTextField txtFinal;
	private JLabel lblFechaInicio_1;
	private JButton btnConsultar;
	private JPanel panelReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteAutor frame = new FrmReporteAutor();
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
	public FrmReporteAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte Autor");
		setBounds(100, 100, 1086, 661);
		getContentPane().setLayout(null);
		
		lblConsultaAutorPor = new JLabel("CONSULTA AUTOR POR FECHA DE REGISTRO");
		lblConsultaAutorPor.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConsultaAutorPor.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaAutorPor.setBounds(10, 11, 1078, 55);
		getContentPane().add(lblConsultaAutorPor);
		
		lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFechaInicio.setBounds(65, 106, 92, 21);
		getContentPane().add(lblFechaInicio);
		
		txtInicio = new JTextField();
		txtInicio.setBounds(147, 107, 273, 26);
		getContentPane().add(txtInicio);
		txtInicio.setColumns(10);
		
		txtFinal = new JTextField();
		txtFinal.setColumns(10);
		txtFinal.setBounds(708, 107, 289, 26);
		getContentPane().add(txtFinal);
		
		lblFechaInicio_1 = new JLabel("Fecha Inicio");
		lblFechaInicio_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFechaInicio_1.setBounds(626, 106, 92, 21);
		getContentPane().add(lblFechaInicio_1);
		
		btnConsultar = new JButton("CONSULTAR");
		btnConsultar.addActionListener(this);
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnConsultar.setBounds(890, 164, 124, 33);
		getContentPane().add(btnConsultar);
		
		panelReporte = new JPanel();
		panelReporte.setBounds(10, 222, 1050, 398);
		getContentPane().add(panelReporte);


	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnConsultar) {
			actionPerformedBtnConsultar(arg0);
		}
	}
	protected void actionPerformedBtnConsultar(ActionEvent arg0) {
		String fecInicio = txtInicio.getText().trim();
		String fecFinal = txtFinal.getText().trim();
		
	
		AutorModel model = new AutorModel();
		List<Autor> lstAutor = null;
		
		
		if(fecInicio.equals("") || fecFinal.equals("")) {
			lstAutor = model.consultaAutor(fecInicio, fecFinal);
		}else {
			lstAutor = model.consultaAutor(fecInicio, fecFinal);
		}
		
		
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstAutor);
		
		String file = "ReporteAutor.jasper";
		
		JasperPrint jasperPrint = GeneradorReporte.genera(file, dataSource, null);
		
		JRViewer jRViewer = new JRViewer(jasperPrint);
		
		panelReporte.removeAll();
		panelReporte.add(jRViewer);
		panelReporte.repaint();
		panelReporte.revalidate();
		
		
		
	}
}
