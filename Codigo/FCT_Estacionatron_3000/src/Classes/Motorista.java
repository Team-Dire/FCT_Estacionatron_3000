package Classes;

public class Motorista {
    private String nomeCompleto;
    private String CPF;
    private String phone;

    public Motorista(String nomeCompleto, String CPF, String phone) {
        this.nomeCompleto = nomeCompleto;
        this.CPF = CPF;
        this.phone = phone;
    }

    public String[] imprimirMotorista(){
        String dados[] = {this.CPF, this.nomeCompleto, this.phone};
        return dados;
    }
}
