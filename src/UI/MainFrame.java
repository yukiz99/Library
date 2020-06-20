package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.C3p0Utils;
import dao.Dao;




public class MainFrame extends JFrame {
	
	String sort="Book";
	int FindOrSearch=0;
	private Dao dao=new Dao();
	private Color deepBlue=new Color(0, 155, 210);
	private Color lightBlue=new Color(206,239,242); 
	private JScrollPane spTable;
	private JPanel pAll,pLeft,pRight,pBtns,pAdd, pUpdate,pSearch;
	private JButton btnFind,btnAdd,btnDelete,btnUpdate,btnSearch,btnCount;
	private DefaultTableModel model;
	private JTable table;
	private JLabel LibraryImg;

	//显示
	private JPanel findPanel,sortPanel;
	private JButton sort1,sort2,sort3;
	
	public MainFrame() {
		super("图书馆管理系统 ");
		pAdd=new AddPanel();
		pUpdate=new UpdatePanel(null);
		pAll=new JPanel();
		pLeft=new JPanel();
		pRight=new JPanel();
		pSearch=new SearchPanel();
		model = new DefaultTableModel(){
		   public boolean isCellEditable(int row ,int column){
			   return false;
		   }
		};

		ImageIcon image=new ImageIcon("images/library1.png");
		LibraryImg=new JLabel(image);
		LibraryImg.setBounds(0,0,350,200);
		pLeft.add(LibraryImg);
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spTable = new JScrollPane(table);

		pBtns = new JPanel();
		pBtns.setLayout(new GridLayout(6,1));
		btnFind=new JButton(new ImageIcon("images/btn10.png"));
		btnAdd=new JButton(new ImageIcon("images/btn20.png"));
		btnSearch=new JButton(new ImageIcon("images/btn30.png"));
		btnUpdate=new JButton(new ImageIcon("images/btn40.png"));
		btnDelete=new JButton(new ImageIcon("images/btn50.png"));
		btnCount=new JButton(new ImageIcon("images/btn60.png"));
		btnFind.setContentAreaFilled(false);
		btnAdd.setContentAreaFilled(false);
		btnSearch.setContentAreaFilled(false);
		btnUpdate.setContentAreaFilled(false);
		btnDelete.setContentAreaFilled(false);
		btnCount.setContentAreaFilled(false);
		btnFind.setBorder(null);
		btnAdd.setBorder(null);
		btnSearch.setBorder(null);
		btnUpdate.setBorder(null);
		btnDelete.setBorder(null);
		btnCount.setBorder(null);


		findPanel=new JPanel();
		sortPanel=new JPanel(new GridLayout(1,3));
		sort1=new JButton("图书");
		sort2=new JButton("视频");
		sort3=new JButton("图画 ");
		sortPanel.setBackground(lightBlue);
		findPanel.setBackground(lightBlue);
		findPanel.setLayout(null);
		Font f=new Font("",1,20);
		sort1.setFont(f);
		sort2.setFont(f);
		sort3.setFont(f);
		sort1.setBorder(null);
		sort2.setBorder(null);
		sort3.setBorder(null);
		sort1.setBackground(deepBlue);
		sort2.setBackground(lightBlue);
		sort3.setBackground(lightBlue);


		pBtns.add(btnFind);
		pBtns.add(btnAdd);
		pBtns.add(btnSearch);
		pBtns.add(btnUpdate);
		pBtns.add(btnDelete);
		pBtns.add(btnCount);
		pLeft.add(pBtns);
		sortPanel.add(sort1);
		sortPanel.add(sort2);
		sortPanel.add(sort3);
		findPanel.add(sortPanel);
		findPanel.add(spTable);
		pRight.add(findPanel);
		pAll.add(pLeft);
		pAll.add(pRight);
    
		pLeft.setBackground(deepBlue);
		pRight.setBackground(lightBlue);
		pAll.setLayout(null); 
		pLeft.setLayout(null);
		pRight.setLayout(null);
		addFindPanel();
		this.add(pAll);

    
		pBtns.setBounds(0,200,350,500);
		sortPanel.setBounds(0,0,400,50);
		spTable.setBounds(0,50,1450,820);
		findPanel.setBounds(0,0,1450,900);
		    
		pLeft.setBounds(0,0,350,900);
		pRight.setBounds(350,0,1450,900);
		this.setBounds(80,20,1800, 900);
		   
		this.showData(dao.getLibraryResultSet(sort));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
  

  
       /*
        * 左边功能键（图片）
        */
		btnFind.addMouseListener(new MouseAdapter()  {
		public void mouseReleased(MouseEvent e) {
		btnFind.setIcon(new ImageIcon("images/btn10.png"));
		showData(dao.getLibraryResultSet(sort));
		}
		public void mousePressed(MouseEvent e) {
		btnFind.setIcon(new ImageIcon("images/btn11.png"));
			
		}
		});
        btnAdd.addMouseListener(new MouseAdapter()  {
		public void mouseReleased(MouseEvent e) {
		btnAdd.setIcon(new ImageIcon("images/btn20.png"));
		addData();
		}
		public void mousePressed(MouseEvent e) {
		btnAdd.setIcon(new ImageIcon("images/btn21.png"));
			
		}
		});
		btnSearch.addMouseListener(new MouseAdapter()  {
		public void mouseReleased(MouseEvent e) {
		btnSearch.setIcon(new ImageIcon("images/btn30.png"));
		searchData();
		}
		public void mousePressed(MouseEvent e) {
		btnSearch.setIcon(new ImageIcon("images/btn31.png"));
		}
		});
		btnUpdate.addMouseListener(new MouseAdapter()  {
		public void mouseReleased(MouseEvent e) {
		btnUpdate.setIcon(new ImageIcon("images/btn40.png"));
		updateDate();
		}
		public void mousePressed(MouseEvent e) {
		btnUpdate.setIcon(new ImageIcon("images/btn41.png"));
		}
		});
		btnDelete.addMouseListener(new MouseAdapter()  {
		public void mouseReleased(MouseEvent e) {
		
			btnDelete.setIcon(new ImageIcon("images/btn50.png"));
		    deleteData();
		}
		public void mousePressed(MouseEvent e) {
		btnDelete.setIcon(new ImageIcon("images/btn51.png"));
		}
		});
		
		btnCount.addMouseListener(new MouseAdapter()  {
		public void mouseReleased(MouseEvent e) {
		btnCount.setIcon(new ImageIcon("images/btn60.png"));
		showCount();
		}
		public void mousePressed(MouseEvent e) {
		btnCount.setIcon(new ImageIcon("images/btn61.png"));
		}
		});   
		
		/*
		 * 查询面板中3个类别
		 */
		sort1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		sort="Book";
		sort1.setBackground(deepBlue);
		sort2.setBackground(lightBlue);
		sort3.setBackground(lightBlue);
		showData(dao.getLibraryResultSet(sort));
				
			}
		});
		sort2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		sort="DVD";
		showData(dao.getLibraryResultSet(sort));
		sort1.setBackground(lightBlue);
		sort2.setBackground(deepBlue);
		sort3.setBackground(lightBlue);
		}
		});
		
		sort3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		sort="Painting";
		showData(dao.getLibraryResultSet(sort));
		sort1.setBackground(lightBlue);
		sort2.setBackground(lightBlue);
		sort3.setBackground(deepBlue);
			}
		});
	
}

    /**添加4个面板（查询，搜索，添加，编辑）
     * 添加查询面板和搜索面板时要写FindOrSearch，判断是哪个spTable
     * 为0的为查询面板上的spTable,为2的为在添加面板上
     * 在编辑面板是可以直接删除的
     */
	public void addFindPanel(){
		FindOrSearch=0;
		
		pRight.removeAll();
		pRight.add(findPanel);
		spTable.setBounds(0,50,1450,820);
		findPanel.add(spTable);
		pRight.updateUI();
		pRight.repaint();
			
		table.setRowHeight(40);
		table.setFont(new Font("",0,20));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(header.getWidth(),40));
		header.setBackground(Color.lightGray);
		header.setFont(new Font("",1,30));
		
		table.getTableHeader().setReorderingAllowed(false);//表格列位置不可拖动 
	}
	public void addSearchPanel(){
		FindOrSearch=1;
		
		pRight.removeAll();
		pSearch=new SearchPanel();
		pRight.add(pSearch);
		pSearch.setBounds(0,0,1450,900);
		pRight.updateUI();
		pRight.repaint();
	}
	public void addAddPanel(){
		FindOrSearch=2;
		pRight.removeAll();
		pAdd=new AddPanel();
		pAdd.setBounds(200,50,500,700);
		pRight.add(pAdd);
		pRight.updateUI();
		pRight.repaint();
	}
    public void addUpdatePanel(String id){
    	FindOrSearch=2;
		pRight.removeAll();
		pUpdate=new UpdatePanel(id);
		pUpdate.setBounds(200,100,500,600);
		pRight.add(pUpdate);
		pRight.updateUI();
		pRight.repaint();
	}
	
 


	/**
	 * 显示数据
	 */

	public void showData(ResultSet rs){
		    addFindPanel();
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
				
				if (rowCount == 0) {
					model.setDataVector(null, title);
				} else {
				    model.setDataVector(data, title);
				}
		 }catch (SQLException e) {
					e.printStackTrace();
		 }finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e1) {
				}
			}
	    C3p0Utils.closeConnection();
	  }
	}

	/**
	 * 添加
	 */
	public void addData(){
	     addAddPanel();
	}

	/**
	 * 搜索数据 
	 */
	public void searchData(){
		addSearchPanel();
		 
	}
	
	/**
	 * 更改数据
	 */
	public void updateDate(){
		JTable updatetable;
		if(FindOrSearch==0){
			updatetable=table;
		}else{
			updatetable=((SearchPanel) pSearch).getTable();
		}
		int index[] = updatetable.getSelectedRows();
		if (index.length == 0) {
			JOptionPane.showMessageDialog(this, "请选择要编辑的记录");
		} else {
			int k = JOptionPane.showConfirmDialog(this,"您确定要编辑所选记录吗 ？", "编辑 ",
			JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if (k == JOptionPane.YES_OPTION) {
			String id=(String) updatetable.getValueAt(index[0], 0);
			addUpdatePanel(id);
		 }
	  }
	 }
	

	/**
	 * 删除数据
	 *  如果num为0，则为查询时得到的纪录
	 *  如果num为1，则为搜索得到的纪录
	 */
	public void deleteData() {
		   if(FindOrSearch==2){
			  return;//如果为2，说明现在在添加面板上
		   }
		    //得到相应的table
			JTable deltable;
			if(FindOrSearch==0){
				 deltable=table;
			}else{
				deltable=((SearchPanel) pSearch).getTable();
			}
			
			int index[] = deltable.getSelectedRows();
			String selectSort=null;
			if (index.length == 0) {
			   JOptionPane.showMessageDialog(this, "请选择要删除的记录");
			} else {
				int k = JOptionPane.showConfirmDialog(this,"您确定要删除所选的数据吗 ？", "删除",
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				
				if (k == JOptionPane.YES_OPTION) {
				    String id = deltable.getValueAt(index[0], 0).toString();
			        int count =dao.deleteById(id);
					if (count == 1) {
						JOptionPane.showMessageDialog(this, "删除操作成功完成!");
						showData(dao.getLibraryResultSet(sort));
					} 
		        }
		 }
	}

	/**
	 * 统计信息
	 */
	public void showCount(){
		int a=dao.getBookCount();
		int b=dao.getDVDCount();
		int c=dao.getPaintingCount();
		int all=a+b+c;
		String msg="总和:"+all+"\n图书:"+a+"\n视频:"+b+"\n图画:"+c;
		JOptionPane.showMessageDialog(this, msg);
			
	}
		
	

		public static void main(String[] args) {
		new MainFrame();
		}
}
