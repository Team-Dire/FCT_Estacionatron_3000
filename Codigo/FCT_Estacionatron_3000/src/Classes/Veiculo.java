package Classes;


import java.io.Serializable;

public class Veiculo implements Serializable {
    private String modelo;
    private String placa;
    private TipoVeiculo tipoVeiculo;

    public Veiculo(String modelo, String placa, TipoVeiculo tipoVeiculo) {
        this.modelo = modelo.replaceAll("\\s", "");
        this.placa = placa.replaceAll("\\s", "");;
        this.tipoVeiculo = tipoVeiculo;
    }

    public String[] dadosVeiculo(){
        if(tipoVeiculo == TipoVeiculo.motocicleta){
            String dados[] = {"Motocicleta", this.modelo, this.placa};
            return dados;
        }else{
            String dados[] = {"Carro", this.modelo, this.placa};
            return dados;
        }
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }
}
