package Classes;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
public class ControladorEstacionamento implements Serializable {
    private static ControladorEstacionamento instance;
    private ArrayList<Estadia> estadiasCarro;
    private ArrayList<Estadia> estadiasMoto;
    private int totalVagasMotos;
    private int totalVagasCarro;
    private boolean estacionamentoAberto;
    public Estadia admitirEstadia(String modelo, String placa, TipoVeiculo tipo,
                                  String nomeCompleto, String CPF, String telefone, boolean diaria){
        Calendar entrada = Calendar.getInstance();
        Estadia estadia = null;

        //verifica se a entrada está dentro do horário de funcionamento do estacionamento
        if(entrada.HOUR_OF_DAY < 6 || entrada.HOUR_OF_DAY > 18){
            //verifica se a entrada ainda está dentro do horário de tolerância
            if(!(entrada.HOUR_OF_DAY == 5 && entrada.MINUTE >= 50) || !(entrada.HOUR_OF_DAY == 18 && entrada.MINUTE <= 10))
                return estadia;
        }
        if(tipo == TipoVeiculo.motocicleta && totalVagasMotos > 0){
            estadia = new Estadia().admitirVeiculo(modelo, placa, tipo, nomeCompleto, CPF, telefone, diaria, entrada);
            estadiasMoto.add(estadia);
            totalVagasMotos--;
        }
        else if(tipo == TipoVeiculo.carro && totalVagasCarro > 0){
            estadia = new Estadia().admitirVeiculo(modelo, placa, tipo, nomeCompleto, CPF, telefone, diaria, entrada);
            estadiasCarro.add(estadia);
            totalVagasCarro--;
        }
        ControladorEstacionamento.serialize();
        return estadia;
    }
    //busca uma estadia com que contenha um veículo com os valores informados
    public Estadia buscarEstadiaManual(TipoVeiculo tipo, String modelo, String placa, String nomeMotorista, String CPFMotorista, String telefoneMotorista){
        if(tipo == TipoVeiculo.carro) {
            for (Estadia estadia : estadiasCarro) {
                if (!estadia.paga() && estadia.getVeiculo().getModelo().equals(modelo) &&
                        estadia.getVeiculo().getPlaca().equals(placa) && estadia.getMotorista().getNomeCompleto().equals(nomeMotorista)
                        && estadia.getMotorista().getCPF().equals(CPFMotorista) && estadia.getMotorista().getPhone().equals(telefoneMotorista)) {
                    return estadia;
                }
            }
        }else{
            for(Estadia estadia:estadiasMoto){
                if(!estadia.paga() && estadia.getVeiculo().getModelo().equals(modelo) &&
                        estadia.getVeiculo().getPlaca().equals(placa) && estadia.getMotorista().getNomeCompleto().equals(nomeMotorista)
                        && estadia.getMotorista().getCPF().equals(CPFMotorista) && estadia.getMotorista().getPhone().equals(telefoneMotorista)) {
                    return estadia;
                }
            }
        }
        return null;
    }

    public float calcularValorEstadia(int numeroEstadia){
        Estadia estadia = buscarEstadia(numeroEstadia);
        if(estadia != null){
            return estadia.calcularValorEstadia();
        }
        return 0.f;
    }

    public float calcularValorEstadiaManual(TipoVeiculo tipo, String modelo, String placa, String nomeMotorista, String CPFMotorista, String telefoneMotorista){
        Estadia estadia = buscarEstadiaManual(tipo, modelo, placa, nomeMotorista, CPFMotorista, telefoneMotorista);
        if(estadia != null){
            return estadia.calcularValorEstadia();
        }
        return 0.f;
    }

    public boolean sairVeiculo(int numeroEstadia, String dadosPagamento){
        Estadia estadia = buscarEstadia(numeroEstadia);
        boolean pagamentoValido = estadia.sairVeiculo(dadosPagamento);
        if(pagamentoValido){
            if(estadia.getVeiculo().getTipoVeiculo() == TipoVeiculo.motocicleta){
                totalVagasMotos++;
            }else {
                totalVagasCarro++;
            }
        }
        ControladorEstacionamento.serialize();
        return pagamentoValido;
    }

    public Estadia buscarEstadia(int numeroEstadia){
        for(Estadia estadia:estadiasCarro){
            if(!estadia.paga() && estadia.getNumeroEstadia() == numeroEstadia){
                return estadia;
            }
        }
        for(Estadia estadia:estadiasMoto){
            if(!estadia.paga() && estadia.getNumeroEstadia() == numeroEstadia){
                return estadia;
            }
        }
        return null;
    }

    public boolean sairVeiculoManual(TipoVeiculo tipo, String modelo, String placa, String nomeMotorista, String CPFMotorista, String telefoneMotorista, String dadosPagamento){
        Estadia estadia = buscarEstadiaManual(tipo, modelo, placa, nomeMotorista, CPFMotorista, telefoneMotorista);
        boolean pagamentoValido = estadia.sairVeiculo(dadosPagamento);
        if(pagamentoValido){
            if(tipo == TipoVeiculo.motocicleta){
                totalVagasMotos++;
            }else{
                totalVagasCarro++;
            }
        }
        ControladorEstacionamento.serialize();
        return pagamentoValido;
    }

    public ControladorEstacionamento() {
        this.estadiasCarro = new ArrayList<Estadia>();
        this.estadiasMoto = new ArrayList<Estadia>();
    }

    public void abrirEstacionamento(int totalVagasCarro, int totalVagasMotos){
        this.estacionamentoAberto = true;
        this.totalVagasCarro = totalVagasCarro;
        this.totalVagasMotos = totalVagasMotos;
    }

    public String fecharEstacionamento() {
        String veiculosFora = "";
        for (Estadia estadia : estadiasCarro) {
            if (!estadia.paga()) {
                estadia.setarFicouAposFechamento();
                veiculosFora += estadia.dadosVeiculo();
                String dadosMotorista[] = estadia.informacaoMotorista();
                veiculosFora += "\nMotorista: " + dadosMotorista[1] + "Telefone: " + dadosMotorista[2] + "\n";
            }
        }
        for (Estadia estadia : estadiasMoto) {
            if (!estadia.paga()) {
                estadia.setarFicouAposFechamento();
                veiculosFora += estadia.dadosVeiculo();
                String dadosMotorista[] = estadia.informacaoMotorista();
                veiculosFora += "\nMotorista: " + dadosMotorista[1] + "Telefone: " + dadosMotorista[2] + "\n";
            }
        }
        this.estacionamentoAberto = false;
        return veiculosFora;
    }

    public boolean isEstacionamentoAberto() {
        return estacionamentoAberto;
    }

    public int getTotalVagasMotos(){
        return totalVagasMotos;
    }
    public int getTotalVagasCarro(){
        return totalVagasCarro;
    }

    public static ControladorEstacionamento getInstance() {
        if (instance == null) {
            try {
                instance = (ControladorEstacionamento) new ObjectInputStream(new FileInputStream("estacionamento.ser")).readObject();
            } catch (IOException | ClassNotFoundException e) {
                instance = new ControladorEstacionamento();
            }
        }
        return instance;
    }
    public static void serialize(){
        try{
            FileOutputStream fileOut = new FileOutputStream("estacionamento.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(ControladorEstacionamento.instance);
            out.close();
            fileOut.close();
        }catch (Exception e){
            e.printStackTrace();
        };
    }
}
