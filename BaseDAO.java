package br.isa.data.dao;

import java.sql.Connection;
import br.isa.data.base.ConnectionFactory;
public abstract class BaseDAO {
    public Connection getConnection() {
        return ConnectionFactory.getInstance().getConnection();
    }
}