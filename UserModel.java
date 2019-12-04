package br.isa.mvc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.isa.data.dao.UserDAO;
import br.isa.data.entities.User;
import br.isa.mvc.util.Observer;
import br.isa.mvc.util.Subject;

public class UserModel extends User implements Subject {
    private List<Observer> observers;

    public UserModel() {
        observers = new ArrayList<Observer>();
    }

    public void newRegister() {
        this.id = 0;
        this.name = "";
        this.email = "";
        this.password = "";
        this.superuser = false;
        notifyObservers();
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override

    public void notifyObservers() {
        for (Observer o : this.observers) {
            o.update(this, this);
        }
    }

    public void save() throws Exception {
        if (this.name == null || this.name.isEmpty()) {
            throw new Exception("Nome inválido");
        }
        if (this.email == null || this.email.isEmpty()) {
            throw new Exception("E-mail inválido");
        }
        if (this.password == null || this.password.isEmpty()) {
            throw new Exception("Senha inválido");
        }
        UserDAO userDAO = new UserDAO();
        User user = new User();
        if (this.id == 0) {
            user.setName(this.name);
            user.setEmail(this.email);
            user.setPassword(this.password);
            user.setSuperuser(this.superuser);
            userDAO.insert(user);
        } else {
            user = userDAO.getById(this.id);
            user.setId(this.id);
            user.setName(this.name);
            user.setEmail(this.email);
            user.setPassword(this.password);
            user.setSuperuser(this.superuser);
            userDAO.update(user);
        }
    }

    public void delete() {
        UserDAO userDAO = new UserDAO();
        userDAO.delete(this.id);

        newRegister();
        notifyObservers();
    }
}