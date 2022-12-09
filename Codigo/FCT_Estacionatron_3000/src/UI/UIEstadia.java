package UI;

import javax.swing.*;
import Classes.ControladorEstacionamento;
import Classes.TipoVeiculo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class UIEstadia extends JFrame{
    private ControladorEstacionamento controladorEstacionamento;
    private JPanel panelMain;
    private JButton btnNovaEstadia;
    private JTextField textFieldNome;
    private JTextField textFieldPlaca;
    private JTextField textFieldModelo;
    private JLabel labelModelo;
    private JLabel labelPlaca;
    private JLabel labelNome;
    private JLabel labelCPF;
    private JLabel labelTelefone;
    private JTextField textFieldTelefone;
    private JTextField textFieldCPF;
    private JCheckBox checkBoxDiaria;
    private JRadioButton radioButtonCarro;
    private JRadioButton radioButtonMotocicleta;
    private JLabel labelResultado;

    public UIEstadia(ControladorEstacionamento controladorEstacionamento) {
        this.controladorEstacionamento = controladorEstacionamento;
        this.setContentPane(this.panelMain);
        this.setTitle("FCT Estacionatron 3000");
        this.setSize(500, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnNovaEstadia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String modelo = textFieldModelo.getText();
                String placa = textFieldPlaca.getText();
                String nome = textFieldNome.getText();
                String CPF = textFieldCPF.getText();
                String telefone = textFieldTelefone.getText();
                boolean diaria = checkBoxDiaria.isSelected();
                if(radioButtonCarro.isSelected()){
                    if(controladorEstacionamento.admitirEstadia(modelo, placa, TipoVeiculo.carro, nome, CPF, telefone, diaria) != null){
                        dispose();
                    }else {
                        labelResultado.setText("Estadia não pode ser criada");
                    }
                }
                if(radioButtonMotocicleta.isSelected()){
                    if(controladorEstacionamento.admitirEstadia(modelo, placa, TipoVeiculo.motocicleta, nome, CPF, telefone, diaria) != null){
                        dispose();
                    }
                    else {
                        labelResultado.setText("Estadia não pode ser criada");
                    }
                }
            }
        });
        textFieldModelo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String modelo = textFieldModelo.getText();
                String placa = textFieldPlaca.getText();
                if(placa.equals("") || modelo.equals("")){
                    btnNovaEstadia.setEnabled(false);
                }else{
                    btnNovaEstadia.setEnabled(true);
                }
            }
        });
        textFieldPlaca.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String modelo = textFieldModelo.getText();
                String placa = textFieldPlaca.getText();
                if(placa.equals("") || modelo.equals("")){
                    btnNovaEstadia.setEnabled(false);
                }else{
                    btnNovaEstadia.setEnabled(true);
                }
            }
        });
    }
}
