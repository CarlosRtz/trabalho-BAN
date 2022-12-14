import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CursoModel {
	   
//	public static int selectNewId(Connection con) {
//	    	int newId = 1;
//	    	try {
//	    		Statement st = con.createStatement();
//	    		ResultSet rs = st.executeQuery("SELECT max(codCurso) FROM cursos");
//	    		while(rs.next()) {
//	    			newId = rs.getInt(1);
//	    		}
//	    	}catch(SQLException e) {
//	    		System.out.println("Não foi possivel selecionar um novo id.");
//	    	}
//	    	return newId;
//	    }
	
	public static int selectNewId(Connection con) throws SQLException {
		int newId = 1;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT max(codCurso) FROM cursos");
		while(rs.next()) {
			newId = rs.getInt(1) + 1;
		}
		return newId;
	}
	
//    public static void insert(Curso c, Connection con){
//        try{
//            PreparedStatement st = con.prepareStatement("INSERT INTO cursos (codCurso, nome) VALUES (?,?)");
//            st.setInt(1, c.getCodCurso());
//            st.setString(2, c.getNome());
//            st.execute();
//            st.close();
//        }catch(SQLException e){
//            System.out.println("Nao foi possivel adicionar o curso ao banco de dados");
//        }
//    }
    
    public static void insert(Curso c, Connection con) throws SQLException{
    	PreparedStatement st = con.prepareStatement("INSERT INTO cursos (codCurso, nome) VALUES (?,?)");
    	st.setInt(1, c.getCodCurso());
    	st.setString(2, c.getNome());
    	st.execute();
    	st.close();
    }
    
    public static List<Curso> select(Connection con){
        List<Curso> cursos = new ArrayList<Curso>();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cursos");
            while(rs.next()){
                Curso c = new Curso(rs.getInt("codCurso"), rs.getString("nome"));
                cursos.add(c);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return cursos;
    }

    public static List<Disciplina> listDisciplinas(int codCurso, Connection con){
        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        try{
            PreparedStatement st = con.prepareStatement("SELECT codDisciplina, nome, cargaHoraria FROM disciplinas NATURAL JOIN grade where codCurso=?");
            st.setInt(1, codCurso);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                disciplinas.add(new Disciplina(rs.getInt("codDisciplina"), rs.getInt("cargaHoraria"), rs.getString("nome")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return disciplinas;
    }
    
    public static int getCargaHoraria(String nome, Connection con) {
    	int cargaHoraria = 0;
    	try {
    		PreparedStatement st = con.prepareStatement(
    				"SELECT sum(cargaHoraria) FROM disciplinas NATURAL JOIN grade WHERE codCurso=(SELECT codCurso FROM cursos WHERE nome=?)"
    		);
    		st.setString(1, nome);
    		ResultSet rs = st.executeQuery();
    		while(rs.next()) {
    			cargaHoraria = rs.getInt(1);
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return cargaHoraria;
    }
}








