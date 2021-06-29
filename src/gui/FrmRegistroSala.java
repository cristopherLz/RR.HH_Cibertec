package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidad.Sala;
import model.SalaModel;
import util.Validaciones;

public class FrmRegistroSala extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblRegistroDeSala;
	private JLabel lblNmero;
	private JTextField txtNumero;
	private JLabel lblPiso;
	private JLabel lblCapacidad;
	private JLabel lblRecursos;
	private JLabel lblEstado;
	private JComboBox<String> cboPiso;
	private JTextField txtCapacidad;
	private JTextField txtRecursos;
	private JComboBox<String> cboEstado;
	private JButton btnRegistrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistroSala frame = new FrmRegistroSala();
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
	public FrmRegistroSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Sala");
		setBounds(100, 100, 536, 441);
		getContentPane().setLayout(null);
		
		lblRegistroDeSala = new JLabel("Registro de Sala ");
		lblRegistroDeSala.setBackground(Color.BLACK);
		lblRegistroDeSala.setForeground(Color.BLACK);
		lblRegistroDeSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeSala.setFont(new Font("Arial", Font.BOLD, 20));
		lblRegistroDeSala.setBounds(10, 11, 500, 33);
		getContentPane().add(lblRegistroDeSala);
		
		lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setBounds(103, 70, 76, 25);
		getContentPane().add(lblNmero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(240, 72, 115, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		
		lblPiso = new JLabel("Piso");
		lblPiso.setBounds(103, 118, 46, 14);
		getContentPane().add(lblPiso);
		
		lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(103, 160, 85, 25);
		getContentPane().add(lblCapacidad);
		
		lblRecursos = new JLabel("Recursos");
		lblRecursos.setBounds(103, 199, 76, 25);
		getContentPane().add(lblRecursos);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(103, 245, 76, 25);
		getContentPane().add(lblEstado);
		
		cboPiso = new JComboBox<String>();
		cboPiso.setModel(new DefaultComboBoxModel<String>(new String[] {"(Seleccione)", "1", "2", "3", "4", "5"}));
		cboPiso.setBounds(240, 115, 115, 20);
		getContentPane().add(cboPiso);
		
		txtCapacidad = new JTextField();
		txtCapacidad.setBounds(240, 162, 270, 20);
		getContentPane().add(txtCapacidad);
		txtCapacidad.setColumns(10);
		
		txtRecursos = new JTextField();
		txtRecursos.setBounds(240, 201, 270, 20);
		getContentPane().add(txtRecursos);
		txtRecursos.setColumns(10);
		
		cboEstado = new JComboBox<String>();
		cboEstado.setModel(new DefaultComboBoxModel<String>(new String[] {"(Seleccione)", "Activo", "Inactivo"}));
		cboEstado.setBounds(240, 247, 115, 20);
		getContentPane().add(cboEstado);
		
		btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.setIcon(new ImageIcon(FrmRegistroSala.class.getResource("/imagenes/registro.png")));
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(196, 330, 134, 33);
		getContentPane().add(btnRegistrar);


	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnRegistrar) {
			btnRegistrarActionPerformed(arg0);
		}
	}
	protected void btnRegistrarActionPerformed(ActionEvent arg0) {
		
		String num = txtNumero.getText();
		
		int posPiso = cboPiso.getSelectedIndex();
		String piso = cboPiso.getSelectedItem().toString();
		
		String cap = txtCapacidad.getText();
		
		String rec = txtRecursos.getText();
		
		int posEstado = cboEstado.getSelectedIndex();
		
		
		if(!num.matches(Validaciones.PISO)) 
		{
			mensaje("El número de la sala es 1 caracter y con 2  o 3 dígitos");
			return;
		}
		else if( posPiso == 0) 
		{
			mensaje("Seleccione piso de la sala");
		 	return;
		}
		else if(!cap.matches(Validaciones.TEXTO_NUMERO)) 
		{
			mensaje("La capacidad de la sala es de 2 a 20 caracteres");
			return;
		} 
		else if(!rec.matches(Validaciones.TEXTO_NUMERO)) 
		{
			mensaje("Los recursos de la sala es de 2 a 20 caracteres");
			return;
		} 
		else if( posEstado == 0) 
		{
			mensaje("Seleccione estado de la sala");
		 	return;
		}
		else 
		{
			Sala obj = new Sala();
			obj.setNumero(num);
			obj.setPiso(Integer.parseInt(piso));
			obj.setCapacidad(cap);
			obj.setRecursos(rec);

			obj.setEstado((byte)posEstado);
			
			SalaModel m = new SalaModel();
			int salida = m.insertaSala(obj);
			
			if(salida>0)
			{
				mensaje("Registro Exitoso");
			}
			else 
			{
				mensaje("Registro erróneo");
			}
			
		}
	}
	
	public void mensaje (String msg)
	{
		JOptionPane.showMessageDialog(this, msg);
	}
}
