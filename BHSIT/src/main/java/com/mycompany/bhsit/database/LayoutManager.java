/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bhsit.database;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;
/**
 *
 * @author cgb_000
 */
public class LayoutManager {
    private static final LayoutManager INSTANCE = new LayoutManager();
    GridBagConstraints constraints = new GridBagConstraints();
    
    public static LayoutManager getInstance(){
        return LayoutManager.INSTANCE;
    }
    
    public LayoutManager(){
    }
    
    public JToolBar CreateToolBar(Action newaction, Action save, Action delete, Action refresh){
        JToolBar toolbar = new JToolBar();

        toolbar.addSeparator();
        toolbar.setPreferredSize(new Dimension(70,40));
        toolbar.setRollover(true);
        toolbar.setFloatable(false);
        
        JButton EDIT = new JButton("Edit");// change
                EDIT.setFont(new Font("Serif",Font.BOLD, 30));
                toolbar.add(newaction);
                toolbar.addSeparator();
                
        JButton SAVE = new JButton("Save");
                SAVE.setFont(new Font("Serif",Font.BOLD, 30));
                toolbar.add(save);
                toolbar.addSeparator();

        JButton DELETE = new JButton("Delete");
                DELETE.setFont(new Font("Serif",Font.BOLD, 30));
                toolbar.add(delete);
                toolbar.addSeparator();

        JButton REFRESH = new JButton("Refresh");
                REFRESH.setFont(new Font("Serif",Font.BOLD, 30));
                REFRESH.setPreferredSize(new Dimension(20,20));
                toolbar.add(refresh);
        
        return toolbar;
    }
    
    public JToolBar CreateSearchBar(Action search){
        JToolBar toolbar = new JToolBar("ToolBar");

        toolbar.addSeparator();
        toolbar.setPreferredSize(new Dimension(70,40));
        toolbar.setRollover(true);
        toolbar.setFloatable(false);
        
        JButton SEARCH = new JButton("Search");
                SEARCH.setFont(new Font("Serif",Font.BOLD, 20));
                SEARCH.setPreferredSize(new Dimension(20,20));
                toolbar.add(search);
                
        return toolbar;
    }
    
    public GridBagConstraints SetListPaneConstraints(){
        constraints = new GridBagConstraints();
        constraints.weighty = 1;
        constraints.gridheight = 9;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        
        return constraints;
    }
    
    public GridBagConstraints SetToolBarConstraints(){
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        
        return constraints;
    }
    
    public JLabel SetLabel(String label){
        JLabel jlabel = new JLabel(label);
        jlabel.setFont(new Font("Serif",Font.BOLD, 20));
        
        return jlabel;
    }
    
    public JLabel SetHomeLabel(JLabel label){
        label.setFont(new Font("Serif",Font.BOLD, 20));
        
        return label;
    }
    
    public JLabel SetHomeTitleLabel(JLabel label){
        label.setFont(new Font("Serif",Font.BOLD, 25));
        
        return label;
    }
    
    public GridBagConstraints SetTitleConstraints(int y){
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = y;
        
        return constraints;
    }
    
    public GridBagConstraints SetLabelConstraints(int y){
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = y;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(2,2,2,2);
        
        return constraints;
    }
    
    public GridBagConstraints SetTextFieldConstraints(int y){
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = y;
        constraints.weightx = 1;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        
        return constraints;
    }
    
    public GridBagConstraints SetHomeCategoryConstraints(int y){
        constraints = new GridBagConstraints();
        constraints.gridy = y;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        
        return constraints;
    }
    
    public GridBagConstraints SetHomeConstraints(int x){    
        constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 3;
        constraints.gridx = x;
        constraints.gridy = 3;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;
        
        return constraints;
    }
}
