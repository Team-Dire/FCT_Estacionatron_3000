package Classes;
import java.util.Date;
public class Pagamento {
    private float valor;
    private boolean diaria;
    private boolean confirmado = false;

    public Pagamento(boolean diaria){
        this.diaria = diaria;
        this.confirmado = false;
    }

    public boolean finalizarPagamento(Date entrada, Date saida, String dadosPagamento){
        float valor = calcularPagamento(entrada, saida);
        this.confirmado = pagar(dadosPagamento, valor);
        return confirmado;
    }

    public float calcularPagamento(Date entrada,Date saida){
        if (this.diaria){
            return 14.f;
        }else{
            long diffHours = (saida.getTime() - entrada.getTime())/(60 * 60 * 1000);
            float valor = 8.f;
            if (diffHours > 4) {
                valor = diffHours + valor;
            }
            return valor;
        }
    }

    public boolean pagar(String dadosPagamento, float valor){
        if(Float.parseFloat(dadosPagamento) >= valor){
            this.confirmado = true;
        }
        return this.confirmado;
    }
}
