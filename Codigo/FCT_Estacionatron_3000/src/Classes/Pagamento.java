package Classes;
import java.util.Calendar;

public class Pagamento {
    private float valor;
    private boolean diaria;
    private boolean confirmado = false;

    public Pagamento(boolean diaria){
        this.diaria = diaria;
        this.confirmado = false;
    }

    public boolean finalizarPagamento(Calendar entrada, Calendar saida, String dadosPagamento, boolean ficouAposPagamento){
        float valor = calcularPagamento(entrada, saida, ficouAposPagamento);
        this.confirmado = pagar(dadosPagamento, valor);
        return confirmado;
    }

    public float calcularPagamento(Calendar entrada, Calendar saida, boolean ficouAposPagamento){
        float valor = 0.f;
        if(ficouAposPagamento){
            valor+=30.f;
        }
        if (this.diaria){
            return valor + 14.f;
        }else{
            long diffHours = (saida.getTimeInMillis() - entrada.getTimeInMillis())/(60 * 60 * 1000);
            valor += 8.f;
            if (diffHours > 4) {
                valor = diffHours + valor;
            }
            return valor;
        }
    }

    public boolean pagar(String dadosPagamento, float valor){
        if(Float.parseFloat(dadosPagamento) >= valor){
            this.confirmado = true;
            this.valor = valor;
        }
        return this.confirmado;
    }
}
