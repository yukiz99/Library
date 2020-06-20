package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.C3p0Utils;
import dao.Dao;

public class AddPanel extends JPanel{
	        
	
	private Dao dao=new Dao();
	private Color lightBlue=new Color(206,239,242);
	private JPanel sortPanel,labelsAndTfs;
	private JPanel[] ps;
	private JLabel[] labels;
	private JTextField[] tfs;
	private JButton btn;
	private JRadioButton sort1,sort2,sort3;
	private ButtonGroup bGroup;
	String selectSort="Book";
	
	
	public AddPanel(){
		
	   this.setBackground(new Color(206,239,242));
	
		labelsAndTfs=new JPanel();
		sortPanel=new JPanel();
		labels=new JLabel[7];
		tfs=new JTextField[7];
		ps=new JPanel[7];
		btn=new JButton("添加");
		
		Font f=new Font("",1,20);
		sort1=new JRadioButton("图书",true);
		sort2=new JRadioButton("视频");
		sort3=new JRadioButton("图画");
		sort1.setFont(f);
		sort2.setFont(f);
		sort3.setFont(f);
		sort1.setBackground(lightBlue);
		sort2.setBackground(lightBlue);
		sort3.setBackground(lightBlue);
		bGroup=new ButtonGroup();
		bGroup.add(sort1);
		bGroup.add(sort2);
		bGroup.add(sort3);
	
		for(int i=0;i<7;i++){
			
			ps[i]=new JPanel();
			tfs[i]=new JTextField(50);
			tfs[i].setFont(new Font("",1,20));
			labels[i]=new JLabel();
			labels[i].setFont(new Font("",1,20));
			labels[i].setBounds(0,0,80,40);
			tfs[i].setBounds(80,0, 420,40);
			ps[i].setLayout(null);
			ps[i].setBackground(lightBlue);
		}
	
		//初始化label，默认为图书 
		labels[0].setText("编号   :");
		labels[1].setText("题目   :");
		labels[2].setText("作者   :");
		labels[3].setText("等级   :");
		labels[4].setText("出版地:");
		labels[5].setText("ISBN :");
		labels[6].setText("页数   :");
					
					
		for(int i=0;i<labels.length;i++){
			ps[i].add(labels[i]);
			ps[i].add(tfs[i]);
			labelsAndTfs.add(ps[i]);
		}
					
		sortPanel.setLayout(new GridLayout(1,3));
		labelsAndTfs.setLayout(new GridLayout(7,1));
		this.setLayout(null);
	
		sortPanel.setBounds(100, 0, 400, 100);
		labelsAndTfs.setBounds(0,100,500,420);
		btn.setBounds(200,600,150,50);
		btn.setFont(new Font("",1,20));
	
		sortPanel.add(sort1);
		sortPanel.add(sort2);
		sortPanel.add(sort3);
		this.add(sortPanel);
		this.add(labelsAndTfs);
		this.add(btn);
					
					
	btn.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		
		  String[] ts=new String[7];
		  for(int i=0;i<7;i++){
			  ts[i]=tfs[i].getText();
			  if(ts[i]==null||ts[i].trim().isEmpty()){
				 JOptionPane.showMessageDialog(null, "请把所有的信息填写完整再添加");
			      return;
			  }
		  }
		  
		    //判断类品已满或编号填写错误 
		      String reqex=null;
		      if(selectSort.equals("Book")){
		    	  reqex="^A[1-9][0-9]*";
		      }else if(selectSort.equals("DVD")){
		    	  reqex="^B[1-9][0-9]*";
			  }else{
				  reqex="^C[1-9][0-9]*";
			  }
				  if(!ts[0].matches(reqex)){
					  JOptionPane.showMessageDialog(null, "编号填写错误");
					  return;
				  }
				  if(dao.idExist(ts[0])){
					  JOptionPane.showMessageDialog(null, "该编号已存在,添加失败");
					  return;
				  }
	         try {
					dao.addByParamAndSort(ts,selectSort);
					JOptionPane.showMessageDialog(null, "添加成功");
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "某些信息填写格式错误");
					C3p0Utils.closeConnection();
				}
	} });	
		
	/*
	 * sort1,sort2,sort3判断类别
	 */
	sort1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		labels[4].setText("出版地:");
		labels[5].setText("ISBN :");
		labels[6].setText("页数   :");
		selectSort="Book";
	}
	});
	
	sort2.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		  labels[4].setText("出版人:");
		  labels[5].setText("出版年:");
		  labels[6].setText("时长   :");
		  selectSort="DVD";
	}
	});
	
	sort3.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		labels[4].setText("出版国:");
		labels[5].setText(" 宽   :");
		labels[6].setText(" 高  :");
		selectSort="Painting";
						
	}
	});
	}
	
	
	}
	
	