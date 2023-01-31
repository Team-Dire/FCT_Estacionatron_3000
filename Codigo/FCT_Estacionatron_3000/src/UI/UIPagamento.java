package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.ControladorEstacionamento;

public class UIPagamento extends JFrame{
    private ControladorEstacionamento controladorEstacionamento;
    private JPanel panelMain;
    private JTextField textFieldValorPagamento;
    private JLabel labelDadosPagamento;
    private JButton btnPagar;
    private JLabel labelResultado;
    private JLabel labelVeiculo;
    private JComboBox comboBoxVeiculos;
    private JLabel labelValorTotal;
    private JLabel labelValorCobrado;
    private JButton btnCalcularValor;
    private JTextField textNumeroEstadia;
    private JButton btnPagamentoManual;

    public UIPagamento(ControladorEstacionamento controladorEstacionamento) {
        this.controladorEstacionamento = controladorEstacionamento;
        this.setContentPane(this.panelMain);
        this.setTitle("FCT Estacionatron 3000");
        this.setSize(400, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //evento para realizar o pagamento
        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroEstadia = Integer.parseInt(textNumeroEstadia.getText());
                //adquire o valor do pagamento
                String valor = textFieldValorPagamento.getText().replace(",", ".");
                //realiza o pagamento
                boolean realizarPagamento = controladorEstacionamento.sairVeiculo(numeroEstadia, valor);
                if(realizarPagamento){
                    float troco = Float.parseFloat(valor) - Float.parseFloat(labelValorCobrado.getText());
                    if(troco > 0){
                        labelResultado.setText("Troco: " + Float.toString(troco));
                        btnPagar.setEnabled(false);
                        btnCalcularValor.setEnabled(false);
                        textNumeroEstadia.setEnabled(false);
                    }else{
                        dispose();
                    }
                }else{
                    labelResultado.setText("Valor insuficiente.");
                }
            }
        });
        //calcula o valor do estacionamento
        btnCalcularValor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //adquire a placa do veículo
                int numeroEstadia = Integer.parseInt(textNumeroEstadia.getText());
                //exibe o valor da estadia
                float valorFinal;
                valorFinal = controladorEstacionamento.calcularValorEstadia(numeroEstadia);
                if(valorFinal == 0.f){
                    labelValorCobrado.setText("Estadia já paga ou não existe!");
                    btnPagar.setEnabled(false);
                }else{
                    labelValorCobrado.setText(Float.toString(valorFinal));
                    btnPagar.setEnabled(true);
                }
            }
        });
        btnPagamentoManual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPagamentoManual uiPagamentoManual = new UIPagamentoManual(controladorEstacionamento);
                dispose();
            }
        });
    }
}
