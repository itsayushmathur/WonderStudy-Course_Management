package collegeapplication.subject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

import net.proteanit.sql.DbUtils;

/**
 * @author Ayush Mathur
 * @mail: mathurayush121@gmail.com
 */


@SuppressWarnings("serial")
public class AddSubjectDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField subjectcodefield;
	private JTextField subjectnamefield;
	private JTextField theorymarksfield;
	private JTextField practicalmarksfield;
	private JButton addsubject;
	private JComboBox<String> coursetypecombo;
	private String Coursecode;
	private int semoryear;
	private JTable table;
	private JLabel lblError;
	private static AddSubjectDialog dialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new AddSubjectDialog("IT", 1, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddSubjectDialog(String courseCode, int sem, JTable datatable) {

		super(dialog, "", Dialog.ModalityType.APPLICATION_MODAL);
		setBackground(new Color(255, 0, 0));
		this.table = datatable;
		this.semoryear = sem;
		this.Coursecode = courseCode;
		setResizable(false);
		setSize(631, 565);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Subject Code");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel.setBounds(30, 71, 132, 35);
		contentPanel.add(lblNewLabel);

		JLabel lblSubjectName = new JLabel("Subject Name");
		lblSubjectName.setForeground(Color.DARK_GRAY);
		lblSubjectName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSubjectName.setBounds(30, 134, 132, 35);
		contentPanel.add(lblSubjectName);

		JLabel lblCourseType = new JLabel("Course Type");
		lblCourseType.setForeground(Color.DARK_GRAY);
		lblCourseType.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCourseType.setBounds(30, 198, 132, 36);
		contentPanel.add(lblCourseType);

		JLabel lblTheoryMarks = new JLabel("Theory Marks");
		lblTheoryMarks.setForeground(Color.DARK_GRAY);
		lblTheoryMarks.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTheoryMarks.setBounds(30, 265, 132, 35);
		contentPanel.add(lblTheoryMarks);

		JLabel lblPracticalMarks = new JLabel("Practical Marks");
		lblPracticalMarks.setForeground(Color.DARK_GRAY);
		lblPracticalMarks.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblPracticalMarks.setBounds(30, 332, 132, 35);
		contentPanel.add(lblPracticalMarks);

		subjectcodefield = new JTextField();
		subjectcodefield.setEditable(false);
		subjectcodefield.setText(new SubjectData().createSubjectcode(Coursecode, sem));

		subjectcodefield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		subjectcodefield.setBounds(172, 70, 331, 40);
		contentPanel.add(subjectcodefield);
		subjectcodefield.setColumns(10);

		subjectnamefield = new JTextField();
		subjectnamefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					subjectnamefield.setFocusable(false);
					theorymarksfield.setFocusable(true);
				}
			}
		});
		subjectnamefield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				subjectnamefield.setFocusable(true);
			}
		});
		subjectnamefield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		subjectnamefield.setColumns(10);
		subjectnamefield.setBounds(172, 136, 331, 40);
		contentPanel.add(subjectnamefield);

		theorymarksfield = new JTextField();
		theorymarksfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					theorymarksfield.setFocusable(false);
					practicalmarksfield.setFocusable(true);
				}
			}
		});
		theorymarksfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				theorymarksfield.setFocusable(true);
			}
		});
		theorymarksfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		theorymarksfield.setColumns(10);

		theorymarksfield.setBounds(172, 267, 331, 40);
		contentPanel.add(theorymarksfield);

		practicalmarksfield = new JTextField();
		practicalmarksfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				practicalmarksfield.setFocusable(true);
			}
		});
		practicalmarksfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		practicalmarksfield.setColumns(10);
		practicalmarksfield.setBounds(172, 334, 331, 40);
		contentPanel.add(practicalmarksfield);

		coursetypecombo = new JComboBox<String>();
		coursetypecombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		coursetypecombo.setModel(new DefaultComboBoxModel<String>(new String[] { "---select---", "core", "optional" }));
		coursetypecombo.setFocusable(false);
		coursetypecombo.setBackground(Color.WHITE);
		coursetypecombo.setBounds(172, 199, 330, 40);
		contentPanel.add(coursetypecombo);

		JLabel headerlabel = new JLabel("   Add New Subject");
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setBackground(new Color(255, 0, 0));
		headerlabel.setOpaque(true);
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
		headerlabel.setBounds(10, 10, 497, 44);
		headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		contentPanel.add(headerlabel);

		addsubject = new JButton("Add Subject");
		addsubject.setBorder(new EmptyBorder(0, 0, 0, 0));
		addsubject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				HandCursor();

			}

			public void mouseExited(MouseEvent e) {
				DefaultCursor();
			}
		});

		addsubject.setBackground(Color.WHITE);
		addsubject.setForeground(Color.RED);
		addsubject.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addsubject.setBounds(363, 413, 139, 33);
		addsubject.addActionListener(this);
		addsubject.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addsubject.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addsubject.setFocusable(false);

		contentPanel.add(addsubject);

		JLabel borderlabel = new JLabel("");
		borderlabel.setBounds(0, 388, 512, 14);
		contentPanel.add(borderlabel);
		borderlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(192, 192, 192)));

		lblError = new JLabel("This is required question !");
		lblError.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Candara", Font.PLAIN, 17));
		lblError.setBounds(172, 107, 331, 30);
		lblError.setVisible(false);
		contentPanel.add(lblError);

	}

	public void HandCursor() {
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public void DefaultCursor() {
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		lblError.setVisible(false);
		lblError.setText("This is required question..!=-");
		if (subjectnamefield.getText().isEmpty()) {
			lblError.setVisible(true);
			lblError.setBounds(subjectnamefield.getX(), subjectnamefield.getY() + subjectnamefield.getHeight() - 5, 331,
					30);
		} else if (coursetypecombo.getSelectedIndex() == 0) {
			lblError.setVisible(true);
			lblError.setBounds(coursetypecombo.getX(), coursetypecombo.getY() + coursetypecombo.getHeight() - 5, 331,
					30);
		}

		else if (theorymarksfield.getText().isEmpty() || subjectnamefield.getText().isEmpty()) {
			lblError.setVisible(true);
			lblError.setBounds(theorymarksfield.getX(), theorymarksfield.getY() + theorymarksfield.getHeight() - 5, 331,
					30);
		} else if (practicalmarksfield.getText().isEmpty()) {
			lblError.setVisible(true);
			lblError.setBounds(practicalmarksfield.getX(),
					practicalmarksfield.getY() + practicalmarksfield.getHeight() - 5, 331, 30);
		} else if (new SubjectData().isExist(Coursecode, semoryear, subjectnamefield.getText())) {
			lblError.setVisible(true);
			lblError.setBounds(subjectnamefield.getX(), subjectnamefield.getY() + subjectnamefield.getHeight() - 5, 331,
					30);
			lblError.setText("Subject name already exist..!");
		} else {
			if (e.getSource() == addsubject) {
				String numbererror = "";
				try {
					String subjectcode = subjectcodefield.getText();
					String subjectname = subjectnamefield.getText();

					String subjecttype = (String) coursetypecombo.getSelectedItem();
					numbererror = "theorymarks";
					int theorymarks = Integer.parseInt(theorymarksfield.getText());
					numbererror = "practicalmarks";
					int practicalmarks = Integer.parseInt(practicalmarksfield.getText());
					Subject su = new Subject();
					su.setSubjectName(subjectname);
					su.setSubjectCode(subjectcode);
					su.setMaxPracticalMarks(practicalmarks);
					su.setCourseCode(Coursecode);
					su.setSemorYear(semoryear);
					su.setSubjectType(subjecttype);
					su.setMaxTheoryMarks(theorymarks);
					int result = new SubjectData().addSubject(su);
					if (result == 1) {
						ResultSet st = new SubjectData().getSubjectinfo(Coursecode, semoryear);
						table.setModel(DbUtils.resultSetToTableModel(st));
						DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
						centerRenderer.setHorizontalAlignment(JLabel.CENTER);
						table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
						table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
						table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
						table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
						table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
						table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
						table.getColumnModel().getColumn(0).setMaxWidth(200);

						table.getColumnModel().getColumn(1).setMaxWidth(400);
						table.getColumnModel().getColumn(2).setMaxWidth(200);
						table.getColumnModel().getColumn(3).setMaxWidth(200);
						table.getColumnModel().getColumn(4).setMaxWidth(200);
						table.getColumnModel().getColumn(5).setMaxWidth(200);
						this.dispose();
					}
				} catch (NumberFormatException exp) {
					if (numbererror.equals("theorymarks")) {
						lblError.setBounds(theorymarksfield.getX(),
								theorymarksfield.getY() + theorymarksfield.getHeight(), 331, 30);
					}
					if (numbererror.equals("practicalmarks")) {
						lblError.setBounds(practicalmarksfield.getX(),
								practicalmarksfield.getY() + practicalmarksfield.getHeight(), 331, 30);
					}
					lblError.setVisible(true);
					lblError.setText("Characters are not allowed !");
				}
			}
		}

	}

}
