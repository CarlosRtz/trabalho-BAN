import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaModel {
    private String insert = "INSERT INTO disciplonas (codDisciplina, cargaHoraria, nome) VALUES (?,?,?)";
    private String select = "SELECT * FROM disciplinas";

    public void insert(Disciplina d, Connection con){
        try{
            PreparedStatement st = con.prepareStatement(insert);
            st.setInt(1, d.getCodDisciplina());
            st.setInt(2, d.getCargaHoraria());
            st.setString(3, d.getNome());
            st.execute();
            st.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Disciplina> select(Connection con){
        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                Disciplina d = new Disciplina(rs.getInt("codDisciplina"), rs.getInt("cargaHoraria"), rs.getString("nome"));
                disciplinas.add(d);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return disciplinas;
    }
}
