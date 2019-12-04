package br.isa.mvc.control;

import br.isa.mvc.model.UserModel;
import br.isa.mvc.view.UserView;

public class UserControl {
    private UserView view;
    private UserModel model;

    public UserControl(UserView view, UserModel model) {
        this.model = model;
        this.view = view;
        model.attach(view);
        view.setModel(model);
        view.setControl(this);
    }

    public void showScreen() {
        view.show();
        model.notifyObservers();
    }

    public void delete() {
        model.delete();
        view.setMessageStatusBar("Usuário excluído");
        view.getDeleteButton().setEnabled(false);
    }

    public void _new() {
        model.newRegister();
        view.getDeleteButton().setEnabled(false);
        view.setMessageStatusBar("Novo usuário");
    }

    public void save() {
        this.model.setUsername(this.view.getUsername());
        this.model.setName(this.view.getName());
        this.model.setEmail(this.view.getEmail());
        this.model.setPassword(this.view.getPassword());
        this.model.setSuperuser(this.view.getSuperuser());
        try {
            this.model.save();
            this.view.setMessageStatusBar("Usuário registrado");
            this.view.getDeleteButton().setEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
            view.setMessageStatusBar("Error: " + e.getMessage());
        }
    }
}