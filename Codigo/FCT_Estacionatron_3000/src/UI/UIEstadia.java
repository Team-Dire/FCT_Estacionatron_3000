package UI;

import javax.print.*;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import Classes.ControladorEstacionamento;
import Classes.TipoVeiculo;
import Classes.Estadia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.print.*;

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
    private JLabel labelVagasCarro;
    private JLabel labelVagasMoto;
    private JLabel labelVagas;

    public UIEstadia(ControladorEstacionamento controladorEstacionamento) {
        this.controladorEstacionamento = controladorEstacionamento;
        this.setContentPane(this.panelMain);
        this.setTitle("FCT Estacionatron 3000");
        this.setSize(500, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.labelVagasCarro.setText(Integer.toString(controladorEstacionamento.getTotalVagasCarro()));
        this.labelVagasMoto.setText(Integer.toString(controladorEstacionamento.getTotalVagasMotos()));
        btnNovaEstadia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String modelo = textFieldModelo.getText();
                String placa = textFieldPlaca.getText();
                String nome = textFieldNome.getText();
                String CPF = textFieldCPF.getText();
                String telefone = textFieldTelefone.getText();
                boolean diaria = checkBoxDiaria.isSelected();
                Estadia estadia = null;
                if(radioButtonCarro.isSelected()){
                    estadia = controladorEstacionamento.admitirEstadia(modelo, placa, TipoVeiculo.carro, nome, CPF, telefone, diaria);
                }
                if(radioButtonMotocicleta.isSelected()){
                    estadia = controladorEstacionamento.admitirEstadia(modelo, placa, TipoVeiculo.carro, nome, CPF, telefone, diaria);
                }
                if(estadia != null){
                    try {
                        FileWriter myWriter = new FileWriter("estadia.txt");
                        myWriter.write(estadia.dadosEstadia());
                        myWriter.close();
                    } catch (IOException er) {
                        er.printStackTrace();
                    }
                    //impressao da estadia
                    String dadosEstadia = estadia.dadosEstadia();
                    System.out.println(dadosEstadia);
                    PrintService printService[] = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.AUTOSENSE, null);
                    PrintService impressoraPadrao = PrintServiceLookup.lookupDefaultPrintService();
                    DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                    HashDocAttributeSet hashDocAttributeSet = new HashDocAttributeSet();
                    try {
                        FileInputStream fileInputStream = new FileInputStream("estadia.txt");
                        Doc doc = new SimpleDoc(fileInputStream, docFlavor, hashDocAttributeSet);
                        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
                        PrintService printServico = ServiceUI.printDialog(null, 300, 200, printService, impressoraPadrao,docFlavor, printRequestAttributeSet);
                    }catch (IOException er){
                        er.printStackTrace();
                    }
                    dispose();
                }else {
                    labelResultado.setText("Estadia não pode ser criada");
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
