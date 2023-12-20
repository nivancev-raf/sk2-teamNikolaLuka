package nikolalukatrening.GUI2.login;

import lombok.Getter;
import lombok.Setter;
import nikolalukatrening.GUI2.client.ClientCreateDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

@Getter
@Setter
@Service
public class SignUp extends JFrame {

    private JPanel jPanel1;
    private JButton jButton1, jButton2;
    private JLabel jLabel4, jLabel5, jLabel6, jLabel7, jLabel8,jlabel9,jlabel10,jlabel11;
    private JPasswordField jPasswordField1;
    private JTextField jTextField1, jTextField2,jTextField3,jTextField4,jTextField5 ;
    private RestTemplate SignUpServiceRestTemplate;
    public SignUp(RestTemplate SignUpServiceRestTemplate) {
        this.SignUpServiceRestTemplate = SignUpServiceRestTemplate;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign Up");
        setPreferredSize(new Dimension(400, 700));

        jPanel1 = new JPanel(new GridBagLayout());
        jPanel1.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        jLabel4 = new JLabel("SIGN UP");
        jLabel4.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabel4.setForeground(new Color(0, 102, 102));

        jLabel5 = new JLabel("Name:");
        jLabel5.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jTextField1 = new JTextField(20);
        jTextField1.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jlabel9 = new JLabel("Lastname:");
        jlabel9.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jTextField2 = new JTextField(20);
        jTextField2.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jlabel10 = new JLabel("Date of birth:");
        jlabel10.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jTextField3 = new JTextField(20);
        jTextField3.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jLabel6 = new JLabel("Email:");
        jLabel6.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jTextField4 = new JTextField(20);
        jTextField4.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jlabel11 = new JLabel("Username:");
        jlabel11.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jTextField5 = new JTextField(20);
        jTextField5.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jLabel7 = new JLabel("Password");
        jLabel7.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jPasswordField1 = new JPasswordField(20);
        jPasswordField1.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jButton1 = new JButton("Sign Up");
        jButton1.setBackground(new Color(0, 102, 102));
        jButton1.setForeground(Color.WHITE);
        jButton1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jButton1.addActionListener(evt -> jButton1ActionPerformed());

        jLabel8 = new JLabel("I've an account");
        jLabel8.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jButton2 = new JButton("Login");
        jButton2.setForeground(new Color(255, 51, 51));
        jButton2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jButton2.addActionListener(evt -> jButton2ActionPerformed());

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        jPanel1.add(jLabel4, gbc);
        jPanel1.add(jLabel5, gbc);
        jPanel1.add(jTextField1, gbc);
        jPanel1.add(jlabel9, gbc);
        jPanel1.add(jTextField2, gbc);
        jPanel1.add(jlabel10, gbc);
        jPanel1.add(jTextField3, gbc);
        jPanel1.add(jLabel6, gbc);
        jPanel1.add(jTextField4, gbc);
        jPanel1.add(jlabel11, gbc);
        jPanel1.add(jTextField5, gbc);
        jPanel1.add(jLabel7, gbc);
        jPanel1.add(jPasswordField1, gbc);
        jPanel1.add(jButton1, gbc);

        gbc.fill = GridBagConstraints.NONE;
        jPanel1.add(jLabel8, gbc);
        jPanel1.add(jButton2, gbc);

        add(jPanel1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void jButton2ActionPerformed() {
        // Kreiranje instance Login prozora
        Login loginFrame = new Login();
        // Postavljanje Login prozora da bude vidljiv
        loginFrame.setVisible(true);
        // Zatvaranje trenutnog (SignUp) prozora
        this.dispose();
    }

//    private RequestEntity<ClientCreateDto> clientCreateDto(){
//        String name = jTextField1.getText();
//        String lastname = jTextField2.getText();
//        String dateOfBirth = jTextField3.getText();
//        String email = jTextField4.getText();
//        String username = jTextField5.getText();
//        String password = new String(jPasswordField1.getPassword());
//        ClientCreateDto clientCreateDto = new ClientCreateDto(username,password,email,dateOfBirth,name,lastname);
//        RequestEntity<ClientCreateDto> clientCreateDtoRequestEntity;
//        clientCreateDtoRequestEntity = new RequestEntity<ClientCreateDto>();
//        return clientCreateDtoRequestEntity;
//    }

    private RequestEntity<ClientCreateDto> clientCreateDtoRequestEntity() {
        // Preuzmite podatke iz tekstualnih polja
        String username = jTextField5.getText();
        String password = new String(jPasswordField1.getPassword());
        String email = jTextField4.getText();
        String dateOfBirth = jTextField3.getText();
        String name = jTextField1.getText();
        String lastname = jTextField2.getText();

        // Kreirajte DTO objekat
        ClientCreateDto clientCreateDto = new ClientCreateDto(username, password, email, dateOfBirth, name, lastname);

        // Kreirajte header-e za zahtev
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Kreirajte RequestEntity koristeći ClientCreateDto i header-e
        RequestEntity<ClientCreateDto> requestEntity = new RequestEntity<>(clientCreateDto, headers, HttpMethod.POST, URI.create("/client/register"));

        return requestEntity;
    }

//    private void jButton1ActionPerformed() {
//        ResponseEntity<ClientCreateDto> clientDtoResponseEntity = null;
//        try {
//            clientDtoResponseEntity = SignUpServiceRestTemplate.exchange("/client/register/"
//                    , HttpMethod.POST, clientCreateDto(), ClientCreateDto.class);
//        } catch (HttpClientErrorException e) {
//            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
//                throw new NotFoundException(String.format("Projection with id: %d not found.", reservationCreateDto.getProjectionId()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void jButton1ActionPerformed() {
        // Kreirajte RequestEntity
        RequestEntity<ClientCreateDto> requestEntity = clientCreateDtoRequestEntity();

        try {
            // Pošaljite zahtev koristeći RestTemplate
            ResponseEntity<ClientCreateDto> response = SignUpServiceRestTemplate.exchange(
                    "http://localhost:8080/client/register",
                    HttpMethod.POST,
                    requestEntity,
                    ClientCreateDto.class
            );

            // Obrada odgovora
            if (response.getStatusCode() == HttpStatus.CREATED) {
                JOptionPane.showMessageDialog(this, "Uspešna registracija!", "Status", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Registracija nije uspešna.", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HttpClientErrorException e) {
            JOptionPane.showMessageDialog(this, "Greška: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}