public class Disciplina {
    private int codDisciplina;
    private int cargaHoraria;
    private String nome;

    public Disciplina(int codDisciplina, int cargaHoraria, String nome) {
        this.codDisciplina = codDisciplina;
        this.cargaHoraria = cargaHoraria;
        this.nome = nome;
    }

    public void setCodDisciplina(int codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodDisciplina() {
        return codDisciplina;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public String getNome() {
        return nome;
    }

    public String toString(){
        String str = "Nome: " + this.nome;
        str += "\nCodigo: " + this.codDisciplina;
        str += "\nCarga horaria: " + this.cargaHoraria;
        return str;
    }
}
