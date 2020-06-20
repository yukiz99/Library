package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Scrollable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.C3p0Utils;
import dao.Dao;

class SearchPanel extends JPanel{
	Dao dao=new Dao();
	private Color lightBlue=new Color(206,239,242); 
	
	private JTextField tfId;
	private JTextField tfTitle;
	private JButton btn1,btn2;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane spTable;

 
   public SearchPanel(){
		this.setLayout(null);
		this.setBackground(lightBlue);
	   
		 tfId=new JTextField("编号查找");
		 tfTitle=new JTextField("标题查找");
		 tfId.setForeground(Color.GRAY);
		 tfTitle.setForeground(Color.GRAY);
		 Font f=new Font("",1, 20);
		 tfId.setFont(f);
		 tfTitle.setFont(f);
		 btn1=new JButton(new ImageIcon("images/searchImage.png"));
		 btn2=new JButton(new ImageIcon("images/searchImage.png"));
	 
     //滚动条
	  model = new DefaultTableModel(){
		   public boolean isCellEditable(int row ,int column){
			   return false;
		   }
		};
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);//表格列位置不可拖动 
		spTable = new JScrollPane(table);
		


		 this.add(tfId);
		 this.add(tfTitle);
		 this.add(btn1);
		 this.add(btn2);
	 
		 tfId.setBounds(300,150, 300, 40);
		 tfTitle.setBounds(800,150,300,40);
		 btn1.setBounds(600,150,40,40);
		 btn2.setBounds(1100,150,40,40);
 
 
    /*
     * btn1/btn2查找
     */
	    btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
		    if(!dao.idExist(tfId.getText())){
		    	JOptionPane.showMessageDialog(null, "该编号不存在");
		    	return;
		    }
			ResultSet rs=dao.findResultSetById(tfId.getText());
			
			
			showDataById(rs);
			
			table.setRowHeight(40);
			table.setFont(new Font("",0,20));
			JTableHeader header=table.getTableHeader();
			header.setPreferredSize(new Dimension(header.getWidth(),40));
			header.setBackground(Color.lightGray);
			header.setFont(new Font("",1,30));
			
			spTable.setBounds(0,250,1432,610);
			SearchPanel.this.add(spTable);
			SearchPanel.this.updateUI();
			SearchPanel.this.repaint();
			
			}
		});
         btn2.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			int null_count=0;
			List<ResultSet> list=dao.findResultSetByTitle(tfTitle.getText());
		
		    	showDataByTitle(list);
		        
		    	table.setRowHeight(40);
		    	table.setFont(new Font("",0,20));
		    	JTableHeader header=table.getTableHeader();
		    	header.setPreferredSize(new Dimension(header.getWidth(),40));
		    	header.setBackground(Color.lightGray);
		    	header.setFont(new Font("",1,30));
		    	
		    	spTable.setBounds(0,250,1432,610);
		    	SearchPanel.this.add(spTable);
		    	SearchPanel.this.updateUI();
		    	SearchPanel.this.repaint();
		    }
		});

       /*
        * tfId/tfTitle里的编号查找或标题查找
        */
	   tfId.addFocusListener(new FocusListener() {
		    public void focusLost(FocusEvent e) {
				
		    	String text=tfId.getText();
				if(text==null||text.trim().isEmpty()){
				Font f=new Font("",1, 20);
				tfId.setText("编号查找");
				tfId.setFont(f);
				tfId.setForeground(Color.gray);
				  }
			 }
	        public void focusGained(FocusEvent e) {
				if(tfId.getText().equals("编号查找")&&tfId.getForeground().equals(Color.gray)){
				 tfId.setText("");
				 tfId.setForeground(Color.black);
				}
			}
			});
      tfTitle.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				String text=tfTitle.getText();
				if(text==null||text.trim().isEmpty()){
				Font f=new Font("",1, 20);
				tfTitle.setText("标题查找");
				tfTitle.setFont(f);
				tfTitle.setForeground(Color.gray);
			}
		   }
		    public void focusGained(FocusEvent e) {
				if(tfTitle.getText().equals("标题查找")&&tfTitle.getForeground().equals(Color.gray)){
				tfTitle.setText("");
				tfTitle.setForeground(Color.black);
			}
			}
	});
	 
 }
 
public void showDataByTitle(List<ResultSet> list){
	 ResultSetMetaData rsmd;
     Vector<Vector<String>> data = new Vector<Vector<String>>();
     Vector<String> title = null;
     int null_count=0;
	 for(int k=0;k<list.size();k++){
	    ResultSet rs=list.get(k);
		try {   

	    	    rsmd = rs.getMetaData();
			    if(rs==null||!rs.next()){//一定要这样写
			    	null_count++;
			    }else{
				    int colCount = rsmd.getColumnCount();

				    title=new Vector<>();
					for (int i = 1; i <= colCount; i++) {
						title.add(rsmd.getColumnLabel(i));
					}
					
					//刚开始判断的时候已经rs.next()过一次；所以rowCount++，操作一遍while（rs.next()）里的
				    int rowCount = 0;
				    rowCount++;
                    Vector<String> rowdata = new Vector<String>();
					for (int i = 1; i <= colCount; i++) {
					  rowdata.add(rs.getString(i));
					}
					data.add(rowdata);
				    while (rs.next()) {
					    rowCount++;
                        rowdata = new Vector<String>();
						for (int i = 1; i <= colCount; i++) {
						  rowdata.add(rs.getString(i));
						}
					    data.add(rowdata);
                    }
				 }
			} catch (SQLException e) {
				e.printStackTrace();
                C3p0Utils.closeConnection();
			}
			finally{
				if(rs!=null){
					try {
						rs.close();
					} catch (SQLException e1) {}
				}
        	}
		 
        }
	 C3p0Utils.closeConnection();
	 if(null_count==3){
		 JOptionPane.showMessageDialog(null, "该标题不存在");
	 }else{
        model.setDataVector(data, title);
	 }
 }


	 public void showDataById(ResultSet rs){

		 
		 ResultSetMetaData rsmd;
		 try {
			 rsmd = rs.getMetaData();
			 int colCount = rsmd.getColumnCount();
			 
			 Vector<String> title = new Vector<String>();
             Vector<Vector<String>> data = new Vector<Vector<String>>();
			 
			 for (int i = 1; i <= colCount; i++) {
			 	title.add(rsmd.getColumnLabel(i));
			 }
			 
			 int rowCount = 0;
			 while (rs.next()) {
			 rowCount++;
			 Vector<String> rowdata = new Vector<String>();
			 for (int i = 1; i <= colCount; i++) {
			   rowdata.add(rs.getString(i));
			 }
			 data.add(rowdata);
		 }
		 model.setDataVector(data, title);
		 } catch (SQLException e) {
		 	e.printStackTrace();
		 }finally{
			 if(rs!=null){
				 try {
				 	rs.close();
				 } catch (SQLException e1) {}
			 }
		 C3p0Utils.closeConnection();
		 }
	 }
	 
	 public JTable getTable(){
		 return table;
	 }
}
