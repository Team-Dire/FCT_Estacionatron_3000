package Classes;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Date;

public class ControladorEstacionamento {
    private ArrayList<Estadia> estadiasCarro;
    private ArrayList<Estadia> estadiasMoto;

    public Estadia admitirEstadia(String modelo, String placa, TipoVeiculo tipo,
                                  String nomeCompleto, String CPF, String telefone, boolean diaria){
        Date entrada = new Date();
        Estadia estadia = null;

        //verifica se a entrada está dentro do horário de funcionamento do estacionamento
        if(entrada.getHours() < 6 || entrada.getHours() > 18){
            //verifica se a entrada ainda está dentro do horário de tolerância
            if(!(entrada.getHours() == 5 && entrada.getMinutes() >= 50) || !(entrada.getHours() == 18 && entrada.getMinutes() <= 10))
                return estadia;
        }
        estadia = new Estadia().admitirVeiculo(modelo, placa, tipo, nomeCompleto, CPF, telefone, diaria, entrada);
        if(tipo == TipoVeiculo.motocicleta){
            estadiasMoto.add(estadia);
        }
        else{
            estadiasCarro.add(estadia);
        }
        return estadia;
    }
    //busca uma estadia com que contenha um veículo com os valores informados
    public Estadia buscarEstadia(TipoVeiculo tipo, String modelo, String placa){
        for(Estadia estadia:estadiasCarro){
            if(!estadia.paga() && estadia.getVeiculo().getModelo().equals(modelo) && estadia.getVeiculo().getPlaca().equals(placa)){
                return estadia;
            }
        }
        for(Estadia estadia:estadiasMoto){
            if(!estadia.paga() && estadia.getVeiculo().getModelo().equals(modelo) && estadia.getVeiculo().getPlaca().equals(placa)){
                return estadia;
            }
        }
        return null;
    }

    public float calcularValorEstadia(TipoVeiculo tipo, String modelo, String placa, String dadosPagamento){
        Estadia estadia = buscarEstadia(tipo, modelo, placa);
        if(estadia != null){
            return estadia.calcularValorEstadia();
        }
        return 0.f;
    }

    public boolean sairVeiculo(TipoVeiculo tipo, String modelo, String placa, String dadosPagamento){
        Estadia estadia = buscarEstadia(tipo, modelo, placa);
        return estadia.sairVeiculo(dadosPagamento);
    }

    public ControladorEstacionamento() {
        this.estadiasCarro = new ArrayList<Estadia>();
        this.estadiasMoto = new ArrayList<Estadia>();
    }

    public Object[] veiculosEstacionados(){
        ArrayList<String> arrayListVeiculos = new ArrayList<String>();
        for(Estadia estadia:estadiasCarro){
            if(!estadia.paga()){
                arrayListVeiculos.add(estadia.imprimirVeiculo());
            }
        }
        for(Estadia estadia:estadiasMoto){
            if(!estadia.paga()){
                arrayListVeiculos.add(estadia.imprimirVeiculo());
            }
        }
        return arrayListVeiculos.toArray();
    }
}
