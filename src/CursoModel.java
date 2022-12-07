import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CursoModel {
    private String insert = "INSERT INTO Cursos (codCurso, nome) VALUES (?,?)";
    private String select = "SELECT * FROM Cursos";

    public void insert(Curso c, Connection con){
        try{
            PreparedStatement st = con.prepareStatement(insert);
            st.setInt(1, c.getCodCurso());
            st.setString(2, c.getNome());
            st.execute();
            st.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Curso> select(Connection con){
        List<Curso> cursos = new ArrayList<Curso>();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                Curso c = new Curso(rs.getInt("codCurso"), rs.getString("nome"));
                cursos.add(c);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return cursos;
    }
}
