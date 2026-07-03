package JDBC.Sistema_De_Agendamento_Medico_Refatorado_Interacao_Banco_De_Dados.entities;

public class Consulta {
    private Especialidade especialidade;
    private String horario;
    private String horarioHescolhido;

    public Consulta(Especialidade especialidade, String horarioEscolhido) {
        this.especialidade=especialidade;
        this.horarioHescolhido=horarioEscolhido;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public String getHorario() {
        return horario;
    }
    public String getHorarioHescolhido(){
        return horarioHescolhido;
    }
}