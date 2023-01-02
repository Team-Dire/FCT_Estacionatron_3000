package UI;

import Classes.ControladorEstacionamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIMainMenu extends JFrame {
    private final ControladorEstacionamento controladorEstacionamento = new ControladorEstacionamento();
    private JPanel panelMain;
    private JButton btnNovaEstadia;
    private JButton btnPagar;
    private JButton btnAbrir;
    private JButton btnFechar;
    private JLabel labelAviso;

    public UIMainMenu() {
        btnNovaEstadia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controladorEstacionamento.isEstacionamentoAberto()){
                    UIEstadia uiEstadia = new UIEstadia(controladorEstacionamento);
                }else{
                    labelAviso.setText("Estacionamento ainda não aberto!");
                }

            }
        });
        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPagamento uiPagamento = new UIPagamento(controladorEstacionamento);
            }
        });
        btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controladorEstacionamento.isEstacionamentoAberto()){
                    UIFechamento uiFechamento = new UIFechamento(controladorEstacionamento);
                }else{
                    labelAviso.setText("Estacionamento ainda não aberto!");
                }

            }
        });
        btnAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controladorEstacionamento.isEstacionamentoAberto()){
                    labelAviso.setText("Estacionamento já foi aberto!");
                }else{
                    UIAbertura uiAbertura = new UIAbertura(controladorEstacionamento);
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String[] args) {
        //ControladorEstacionamento controladorEstacionamento1 = new ControladorEstacionamento();
        UIMainMenu uiMainMenu = new UIMainMenu();
        uiMainMenu.setContentPane(uiMainMenu.panelMain);
        uiMainMenu.setTitle("FCT Estacionatron 3000");
        uiMainMenu.setSize(350, 600);
        uiMainMenu.setVisible(true);
        uiMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
