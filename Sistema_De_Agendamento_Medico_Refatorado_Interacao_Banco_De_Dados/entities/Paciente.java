package JDBC.Sistema_De_Agendamento_Medico_Refatorado_Interacao_Banco_De_Dados.entities;

public class Paciente extends Pessoa {

    private String cpf;

    public Paciente(String cpf, String nomePaciente) {
        super(nomePaciente);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String getIdentificacao() {
        return "CPF: " + cpf;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "cpf='" + cpf + '\'' +
                ", nomePaciente='" + getNome() + '\'' +
                '}';
    }
}