package Classes;
import java.io.Serializable;
import java.util.Calendar;

public class Estadia implements Serializable {
    private Pagamento pagamento;
    private Motorista motorista;
    private Veiculo veiculo;
    private Calendar entrada;
    private Calendar saida;
    private boolean diaria;
    private boolean ficouAposFechamento;
    private int numeroEstadia;
    private static int count;
    public Estadia(){
        numeroEstadia = count++;
    }

    public Veiculo criarVeiculo(String modelo, String placa, TipoVeiculo tipo){
        Veiculo veiculo = new Veiculo(modelo, placa, tipo);
        return veiculo;
    }

    public Motorista criarMotorista(String nomeCompleto, String CPF, String telefone){
        Motorista motorista = new Motorista(nomeCompleto, CPF, telefone);
        return motorista;
    }

    public Pagamento criarPagamento(boolean diaria){
        Pagamento pagamento = new Pagamento(diaria);
        return pagamento;
    }

    public Estadia admitirVeiculo(String modelo, String placa, TipoVeiculo tipo,
                                         String nomeCompleto, String CPF, String telefone, boolean diaria, Calendar entrada){
        this.veiculo = new Veiculo(modelo, placa, tipo);
        this.motorista = new Motorista(nomeCompleto, CPF, telefone);
        this.entrada = entrada;
        this.pagamento = criarPagamento(diaria);
        this.diaria = diaria;
        this.saida = null;
        return this;
    }

    public boolean sairVeiculo(String dadosPagamento){
        Calendar saida = Calendar.getInstance();
        boolean pago = this.pagamento.finalizarPagamento(entrada, saida, dadosPagamento, ficouAposFechamento);
        if(pago){
            this.saida = saida;
        }
        return pago;
    }

    public boolean paga(){
        return this.saida != null;
    }

    public String[] dadosVeiculo() {
        return this.veiculo.dadosVeiculo();
    }

    public float calcularValorEstadia(){
        return this.pagamento.calcularPagamento(entrada, Calendar.getInstance(), ficouAposFechamento);
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Motorista getMotorista(){
        return motorista;
    }

    public int getNumeroEstadia(){
        return numeroEstadia;
    }

    public void setarFicouAposFechamento(){
        this.ficouAposFechamento = true;
    }
    public String[] informacaoMotorista(){
        return this.motorista.dadosMotorista();
    }
    public String dadosEstadia(){
        String estadia = String.valueOf(numeroEstadia);
        String hora = String.valueOf(entrada.HOUR_OF_DAY);
        String minuto = String.valueOf(entrada.MINUTE);
        String dadosVeiculo[] = this.getVeiculo().dadosVeiculo();
        String dadosMotorista[] = this.getMotorista().dadosMotorista();
        return String.format("""
                Estadia: %s
                Entrada: %s:%s
                Veículo:
                Tipo: %s
                Modelo: %s
                Placa: %s
                Motorista:
                CPF: %s
                Nome: %s
                Telefone: %s
                """, estadia, hora, minuto, dadosVeiculo[0], dadosVeiculo[1], dadosVeiculo[2], dadosMotorista[0], dadosMotorista[1], dadosMotorista[2]);
    }
}
