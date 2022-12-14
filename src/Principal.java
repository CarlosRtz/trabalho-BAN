import java.util.Scanner;
import java.sql.Connection;

public class Principal {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
    	CursoController cursoController = new CursoController();
    	DisciplinaController disciplinaController = new DisciplinaController();
        Conexao conexao = new Conexao();
        Connection con = conexao.getConnection();
        menu();
        int op = Integer.parseInt(sc.nextLine());
        while(op != 0){
            switch(op){
                case 1:
                    cursoController.createCurso(con);
                break;
                case 2:
                    disciplinaController.createDisciplina(con);
                break;
                case 3:
                    cursoController.listCursos(con);
                break;
                case 4:
                    disciplinaController.listDisciplinas(con);
                break;
                case 5:
                    cursoController.listDisciplinas(con);
                break;
                case 6:
                    cursoController.getCargaHoraria(con);
                break;
                default:
                    System.out.println("Opção invalida");
                break;
            }
            menu();
            op = Integer.parseInt(sc.nextLine());
        }
        // conexao.closeConnection();
    }

    public static void menu(){
        System.out.println("\n==================================================");
        System.out.println("1 - Adicionar curso");
        System.out.println("2 - Adicionar disciplina");
        System.out.println("3 - Listar cursos");
        System.out.println("4 - Listar disciplinas");
        System.out.println("5 - Listar disciplinas do curso");
        System.out.println("6 - Carga horaria do curso");
        System.out.println("(Digite 0 para encerrar)");
    }
}
