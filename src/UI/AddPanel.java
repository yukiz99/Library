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
		btn=new JButton("���");
		
		Font f=new Font("",1,20);
		sort1=new JRadioButton("ͼ��",true);
		sort2=new JRadioButton("��Ƶ");
		sort3=new JRadioButton("ͼ��");
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
	
		//��ʼ��label��Ĭ��Ϊͼ�� 
		labels[0].setText("���   :");
		labels[1].setText("��Ŀ   :");
		labels[2].setText("����   :");
		labels[3].setText("�ȼ�   :");
		labels[4].setText("�����:");
		labels[5].setText("ISBN :");
		labels[6].setText("ҳ��   :");
					
					
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
				 JOptionPane.showMessageDialog(null, "������е���Ϣ��д���������");
			      return;
			  }
		  }
		  
		    //�ж���Ʒ����������д���� 
		      String reqex=null;
		      if(selectSort.equals("Book")){
		    	  reqex="^A[1-9][0-9]*";
		      }else if(selectSort.equals("DVD")){
		    	  reqex="^B[1-9][0-9]*";
			  }else{
				  reqex="^C[1-9][0-9]*";
			  }
				  if(!ts[0].matches(reqex)){
					  JOptionPane.showMessageDialog(null, "�����д����");
					  return;
				  }
				  if(dao.idExist(ts[0])){
					  JOptionPane.showMessageDialog(null, "�ñ���Ѵ���,���ʧ��");
					  return;
				  }
	         try {
					dao.addByParamAndSort(ts,selectSort);
					JOptionPane.showMessageDialog(null, "��ӳɹ�");
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "ĳЩ��Ϣ��д��ʽ����");
					C3p0Utils.closeConnection();
				}
	} });	
		
	/*
	 * sort1,sort2,sort3�ж����
	 */
	sort1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		labels[4].setText("�����:");
		labels[5].setText("ISBN :");
		labels[6].setText("ҳ��   :");
		selectSort="Book";
	}
	});
	
	sort2.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		  labels[4].setText("������:");
		  labels[5].setText("������:");
		  labels[6].setText("ʱ��   :");
		  selectSort="DVD";
	}
	});
	
	sort3.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		labels[4].setText("�����:");
		labels[5].setText(" ��   :");
		labels[6].setText(" ��  :");
		selectSort="Painting";
						
	}
	});
	}
	
	
	}
	
	