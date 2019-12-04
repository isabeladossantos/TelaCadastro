package br.isa;

import br.isa.mvc.model.UserModel;
import br.isa.mvc.view.UserView;
import br.isa.mvc.control.UserControl;

public class Main {
    public static void main(String[] args) {
        UserModel model = new UserModel();
        UserControl control = new UserControl(new UserView(), model);
        control.showScreen();
    }
}