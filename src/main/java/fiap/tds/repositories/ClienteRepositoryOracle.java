package fiap.tds.repositories;

import fiap.tds.models.Cliente;
import fiap.tds.models.ConexaoOracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import java.util.Scanner;

import static fiap.tds.Main.LOGGER;

public class ClienteRepositoryOracle {

    public static final String URL_CONNECTION = ConexaoOracle.URL_CONNECTION;
    public static final String USER = ConexaoOracle.USER;
    public static final String PASSWORD = ConexaoOracle.PASSWORD;
    private static final String TB_NAME = "TB_CLIENTE";
    private static final Map<String, String> TB_COLUMNS = Map.of(
        "ID", "id_clie",
        "NOME", "nm_clie",
        "SOBRENOME", "sobrenome",
        "EMAIL", "email",
        "EMPRESA", "empresa",
        "TAMANHO_EMPRESA", "tamanho_empresa",
        "PAIS", "pais",
        "CARGO", "cargo",
        "TELEFONE", "telefone"
    );

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
    }

    public ClienteRepositoryOracle(){
    }

    //Scanner scanner = new Scanner(System.in);

    public Cliente findById(int id){
        var cliente = new Cliente();
        try(
            var conn = getConnection();
            var stmt = conn.prepareStatement(
                "SELECT * FROM %s WHERE %s = ?"
                    .formatted(
                        TB_NAME,
                        TB_COLUMNS.get("ID")
                    )
            )
        )
        {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if(rs.next()){
                var _id = rs.getInt(TB_COLUMNS.get("ID"));
                var nome = rs.getString(TB_COLUMNS.get("NOME"));
                var sobrenome = rs.getString(TB_COLUMNS.get("SOBRENOME"));
                var email = rs.getString(TB_COLUMNS.get("EMAIL"));
                var empresa = rs.getString(TB_COLUMNS.get("EMPRESA"));
                var tamanhoEmpresa = rs.getInt(TB_COLUMNS.get("TAMANHO_EMPRESA"));
                var pais = rs.getString(TB_COLUMNS.get("PAIS"));
                var cargo = rs.getString(TB_COLUMNS.get("CARGO"));
                var telefone = rs.getString(TB_COLUMNS.get("TELEFONE"));
                cliente = new Cliente(_id,nome,sobrenome,email,empresa,tamanhoEmpresa,pais,cargo,telefone);
                LOGGER.info("Cliente retornado com sucesso");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao buscar cliente: {0}", e.getMessage()));
        }
        /*System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Sobrenome: " + cliente.getSobrenome());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Empresa: " + cliente.getEmpresa());
        System.out.println("Tamanho da Empresa: " + cliente.getTamanhoEmpresa());
        System.out.println("País: " + cliente.getPais());
        System.out.println("Idioma: " + cliente.getIdioma());
        System.out.println("Cargo: " + cliente.getCargo());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("--------------------------------");*/

        return cliente;
    }

    public void create(Cliente cliente){

        try(
            var conn = getConnection();
            var stmt = conn.prepareStatement(
                "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s) VALUES (?,?,?,?,?,?,?,?)"
                    .formatted(
                        TB_NAME,
                        TB_COLUMNS.get("NOME"),
                        TB_COLUMNS.get("SOBRENOME"),
                        TB_COLUMNS.get("EMAIL"),
                        TB_COLUMNS.get("EMPRESA"),
                        TB_COLUMNS.get("TAMANHO_EMPRESA"),
                        TB_COLUMNS.get("PAIS"),
                        TB_COLUMNS.get("CARGO"),
                        TB_COLUMNS.get("TELEFONE")
                    )
            )
        )
        {
            stmt.setString(1, cliente.getNm_clie());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getEmpresa());
            stmt.setInt(5, cliente.getTamanho_empresa());
            stmt.setString(6, cliente.getPais());
            stmt.setString(7, cliente.getCargo());
            stmt.setString(8, cliente.getTelefone());
            var rs = stmt.executeUpdate();
            if (rs == 1){
                LOGGER.info("Cliente criado com sucesso!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao criar cliente: {0}", e.getMessage()));
        }
    }

    public List<Cliente> readAll(){
        var lista = new ArrayList<Cliente>();
        try (
            var conn = getConnection();
            var stmt = conn.prepareStatement("SELECT * FROM "+TB_NAME)
        )
        {
            var rs = stmt.executeQuery();
            while (rs.next()){
                lista.add(
                    new Cliente(
                        rs.getInt(TB_COLUMNS.get("ID")),
                        rs.getString(TB_COLUMNS.get("NOME")),
                        rs.getString(TB_COLUMNS.get("SOBRENOME")),
                        rs.getString(TB_COLUMNS.get("EMAIL")),
                        rs.getString(TB_COLUMNS.get("EMPRESA")),
                        rs.getInt(TB_COLUMNS.get("TAMANHO_EMPRESA")),
                        rs.getString(TB_COLUMNS.get("PAIS")),
                        rs.getString(TB_COLUMNS.get("CARGO")),
                        rs.getString(TB_COLUMNS.get("TELEFONE"))
                    )
                );
            }
            LOGGER.info("Clientes retornados com sucesso");
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao buscar clientes: {0}", e.getMessage()));
        }
        /*for (Cliente cliente : lista) {
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Sobrenome: " + cliente.getSobrenome());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Empresa: " + cliente.getEmpresa());
            System.out.println("Tamanho da Empresa: " + cliente.getTamanhoEmpresa());
            System.out.println("País: " + cliente.getPais());
            System.out.println("Idioma: " + cliente.getIdioma());
            System.out.println("Cargo: " + cliente.getCargo());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("--------------------------------");
        }*/
        return lista;
    }

    public int update(Cliente cliente){
        try(
            var conn = getConnection();
            var stmt = conn.prepareStatement(
                "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?"
                    .formatted(
                        TB_NAME,
                        TB_COLUMNS.get("NOME"),
                        TB_COLUMNS.get("SOBRENOME"),
                        TB_COLUMNS.get("EMAIL"),
                        TB_COLUMNS.get("EMPRESA"),
                        TB_COLUMNS.get("TAMANHO_EMPRESA"),
                        TB_COLUMNS.get("PAIS"),
                        TB_COLUMNS.get("CARGO"),
                        TB_COLUMNS.get("TELEFONE"),
                        TB_COLUMNS.get("ID")
                    )
            )
        )
        {
            /*System.out.println("Insira o id do cliente que deseja alterar: ");
            cliente.setId(scanner.next());
            System.out.println("Insira o novo nome do cliente: ");
            cliente.setNome(scanner.next());
            System.out.println("Insira o novo sobrenome do cliente: ");
            cliente.setSobrenome(scanner.next());
            System.out.println("Insira o novo email do cliente: ");
            cliente.setEmail(scanner.next());
            System.out.println("Insira o novo nome da empresa: ");
            cliente.setEmpresa(scanner.next());
            System.out.println("Insira o novo tamanho da empresa: ");
            cliente.setTamanhoEmpresa(scanner.nextInt());
            System.out.println("Insira o novo país da empresa: ");
            cliente.setPais(scanner.next());
            System.out.println("Insira o novo idioma do cliente: ");
            cliente.setIdioma(scanner.next());
            System.out.println("Insira o novo cargo do cliente: ");
            cliente.setCargo(scanner.next());
            System.out.println("Insira o novo telefone do cliente");
            cliente.setTelefone(scanner.next());*/
            stmt.setString(1, cliente.getNm_clie());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getEmpresa());
            stmt.setInt(5, cliente.getTamanho_empresa());
            stmt.setString(6, cliente.getPais());
            stmt.setString(7, cliente.getCargo());
            stmt.setString(8, cliente.getTelefone());
            stmt.setInt(9, cliente.getId());
            var rs = stmt.executeUpdate();
            if (rs == 1){
                LOGGER.info("Especialista atualizado com sucesso!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao atualizar cliente: {0}", e.getMessage()));
        }
        return 0;
    }

    public void delete(int id){
        try (
            var conn = getConnection();
            var stmt = conn.prepareStatement(
                "DELETE FROM %s WHERE %s = ?"
                    .formatted(
                        TB_NAME,
                        TB_COLUMNS.get("ID")
                    )
            )
        )
        {
            stmt.setInt(1, id);
            var rs = stmt.executeUpdate();
            if (rs == 1){
                LOGGER.info("Cliente removido com sucesso!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao deletar cliente: {0}", e.getMessage()));
        }
    }
}
