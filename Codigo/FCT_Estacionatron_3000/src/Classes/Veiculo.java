package Classes;


public class Veiculo {
    private String modelo;
    private String placa;
    private TipoVeiculo tipoVeiculo;

    public Veiculo(String modelo, String placa, TipoVeiculo tipoVeiculo) {
        this.modelo = modelo.replaceAll("\\s", "");
        this.placa = placa.replaceAll("\\s", "");;
        this.tipoVeiculo = tipoVeiculo;
    }

    public String imprimirVeiculo(){
        String dados = "";
        if(tipoVeiculo == TipoVeiculo.motocicleta){
            dados += "Motocicleta ";
        }else{
            dados += "Carro ";
        }
        dados += this.modelo + " Placa: " + this.placa;
        return dados;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }
}
