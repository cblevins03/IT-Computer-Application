package com.mycompany.bhsit.database;

import java.sql.SQLException;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/*
 * @author cgb_000
 */
public class Application extends JFrame {
    
    private static final long serialVersionUID = 123456789L;

    private JTextField idTextField;
    private JTextField compnameTextField;
    private JTextField osTextField;
    private JTextField ipTextField;
    private JTextField sernumTextField;
    private JTextField datepurchTextField;
    private JTextField statusTextField;
    private JTextArea notesTextArea;
    private JTextArea computersTextArea;
    
    private DefaultListModel<Computers> computersListModel;
    private JList<Computers> computersList;
       
    private Action refreshAction;
    private Action newAction;
    private Action saveAction;
    private Action deleteAction;
    
    private Computers selected;
    
    
    
    public Application(){
        initActions();
        initComponents();
        refreshData();
    }
    
    private JToolBar createToolBar(){
        final JToolBar toolBar = new JToolBar();
        toolBar.add(refreshAction);
        toolBar.addSeparator();
        toolBar.add(newAction);
        toolBar.add(saveAction);
        toolBar.addSeparator();
        toolBar.add(deleteAction);
        
        return toolBar;
    }
    
    private ImageIcon load(final String name){
        return new ImageIcon(getClass().getResource("/icons/" + name + ".png"));
    }
    
    private void refreshData(){
        computersListModel.removeAllElements();
        SwingWorker<Void, Computers> worker = new SwingWorker<Void, Computers>(){
           @Override
           protected Void doInBackground() throws Exception{
               List<Computers> computers = ComputersHelper.getInstance().getComputers();
               for(final Computers computer : computers){
                publish(computer);
               }
               return null;
           }
           
           @Override
           protected void process(final List<Computers> chunks){
               for (final Computers computer : chunks){
                   computersListModel.addElement(computer);
               }
           }
        };
        worker.execute();
        
        /*try{
            computersListModel.removeAllElements();
            for(Computers computer : computers){
                computersListModel.addElement(computer);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Failed to refresh the computers", "Refresh",JOptionPane.WARNING_MESSAGE);
        }*/
    }
    
    private void save(){
        if(selected!=null){
            selected.setId(idTextField.getText());
            selected.setCompname(compnameTextField.getText());
            selected.setOs(osTextField.getText());
            selected.setIp(ipTextField.getText());
            selected.setSernum(sernumTextField.getText());
            selected.setDatepurch(datepurchTextField.getText());
            selected.setStatus(statusTextField.getText());
            selected.setNotes(notesTextArea.getText());
            try{
                selected.save();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Failed to save the selected computer", "Save", JOptionPane.WARNING_MESSAGE);
            }finally{
                refreshData();
            }
        }
    }
    
    private void createNew(){
        Computers computer = new Computers();
        computer.setId("New");
        computer.setCompname("New");
        computer.setOs("New");
        computer.setIp("New");
        computer.setSernum("New");
        computer.setDatepurch("New");
        computer.setStatus("New");
        computer.setNotes("New");
        setSelectedComputer(computer);
    }
    
    private void delete(){
        if(selected != null){
            if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + selected + "?","Delete",JOptionPane.YES_NO_OPTION)){
                try{
                    selected.delete();
                }catch (final SQLException e){
                    JOptionPane.showMessageDialog(this, "Failed to delete the selected computer", "Delete",JOptionPane.WARNING_MESSAGE);
                }finally{
                    setSelectedComputer(null);
                    refreshData();
                }
            }
        }
    }
    private void initActions() {
        refreshAction = new AbstractAction("Refresh", load("Refresh")){
            private static final long serialVersionUID = 123456789L;
        
            @Override
            public void actionPerformed(final ActionEvent e){
            refreshData();
            }
        };
        
        newAction = new AbstractAction("New", load("New")){
            private static final long serialVersionUID = 123456789L;
            
            @Override
            public void actionPerformed(final ActionEvent e){
                createNew();
            }
        };
        
        saveAction = new AbstractAction("Save", load("Save")){
            private static final long serialVersionUID = 123456789L;
            
            @Override
            public void actionPerformed(final ActionEvent e){
                save();
            }
        };
        
        deleteAction = new AbstractAction("Delete", load("Delete")){
            private static final long serialVersionUID = 123456789L;
            
            @Override
            public void actionPerformed(final ActionEvent e){
                delete();
            }
        };
    }
    
    private void initComponents(){
        add(createToolBar(), BorderLayout.PAGE_START);
        add(createListPane(), BorderLayout.WEST);
        add(createEditor(), BorderLayout.CENTER);
    }
    
    private JComponent createListPane(){
       computersListModel = new DefaultListModel<>();
       computersList = new JList<>(computersListModel);
       computersList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
           @Override
           public void valueChanged(ListSelectionEvent e){
               if(!e.getValueIsAdjusting()){
                   Computers selected = computersList.getSelectedValue();
                   setSelectedComputer(selected);
               }
           }
       });
       
       return new JScrollPane(computersList);
    }
    
    private void setSelectedComputer(Computers computer){
        this.selected = computer;
        if(computer == null){
            idTextField.setText("");
            compnameTextField.setText("");
            osTextField.setText("");
            ipTextField.setText("");
            sernumTextField.setText("");
            datepurchTextField.setText("");
            statusTextField.setText("");
            notesTextArea.setText("");
        }else{
            idTextField.setText(computer.getId());
            compnameTextField.setText(computer.getCompname());
            osTextField.setText(computer.getOs());
            ipTextField.setText(computer.getIp());
            sernumTextField.setText(computer.getSernum());
            datepurchTextField.setText(computer.getDatepurch());
            statusTextField.setText(computer.getStatus());
            notesTextArea.setText(computer.getNotes());
        }
    }
    
    private JComponent createEditor(){
        final JPanel panel = new JPanel(new GridBagLayout());
        
        //ID
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(2,2,2,2);
        panel.add(new JLabel("ID"), constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        idTextField = new JTextField();
        panel.add(idTextField, constraints);
        
        //Compname
        constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(2,2,2,2);
        panel.add(new JLabel("Computer Name"), constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        compnameTextField = new JTextField();
        panel.add(compnameTextField, constraints);
        
        //OS
        constraints = new GridBagConstraints();
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(2,2,2,2);
        panel.add(new JLabel("Operating System"), constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        osTextField = new JTextField();
        panel.add(osTextField, constraints);
        
        //IP
        constraints = new GridBagConstraints();
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(2,2,2,2);
        panel.add(new JLabel("IP"), constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.weightx = 1;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        ipTextField = new JTextField();
        panel.add(ipTextField, constraints);
        
        //Serial Number
        constraints = new GridBagConstraints();
        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(2,2,2,2);
        panel.add(new JLabel("Serial Number"), constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.weightx = 1;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        sernumTextField = new JTextField();
        panel.add(sernumTextField, constraints);
        
        //Date Purchased
        constraints = new GridBagConstraints();
        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(2,2,2,2);
        panel.add(new JLabel("Date Purchased"), constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.weightx = 1;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        datepurchTextField = new JTextField();
        panel.add(datepurchTextField, constraints);
        
        //Status
        constraints = new GridBagConstraints();
        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(2,2,2,2);
        panel.add(new JLabel("Status"), constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.weightx = 1;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        statusTextField = new JTextField();
        panel.add(statusTextField, constraints);
        
        //Notes
        constraints = new GridBagConstraints();
        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(2,2,2,2);
        panel.add(new JLabel("Notes"), constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        notesTextArea = new JTextArea();
        panel.add(new JScrollPane(notesTextArea), constraints);
        
        /*Computers
        constraints = new GridBagConstraints();
        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(2,2,2,2);
        panel.add(new JLabel("Computers"), constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        computersTextArea = new JTextArea();
        panel.add(new JScrollPane(computersTextArea), constraints);
        */
        
        return panel;
    }
}
