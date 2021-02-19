package com.Library.GUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.*;

import com.mysql.cj.Query;

@SuppressWarnings("serial")
class Item extends JPanel {
    private JLabel label;
    private JTextField textfield;

    public Item(String labelText, int textWidth) {
        label = new JLabel(labelText);
        textfield = new JTextField(textWidth);
        this.add(label);
        this.add(textfield);
    }
    public String getText() {
        return textfield.getText();
    }
}

@SuppressWarnings("serial")
class Item2 extends JPanel {
	
    private JLabel label, label2, label3;
    private JTextField textfield, textfield2, textfield3;

    public Item2(String labelText, String labelText2, String labelText3, int textWidth) {

        label = new JLabel(labelText);
        textfield = new JTextField(textWidth);
        label2 = new JLabel(labelText2);
        textfield2 = new JTextField(textWidth);
        label3 = new JLabel(labelText3);
        textfield3 = new JTextField(textWidth);
        this.add(label);
        this.add(textfield);
        this.add(label2);
        this.add(textfield2);
        this.add(label3);
        this.add(textfield3);
    }
    public String getText() {
        return textfield.getText();
    }
    public String getText2() {
        return textfield2.getText();
    }
    public String getText3() {
        return textfield3.getText();
    }
}

@SuppressWarnings("serial")
class Item3 extends JPanel {
	
    private JCheckBox checkbox, checkbox2, checkbox3;
    private JLabel label, label2, label3;
    private JTextField textfield, textfield2, textfield3;

    public Item3(String labelText, String labelText2, String labelText3, int textWidth) {

        checkbox = new JCheckBox();
        label = new JLabel(labelText);
        textfield = new JTextField(textWidth);
        checkbox2 = new JCheckBox();
        label2 = new JLabel(labelText2);
        textfield2 = new JTextField(textWidth);
        checkbox3 = new JCheckBox();
        label3 = new JLabel(labelText3);
        textfield3 = new JTextField(textWidth);
        this.add(checkbox);
        this.add(label);
        this.add(textfield);
        this.add(checkbox2);
        this.add(label2);
        this.add(textfield2);
        this.add(checkbox3);
        this.add(label3);
        this.add(textfield3);
    }
    public String getText() {
        return textfield.getText();
    }
    public boolean select() {
        return checkbox.isSelected();
    }
    public String getText2() {
        return textfield2.getText();
    }
    public boolean select2() {
        return checkbox2.isSelected();
    }
    public String getText3() {
        return textfield3.getText();
    }
    public boolean select3() {
        return checkbox3.isSelected();
    }
}


@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	private final int columnsBook = 4;
    private final List<String> ResultsBook = Arrays.asList("ISBN", "Title", "Author", "Availability");
	private final int columnsCheckIn = 6;
    private final List<String> ResultsCheckIn = Arrays.asList("Loan ID", "ISBN", "Card ID", "Date Out", "Due Date", "Date In");
	private final int columnsFine = 2;
    private final List<String> ResultsFine = Arrays.asList("Card ID", "Fine Amount");
    
    private Vector<Vector<String>> dataBook = new Vector<Vector<String>>();
    private Vector<Vector<String>> dataCheckIn = new Vector<Vector<String>>();
    private Vector<Vector<String>> dataFine = new Vector<Vector<String>>();

    private Item bookSearch = new Item("Book Search",20);
    private Item3 bookLoans = new Item3("ISBN", "Card_ID", "Borrower Name", 12);
    private Item2 borrower = new Item2("Name", "SSN", "Address", 17);

    
    private JButton book_Search = new JButton("Book Search");
    private JButton check_Out = new JButton("Check Out");
    private JButton check_In_Search = new JButton("Check In Search");
    private JButton check_In = new JButton("Check In");
    private JButton fine_Update = new JButton("Fine Update");
    private JButton borrower_Add = new JButton("Add Borrower");
    private JButton pay_Fine = new JButton("Pay Fine");
    private JButton fine_Filter = new JButton("Fine Filter"); 

    private JTextArea Textarea = new JTextArea(5, 5);
    
    private JTable book;
    private JTable checkIn;
    private JTable fine;
	
    private Connection conn = null;
    
    public GUI(String title) {
        super(title);

        Vector<String> titlesBook = new Vector<String>(ResultsBook);
        Vector<String> titlesCheckIn = new Vector<String>(ResultsCheckIn);
        Vector<String> titlesFine = new Vector<String>(ResultsFine);
        book = new JTable(dataBook, titlesBook);
    	book.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    
        checkIn = new JTable(dataCheckIn, titlesCheckIn);
    	checkIn.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        fine = new JTable(dataFine, titlesFine);
    	fine.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(bookSearch);
        controlPanel.add(book_Search);
        controlPanel.add(check_Out);
        controlPanel.add(bookLoans);
        controlPanel.add(check_In_Search);
        controlPanel.add(check_In);
        controlPanel.add(borrower);
        controlPanel.add(borrower_Add);
        controlPanel.add(fine_Update);
        controlPanel.add(pay_Fine);
        controlPanel.add(fine_Filter); 
        controlPanel.setPreferredSize(new Dimension(0, 200));

        JPanel bookSearch = new JPanel();
        bookSearch.setLayout(new BoxLayout(bookSearch, BoxLayout.Y_AXIS));
        bookSearch.add(Box.createRigidArea(new Dimension(0, 20)));
        bookSearch.add(book.getTableHeader());
        bookSearch.add(new JScrollPane(book));
        
        JPanel checkInSearch = new JPanel();
        checkInSearch.setLayout(new BoxLayout(checkInSearch, BoxLayout.Y_AXIS));
        checkInSearch.add(Box.createRigidArea(new Dimension(0, 20)));
        checkInSearch.add(checkIn.getTableHeader());
        checkInSearch.add(new JScrollPane(checkIn));
        
        JPanel fineResult = new JPanel();
        fineResult.setLayout(new BoxLayout(fineResult, BoxLayout.Y_AXIS));
        fineResult.add(Box.createRigidArea(new Dimension(0, 20)));
        fineResult.add(fine.getTableHeader());
        fineResult.add(new JScrollPane(fine));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        tablePanel.add(Textarea);
        tablePanel.add(bookSearch);
        tablePanel.add(checkInSearch);
        tablePanel.add(fineResult);

        this.add(controlPanel, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.WEST);
        this.add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.EAST);
        this.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.SOUTH);

        setActionListener();
    }

    public void connectToMysql() throws SQLException, ClassNotFoundException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	final String URL = "jdbc:mysql://localhost:3306/Library";
    	conn = DriverManager.getConnection(URL, "root", "");	
    }
    
    public void setActionListener() {
    	
    	book_Search.addActionListener( 
	    		new ActionListener() {
	    			
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				
	    				String search = bookSearch.getText();
	    	            
	    				String cmd = "select book.Isbn, book.Title, authors.Name, if(a.date_in is null, 'No', 'Yes') from book "
	    	            		+ "inner join book_authors on book.Isbn = book_authors.Isbn "
	    	            		+ "inner join authors on book_authors.Author_id = authors.Author_id "
	    	            		+ "inner join book_loans a on a.isbn = book.isbn "
	    						+ "where a.loan_id = (select b.loan_id from book_loans b where a.isbn = b.isbn order by b.loan_id desc limit 1) "
	    						+ "and (lower(book.title) like '%" + search + "%' "
	    	            		+ "or lower(authors.name) like '%" + search + "%' "
	    	            		+ "or lower(book.Isbn) like '%" + search + "%');"; 
	
	    	            dataBook.clear();
	    	            
	    	            Statement stmt;
	    	            try {
	    	            	stmt = conn.createStatement();
	    	                ResultSet rs = stmt.executeQuery(cmd);
	    	                Vector<String> record;
	    	                
	    	                while(rs.next()) {
	    	                	record = new Vector<String>();
	    	                
	    	                	for(int i = 1; i <= columnsBook; i++) {
	    	                        record.add(rs.getString(i));
	    	                    }
	    	                	
	    	                    dataBook.add(record);
	    	                }
	    	            }
	    	            catch(SQLException e1) {
		                    e1.printStackTrace();
	    	            }
	    	            
	    	            book.validate();
	    	            book.updateUI();
	    			}
	    		}
    		);
    	
    	check_Out.addActionListener(
	            new ActionListener() {
	        				
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	
        				int row = book.getSelectedRow();
        				String isbn = book.getValueAt(row, 0).toString();
	                	String card_id = bookLoans.getText2();
	                	String insert = "insert into book_loans(isbn, card_id, date_out, due_date, date_in) values "
	                			+ "('" + isbn + "', '" + card_id + "', current_date(), date_add(current_date(), interval 14 day), null);";
	                	String number = "select count(*) from book_loans where card_id = '" + card_id + "' and date_in is null group by card_id;";
	    				String avalability = "select if(a.date_in is null, 'No', 'Yes') from book_loans a "
	    						+ "where a.loan_id = (select b.loan_id from book_loans b where a.isbn = b.isbn order by b.loan_id desc limit 1) "
	    						+ "and a.isbn = '" + isbn + "';";
	                	
	    				int count = 0;
	    				String aval = null;
	                	
	                    Statement stmt;
	                    try {
	                    	stmt = conn.createStatement();
	                    	ResultSet rs1 = stmt.executeQuery(number);
	                    	while(rs1.next()) {
	                    		count = rs1.getInt(1);
	                    	}
	                    	
	                    	ResultSet rs2 = stmt.executeQuery(avalability);
	                    	while(rs2.next()) {
	                    		aval = rs2.getString(1);
	                    	}
	                    }
	                    catch (SQLException e1) {
	                        e1.printStackTrace();
	                    }
	                	
	                	if(aval.equals("No")) {
	                		Textarea.setText("ERROR: The Book has been borrowed.");
	                	}
	                	else if(count >= 3) {
	                		Textarea.setText("ERROR: A Maximum of Three Borrowed Books.");
	                	}
	                	else {
	                		try {
	                        	stmt = conn.createStatement();
	                        	stmt.execute(insert);
	                		}
	                		catch (SQLException e1) {
	                            e1.printStackTrace();
	                        }
	                	}
	                }
	          
	            });
    	
    	check_In_Search.addActionListener(
	    		new ActionListener() {
	    			
	    			@Override
	                public void actionPerformed(ActionEvent e) {
	    				
	    				String isbn = null, card_id = null, name = null;
	    				if(bookLoans.select()) {
	    					isbn = bookLoans.getText();
	    				}
	    				if(bookLoans.select2()) {
	    					card_id = bookLoans.getText2();
	    				}
	    				if(bookLoans.select3()) {
	    					name = bookLoans.getText3();
	    				}
	                	String cmd = "select a.loan_id, a.isbn, a.card_id, a.date_out, a.due_date, a.date_in "
	                				+ "from book_loans a inner join borrower b on a.card_id = b.card_id "
	                				+ "where a.isbn = '" + isbn + "' or a.card_id = '" + card_id + "' or b.bname like '%" + name + "%';";
	                	
	                	dataCheckIn.clear();
	                	
	    	            Statement stmt;
	    	            try {
	    	            	stmt = conn.createStatement();
	    	                ResultSet rs = stmt.executeQuery(cmd);
	    	                Vector<String> record;
	    	                
	    	                while(rs.next()) {
	    	                	record = new Vector<String>();
	    	                
	    	                	for(int i = 1; i <= columnsCheckIn; i++) {
	    	                        record.add(rs.getString(i));
	    	                    }
	    	                	
	    	                    dataCheckIn.add(record);
	    	                }
	    	            } 
	    	            catch(SQLException e1) {
		                    e1.printStackTrace();
	    	            }
	    	            
	    	            checkIn.validate();
	    	            checkIn.updateUI();
	                }
	    		}
	    		
	    		
    			);
    	
    	check_In.addActionListener(
        		new ActionListener() {
        			
        			@Override
                    public void actionPerformed(ActionEvent e) {
        				
        				int row = checkIn.getSelectedRow();
        				String loan_id = checkIn.getValueAt(row, 0).toString();
        				String cmd = "update book_loans set date_in = current_date() where loan_id = '" + loan_id + "';";
        				String calculate = "select datediff(date_in, due_date) from book_loans where loan_id = '" + loan_id + "';";
        				String update = "update fines a inner join book_loans b on a.loan_id = b.loan_id "
        								+ "set fine_amt = datediff(date_in, due_date) * 0.25 where a.loan_id = '" + loan_id + "';";
        				String display = "select a.loan_id, a.isbn, a.card_id, a.date_out, a.due_date, a.date_in "
                						+ "from book_loans a inner join borrower b on a.card_id = b.card_id "
                						+ "where a.loan_id = '" + loan_id + "';";

        				int due = 0;
        				Statement stmt;
        	            try {
        	            	stmt = conn.createStatement();
        	                stmt.execute(cmd);
        	                ResultSet rs = stmt.executeQuery(calculate);
        	                
        	                while(rs.next()) {
        	                	due = rs.getInt(1);
        	                }
        	            }      	            
        	            catch(SQLException e1) {
    	                    e1.printStackTrace();
        	            }
        	            
	                	dataCheckIn.clear();
	    	            try {
	    	            	stmt = conn.createStatement();
	    	                ResultSet rs = stmt.executeQuery(display);
	    	                Vector<String> record;
	    	                
	    	                while(rs.next()) {
	    	                	record = new Vector<String>();
	    	                
	    	                	for(int i = 1; i <= columnsCheckIn; i++) {
	    	                        record.add(rs.getString(i));
	    	                    }
	    	                	
	    	                    dataCheckIn.add(record);
	    	                }
	    	            }
	    	            catch(SQLException e1) {
		                    e1.printStackTrace();
	    	            }
	    	            checkIn.validate();
	    	            checkIn.updateUI();
	    	            
        	            if(due > 0) {
        	            	try {
            	            	stmt = conn.createStatement();
            	                stmt.execute(update);
        	            	}
            	            catch(SQLException e1) {
        	                    e1.printStackTrace();
            	            }
        	            }
                    }
        			
        		});
    	
    	borrower_Add.addActionListener(
    			new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String name = borrower.getText();
						String ssn = borrower.getText2();
						String address = borrower.getText3();
						String cmd = "insert into borrower(ssn, bname, address) VALUES('" + ssn + "', '" + name +"', '" + address + "')";
						String check = "select ssn from borrower where ssn = '" + ssn + "'";
						
						String ssn2 = null;
	                    Statement stmt;
	                    try {
	                    	stmt = conn.createStatement();
	                    	ResultSet rs = stmt.executeQuery(check);
	                    	
	                    	while(rs.next()) {
	                    		ssn2 = rs.getString(1);
	                    	}
	                    }
	                    catch (SQLException e1) {
	                        e1.printStackTrace();
	                    }
	                    
	                    
                		if(ssn.equals(ssn2)) {
                			Textarea.setText("ERROR: The SSN has been used.");
                		}
                		else {
                    		try {
                            	stmt = conn.createStatement();
                            	stmt.execute(cmd);
                    		}
                    		catch(SQLException e2) {
                                e2.printStackTrace();
                            }
                		}
            	
	                }
				});
    	
    	fine_Update.addActionListener(
    			new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String select = "select loan_id from book_loans where loan_id not in (select loan_id from fines) and date_in is null and current_date() > due_date";
						String update = "update fines a inner join book_loans b on a.loan_id = b.loan_id set fine_amt = datediff(current_date(), due_date) * 0.25 where date_in is null;";
						
	                    Statement stmt;
	                    try {
	                    	stmt = conn.createStatement();
	                    	ResultSet rs = stmt.executeQuery(select);

	                    	while(rs.next()) {
	                    		int loan_id = rs.getInt(1);
	                    		stmt = conn.createStatement();
	                    		stmt.execute("insert into fines(loan_id, paid) values (" + loan_id + ", '0')");
	                    		
	                    	}
	                    	
	                    	stmt.execute(update);
	                    }
	                    catch (SQLException e1) {
	                        e1.printStackTrace();
	                    }
	                    

					}
				});
    	pay_Fine.addActionListener(
    			new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
        				
						int row = checkIn.getSelectedRow();
        				String loan_id= checkIn.getValueAt(row, 0).toString();
						String update = "update fines a inner join book_loans b on a.loan_id = b.loan_id set paid = 1 where a.loan_id = '" + loan_id + "';";
						String display = "select card_id, sum(fine_amt) from book_loans a inner join fines b on a.loan_id = b.loan_id group by card_id;";
						
						dataFine.clear();
						
	                    Statement stmt;
	                    try {
	                    	stmt = conn.createStatement();
	                    	stmt.execute(update);	
	                    }
	                    catch(SQLException e1) {
	                        e1.printStackTrace();
	                    }
	                    
	    	            try {
	    	            	stmt = conn.createStatement();
	    	                ResultSet rs = stmt.executeQuery(display);
	    	                Vector<String> record;
	    	                
	    	                while(rs.next()) {
	    	                	record = new Vector<String>();
	    	                
	    	                	for(int i = 1; i <= columnsFine; i++) {
	    	                        record.add(rs.getString(i));
	    	                    }
	    	                	
	    	                    dataFine.add(record);
	    	                }
	    	            }
	    	            catch(SQLException e1) {
		                    e1.printStackTrace();
	    	            }
	    	            
	    	            fine.validate();
	    	            fine.updateUI();
					}
				});
    	
    	fine_Filter.addActionListener(
    			new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
					
						String filter = "select card_id, sum(fine_amt) from book_loans a inner join fines b on a.loan_id = b.loan_id "
										+ "where paid = 0 group by card_id;";
						
						dataFine.clear();
						
	                    Statement stmt;
						try {
	    	            	stmt = conn.createStatement();
	    	                ResultSet rs = stmt.executeQuery(filter);
	    	                Vector<String> record;
	    	                
	    	                while(rs.next()) {
	    	                	record = new Vector<String>();
	    	                
	    	                	for(int i = 1; i <= columnsFine; i++) {
	    	                        record.add(rs.getString(i));
	    	                    }
	    	                	
	    	                    dataFine.add(record);
	    	                }
	    	            }
	    	            catch(SQLException e1) {
		                    e1.printStackTrace();
	    	            }
						
	    	            fine.validate();
	    	            fine.updateUI();
					}
    				
    			});
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    	
        GUI frame = new GUI("Library Query");
        
        frame.connectToMysql();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setVisible(true);
        frame.setResizable(false);

    }
}


