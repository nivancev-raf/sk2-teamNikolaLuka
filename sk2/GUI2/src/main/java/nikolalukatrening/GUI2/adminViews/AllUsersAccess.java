package nikolalukatrening.GUI2.adminViews;


import nikolalukatrening.GUI2.customTable.CustomTable;
import nikolalukatrening.GUI2.dto.ClientProfileEditorDto;
import nikolalukatrening.GUI2.dto.ManagerDto;
import nikolalukatrening.GUI2.service.impl.AdminServiceImpl;
import nikolalukatrening.GUI2.service.impl.RestTemplateServiceImpl;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AllUsersAccess extends JPanel {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JToolBar toolBar;
    private JPanel clientsPanel;
    private JPanel managersPanel;
    private CustomTable clientsTable;
    private JTable managersTable;
    private RestTemplate adminServiceRestTemplate;
    private RestTemplate activationServiceRestTemplate;
    private RestTemplate deActivationServiceRestTemplate;
    private RestTemplate managerRestTemplate;
    private AdminServiceImpl adminService;

    private RestTemplateServiceImpl restTemplateService;

    private Frame parentFrame;

        public AllUsersAccess(Frame parentFrame) {
        this.parentFrame = parentFrame;
        this.restTemplateService = new RestTemplateServiceImpl();
        this.adminService = new AdminServiceImpl();
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);
        this.toolBar = new JToolBar();
        this.clientsPanel = new JPanel(new BorderLayout());
        this.managersPanel = new JPanel(new BorderLayout());

        setLayout(new BorderLayout());
        setupToolbar();
        initComponents();
        setupClientsPanel();
        setupManagersPanel();
    }

    private void initComponents() {
        add(toolBar, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        cardPanel.add(clientsPanel, "Clients");
        cardPanel.add(managersPanel, "Managers");
    }

    private void setupToolbar() {
        JButton clientsButton = new JButton("Klijenti");
        clientsButton.addActionListener(e -> cardLayout.show(cardPanel, "Clients"));
        toolBar.add(clientsButton);

        JButton managersButton = new JButton("Menadžeri");
        managersButton.addActionListener(e -> cardLayout.show(cardPanel, "Managers"));
        toolBar.add(managersButton);

        JButton searchButton = new JButton("Zabrani");
        deActivationServiceRestTemplate = restTemplateService.setupRestTemplate(deActivationServiceRestTemplate);
        searchButton.addActionListener(e -> adminService.zabrani(clientsTable, deActivationServiceRestTemplate,parentFrame));
        toolBar.add(searchButton);

        JButton resetColorButton = new JButton("Odblokiraj");
        activationServiceRestTemplate = restTemplateService.setupRestTemplate(activationServiceRestTemplate);
        resetColorButton.addActionListener(e -> adminService.odblokiraj(clientsTable, activationServiceRestTemplate, parentFrame));
        toolBar.add(resetColorButton);
    }
    private void setupClientsPanel() {
        String[] clientColumns = new String[]{"id", "username", "email", "firstName", "lastName", "dateOfBirth", "reservedTraining",
                                              "cardNumber", "isActivated", "password", "activationToken", "role"};

        adminServiceRestTemplate = restTemplateService.setupRestTemplate(adminServiceRestTemplate);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);

        ResponseEntity<List<ClientProfileEditorDto>> response = adminServiceRestTemplate.exchange(
                "http://localhost:8080/api/client/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ClientProfileEditorDto>>() {});
        List<ClientProfileEditorDto> clients = response.getBody();

        // Create a data model for the table
        DefaultTableModel clientsModel = new DefaultTableModel(clientColumns, 0);

        // Populate the model with client data
        for (ClientProfileEditorDto client : clients) {
            Object[] row = new Object[]{
                    client.getId(),
                    client.getUser().getUsername(),
                    client.getUser().getEmail(),
                    client.getUser().getFirstName(),
                    client.getUser().getLastName(),
                    client.getUser().getDateOfBirth(),
                    client.getReservedTraining(),
                    client.getCardNumber(),
                    client.getIsActivated(),
                    client.getUser().getPassword(),
                    client.getActivationToken(),
                    client.getUser().getRole()
            };
            clientsModel.addRow(row);
        }

        // Set the model to the JTable and add it to the JScrollPane
        clientsTable = new CustomTable(clientsModel);
        JScrollPane scrollPane = new JScrollPane(clientsTable);
        clientsPanel.add(scrollPane, BorderLayout.CENTER);

    }

    private void setupManagersPanel() {
        String[] managerColumns = new String[]{"id", "username", "email", "firstName", "lastName", "dateOfHiring", "gymName"};


        managerRestTemplate = restTemplateService.setupRestTemplate(managerRestTemplate);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);

        ResponseEntity<List<ManagerDto>> response = managerRestTemplate.exchange(
                "http://localhost:8080/api/manager/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ManagerDto>>() {});
        List<ManagerDto> managers = response.getBody();

        // Create a data model for the table
        DefaultTableModel managersModel = new DefaultTableModel(managerColumns, 0);

        // Populate the model with client data
        for (ManagerDto manager : managers) {
            Object[] row = new Object[]{
                    manager.getId(),
                    manager.getUser().getUsername(),
                    manager.getUser().getEmail(),
                    manager.getUser().getFirstName(),
                    manager.getUser().getLastName(),
                    manager.getDateOfHiring(),
                    manager.getGymName()
            };
            managersModel.addRow(row);
        }
        managersTable = new JTable(managersModel);
        JScrollPane scrollPane = new JScrollPane(managersTable);
        managersPanel.add(scrollPane, BorderLayout.CENTER);
    }

}
