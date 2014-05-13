package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	ArrayList<PacienteDTO> lista = new ArrayList<PacienteDTO>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PPaciente dialog = new PPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listar_pacientes() {

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
	 * Create the dialog.
	 */
	public PPaciente() {
		setTitle("Pacientes");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(16, 52, 420, 181);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Cod", "Nome", "Endereço", "Contato"
					}
				));
			}
		}

		listar_pacientes();

		JButton btnNovoPaciente = new JButton("Novo paciente");
		btnNovoPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CPaciente pNew2 = new CPaciente();
				pNew2.setVisible(true);
				listar_pacientes();
			}
		});
		btnNovoPaciente.setBounds(16, 11, 137, 29);
		contentPanel.add(btnNovoPaciente);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnAlterar.setBounds(177, 11, 117, 29);
		contentPanel.add(btnAlterar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PacienteDTO pacienteDTO = new PacienteDTO();
				PacienteBO pacienteBO = new PacienteBO();
				int row = table.getSelectedRow();

				try {
					if (table.getRowCount() == 0)
						throw new Exception("Não há registros.");
					else if (row < 0)
						throw new Exception("Selecione um registro.");
					else
						pacienteDTO = lista.get(row);

					pacienteBO.delete(pacienteDTO);
					JOptionPane.showMessageDialog(null, "Paciente excluído com sucesso!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
					listar_pacientes();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					erro.getStackTrace();
				}
			}
		});
		btnExcluir.setBounds(310, 11, 117, 29);
		contentPanel.add(btnExcluir);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnVoltar = new JButton("Voltar");
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnVoltar.setActionCommand("Cancel");
				buttonPane.add(btnVoltar);
			}
		}
	}
}
