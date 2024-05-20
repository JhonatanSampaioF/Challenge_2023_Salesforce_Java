package fiap.tds.repositories;

import fiap.tds.models.ConexaoOracle;
import fiap.tds.models.Feedback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static fiap.tds.Main.LOGGER;

public class FeedbackRepositoryOracle {

    public static final String URL_CONNECTION = ConexaoOracle.URL_CONNECTION;
    public static final String USER = ConexaoOracle.USER;
    public static final String PASSWORD = ConexaoOracle.PASSWORD;
    private static final String TB_NAME = "TB_FEEDBACK";
    private static final Map<String, String> TB_COLUMNS = Map.of(
        "ID", "id_feedback",
        "LIKE_DISLIKE", "like_dislike_feedback",
        "DESCRICAO", "desc_feedback",
        "DATA", "dt_feedback",
        "CLIENTE", "fk_cliente_id_clie"
    );
    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
    }

    public FeedbackRepositoryOracle(){
    }

    //Scanner scanner = new Scanner(System.in);

    public Feedback findById(int id){
        var feedback = new Feedback();
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
                var like_dislike = rs.getInt(TB_COLUMNS.get("LIKE_DISLIKE"));
                var desc = rs.getString(TB_COLUMNS.get("DESCRICAO"));
                var data = rs.getDate(TB_COLUMNS.get("DATA"));
                var cliente = rs.getInt(TB_COLUMNS.get("CLIENTE"));
                feedback = new Feedback(_id,like_dislike,desc,data,cliente);
                LOGGER.info("Feedback retornado com sucesso");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao buscar feedback: {0}", e.getMessage()));
        }
        /*System.out.println("ID: " + feedback.getId());
        System.out.println("Like/Dislike: " + feedback.getLike_dislikeFB());
        System.out.println("Descrição: " + feedback.getDescFB());
        System.out.println("Data: " + feedback.getDt_FB());
        System.out.println("ID cliente: " + feedback.getClienteId());
        System.out.println("--------------------------------");*/

        return feedback;
    }

    public void create(Feedback feedback){
        try(
            var conn = getConnection();
            var stmt = conn.prepareStatement(
                "INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)"
                    .formatted(
                        TB_NAME,
                        TB_COLUMNS.get("LIKE_DISLIKE"),
                        TB_COLUMNS.get("DESCRICAO"),
                        TB_COLUMNS.get("CLIENTE")
                    )
            )
        )
        {
            /*System.out.println("Insira o id do feedback: ");
            feedback.setId(scanner.nextInt());
            System.out.println("Insira o like-1/dislike-0 do feedback: ");
            feedback.setLike_dislikeFB(scanner.nextInt());
            System.out.println("Insira a descrição do feedback: ");
            feedback.setDescFB(scanner.next());
            System.out.println("Insira a data do feedback (DD/MM/YYYY): ");
            String dateinput = scanner.next();
            String[] datadiv = dateinput.split("/");
            LocalDate myObj = LocalDate.of(Integer.parseInt(datadiv[2]), Integer.parseInt(datadiv[1]), Integer.parseInt(datadiv[0]));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = myObj.format(formatter);
            Date sqlDate = Date.valueOf(formattedDate);
            feedback.setDt_FB(sqlDate);
            System.out.println("Insira o id do cliente: ");
            feedback.setClienteId(scanner.next());*/
            stmt.setInt(1, feedback.getLike_dislike_feedback());
            stmt.setString(2, feedback.getDesc_feedback());
            stmt.setInt(3, feedback.getFk_cliente_id_clie());
            var rs = stmt.executeUpdate();
            if (rs == 1){
                System.out.println("Feedback criado com sucesso!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao criar feedback: {0}", e.getMessage()));
        }
    }

    public List<Feedback> readAll(){
        var lista = new ArrayList<Feedback>();
        try (
            var conn = getConnection();
            var stmt = conn.prepareStatement("SELECT * FROM "+TB_NAME)
        )
        {
            var rs = stmt.executeQuery();
            while (rs.next()){
                lista.add(
                    new Feedback(
                        rs.getInt(TB_COLUMNS.get("ID")),
                        rs.getInt(TB_COLUMNS.get("LIKE_DISLIKE")),
                        rs.getString(TB_COLUMNS.get("DESCRICAO")),
                        rs.getDate(TB_COLUMNS.get("DATA")),
                        rs.getInt(TB_COLUMNS.get("CLIENTE"))
                    )
                );
            }
            LOGGER.info("Feedbacks retornados com sucesso");
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao buscar feedbacks: {0}", e.getMessage()));
        }
        /*for (Feedback feedback : lista) {
            System.out.println("ID: " + feedback.getId());
            System.out.println("Like-1/Dislike-0: " + feedback.getLike_dislikeFB());
            System.out.println("Descrição: " + feedback.getDescFB());
            System.out.println("Data: " + feedback.getDt_FB());
            System.out.println("ID cliente: " + feedback.getClienteId());
            System.out.println("--------------------------------");
        }*/
        return lista;
    }

    public void update(Feedback feedback){
        try(
            var conn = getConnection();
            var stmt = conn.prepareStatement(
                "UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?"
                    .formatted(
                        TB_NAME,
                        TB_COLUMNS.get("LIKE_DISLIKE"),
                        TB_COLUMNS.get("DESCRICAO"),
                        TB_COLUMNS.get("CLIENTE"),
                        TB_COLUMNS.get("ID")
                    )
            )
        )
        {
            /*System.out.println("Insira o id do feedback que deseja alterar: ");
            feedback.setId(scanner.nextInt());
            System.out.println("Insira o like-1/dislike-0 do feedback: ");
            feedback.setLike_dislikeFB(scanner.nextInt());
            System.out.println("Insira a nova descrição do feedback: ");
            feedback.setDescFB(scanner.next());
            System.out.println("Insira a nova data do feedback (DD/MM/YYYY): ");
            String dateinput = scanner.next();
            String[] datadiv = dateinput.split("/");
            LocalDate myObj = LocalDate.of(Integer.parseInt(datadiv[2]), Integer.parseInt(datadiv[1]), Integer.parseInt(datadiv[0]));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = myObj.format(formatter);
            Date sqlDate = Date.valueOf(formattedDate);
            feedback.setDt_FB(sqlDate);
            System.out.println("Insira o novo id do cliente: ");
            feedback.setClienteId(scanner.next());*/
            stmt.setInt(1, feedback.getLike_dislike_feedback());
            stmt.setString(2, feedback.getDesc_feedback());
            stmt.setInt(3, feedback.getFk_cliente_id_clie());
            stmt.setInt(4, feedback.getId());
            var rs = stmt.executeUpdate();
            if (rs == 1){
                LOGGER.info("Feedback atualizado com sucesso!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao atualizar feedback: {0}", e.getMessage()));
        }
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
                LOGGER.info("Feedback removido com sucesso!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao deletar feedback: {0}", e.getMessage()));
        }
    }
}
