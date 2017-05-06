package com.mycompany.bhsit.database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ApplicationInterface extends JFrame {
	
    private static final long serialVersionUID = 123456789L;
    
    private JTextField compidTextField;
    private JTextField tabletidTextField;
    private JTextField compnameTextField;
    private JTextField tabletnameTextField;
    private JTextField composTextField;
    private JTextField tabletosTextField;
    private JTextField compipTextField;
    private JTextField tabletipTextField;
    private JTextField otheripTextField;
    private JTextField compsernumTextField;
    private JTextField tabletsernumTextField;
    private JTextField othersernumTextField;
    private JTextField compdatepurchTextField;
    private JTextField tabletdatepurchTextField;
    private JTextField otherdatepurchTextField;
    private JTextField compstatusTextField;
    private JTextField tabletstatusTextField;
    private JTextField otherstatusTextField;
    private JTextField searchTextField;
    
    private JTextField typeTextField;
    private JTextField makeTextField;
    private JTextField modelTextField;
    private JTextArea compnotesTextArea;
    private JTextArea tabletnotesTextArea;
    private JTextArea othernotesTextArea;
    
    private DefaultListModel<Computers> computersListModel;
    private JList<Computers> computersList;
    
    private DefaultListModel<Tablets> tabletsListModel;
    private JList<Tablets> tabletsList;
    
    private DefaultListModel<OtherHardware> otherHardwareListModel;
    private JList<OtherHardware> otherHardwareList;
    
    private DefaultListModel<Search> searchListModel;
    private JList<Search> searchList;
       
    private Action comprefreshAction;
    private Action compnewAction;
    private Action compsaveAction;
    private Action compdeleteAction;
    private Action tabletrefreshAction;
    private Action tabletnewAction;
    private Action tabletsaveAction;
    private Action tabletdeleteAction;
    private Action otherrefreshAction;
    private Action othernewAction;
    private Action othersaveAction;
    private Action otherdeleteAction;
    private Action searchAction;
    
    private Computers compSelected;
    private Computers tempComp;
    private Tablets tabletSelected;
    private Tablets tempTablet;
    private OtherHardware otherHardwareSelected;
    private OtherHardware tempHardware;
    
    String c = "computers";
    String t = "tablets";
    String o = "otherhardware";
    String u = "Use";
    String s = "Storage";
    String r = "Repair";
    
    LayoutManager lm = new LayoutManager();    
    SearchHelper sh = new SearchHelper();
    Computers comp = new Computers();
    ComputersHelper ch = new ComputersHelper();
    Tablets tab = new Tablets();
    OtherHardware other = new OtherHardware();
    
    public void Launch() throws SQLException {
        initActions();
  	add(TabBar(), BorderLayout.CENTER);
    }
    
    public void initActions() {
        comprefreshAction = new AbstractAction("Refresh"){
            private static final long serialVersionUID = 123456789L;
        
            @Override
            public void actionPerformed(final ActionEvent e){
                comprefreshData();
            }
        };
        
        compnewAction = new AbstractAction("New"){
            private static final long serialVersionUID = 123456789L;
            
            @Override
            public void actionPerformed(final ActionEvent e){
                compcreateNew();
            }
        };
        
        compsaveAction = new AbstractAction("Save"){
            private static final long serialVersionUID = 123456789L;
            
            @Override
            public void actionPerformed(final ActionEvent e){
                compsave();
            }
        };
        
        compdeleteAction = new AbstractAction("Delete"){
            private static final long serialVersionUID = 123456789L;
            
            @Override
            public void actionPerformed(final ActionEvent e){
                compdelete();
            }
        };
    
        tabletrefreshAction = new AbstractAction("Refresh"){
            private static final long serialVersionUID = 123456789L;
        
            @Override
            public void actionPerformed(final ActionEvent e){
                tabletrefreshData();
            }
        };
        
        tabletnewAction = new AbstractAction("New"){
            private static final long serialVersionUID = 123456789L;
            
            @Override
            public void actionPerformed(final ActionEvent e){
                tabletcreateNew();
            }
        };
        
        tabletsaveAction = new AbstractAction("Save"){
            private static final long serialVersionUID = 123456789L;
            
            @Override
            public void actionPerformed(final ActionEvent e){
                tabletsave();
            }
        };
        
        tabletdeleteAction = new AbstractAction("Delete"){
            private static final long serialVersionUID = 123456789L;
            
            @Override
            public void actionPerformed(final ActionEvent e){
                tabletdelete();
            }
        };
    
        otherrefreshAction = new AbstractAction("Refresh"){
            private static final long serialVersionUID = 123456789L;
        
            @Override
            public void actionPerformed(final ActionEvent e){
                otherrefreshData();
            }
        };
        
        othernewAction = new AbstractAction("New"){
            private static final long serialVersionUID = 123456789L;
            
            @Override
            public void actionPerformed(final ActionEvent e){
                othercreateNew();
            }
        };
        
        othersaveAction = new AbstractAction("Save"){
            private static final long serialVersionUID = 123456789L;
            
            @Override
            public void actionPerformed(final ActionEvent e){
                othersave();
            }
        };
        
        otherdeleteAction = new AbstractAction("Delete"){
            private static final long serialVersionUID = 123456789L;
            
            @Override
            public void actionPerformed(final ActionEvent e){
                otherdelete();
            }
        };
        
        searchAction = new AbstractAction("Search"){
            private static final long serialVersionUID = 123456789L;
            
            @Override
            public void actionPerformed(final ActionEvent e){
                Search();
            }
        };
    }
    
    public JPanel CreateHomePanel() throws SQLException{        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.weightx = 3;
        constraints.weighty = 1;
        constraints.insets = new Insets(2,2,2,2);
        
        final JPanel homepanel = new JPanel();
        homepanel.setLayout(new GridBagLayout());
        
        JLabel title = new JLabel("Summary of Devices");   
        JLabel title2 = new JLabel("Total: "+(Integer.parseInt(sh.GetCategoryTotals(1,c))
                                            +Integer.parseInt(sh.GetCategoryTotals(2,t))
                                            +Integer.parseInt(sh.GetCategoryTotals(3,o))));
        title.setFont(new Font("Serif",Font.BOLD, 35));
        title2.setFont(new Font("Serif",Font.BOLD, 35));
        homepanel.add(title,constraints);
        constraints.gridy = 2;
        homepanel.add(title2,constraints);
        
        JPanel compupdate = new JPanel();
        compupdate.setPreferredSize(new Dimension(300,200));
        compupdate.setLayout(new GridBagLayout());
        compupdate.add(lm.SetHomeTitleLabel(new JLabel("Computers: "+Integer.parseInt(sh.GetCategoryTotals(1,c)))),lm.SetTitleConstraints(1));
        compupdate.add(lm.SetHomeLabel(new JLabel("In Use: "+sh.GetTotals(1,c,u))),lm.SetHomeCategoryConstraints(2));
        compupdate.add(lm.SetHomeLabel(new JLabel("In Storage: "+sh.GetTotals(1,c,s))),lm.SetHomeCategoryConstraints(3));
        compupdate.add(lm.SetHomeLabel(new JLabel("In Repair: "+sh.GetTotals(1,c,r))),lm.SetHomeCategoryConstraints(4));
        homepanel.add(compupdate,lm.SetHomeConstraints(1));
        
        JPanel tabletupdate = new JPanel();
        tabletupdate.setPreferredSize(new Dimension(300,200));
        tabletupdate.setLayout(new GridBagLayout());
        tabletupdate.add(lm.SetHomeTitleLabel(new JLabel("Tablets: "+Integer.parseInt(sh.GetCategoryTotals(1,c)))),lm.SetTitleConstraints(1));
        tabletupdate.add(lm.SetHomeLabel(new JLabel("In Use: "+sh.GetTotals(2,t,u))),lm.SetHomeCategoryConstraints(2));
        tabletupdate.add(lm.SetHomeLabel(new JLabel("In Storage: "+sh.GetTotals(2,t,s))),lm.SetHomeCategoryConstraints(3));
        tabletupdate.add(lm.SetHomeLabel(new JLabel("In Repair: "+sh.GetTotals(2,t,r))),lm.SetHomeCategoryConstraints(4));
        homepanel.add(tabletupdate,lm.SetHomeConstraints(2));
        
        JPanel otherdevupdate = new JPanel();
        otherdevupdate.setPreferredSize(new Dimension(300,200));
        otherdevupdate.setLayout(new GridBagLayout());
        otherdevupdate.add(lm.SetHomeTitleLabel(new JLabel("Other Devices: "+Integer.parseInt(sh.GetCategoryTotals(1,c)))),lm.SetTitleConstraints(1));
        otherdevupdate.add(lm.SetHomeLabel(new JLabel("In Use: "+sh.GetTotals(3,o,u))),lm.SetHomeCategoryConstraints(2));
        otherdevupdate.add(lm.SetHomeLabel(new JLabel("In Storage: "+sh.GetTotals(3,o,s))),lm.SetHomeCategoryConstraints(3));
        otherdevupdate.add(lm.SetHomeLabel(new JLabel("In Repair: "+sh.GetTotals(3,o,r))),lm.SetHomeCategoryConstraints(4));
        homepanel.add(otherdevupdate,lm.SetHomeConstraints(3));
        
        return homepanel;
    }
    /*
     * This method creats the tabbar and respective panes
     * allowing the user to see the different categories of devices
     */
    public JComponent TabBar() throws SQLException{        
        if(ch.getLaunch() == 0){
            comp.Inserts();
            tab.Inserts();
            other.Inserts();
            comp.SetLaunch(1);
        }
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.weightx = 3;
        constraints.weighty = 1;
        constraints.insets = new Insets(2,2,2,2);
        
        final JPanel homepanel = new JPanel();
        homepanel.setLayout(new GridBagLayout());
        
        JLabel title = new JLabel("Summary of Devices");   
        JLabel title2 = new JLabel("Total: "+(Integer.parseInt(sh.GetCategoryTotals(1,c))
                                            +Integer.parseInt(sh.GetCategoryTotals(2,t))
                                            +Integer.parseInt(sh.GetCategoryTotals(3,o))));
        title.setFont(new Font("Serif",Font.BOLD, 35));
        title2.setFont(new Font("Serif",Font.BOLD, 35));
        homepanel.add(title,constraints);
        constraints.gridy = 2;
        homepanel.add(title2,constraints);
        
        JPanel compupdate = new JPanel();
        compupdate.setPreferredSize(new Dimension(300,200));
        compupdate.setLayout(new GridBagLayout());
        compupdate.add(lm.SetHomeTitleLabel(new JLabel("Computers: "+Integer.parseInt(sh.GetCategoryTotals(1,c)))),lm.SetTitleConstraints(1));
        compupdate.add(lm.SetHomeLabel(new JLabel("In Use: "+sh.GetTotals(1,c,u))),lm.SetHomeCategoryConstraints(2));
        compupdate.add(lm.SetHomeLabel(new JLabel("In Storage: "+sh.GetTotals(1,c,s))),lm.SetHomeCategoryConstraints(3));
        compupdate.add(lm.SetHomeLabel(new JLabel("In Repair: "+sh.GetTotals(1,c,r))),lm.SetHomeCategoryConstraints(4));
        homepanel.add(compupdate,lm.SetHomeConstraints(1));
        
        JPanel tabletupdate = new JPanel();
        tabletupdate.setPreferredSize(new Dimension(300,200));
        tabletupdate.setLayout(new GridBagLayout());
        tabletupdate.add(lm.SetHomeTitleLabel(new JLabel("Tablets: "+Integer.parseInt(sh.GetCategoryTotals(1,c)))),lm.SetTitleConstraints(1));
        tabletupdate.add(lm.SetHomeLabel(new JLabel("In Use: "+sh.GetTotals(2,t,u))),lm.SetHomeCategoryConstraints(2));
        tabletupdate.add(lm.SetHomeLabel(new JLabel("In Storage: "+sh.GetTotals(2,t,s))),lm.SetHomeCategoryConstraints(3));
        tabletupdate.add(lm.SetHomeLabel(new JLabel("In Repair: "+sh.GetTotals(2,t,r))),lm.SetHomeCategoryConstraints(4));
        homepanel.add(tabletupdate,lm.SetHomeConstraints(2));
        
        JPanel otherdevupdate = new JPanel();
        otherdevupdate.setPreferredSize(new Dimension(300,200));
        otherdevupdate.setLayout(new GridBagLayout());
        otherdevupdate.add(lm.SetHomeTitleLabel(new JLabel("Other Devices: "+Integer.parseInt(sh.GetCategoryTotals(1,c)))),lm.SetTitleConstraints(1));
        otherdevupdate.add(lm.SetHomeLabel(new JLabel("In Use: "+sh.GetTotals(3,o,u))),lm.SetHomeCategoryConstraints(2));
        otherdevupdate.add(lm.SetHomeLabel(new JLabel("In Storage: "+sh.GetTotals(3,o,s))),lm.SetHomeCategoryConstraints(3));
        otherdevupdate.add(lm.SetHomeLabel(new JLabel("In Repair: "+sh.GetTotals(3,o,r))),lm.SetHomeCategoryConstraints(4));
        homepanel.add(otherdevupdate,lm.SetHomeConstraints(3));
        
        //COMPUTERS
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.add(compcreateListPane(),lm.SetListPaneConstraints());
        comprefreshData();
        panel.add(lm.CreateToolBar(compnewAction, compsaveAction, compdeleteAction, comprefreshAction),lm.SetToolBarConstraints());
        panel.add(lm.SetLabel("ID"), lm.SetLabelConstraints(1));
        compidTextField = new JTextField();
        panel.add(compidTextField, lm.SetTextFieldConstraints(1));
        panel.add(lm.SetLabel("Computer Name"), lm.SetLabelConstraints(2));
        compnameTextField = new JTextField();
        panel.add(compnameTextField, lm.SetTextFieldConstraints(2));
        panel.add(lm.SetLabel("OS"), lm.SetLabelConstraints(3));
        composTextField = new JTextField();
        panel.add(composTextField, lm.SetTextFieldConstraints(3));
        panel.add(lm.SetLabel("IP"), lm.SetLabelConstraints(4));
        compipTextField = new JTextField();
        panel.add(compipTextField, lm.SetTextFieldConstraints(4));
        panel.add(lm.SetLabel("Serial Number"), lm.SetLabelConstraints(5));
        compsernumTextField = new JTextField();
        panel.add(compsernumTextField, lm.SetTextFieldConstraints(5));
        panel.add(lm.SetLabel("Date Purchased"), lm.SetLabelConstraints(6));
        compdatepurchTextField = new JTextField();
        panel.add(compdatepurchTextField, lm.SetTextFieldConstraints(6));
        panel.add(lm.SetLabel("Status"), lm.SetLabelConstraints(7));
        compstatusTextField = new JTextField();
        panel.add(compstatusTextField, lm.SetTextFieldConstraints(7));
        panel.add(lm.SetLabel("Notes"), lm.SetLabelConstraints(8));
        compnotesTextArea = new JTextArea();
        panel.add(new JScrollPane(compnotesTextArea), lm.SetTextFieldConstraints(8));      
        
        //TABLETS
        final JPanel tabletpanel = new JPanel(new GridBagLayout());
        tabletpanel.add(tabletcreateListPane(),lm.SetListPaneConstraints());
        tabletrefreshData();
        tabletpanel.add(lm.CreateToolBar(tabletnewAction, tabletsaveAction, tabletdeleteAction, tabletrefreshAction),lm.SetToolBarConstraints());
        tabletpanel.add(lm.SetLabel("ID"), lm.SetLabelConstraints(1));
        tabletidTextField = new JTextField();
        tabletpanel.add(tabletidTextField, lm.SetTextFieldConstraints(1));
        tabletpanel.add(lm.SetLabel("Tablet Name"), lm.SetLabelConstraints(2));
        tabletnameTextField = new JTextField();
        tabletpanel.add(tabletnameTextField, lm.SetTextFieldConstraints(2));
        tabletpanel.add(lm.SetLabel("OS"), lm.SetLabelConstraints(3));
        tabletosTextField = new JTextField();
        tabletpanel.add(tabletosTextField, lm.SetTextFieldConstraints(3));
        tabletpanel.add(lm.SetLabel("IP"), lm.SetLabelConstraints(4));
        tabletipTextField = new JTextField();
        tabletpanel.add(tabletipTextField, lm.SetTextFieldConstraints(4));
        tabletpanel.add(lm.SetLabel("Serial Number"), lm.SetLabelConstraints(5));
        tabletsernumTextField = new JTextField();
        tabletpanel.add(tabletsernumTextField, lm.SetTextFieldConstraints(5));
        tabletpanel.add(lm.SetLabel("Date Purchased"), lm.SetLabelConstraints(6));
        tabletdatepurchTextField = new JTextField();
        tabletpanel.add(tabletdatepurchTextField, lm.SetTextFieldConstraints(6));
        tabletpanel.add(lm.SetLabel("Status"), lm.SetLabelConstraints(7));
        tabletstatusTextField = new JTextField();
        tabletpanel.add(tabletstatusTextField, lm.SetTextFieldConstraints(7));
        tabletpanel.add(lm.SetLabel("Notes"), lm.SetLabelConstraints(8));
        tabletnotesTextArea = new JTextArea();
        tabletpanel.add(new JScrollPane(tabletnotesTextArea), lm.SetTextFieldConstraints(8));
               
        //OTHER DEVICES
        final JPanel otherdevpanel = new JPanel(new GridBagLayout());
        otherdevpanel.add(othercreateListPane(),lm.SetListPaneConstraints());
        otherrefreshData();
        otherdevpanel.add(lm.CreateToolBar(othernewAction, othersaveAction, otherdeleteAction, otherrefreshAction),lm.SetToolBarConstraints());
        otherdevpanel.add(lm.SetLabel("Type"), lm.SetLabelConstraints(1));
        typeTextField = new JTextField();
        otherdevpanel.add(typeTextField, lm.SetTextFieldConstraints(1));
        otherdevpanel.add(lm.SetLabel("Make"), lm.SetLabelConstraints(2));
        makeTextField = new JTextField();
        otherdevpanel.add(makeTextField, lm.SetTextFieldConstraints(2));
        otherdevpanel.add(lm.SetLabel("Model"), lm.SetLabelConstraints(3));
        modelTextField = new JTextField();
        otherdevpanel.add(modelTextField, lm.SetTextFieldConstraints(3));
        otherdevpanel.add(lm.SetLabel("IP"), lm.SetLabelConstraints(4));
        otheripTextField = new JTextField();
        otherdevpanel.add(otheripTextField, lm.SetTextFieldConstraints(4));
        otherdevpanel.add(lm.SetLabel("Serial Number"), lm.SetLabelConstraints(5));
        othersernumTextField = new JTextField();
        otherdevpanel.add(othersernumTextField, lm.SetTextFieldConstraints(5));
        otherdevpanel.add(lm.SetLabel("Date Purchased"), lm.SetLabelConstraints(6));
        otherdatepurchTextField = new JTextField();
        otherdevpanel.add(otherdatepurchTextField, lm.SetTextFieldConstraints(6));
        otherdevpanel.add(lm.SetLabel("Status"), lm.SetLabelConstraints(7));
        otherstatusTextField = new JTextField();
        otherdevpanel.add(otherstatusTextField, lm.SetTextFieldConstraints(7));
        otherdevpanel.add(lm.SetLabel("Notes"), lm.SetLabelConstraints(8));
        othernotesTextArea = new JTextArea();
        otherdevpanel.add(new JScrollPane(othernotesTextArea), lm.SetTextFieldConstraints(8));
        
        //SEARCH
        final JPanel searchpanel = new JPanel(new GridBagLayout());
        //searchpanel.setBackground(Color.blue);

        constraints = new GridBagConstraints();
        constraints.gridwidth = 2;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        searchTextField = new JTextField();
        searchpanel.add(searchTextField, constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        searchpanel.add(lm.CreateSearchBar(searchAction),constraints);
                
        constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(2,2,2,2);
        JLabel jlabel = new JLabel("Results");
        jlabel.setFont(new Font("Serif",Font.BOLD, 20));
        searchpanel.add(jlabel, constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridy = 2;
        constraints.weighty = 3;
        constraints.weightx = 3;
        constraints.gridheight = 8;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        searchpanel.add(searchcreateListPane(),constraints);
        searchrefreshData();
        
        final JTabbedPane tabbar = new JTabbedPane();
        tabbar.add("Home", homepanel);
        tabbar.add("Computers", panel);
        tabbar.add("Tablets", tabletpanel);
        tabbar.add("Other Devices", otherdevpanel);
        tabbar.add("Search", searchpanel);
        tabbar.setBackground(Color.LIGHT_GRAY);
        tabbar.setFont(new Font("Serif", Font.BOLD, 20));
        tabbar.setPreferredSize(new Dimension(100, 955));
        
        tabbar.addChangeListener(new ChangeListener() {
            // This method is called whenever the selected tab changes
           @Override
           public void stateChanged(ChangeEvent evt) {
                JTabbedPane pane = (JTabbedPane)evt.getSource();
                // Get current tab
                if(pane.getSelectedIndex()==4)
                {
                    searchrefreshData();
                }
                /*if(pane.getSelectedIndex()==1 || pane.getSelectedIndex()==2 || pane.getSelectedIndex()==3)
                {
                    
                    homepanel.repaint();
                    /*    tabbar.removeTabAt(0);
                    try {                   
                        tabbar.insertTab("Home", null, CreateHomePanel(), null, 0);
                    } catch (SQLException ex) {
                        Logger.getLogger(ApplicationInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }*/
            }
          });

        return tabbar;          
    }
       
    private void comprefreshData(){
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
        
    }
    
    private void compsave(){
        if(compSelected!=null){
            tempComp = compSelected;
            try{
                tempComp.delete(tempComp);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Failed to save the selected computer.", "Save", JOptionPane.WARNING_MESSAGE);
            }
            compSelected.setId(compidTextField.getText());
            compSelected.setCompname(compnameTextField.getText());
            compSelected.setOs(composTextField.getText());
            compSelected.setIp(compipTextField.getText());
            compSelected.setSernum(compsernumTextField.getText());
            compSelected.setDatepurch(compdatepurchTextField.getText());
            compSelected.setStatus(compstatusTextField.getText());
            compSelected.setNotes(compnotesTextArea.getText());
            try{
                compSelected.save();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Failed to save the selected computer.\nCheck the Date field.\nDate Format: YYYY-MM-DD", "Save", JOptionPane.WARNING_MESSAGE);
            }finally{
                comprefreshData();
            }
        }
    }
       
    private void compcreateNew(){
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
    
    private void compdelete(){
        if(compSelected != null){
            if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + compSelected + "?","Delete",JOptionPane.YES_NO_OPTION)){
                try{
                    compSelected.delete(compSelected);
                }catch (final SQLException e){
                    JOptionPane.showMessageDialog(this, "Failed to delete the selected computer", "Delete",JOptionPane.WARNING_MESSAGE);
                }finally{
                    setSelectedComputer(null);
                    comprefreshData();
                }
            }
        }
    }
    
    private void setSelectedComputer(Computers computer){
        this.compSelected = computer;
        if(computer == null){
            compidTextField.setText("");
            compnameTextField.setText("");
            composTextField.setText("");
            compipTextField.setText("");
            compsernumTextField.setText("");
            compdatepurchTextField.setText("");
            compstatusTextField.setText("");
            compnotesTextArea.setText("");
        }else{
            compidTextField.setText(computer.getId());
            compnameTextField.setText(computer.getCompname());
            composTextField.setText(computer.getOs());
            compipTextField.setText(computer.getIp());
            compsernumTextField.setText(computer.getSernum());
            compdatepurchTextField.setText(computer.getDatepurch());
            compstatusTextField.setText(computer.getStatus());
            compnotesTextArea.setText(computer.getNotes());
        }
    }
    
    private JComponent compcreateListPane(){
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
        
    private void tabletrefreshData(){
        tabletsListModel.removeAllElements();
        SwingWorker<Void, Tablets> worker = new SwingWorker<Void, Tablets>(){
           @Override
           protected Void doInBackground() throws Exception{
               List<Tablets> tablets = TabletsHelper.getInstance().getTablets();
               for(final Tablets tablet : tablets){
                publish(tablet);
               }
               return null;
           }
           
           @Override
           protected void process(final List<Tablets> chunks){
               for (final Tablets tablet : chunks){
                   tabletsListModel.addElement(tablet);
               }
           }
        };
        worker.execute();
    }
    
    private void tabletsave(){
        if(tabletSelected!=null){
            tempTablet = tabletSelected;
            try{
                tempTablet.delete(tempTablet);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Failed to save the selected tablet.", "Save", JOptionPane.WARNING_MESSAGE);
            }
            tabletSelected.setId(tabletidTextField.getText());
            tabletSelected.setTabletname(tabletnameTextField.getText());
            tabletSelected.setOs(tabletosTextField.getText());
            tabletSelected.setIp(tabletipTextField.getText());
            tabletSelected.setSernum(tabletsernumTextField.getText());
            tabletSelected.setDatepurch(tabletdatepurchTextField.getText());
            tabletSelected.setStatus(tabletstatusTextField.getText());
            tabletSelected.setNotes(tabletnotesTextArea.getText());
            try{
                tabletSelected.save();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Failed to save the selected tablet.", "Save", JOptionPane.WARNING_MESSAGE);
            }finally{
                tabletrefreshData();
            }
        }
    }
    
    private void tabletcreateNew(){
        Tablets tablet = new Tablets();
        tablet.setId("New");
        tablet.setTabletname("New");
        tablet.setOs("New");
        tablet.setIp("New");
        tablet.setSernum("New");
        tablet.setDatepurch("New");
        tablet.setStatus("New");
        tablet.setNotes("New");
        setSelectedTablet(tablet);
    }
    
    private void tabletdelete(){
        if(tabletSelected != null){
            if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + tabletSelected + "?","Delete",JOptionPane.YES_NO_OPTION)){
                try{
                    tabletSelected.delete(tabletSelected);
                }catch (final SQLException e){
                    JOptionPane.showMessageDialog(this, "Failed to delete the selected tablet", "Delete",JOptionPane.WARNING_MESSAGE);
                }finally{
                    setSelectedTablet(null);
                    tabletrefreshData();
                }
            }
        }
    }
    
    private void setSelectedTablet(Tablets tablet){
        this.tabletSelected = tablet;
        if(tablet == null){
            tabletidTextField.setText("");
            tabletnameTextField.setText("");
            tabletosTextField.setText("");
            tabletipTextField.setText("");
            tabletsernumTextField.setText("");
            tabletdatepurchTextField.setText("");
            tabletstatusTextField.setText("");
            tabletnotesTextArea.setText("");
        }else{
            tabletidTextField.setText(tablet.getId());
            tabletnameTextField.setText(tablet.getTabletname());
            tabletosTextField.setText(tablet.getOs());
            tabletipTextField.setText(tablet.getIp());
            tabletsernumTextField.setText(tablet.getSernum());
            tabletdatepurchTextField.setText(tablet.getDatepurch());
            tabletstatusTextField.setText(tablet.getStatus());
            tabletnotesTextArea.setText(tablet.getNotes());
        }
    }
    
    private JComponent tabletcreateListPane(){
       tabletsListModel = new DefaultListModel<>();
       tabletsList = new JList<>(tabletsListModel);
       tabletsList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
           @Override
           public void valueChanged(ListSelectionEvent e){
               if(!e.getValueIsAdjusting()){
                   Tablets selected = tabletsList.getSelectedValue();
                   setSelectedTablet(selected);
               }
           }
       });
       return new JScrollPane(tabletsList);
    }
        
    private void otherrefreshData(){
        otherHardwareListModel.removeAllElements();
        SwingWorker<Void, OtherHardware> worker = new SwingWorker<Void, OtherHardware>(){
           @Override
           protected Void doInBackground() throws Exception{
               List<OtherHardware> otherhardware = OtherHardwareHelper.getInstance().getOtherHardware();
               for(final OtherHardware otherHardware : otherhardware){
                publish(otherHardware);
               }
               return null;
           }
           
           @Override
           protected void process(final List<OtherHardware> chunks){
               for (final OtherHardware otherHardware : chunks){
                   otherHardwareListModel.addElement(otherHardware);
               }
           }
        };
        worker.execute();
    }
    
    private void othersave(){
        if(otherHardwareSelected!=null){
            tempHardware = otherHardwareSelected;
            try{
                tempHardware.delete(tempHardware);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Failed to save the selected Other Device.", "Save", JOptionPane.WARNING_MESSAGE);
            }
            otherHardwareSelected.setType(typeTextField.getText());
            otherHardwareSelected.setMake(makeTextField.getText());
            otherHardwareSelected.setModel(modelTextField.getText());
            otherHardwareSelected.setIp(otheripTextField.getText());
            otherHardwareSelected.setSernum(othersernumTextField.getText());
            otherHardwareSelected.setDatepurch(otherdatepurchTextField.getText());
            otherHardwareSelected.setStatus(otherstatusTextField.getText());
            otherHardwareSelected.setNotes(othernotesTextArea.getText());
            try{
                otherHardwareSelected.save();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Failed to save the selected Other Device.", "Save", JOptionPane.WARNING_MESSAGE);
            }finally{
                otherrefreshData();
            }
        }
    }
    
    private void othercreateNew(){
        OtherHardware otherHardware = new OtherHardware();
        otherHardware.setType("New");
        otherHardware.setMake("New");
        otherHardware.setModel("New");
        otherHardware.setIp("New");
        otherHardware.setSernum("New");
        otherHardware.setDatepurch("New");
        otherHardware.setStatus("New");
        otherHardware.setNotes("New");
        setSelectedOther(otherHardware);
    }
    
    private void otherdelete(){
        if(otherHardwareSelected != null){
            if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + otherHardwareSelected + "?","Delete",JOptionPane.YES_NO_OPTION)){
                try{
                    otherHardwareSelected.delete(otherHardwareSelected);
                }catch (final SQLException e){
                    JOptionPane.showMessageDialog(this, "Failed to delete the selected Other Device", "Delete",JOptionPane.WARNING_MESSAGE);
                }finally{
                    setSelectedOther(null);
                    otherrefreshData();
                }
            }
        }
    }
    
    private void setSelectedOther(OtherHardware otherHardware){
        this.otherHardwareSelected = otherHardware;
        if(otherHardware == null){
            typeTextField.setText("");
            makeTextField.setText("");
            modelTextField.setText("");
            otheripTextField.setText("");
            othersernumTextField.setText("");
            otherdatepurchTextField.setText("");
            otherstatusTextField.setText("");
            othernotesTextArea.setText("");
        }else{
            typeTextField.setText(otherHardware.getType());
            makeTextField.setText(otherHardware.getMake());
            modelTextField.setText(otherHardware.getModel());
            otheripTextField.setText(otherHardware.getIp());
            othersernumTextField.setText(otherHardware.getSernum());
            otherdatepurchTextField.setText(otherHardware.getDatepurch());
            otherstatusTextField.setText(otherHardware.getStatus());
            othernotesTextArea.setText(otherHardware.getNotes());
        }
    }
    
    private JComponent othercreateListPane(){
       otherHardwareListModel = new DefaultListModel<>();
       otherHardwareList = new JList<>(otherHardwareListModel);
       otherHardwareList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
           @Override
           public void valueChanged(ListSelectionEvent e){
               if(!e.getValueIsAdjusting()){
                   OtherHardware selected = otherHardwareList.getSelectedValue();
                   setSelectedOther(selected);
               }
           }
       });
       return new JScrollPane(otherHardwareList);
    }
    
    private void Search(){
        searchListModel.removeAllElements();
        SwingWorker<Void, Search> searchWorker = new SwingWorker<Void, Search>(){
           @Override
           protected Void doInBackground() throws Exception{
               String query = searchTextField.getText();
               String all = "*";
               if(all.equals(query)){
                   searchrefreshData();
               }else{
                    List<Search> searches = SearchHelper.getInstance().Search(query);
                    for(final Search search : searches){
                     publish(search);
                    }
                }
               return null;
           }
           @Override
           protected void process(final List<Search> chunks){
               for (final Search search : chunks){
                   searchListModel.addElement(search);
               }
           }
        };
        searchWorker.execute();
    }
    
    private JComponent searchcreateListPane(){       
       searchListModel = new DefaultListModel<>();
       searchList = new JList<>(searchListModel);
       return new JScrollPane(searchList);
    }
    
    private void searchrefreshData(){
        searchListModel.removeAllElements();
        SwingWorker<Void, Search> worker = new SwingWorker<Void, Search>(){
           @Override
           protected Void doInBackground() throws Exception{
               List<Search> search = SearchHelper.getInstance().getSearch();
               for(final Search searches : search){
                publish(searches);
               }
               return null;
           }
           
           @Override
           protected void process(final List<Search> chunks){
               for (final Search searches : chunks){
                   searchListModel.addElement(searches);
               }
           }
        };
        worker.execute();
    }
} //class