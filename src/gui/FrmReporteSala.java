package gui;

import java.awt.BorderLayout;
import java.awt.Color;

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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import entidad.Sala;
import model.SalaModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import reporte.GeneradorReporte;

public class FrmReporteSala extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtInicio;
	private JTextField txtFin;
	private JButton btnFiltrar;
	private JPanel pnlReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteSala frame = new FrmReporteSala();
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
	public FrmReporteSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte Sala");
		setBounds(100, 100, 931, 632);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Consulta de Sala por Piso");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(24, 26, 653, 54);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPiso = new JLabel("Piso(Desde)");
		lblPiso.setBounds(54, 116, 73, 14);
		getContentPane().add(lblPiso);
		
		txtInicio = new JTextField();
		txtInicio.setBounds(184, 113, 109, 20);
		getContentPane().add(txtInicio);
		txtInicio.setColumns(10);
		
		JLabel lblHasta = new JLabel("(Hasta)");
		lblHasta.setBounds(387, 116, 46, 14);
		getContentPane().add(lblHasta);
		
		txtFin = new JTextField();
		txtFin.setBounds(484, 113, 109, 20);
		getContentPane().add(txtFin);
		txtFin.setColumns(10);
		
		
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(705, 113, 89, 20);
		getContentPane().add(btnFiltrar);
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
				
				
				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstSala);
				
				String file = "ReporteSala.jasper";
				
				JasperPrint jasperPrint = GeneradorReporte.genera(file, dataSource, null);
				
				JRViewer jRViewer = new JRViewer(jasperPrint);
				
				pnlReporte.removeAll();
				pnlReporte.add(jRViewer);
				pnlReporte.repaint();
				pnlReporte.revalidate();
				
				
				
			}
		});
		
		pnlReporte = new JPanel();
		pnlReporte.setLayout( new BorderLayout(0,0));
		pnlReporte.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Reporte ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlReporte.setBounds(41, 158, 835, 394);
		getContentPane().add(pnlReporte);

		

	}
	public void mensaje (String msg)
	{
		JOptionPane.showMessageDialog(this, msg);
	}
}
