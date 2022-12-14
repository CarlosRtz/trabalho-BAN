import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;

public class CursoController {

    public void createCurso(Connection con){
    	try {
    		Scanner sc = new Scanner(System.in);
    		
			int codCurso = CursoModel.selectNewId(con);
			System.out.println("Nome do curso: ");
			String nome = sc.nextLine();
			CursoModel.insert(new Curso(codCurso, nome), con);
			
			System.out.println("Curso inserido no banco de dados");
			System.out.println("Codigo do curso: " + codCurso);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
    }

    public void listCursos(Connection con){
        List<Curso> cursos = CursoModel.select(con);
        System.out.println();
        for(Curso c : cursos){
            System.out.println(c + "\n");
        }
    }

    public void listDisciplinas(Connection con){
		Scanner sc = new Scanner(System.in);
        System.out.println("Codigo do curso: ");
        int codCurso = Integer.parseInt(sc.nextLine());

        List<Disciplina> disciplinas  = CursoModel.listDisciplinas(codCurso, con);
        System.out.println();
        for(Disciplina d : disciplinas){
            System.out.println(d + "\n");
        }
    }
    
    public void getCargaHoraria(Connection con) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nome do curso: ");
		String nome = sc.nextLine();
		
		int cargaHoraria = CursoModel.getCargaHoraria(nome, con);
		System.out.println("Carga horaria do curso: " + cargaHoraria);
    }
}





