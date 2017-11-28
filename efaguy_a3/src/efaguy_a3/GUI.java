/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efaguy_a3;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Jesse
 */
public class GUI extends JFrame{
    
    private Portfolio investments;
    int posn;
    ArrayList<String> symbols;
    ArrayList<String> names;
    ArrayList<Double> prices;
    ArrayList<Integer> quantities;
    ArrayList<Double> bookVals;
    
    private JPanel make1x2Panel(JLabel left, JComponent right)
    {
        JPanel temp = new JPanel();
        temp.setLayout(new GridLayout(1,2));
        left.setHorizontalAlignment(JLabel.CENTER);
        temp.add(left);
        temp.add(right);
        
        return temp;
    }
    
    private JPanel make1x2Panel(JComponent left, JComponent right)
    {
        JPanel temp = new JPanel();
        temp.setLayout(new GridLayout(1,2));
        temp.add(left);
        temp.add(right);
        
        return temp;
    }

    
    public GUI(String filename){
        super("Stock Market");
        this.posn = 0;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        this.investments = new Portfolio();
        String load = investments.load(filename);
        this.setSize(new Dimension(800,600));
        BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        JPanel mainPanel = new JPanel();
        JPanel buyPanel = new JPanel();
        JPanel sellPanel = new JPanel();
        JPanel updatePanel = new JPanel();
        JPanel gainPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(2,1));
        gainPanel.setLayout(new BorderLayout());
        buyPanel.setLayout(new GridLayout(2,1));
        sellPanel.setLayout(new GridLayout(2,1));
        mainPanel.setLayout(new GridLayout(1,1));
        updatePanel.setLayout(new GridLayout(2,1));
        
       
        JTextArea area = new JTextArea(1, 1);
        area.setText(load);
        area.append(investments.toString());
        JScrollPane ascroll = new JScrollPane(area);
        
        
        
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Commands");
        menu.setMnemonic(KeyEvent.VK_C);
        JMenuItem buyM = new JMenuItem("Buy");
        JMenuItem sellM = new JMenuItem("Sell");
        JMenuItem update = new JMenuItem("Update");
        JMenuItem gain = new JMenuItem("Gain");
        JMenuItem searchM = new JMenuItem("Search");
        JMenuItem quit = new JMenuItem("Quit");
        menu.add(buyM);
        buyM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));
        menu.add(sellM);
        sellM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menu.add(update);
        update.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.ALT_MASK));
        menu.add(gain);
        gain.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.ALT_MASK));
        menu.add(searchM);
        searchM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));
        menu.add(quit);
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuBar.add(menu);
        

        quit.addActionListener((ActionEvent ae) -> {
            String save = investments.save(filename);
            this.setVisible(false);
            this.dispose();
        });
        
        

        //Buy Screen
        String[] invests = {"Stock", "Mutual Fund"};
        JComboBox investSelect = new JComboBox(invests);
        JPanel lInvestSelect = make1x2Panel(new JLabel("Type") ,investSelect);
        JTextField symbolBox = new JTextField();
        JTextField nameBox = new JTextField();
        JTextField quantityBox = new JTextField();
        JTextField priceBox = new JTextField();
        JPanel lSymbolBox = make1x2Panel(new JLabel("Symbol"), symbolBox);
        JPanel lNameBox = make1x2Panel(new JLabel("Name"), nameBox);
        JPanel lQuantityBox = make1x2Panel(new JLabel("Quantity"), quantityBox);
        JPanel lPriceBox = make1x2Panel(new JLabel("Price"), priceBox);
        
        JPanel buyLeft = new JPanel();
        buyLeft.setLayout(new GridLayout(6, 1));
        buyLeft.add(new JLabel("Buying an investment"));
        buyLeft.add(lInvestSelect);
        buyLeft.add(lSymbolBox);
        buyLeft.add(lNameBox);
        buyLeft.add(lQuantityBox);
        buyLeft.add(lPriceBox);
        
        JPanel buyRight = new JPanel();
        buyRight.setLayout(new GridLayout(2,1));
        JButton reset = new JButton("Reset");
        JButton buy = new JButton("Buy");
        buyRight.add(reset);
        buyRight.add(buy);
        
        JPanel buyTop = make1x2Panel(buyLeft, buyRight);
        
        
        JPanel buyBot = new JPanel();
        buyBot.setLayout(new BorderLayout());
        
        JLabel messages = new JLabel("Messages");
        messages.setHorizontalAlignment(JLabel.CENTER);
        buyBot.add(messages, BorderLayout.NORTH);
        JTextArea mess = new JTextArea();
        JScrollPane scroll = new JScrollPane(mess);
        buyBot.add(scroll, BorderLayout.CENTER);
        mess.setEditable(false);
        
        buyPanel.add(buyTop);
        buyPanel.add(buyBot);
        
        buy.addActionListener((ActionEvent ae) -> {
            String buy1 = investments.buy((String)investSelect.getSelectedItem(), symbolBox.getText(), nameBox.getText(), quantityBox.getText(), priceBox.getText());
            mess.setText(buy1 + "\n");
            mess.append(investments.toString());
        });
        
        reset.addActionListener((ActionEvent ae) -> {
           mess.setText("");
           investSelect.setSelectedIndex(0);
           symbolBox.setText("");
           nameBox.setText("");
           quantityBox.setText("");
           priceBox.setText("");
        });
        
        buyM.addActionListener((ActionEvent ae) -> {
            mainPanel.setVisible(false);
            sellPanel.setVisible(false);
            updatePanel.setVisible(false);
            gainPanel.setVisible(false);
            searchPanel.setVisible(false);
            buyPanel.setVisible(true);
            mess.setText("");
            investSelect.setSelectedIndex(0);
            symbolBox.setText("");
            nameBox.setText("");
            quantityBox.setText("");
            priceBox.setText("");
        });
        
        //Sell Screen
        JPanel sellLeft = new JPanel();
        sellLeft.setLayout(new GridLayout(4, 1));
        sellLeft.add(new JLabel("Selling an investment"));
        JTextField symbolBox2 = new JTextField();
        JTextField quantityBox2 = new JTextField();
        JTextField priceBox2 = new JTextField();
        JPanel lSymbolBox2 = make1x2Panel(new JLabel("Symbol"), symbolBox2);
        JPanel lQuantityBox2 = make1x2Panel(new JLabel("Quantity"), quantityBox2);
        JPanel lPriceBox2 = make1x2Panel(new JLabel("Price"), priceBox2);
        sellLeft.add(lSymbolBox2);
        sellLeft.add(lQuantityBox2);
        sellLeft.add(lPriceBox2);
        
        JPanel sellRight = new JPanel();
        sellRight.setLayout(new GridLayout(2,1));
        JButton sell = new JButton("Sell");
        JButton reset2 = new JButton("Reset");
        sellRight.add(reset2);
        sellRight.add(sell);
        
        JPanel sellTop = make1x2Panel(sellLeft, sellRight);
        
        JPanel sellBot = new JPanel();
        sellBot.setLayout(new BorderLayout());
        
        JLabel messages2 = new JLabel("Messages");
        messages2.setHorizontalAlignment(JLabel.CENTER);
        sellBot.add(messages2, BorderLayout.NORTH);
        JTextArea mess2 = new JTextArea();
        JScrollPane scroll2 = new JScrollPane(mess2);
        sellBot.add(scroll2, BorderLayout.CENTER);
        mess2.setEditable(false);
        
        sellPanel.add(sellTop);
        sellPanel.add(sellBot);
        
        sell.addActionListener((ActionEvent ae) -> {
            String sell1 = investments.sell(symbolBox2.getText(), quantityBox2.getText(), priceBox2.getText());
            mess2.setText(sell1 + "\n");
            mess2.append(investments.toString());
        });
        reset2.addActionListener((ActionEvent ae) -> {
           mess2.setText("");
           symbolBox2.setText("");
           quantityBox2.setText("");
           priceBox2.setText("");
        });
        
        sellM.addActionListener((ActionEvent ae) -> {
            mainPanel.setVisible(false);
            buyPanel.setVisible(false);
            updatePanel.setVisible(false);
            gainPanel.setVisible(false);
            searchPanel.setVisible(false);
            sellPanel.setVisible(true);
            mess2.setText("");
            symbolBox2.setText("");
            quantityBox2.setText("");
            priceBox2.setText("");
        });
        
        
        //Update Screen
        JPanel upLeft = new JPanel();
        upLeft.setLayout(new GridLayout(4, 1));
        upLeft.add(new JLabel("Updating investments"));
        JTextField symbolBox3 = new JTextField();
        symbolBox3.setEditable(false);
        JTextField nameBox3 = new JTextField();
        nameBox3.setEditable(false);
        JTextField priceBox3 = new JTextField();
        JPanel lsymbolBox3 = make1x2Panel(new JLabel("Symbol"), symbolBox3);
        JPanel lnameBox3 = make1x2Panel(new JLabel("Name"), nameBox3);
        JPanel lpriceBox3 = make1x2Panel(new JLabel("Price"), priceBox3);
        upLeft.add(lsymbolBox3);
        upLeft.add(lnameBox3);
        upLeft.add(lpriceBox3);
        
        JPanel upRight = new JPanel();
        upRight.setLayout(new GridLayout(3, 1));
        JButton prev = new JButton("Prev");
        JButton next = new JButton("Next");
        JButton save = new JButton("Save");
        upRight.add(prev);
        upRight.add(next);
        upRight.add(save);
        
        JPanel upTop = make1x2Panel(upLeft, upRight);
        
        JPanel upBot = new JPanel();
        upBot.setLayout(new BorderLayout());
        JLabel messages3 = new JLabel("Messages");
        messages3.setHorizontalAlignment(JLabel.CENTER);
        upBot.add(messages3, BorderLayout.NORTH);
        JTextArea mess3 = new JTextArea();
        JScrollPane scroll3 = new JScrollPane(mess3);
        upBot.add(scroll3, BorderLayout.CENTER);
        mess3.setEditable(false);
        
        updatePanel.add(upTop);
        updatePanel.add(upBot);
        


        prev.addActionListener((ActionEvent ae) -> {
            posn--;
            symbolBox3.setText(symbols.get(posn));
            nameBox3.setText(names.get(posn));
            priceBox3.setText(prices.get(posn).toString());
            mess3.setText("");
            if(posn == 0)
            {
                prev.setEnabled(false);
            }
            next.setEnabled(true);
        });
        next.addActionListener((ActionEvent ar) -> {
            posn++;
            symbolBox3.setText(symbols.get(posn));
            nameBox3.setText(names.get(posn));
            priceBox3.setText(prices.get(posn).toString());
            mess3.setText("");
            if(posn == symbols.size() - 1)
            {
                next.setEnabled(false);
            }
            prev.setEnabled(true);
        });
        save.addActionListener((ActionEvent ar) -> {
            String update1 = investments.update(posn, priceBox3.getText());
            mess3.setText(update1);
            mess3.append(investments.toString());
            prices = investments.getPrices();
        });
        
        
        update.addActionListener((ActionEvent ae) -> {
           mainPanel.setVisible(false);
           buyPanel.setVisible(false);
           sellPanel.setVisible(false);
           gainPanel.setVisible(false);
           searchPanel.setVisible(false);
           updatePanel.setVisible(true);
           symbols = investments.getSymbols();
           names = investments.getNames();
           prices = investments.getPrices();
           if(symbols.size() > 0)
           {
                symbolBox3.setText(symbols.get(0));
                nameBox3.setText(names.get(0));
                priceBox3.setText(prices.get(0).toString());
                mess3.setText("");
                prev.setEnabled(false);
                next.setEnabled(true);
                if(symbols.size() == 1)
                {
                    next.setEnabled(false);
                }
            }
            else
            {
                mess3.setText("You don't have any investments.");
                symbolBox3.setText("");
                nameBox3.setText("");
                priceBox3.setText("");
                prev.setEnabled(false);
                next.setEnabled(false);
            }
        });
        
        //Gain screen
        JPanel gainLeft = new JPanel();
        gainLeft.setLayout(new GridLayout(2,1));
        JTextField gainBox = new JTextField();
        JPanel gainField = make1x2Panel(new JLabel("Total gain"), gainBox);
        gainLeft.add(new JLabel("Getting total gain"));
        gainLeft.add(gainField);
        gainBox.setEditable(false);
        
        JPanel gainTop = make1x2Panel(gainLeft, new JPanel());
        
        JPanel gainBot = new JPanel();
        gainBot.setLayout(new BorderLayout());
        JLabel messages4 = new JLabel("Messages");
        messages4.setHorizontalAlignment(JLabel.CENTER);
        gainBot.add(messages4, BorderLayout.NORTH);
        JTextArea mess4 = new JTextArea();
        JScrollPane scroll4 = new JScrollPane(mess4);
        gainBot.add(scroll4, BorderLayout.CENTER);
        mess4.setEditable(false);
        
        gainPanel.add(gainTop, BorderLayout.NORTH);
        gainPanel.add(gainBot, BorderLayout.CENTER);
        
        gain.addActionListener((ActionEvent ae) -> {
           mainPanel.setVisible(false);
           buyPanel.setVisible(false);
           sellPanel.setVisible(false);
           updatePanel.setVisible(false);
           searchPanel.setVisible(false);
           gainPanel.setVisible(true);
           
           String[] output = investments.getGain();
           
           gainBox.setText(output[1]);
           mess4.setText(output[0]);
           
        });
        
        //Search Screen
        JTextField symbolBox5 = new JTextField();
        JTextField nameBox5 = new JTextField();
        JTextField lowPriceBox = new JTextField();
        JTextField highPriceBox = new JTextField();
        JPanel lSymbolBox5 = make1x2Panel(new JLabel("Symbol"), symbolBox5);
        JPanel lNameBox5 = make1x2Panel(new JLabel("Name keywords"), nameBox5);
        JPanel llowPriceBox = make1x2Panel(new JLabel("Low price"), lowPriceBox);
        JPanel lhighPriceBox = make1x2Panel(new JLabel("High price"), highPriceBox);
        
        JPanel searchLeft = new JPanel();
        searchLeft.setLayout(new GridLayout(5, 1));
        searchLeft.add(new JLabel("Searching investments"));
        searchLeft.add(lSymbolBox5);
        searchLeft.add(lNameBox5);
        searchLeft.add(llowPriceBox);
        searchLeft.add(lhighPriceBox);
        
        JPanel searchRight = new JPanel();
        searchRight.setLayout(new GridLayout(2,1));
        JButton reset5 = new JButton("Reset");
        JButton search = new JButton("Search");
        searchRight.add(reset5);
        searchRight.add(search);
        
        JPanel searchTop = make1x2Panel(searchLeft, searchRight);
        
        
        JPanel searchBot = new JPanel();
        searchBot.setLayout(new BorderLayout());
        
        JLabel messages5 = new JLabel("Messages");
        messages5.setHorizontalAlignment(JLabel.CENTER);
        searchBot.add(messages5, BorderLayout.NORTH);
        JTextArea mess5 = new JTextArea();
        JScrollPane scroll5 = new JScrollPane(mess5);
        searchBot.add(scroll5, BorderLayout.CENTER);
        mess5.setEditable(false);
        
        searchPanel.add(searchTop);
        searchPanel.add(searchBot);
        
        
        reset5.addActionListener((ActionEvent ae) -> { 
            symbolBox5.setText("");
            nameBox5.setText("");
            lowPriceBox.setText("");
            highPriceBox.setText("");
            mess5.setText("");
        });
        search.addActionListener((ActionEvent ae) -> {
            String search1 = investments.search(symbolBox5.getText(), nameBox5.getText(), lowPriceBox.getText(), highPriceBox.getText());
            mess5.setText(search1);
        });
        
        searchM.addActionListener((ActionEvent ae) -> {
            mainPanel.setVisible(false);
            buyPanel.setVisible(false);
            updatePanel.setVisible(false);
            gainPanel.setVisible(false);
            sellPanel.setVisible(false);
            searchPanel.setVisible(true);
            symbolBox5.setText("");
            nameBox5.setText("");
            lowPriceBox.setText("");
            highPriceBox.setText("");
            mess5.setText("");
        });
        

        mainPanel.add(ascroll);
        area.setEditable(false);
        this.setJMenuBar(menuBar);
        this.add(gainPanel);
        this.add(buyPanel);
        this.add(sellPanel);
        this.add(updatePanel);
        this.add(searchPanel);
        this.add(mainPanel);
        
        buyPanel.setVisible(false);
        sellPanel.setVisible(false);
        updatePanel.setVisible(false);
        gainPanel.setVisible(false);
        searchPanel.setVisible(false);
        mainPanel.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        
        
        

        
      
    }
    
}
