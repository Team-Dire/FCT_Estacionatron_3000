package UI;

import Classes.ControladorEstacionamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UIAbertura extends JFrame {
    private JPanel panelMain;
    private JTextArea textAreaMoto;
    private JTextArea textAreaCarro;
    private JLabel labelMotos;
    private JLabel labelCarro;
    private JButton btnAbrir;
    private JLabel labelVagas;
    private ControladorEstacionamento controladorEstacionamento;
    public UIAbertura(ControladorEstacionamento controladorEstacionamento) {
        this.controladorEstacionamento = controladorEstacionamento;
        this.setContentPane(this.panelMain);
        this.setTitle("FCT Estacionatron 3000");
        this.setSize(500, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalVagasCarro;
                int totalVagasMotos;
                String vagasCarro = textAreaCarro.getText();
                String vagasMotos = textAreaMoto.getText();
                if(vagasCarro.equals("")){
                    totalVagasCarro = 0;
                }else{
                    totalVagasCarro = Integer.parseInt(vagasCarro);
                }
                if(vagasMotos.equals("")){
                    totalVagasMotos = 0;
                }else{
                    totalVagasMotos = Integer.parseInt(vagasMotos);
                }
                controladorEstacionamento.abrirEstacionamento(totalVagasCarro, totalVagasMotos);
                dispose();
            }
        });
    }
}
