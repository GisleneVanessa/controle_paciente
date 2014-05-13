package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import java.awt.Color;

public class CPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField fCod;
	private JTextField fNome;
	private JTextField fEnd;
	private JTextField fContato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CPaciente dialog = new CPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CPaciente() {
		setModal(true);
		setResizable(false);
		setTitle("Cadastrar paciente");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(30, 18, 258, 16);
		contentPanel.add(lblCdigo);

		fCod = new JTextField();
		fCod.setBounds(27, 35, 134, 28);
		contentPanel.add(fCod);
		fCod.setColumns(10);
		{
			fNome = new JTextField();
			fNome.setColumns(10);
			fNome.setBounds(30, 81, 310, 28);
			contentPanel.add(fNome);
		}
		{
			JLabel lblNome = new JLabel("Nome");
			lblNome.setBounds(33, 64, 307, 16);
			contentPanel.add(lblNome);
		}
		{
			fEnd = new JTextField();
			fEnd.setColumns(10);
			fEnd.setBounds(30, 127, 310, 28);
			contentPanel.add(fEnd);
		}
		{
			JLabel lblEndereo = new JLabel("Endere\u00E7o");
			lblEndereo.setBounds(33, 110, 307, 16);
			contentPanel.add(lblEndereo);
		}
		{
			JLabel lblContato = new JLabel("Contato");
			lblContato.setBounds(33, 157, 128, 16);
			contentPanel.add(lblContato);
		}
		{
			fContato = new JTextField();
			fContato.setColumns(10);
			fContato.setBounds(30, 174, 131, 28);
			contentPanel.add(fContato);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCadastrar = new JButton("Cadastrar");
				btnCadastrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int cod = 0;
						if (!fCod.getText().isEmpty())
							cod = Integer.parseInt(fCod.getText());

						String nome = fNome.getText();
						String end = fEnd.getText();
						String contato = fContato.getText();

						try {
							PacienteDTO pDTO = new PacienteDTO();
							PacienteBO pBO = new PacienteBO();

							pDTO.setCod(cod);
							pDTO.setNome(nome);
							pDTO.setEnd(end);
							pDTO.setContato(contato);

							pBO.cadastrar(pDTO);
							dispose();
						} catch (Exception erro) {
							JOptionPane.showMessageDialog(null, erro.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnCadastrar.setActionCommand("OK");
				buttonPane.add(btnCadastrar);
				getRootPane().setDefaultButton(btnCadastrar);
			}
			{
				JButton btnVoltar = new JButton("Voltar");
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnVoltar.setActionCommand("Cancel");
				buttonPane.add(btnVoltar);
			}
		}
	}
}
