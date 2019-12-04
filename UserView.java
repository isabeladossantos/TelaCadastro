package br.isa.mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import br.isa.mvc.control.UserControl;
import br.isa.mvc.model.UserModel;
import br.isa.mvc.util.Observer;
import br.isa.mvc.util.Subject;

public class UserView extends JFrame implements Observer {
    private UserModel model;
    private UserControl control;
    private JTextField idField;
    private JTextField usernameField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField passwordField;
    private JCheckBox superuserCheck;
    private JButton editButton;
    private JButton saveButton;
    private JButton newButton;
    private JButton deleteButton;
    private JLabel status;

    public UserView() {
        setSize(400, 280);
        setTitle("Cadastro de usuário");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildLayout();
    }

    public void setModel(UserModel model) {
        this.model = model;
    }

    public void setControl(UserControl control) {
        this.control = control;
    }

    private void buildLayout() {
        // Terminar 
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 0, 20)
                , BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Detalhes do usuário")
                        , BorderFactory.createEmptyBorder(10, 10, 10, 10))));
        idField = new JTextField(2);
        idField.setEnabled(false);
        usernameField = new JTextField(15);
        nameField = new JTextField(24);
        emailField = new JTextField(24);
        passwordField = new JTextField(20);
        superuserCheck = new JCheckBox("Superuser");

        editButton = new JButton("Editar");
        editButton.setToolTipText("Editar informações do usuário");
        editButton.addActionListener(e -> edit());

        saveButton = new JButton("Salvar");
        saveButton.setToolTipText("Salvar alterações");
        saveButton.setEnabled(false);
        saveButton.addActionListener(e -> save());

        newButton = new JButton("Novo");
        newButton.setToolTipText("Criar usuário");
        newButton.addActionListener(e -> _new());

        deleteButton = new JButton("Excluir");
        deleteButton.setToolTipText("Excluir usuário");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(e -> delete());

        JLabel label = new JLabel("Código", JLabel.RIGHT);
        label.setPreferredSize(new Dimension(45, 16));
        panel.add(label);
        panel.add(idField);

        label = new JLabel("Username",JLabel.RIGHT);
        label.setPreferredSize(new Dimension(60,16));
        panel.add(label);
        panel.add(usernameField);

        label = new JLabel("Nome",JLabel.RIGHT);
        label.setPreferredSize(new Dimension(38,16));
        panel.add(label);
        panel.add(nameField);

        label = new JLabel("E-mail",JLabel.RIGHT);
        label.setPreferredSize(new Dimension(43,16));
        panel.add(label);
        panel.add(emailField);

        label = new JLabel("Senha",JLabel.RIGHT);
        label.setPreferredSize(new Dimension(60,16));
        panel.add(label);
        panel.add(passwordField);

        panel.add(superuserCheck);

        label = new JLabel();
        label.setPreferredSize(new Dimension(160,16));
        panel.add(label);
        panel.add(editButton);
        panel.add(saveButton);
        panel.add(newButton);
        panel.add(deleteButton);

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 10));
        status = new JLabel("Incluir usuário");
        statusPanel.add(status);

        rootPanel.add(statusPanel, BorderLayout.SOUTH);
        rootPanel.add(panel, BorderLayout.CENTER);

        add(rootPanel);
    }

    private void showData() {
        idField.setText(Integer.toString(this.model.getId()));
        usernameField.setText(this.model.getUsername());
        nameField.setText(this.model.getName());
        emailField.setText(this.model.getEmail());
        passwordField.setText(this.model.getPassword());
        superuserCheck.setSelected(this.model.getSuperuser());

    }

    public void edit() {
        idField.setEnabled(true);
        saveButton.setEnabled(true);
        deleteButton.setEnabled(true);
    }

    public void save() {
        this.control.save();
        idField.setEnabled(false);
    }

    public void delete() {
        this.control.delete();
        idField.setEnabled(false);
    }

    public void _new() {
        this.control._new();
        idField.setEnabled(false);
    }

    public JButton getDeleteButton() {
        return this.deleteButton;
    }

    public void setMessageStatusBar(String status) {
        this.status.setText(status);
    }

    @Override
    public void update(Subject s, Object o) {
        this.model = (UserModel) o;
        showData();
    }

    public String getUsername() {
        return this.usernameField.getText();
    }

    public String getName() {
        return this.nameField.getText();
    }

    public String getEmail() {
        return this.emailField.getText();
    }

    public String getPassword() {
        return this.passwordField.getText();
    }

    public boolean getSuperuser() {
        return this.superuserCheck.isSelected();
    }
}