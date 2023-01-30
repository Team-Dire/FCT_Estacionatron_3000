package Classes;

import java.io.Serializable;

public class Motorista implements Serializable {
    private String nomeCompleto;
    private String CPF;
    private String phone;

    public Motorista(String nomeCompleto, String CPF, String phone) {
        this.nomeCompleto = nomeCompleto;
        this.CPF = CPF;
        this.phone = phone;
    }

    public String[] dadosMotorista(){
        String dados[] = {this.CPF, this.nomeCompleto, this.phone};
        return dados;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCPF() {
        return CPF;
    }

    public String getPhone() {
        return phone;
    }
}
