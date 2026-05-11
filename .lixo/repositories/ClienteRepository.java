package repositories;

import database.LigacaoBD;
import models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    // TODOS
    public static List<Cliente> todos() throws Exception {

        List<Cliente> lista = new ArrayList<>();

        Connection con = LigacaoBD.ligar();

        Statement st = con.createStatement();

        ResultSet rs =  st.executeQuery("SELECT * FROM clientes");

        while(rs.next()) {

            Cliente c = new Cliente();

            c.setId(rs.getInt("id"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            c.setTelefone(rs.getString("telefone"));

            lista.add(c);
        }

        rs.close();
        st.close();
        con.close();

        return lista;
    }


    // GUARDAR
    public static void guardar(Cliente cliente) throws Exception {

        Connection con = LigacaoBD.ligar();

        String sql = """
            INSERT INTO clientes
            (nome,email,telefone)
            VALUES (?,?,?)
        """;

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, cliente.getNome());
        ps.setString(2, cliente.getEmail());
        ps.setString(3, cliente.getTelefone());

        ps.executeUpdate();

        ps.close();
        con.close();
    }


    // BUSCAR POR ID
    public static Cliente buscarPorId(int id) throws Exception {

        Connection con =  LigacaoBD.ligar();

        String sql =  "SELECT * FROM clientes WHERE id=?";

        PreparedStatement ps =  con.prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        Cliente c = new Cliente();

        if (rs.next()) {

            c.setId(rs.getInt("id"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            c.setTelefone(rs.getString("telefone"));
        }

        rs.close();
        ps.close();
        con.close();

        return c;
    }


    // ATUALIZAR
    public static void atualizar(Cliente cliente) throws Exception {

        Connection con =  LigacaoBD.ligar();

        String sql = """
            UPDATE clientes
            SET nome=?,
                email=?,
                telefone=?
            WHERE id=?
        """;

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, cliente.getNome());
        ps.setString(2, cliente.getEmail());
        ps.setString(3, cliente.getTelefone());
        ps.setInt(4, cliente.getId());

        ps.executeUpdate();

        ps.close();
        con.close();
    }


    // APAGAR
    public static void apagar(int id)   throws Exception {

        Connection con =  LigacaoBD.ligar();

        String sql = "DELETE FROM clientes WHERE id=?";

        PreparedStatement ps =  con.prepareStatement(sql);

        ps.setInt(1, id);

        ps.executeUpdate();

        ps.close();
        con.close();
    }
}