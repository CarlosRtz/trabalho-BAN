public class Curso {
    private String nome;
    private int codCurso;

    public Curso(int codCurso, String nome){
        this.codCurso = codCurso;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public String toString(){
        String str = "Nome: " + this.nome;
        str += "\nCodigo: " + this.codCurso;
        return str;
    }
}
