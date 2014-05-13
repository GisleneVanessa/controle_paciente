package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Application {

	private JFrame frmControleDeConsultas;
	private JTable table;
	ArrayList<PacienteDTO> lista = new ArrayList<PacienteDTO>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frmControleDeConsultas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	public void listar() {

		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.getDataVector().removeAllElements();
		modelo.fireTableDataChanged();

		try {
			PacienteBO abo = new PacienteBO();
			lista = (ArrayList) abo.list();

			for (PacienteDTO a : lista) {
				modelo.addRow(new String[] {
						Integer.toString(a.getCod()),
						a.getNome(),
						a.getEnd(),
						a.getContato()
				});
			}

		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmControleDeConsultas = new JFrame();
		frmControleDeConsultas.setResizable(false);
		frmControleDeConsultas.setTitle("Controle de consultas");
		frmControleDeConsultas.setBounds(100, 100, 433, 404);
		frmControleDeConsultas.setLocationRelativeTo(null);
		frmControleDeConsultas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmControleDeConsultas.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(17, 22, 179, 139);
		frmControleDeConsultas.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblPacientes = new JLabel("Pacientes");
		lblPacientes.setBounds(6, 6, 167, 16);
		panel.add(lblPacientes);

		JButton btnProcurar = new JButton("Listar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PPaciente pList = new PPaciente();
				pList.setVisible(true);
				listar();
			}
		});
		btnProcurar.setBounds(29, 90, 117, 29);
		panel.add(btnProcurar);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(237, 22, 179, 139);
		frmControleDeConsultas.getContentPane().add(panel_1);

		JLabel lblMdicas = new JLabel("M\u00E9dicos");
		lblMdicas.setBounds(6, 6, 167, 16);
		panel_1.add(lblMdicas);

		JButton button = new JButton("Cadastrar");
		button.setBounds(29, 44, 117, 29);
		panel_1.add(button);

		JButton button_1 = new JButton("Listar");
		button_1.setBounds(29, 90, 117, 29);
		panel_1.add(button_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 188, 399, 146);
		frmControleDeConsultas.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Paciente", "Tipo consulta", "MŽdico", "Data"
			}
		));

		listar();

		JButton btnCadPacientes = new JButton("Cadastrar");
		btnCadPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CPaciente cpaciente = new CPaciente();
				cpaciente.setVisible(true);
				listar();
			}
		});
		btnCadPacientes.setBounds(29, 44, 117, 29);
		panel.add(btnCadPacientes);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmControleDeConsultas.dispose();
			}
		});
		btnSair.setBounds(299, 347, 117, 29);
		frmControleDeConsultas.getContentPane().add(btnSair);
	}
}
