package br.isa.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.isa.data.entities.User;

public class UserDAO extends BaseDAO {
    public void insert(User user) {
        String sql = "INSERT INTO usuarios(username, name, email, password, "
                + "superuser) VALUES (?, ?, ?, ?, ?)";

        try {
            try (Connection conn = getConnection();
                 PreparedStatement p = conn.prepareStatement(sql)) {
                p.setString(1, user.getUsername());
                p.setString(2, user.getName());
                p.setString(3, user.getEmail());
                p.setString(4, user.getPassword());
                p.setBoolean(5, user.getSuperuser());
                p.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User user) {
        String sql = "UPDATE usuarios SET name = ?, email = ?, password = ?,"
                + "superuser = ? WHERE id = ?";

        try {
            try (Connection conn = getConnection();
                 PreparedStatement p = conn.prepareStatement(sql)) {
                p.setString(1, user.getName());
                p.setString(2, user.getEmail());
                p.setString(3, user.getPassword());
                p.setBoolean(4, user.getSuperuser());
                p.setInt(5, user.getId());
                p.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try {
            try (Connection conn = getConnection();
                 PreparedStatement p = conn.prepareStatement(sql)) {
                p.setInt(1, id);
                p.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getById(int id) {
        String sql = "SELECT id, name, username, email, superuser"
                + "FROM usuarios WHERE id = ?";

        User user = null;
        try {
            try (Connection conn = getConnection();
                 PreparedStatement p = conn.prepareStatement(sql)) {
                p.setInt(1, id);
                ResultSet resultSet = p.executeQuery();
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt(1));
                    user.setName(resultSet.getString(2));
                    user.setUsername(resultSet.getString(3));
                    user.setEmail(resultSet.getString(4));
                    user.setSuperuser(resultSet.getBoolean(5));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> get() {
        String sql = "SELECT id, name, username, email, superuser"
                + "FROM user";
        List<User> usuarios = new ArrayList<>();
        try {
            try (Connection conn = getConnection();
                 PreparedStatement p = conn.prepareStatement(sql)) {
                ResultSet resultSet = p.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt(1));
                    user.setName(resultSet.getString(2));
                    user.setUsername(resultSet.getString(3));
                    user.setEmail(resultSet.getString(4));
                    user.setSuperuser(resultSet.getBoolean(5));
                    usuarios.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}