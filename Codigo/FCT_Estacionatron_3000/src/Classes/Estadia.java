package Classes;
import java.util.Calendar;

public class Estadia {
    private Pagamento pagamento;
    private Motorista motorista;
    private Veiculo veiculo;
    private Calendar entrada;
    private Calendar saida;
    private boolean diaria;
    private boolean ficouAposFechamento;

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

    public String imprimirVeiculo() {
        return this.veiculo.imprimirVeiculo();
    }

    public float calcularValorEstadia(){

        return this.pagamento.calcularPagamento(entrada, Calendar.getInstance(), ficouAposFechamento);
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setarFicouAposFechamento(){
        this.ficouAposFechamento = true;
    }
    public String[] informacaoMotorista(){
        return this.motorista.imprimirMotorista();
    }
}
