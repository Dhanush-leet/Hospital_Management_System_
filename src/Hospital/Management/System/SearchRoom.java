package Hospital.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class SearchRoom extends JFrame {

    private Choice choice;
    private JTable table;

    public SearchRoom() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JLabel titleLabel = new JLabel("Search for Room");
        titleLabel.setBounds(250, 11, 186, 31);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(titleLabel);

        JLabel statusLabel = new JLabel("Status");
        statusLabel.setBounds(50, 73, 120, 20);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(statusLabel);

        choice = new Choice();
        choice.setBounds(170, 70, 120, 20);
        choice.add("Available");
        choice.add("Occupied");
        panel.add(choice);

        table = new JTable();
        table.setBounds(0, 187, 700, 210);
        table.setBackground(new Color(90, 156, 163));
        table.setForeground(Color.white);
        panel.add(table);

        try {
            conn c = new conn();
            String query = "SELECT * FROM room";
            ResultSet resultSet = c.statement.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel roomNoLabel = new JLabel("Room Number");
        roomNoLabel.setBounds(23, 162, 150, 20);
        roomNoLabel.setForeground(Color.WHITE);
        roomNoLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(roomNoLabel);

        JLabel availabilityLabel = new JLabel("Availability");
        availabilityLabel.setBounds(175, 162, 150, 20);
        availabilityLabel.setForeground(Color.WHITE);
        availabilityLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(availabilityLabel);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setBounds(458, 162, 150, 20);
        priceLabel.setForeground(Color.WHITE);
        priceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(priceLabel);

        JLabel bedTypeLabel = new JLabel("Bed Type");
        bedTypeLabel.setBounds(580, 162, 150, 20);
        bedTypeLabel.setForeground(Color.WHITE);
        bedTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(bedTypeLabel);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(200, 420, 120, 25);
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);
        panel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStatus = choice.getSelectedItem().trim().toLowerCase();
                String query = "SELECT * FROM room WHERE LOWER(TRIM(Availability)) = '" + selectedStatus + "'";

                try {
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery(query);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(380, 420, 120, 25);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(700, 500);
        setLayout(null);
        setLocation(450, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}

