package JDBC.Sistema_De_Agendamento_Medico_Refatorado_Interacao_Banco_De_Dados.entities;

public final class ValidadorPaciente {
    private ValidadorPaciente() {}

    // \\d{11} permite somente números com limite de 11
    public static boolean cpfValido(String cpf) {
        return cpf.matches("\\d{11}");
    }

    // .*\\d.* verifica se existe algum número no meio do texto
    public static boolean nomeValido(String nome) {
        return !nome.matches(".*\\d.*");
    }
}