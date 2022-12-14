import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;

public class DisciplinaController {
    public void createDisciplina(Connection con){
    	try {
    		Scanner sc = new Scanner(System.in);
    
            int codDisciplina = DisciplinaModel.selectNewId(con);
            System.out.println("Nome da disciplina");
            String nome = sc.nextLine();
            System.out.println("Carga horaria: ");
            int cargaHoraria = Integer.parseInt(sc.nextLine());
            DisciplinaModel.insert(new Disciplina(codDisciplina, cargaHoraria, nome), con);

            System.out.println("Cursos que contem a disciplina:");
            String cursos = sc.nextLine();
            String[] codCursos = cursos.split(",");
            for(String cod : codCursos){
                int codCurso = Integer.parseInt(cod.trim());
                DisciplinaModel.insertGrade(codCurso, codDisciplina, con);
            }
            
            System.out.println("Disciplina adicionada ao banco de dados.");
            System.out.println("Codigo da disciplina: " + codDisciplina);
            
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}

    }

    public void listDisciplinas(Connection con){
        List<Disciplina> disciplinas = DisciplinaModel.select(con);
        System.out.println();
        for(Disciplina d : disciplinas){
            System.out.println(d + "\n");
        }
    }

    public void listMinCargaHoraria(Connection con){
        List<Disciplina> disciplinas = DisciplinaModel.listMinCargaHoraria(con);
        System.out.println();
        for(Disciplina d : disciplinas){
            System.out.println(d + "\n");
        }
    }
}   
