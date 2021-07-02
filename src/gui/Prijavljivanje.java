package gui;

import repository.Fabrika;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Prijavljivanje extends JFrame {
    private static final long serialVersionUID = 1L;
    private JFrame frame;
    private JPanel contentPane;
    private final JTextField usernameText;
    private final JPasswordField passwordField;
    private JLabel label;


    public static void main(String[] args, Fabrika repozitorijum) {
        EventQueue.invokeLater(() -> {
            try {
                Prijavljivanje prijava = new Prijavljivanje(repozitorijum);
                prijava.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public Prijavljivanje(Fabrika repozitorijum) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Prijavljivanje.class.getResource("/slike/logo.jpg")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 920, 644);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        passwordField = new JPasswordField();
        passwordField.setBounds(575, 115, 246, 37);
        passwordField.setFont(new Font("Arial Black", Font.PLAIN, 15));
        passwordField.setForeground(Color.WHITE);
        passwordField.setOpaque(false);
        contentPane.add(passwordField);

        usernameText = new JTextField();
        usernameText.setBounds(575, 49, 246, 37);
        usernameText.setFont(new Font("Arial Black", Font.PLAIN, 15));
        usernameText.setForeground(Color.WHITE);
        usernameText.setOpaque(false);
        contentPane.add(usernameText);
        usernameText.setColumns(10);
        JButton button = new JButton("Prijavi se");
        button.addActionListener(e -> {
            String username = usernameText.getText();
            String password = String.valueOf(passwordField.getPassword());
            /*if (!repo.getChecks().isUsernameValid(username)) {
                JOptionPane.showMessageDialog(frame,"Korisnicko ime nije ispravno. Molim Vas, pokusajte ponovo!","Greska",JOptionPane.ERROR_MESSAGE);
            } else if (!repo.getChecks().isPasswordValid(username, password)) {
                JOptionPane.showMessageDialog(frame,"Lozinka nije ispravna. Molim Vas, pokusajte ponovo!","Greska",JOptionPane.ERROR_MESSAGE);
            } else if (repo.getChecks().checkIfInputIsBlank(username) || repo.getChecks().checkIfInputIsBlank(password)) {
                JOptionPane.showMessageDialog(frame,"Morate uneti podatke. Molim Vas, pokusajte ponovo!","Greska",JOptionPane.ERROR_MESSAGE);
            } else {
                User user = repo.getUserManager().getUserByUsername(username);
                if (user.getRole().equals("administrator")) {
                    JOptionPane.showMessageDialog(contentPane,"Ulogovali ste se kao " + user.getName() + " " + user.getSurname() + ", administrator.", "Uspesno prijavljivanje", JOptionPane.INFORMATION_MESSAGE);
                    AdministratorMenu administrator = new AdministratorMenu(repo);
                    administrator.setVisible(true);
                    MainFrame.this.setVisible(false);
                    MainFrame.this.dispose();
                } else if (user.getRole().equals("medical_technician")) {
                    JOptionPane.showMessageDialog(contentPane,"Ulogovali ste se kao " + user.getName() + " " + user.getSurname() + ", medicinski tehnicar.", "Uspesno prijavljivanje", JOptionPane.INFORMATION_MESSAGE);
                    MedicalTechnicianMenu medTech = new MedicalTechnicianMenu(repo, user);
                    medTech.setVisible(true);
                    MainFrame.this.setVisible(false);
                    MainFrame.this.dispose();
                } else if (user.getRole().equals("patient")) {
                    JOptionPane.showMessageDialog(contentPane,"Ulogovali ste se kao " + user.getName() + " " + user.getSurname() + ", pacijent.", "Uspesno prijavljivanje", JOptionPane.INFORMATION_MESSAGE);
                    PatientMenu patient = new PatientMenu(repo, user);
                    patient.setVisible(true);
                    MainFrame.this.setVisible(false);
                    MainFrame.this.dispose();
                } else if (user.getRole().equals("laboratory_technician")) {
                    JOptionPane.showMessageDialog(contentPane,"Ulogovali ste se kao " + user.getName() + " " + user.getSurname() + ", laborant.", "Uspesno prijavljivanje", JOptionPane.INFORMATION_MESSAGE);
                    LaboratoryTechnicianMenu labTech = new LaboratoryTechnicianMenu(repo, user);
                    labTech.setVisible(true);
                    MainFrame.this.setVisible(false);
                    MainFrame.this.dispose();
                }
            }*/
        });
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial Black", Font.PLAIN, 26));
        button.setBounds(575, 189, 246, 60);
        contentPane.add(button);


        JLabel passwordLabel = new JLabel("");
        passwordLabel.setBounds(500, 100, 65, 58);
        passwordLabel.setIcon(new ImageIcon(Prijavljivanje.class.getResource("/slike/lozinkaLogo.png")));
        contentPane.add(passwordLabel);

        JLabel usernameLabel = new JLabel("");
        usernameLabel.setBounds(518, 49, 45, 38);
        usernameLabel.setIcon(new ImageIcon(Prijavljivanje.class.getResource("/slike/korisnikLogo.png")));
        contentPane.add(usernameLabel);

        label = new JLabel("");
        label.setBackground(Color.BLUE);
        label.setIcon(new ImageIcon(Prijavljivanje.class.getResource("/slike/prijavaPozadina.png")));
        label.setBounds(-49, 0, 951, 600);
        contentPane.add(label);
    }

}
