package UI;

import Classes.ControladorEstacionamento;
import Classes.TipoVeiculo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIPagamentoManual extends JFrame{
    private ControladorEstacionamento controladorEstacionamento;
    private JPanel panelMain;
    private JButton btnCalcularValor;
    private JButton btnPagar;
    private JTextField textModelo;
    private JTextField textPlaca;
    private JTextField textNomeCompleto;
    private JTextField textCPF;
    private JTextField textTelefone;
    private JLabel labelValorTotal;
    private JLabel labelDadosPagamento;
    private JLabel labelPlaca;
    private JLabel labelNome;
    private JLabel labelCPF;
    private JLabel labelTelefone;
    private JLabel labelModelo;
    private JLabel labelTipo;
    private JRadioButton radioMoto;
    private JRadioButton radioCarro;
    private JLabel labelValorCobrado;
    private JTextField textValorPagamento;
    private JLabel labelResultado;

    public UIPagamentoManual(ControladorEstacionamento controladorEstacionamento) {
        this.controladorEstacionamento = controladorEstacionamento;
        this.setContentPane(this.panelMain);
        this.setTitle("FCT Estacionatron 3000");
        this.setSize(500, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //evento para realizar o pagamento
        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //adquire os dados do veículo e motorista
                String modelo = textModelo.getText();
                String placa = textPlaca.getText();
                String nomeMotorista = textNomeCompleto.getText();
                String CPFMotorista = textCPF.getText();
                String telefoneMotorista = textTelefone.getText();
                //adquire o valor do pagamento
                String valor = textValorPagamento.getText().replace(",", ".");
                //realiza o pagamento
                boolean realizarPagamento;
                if(radioMoto.isSelected()){
                    realizarPagamento = controladorEstacionamento.sairVeiculoManual(TipoVeiculo.motocicleta, modelo, placa, nomeMotorista, CPFMotorista, telefoneMotorista, valor);
                }else{
                    realizarPagamento = controladorEstacionamento.sairVeiculoManual(TipoVeiculo.carro, modelo, placa, nomeMotorista, CPFMotorista, telefoneMotorista, valor);
                }
                if(realizarPagamento){
                    float troco = Float.parseFloat(valor) - Float.parseFloat(labelValorCobrado.getText());
                    if(troco > 0){
                        labelResultado.setText("Troco: " + Float.toString(troco));
                        btnPagar.setEnabled(false);
                        btnCalcularValor.setEnabled(false);
                    }else{
                        dispose();
                    }
                }else{
                    labelResultado.setText("Valor insuficiente.");
                }
            }
        });
        btnCalcularValor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //adquire os dados do veículo e motorista
                String modelo = textModelo.getText();
                String placa = textPlaca.getText();
                String nomeMotorista = textNomeCompleto.getText();
                String CPFMotorista = textCPF.getText();
                String telefoneMotorista = textTelefone.getText();
                //exibe o valor da estadia
                float valorFinal;
                if(radioMoto.isSelected()){
                    valorFinal = controladorEstacionamento.calcularValorEstadiaManual(TipoVeiculo.motocicleta, modelo, placa, nomeMotorista, CPFMotorista, telefoneMotorista);
                }else{
                    valorFinal = controladorEstacionamento.calcularValorEstadiaManual(TipoVeiculo.carro, modelo, placa, nomeMotorista, CPFMotorista, telefoneMotorista);
                }
                if(valorFinal == 0.f){
                    labelValorCobrado.setText("Estadia já paga ou não existe!");
                    btnPagar.setEnabled(false);
                }else{
                    labelValorCobrado.setText(Float.toString(valorFinal));
                    btnPagar.setEnabled(true);
                }
            }
        });
    }
}
