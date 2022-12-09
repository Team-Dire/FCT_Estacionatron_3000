package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import Classes.ControladorEstacionamento;
import Classes.TipoVeiculo;
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

    public UIPagamento(ControladorEstacionamento controladorEstacionamento) {
        this.controladorEstacionamento = controladorEstacionamento;
        //população da comboBox com os veículos do estacionamento
        Object[] veiculos = controladorEstacionamento.veiculosEstacionados();
        for(Object veiculo: veiculos){
            comboBoxVeiculos.addItem(veiculo);
        }
        this.setContentPane(this.panelMain);
        this.setTitle("FCT Estacionatron 3000");
        this.setSize(400, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //evento para realizar o pagamento
        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //adquire a placa do veículo
                String[] dadosVeiculo = comboBoxVeiculos.getSelectedItem().toString().split(" ");
                String tipo = dadosVeiculo[0];
                String modelo = dadosVeiculo[1];
                String placa = dadosVeiculo[dadosVeiculo.length-1];
                //adquire o valor do pagamento
                String valor = textFieldValorPagamento.getText().replace(",", ".");
                //realiza o pagamento
                boolean realizarPagamento;
                if(tipo.equals("Motocicleta")){
                    realizarPagamento = controladorEstacionamento.sairVeiculo(TipoVeiculo.motocicleta, modelo, placa, valor);
                }else{
                    realizarPagamento = controladorEstacionamento.sairVeiculo(TipoVeiculo.carro, modelo, placa, valor);
                }
                if(realizarPagamento){
                    float troco = Float.parseFloat(valor) - Float.parseFloat(labelValorCobrado.getText());
                    if(troco > 0){
                        labelResultado.setText("Troco: " + Float.toString(troco));
                        btnPagar.setEnabled(false);
                        btnCalcularValor.setEnabled(false);
                        comboBoxVeiculos.setEnabled(false);
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
                //adquire a placa do veículo
                String[] dadosVeiculo = comboBoxVeiculos.getSelectedItem().toString().split(" ");
                String tipo = dadosVeiculo[0];
                String modelo = dadosVeiculo[1];
                String placa = dadosVeiculo[dadosVeiculo.length-1];
                //adquire o valor do pagamento
                String valor = textFieldValorPagamento.getText();
                //exibe o valor da estadia
                float valorFinal;
                if(tipo.equals("Motocicleta")){
                    valorFinal = controladorEstacionamento.calcularValorEstadia(TipoVeiculo.motocicleta, modelo, placa, valor);
                }else{
                    valorFinal = controladorEstacionamento.calcularValorEstadia(TipoVeiculo.carro, modelo, placa, valor);
                }
                labelValorCobrado.setText(Float.toString(valorFinal));
                btnPagar.setEnabled(true);
            }
        });
    }
}
