import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaModel {
	public static int selectNewId(Connection con) throws SQLException {
		int newId = 1;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT max(codDisciplina) FROM disciplinas");
		while(rs.next()) {
			newId = rs.getInt(1) + 1;
		}
		return newId;
	}
	
//    public static void insert(Disciplina d, Connection con){
//        try{
//            PreparedStatement st = con.prepareStatement("INSERT INTO disciplinas (codDisciplina, cargaHoraria, nome) VALUES (?,?,?)");
//            st.setInt(1, d.getCodDisciplina());
//            st.setInt(2, d.getCargaHoraria());
//            st.setString(3, d.getNome());
//            st.execute();
//            st.close();
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//    }
    
    public static void insert(Disciplina d, Connection con) throws SQLException {
    	PreparedStatement st = con.prepareStatement("INSERT INTO disciplinas (codDisciplina, cargaHoraria, nome) VALUES (?,?,?)");
    	st.setInt(1, d.getCodDisciplina());
    	st.setInt(2, d.getCargaHoraria());
    	st.setString(3, d.getNome());
    	st.execute();
    	st.close();
    }

    public static void insertGrade(int codCurso, int codDisciplina, Connection con){
        try{
            PreparedStatement st = con.prepareStatement("INSERT INTO grade (codCurso, codDisciplina) VALUES (?,?)");
            st.setInt(1, codCurso);
            st.setInt(2, codDisciplina);
            st.execute();
            st.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static List<Disciplina> select(Connection con){
        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM disciplinas");
            while(rs.next()){
                Disciplina d = new Disciplina(rs.getInt("codDisciplina"), rs.getInt("cargaHoraria"), rs.getString("nome"));
                disciplinas.add(d);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return disciplinas;
    }

    public static List<Disciplina> listMinCargaHoraria(Connection con){
        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM disciplinas d WHERE cargaHoraria<=all(SELECT cargaHoraria FROM disciplinas)");
            while(rs.next()){
                disciplinas.add(new Disciplina(rs.getInt("codDisciplina"), rs.getInt("cargaHoraria"), rs.getString("nome")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return disciplinas;
    }
}
