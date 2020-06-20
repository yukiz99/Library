package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;







import dao.C3p0Utils;
import dao.Dao;
import domain.Book;
import domain.DVD;
import domain.Library;
import domain.Painting;

public class UpdatePanel extends JPanel{
	private Dao dao=new Dao();
	private Library lib=null;
	private Color lightBlue=new Color(206,239,242);
	private JPanel labelsAndTfs;
	private JPanel[] ps;
	private JLabel[] labels;
	private JTextField[] textFields;
	private JButton btn;

	public UpdatePanel(String id){
		
	   this.setBackground(lightBlue);
			
		ps=new JPanel[7];
		labels=new JLabel[7];
		textFields=new JTextField[7];
		labelsAndTfs=new JPanel();
		btn=new JButton("����");
			
		for(int i=0;i<ps.length;i++){
			 ps[i]=new JPanel();
			 textFields[i]=new JTextField(50);
			 textFields[i].setFont(new Font("",1,20));
			 labels[i]=new JLabel();
			 labels[i].setFont(new Font("",1,20));
			 labels[i].setBounds(0,0,80,40);
			 textFields[i].setBounds(80,0, 420,40);
			 ps[i].setLayout(null);
			 ps[i].setBackground(lightBlue);
		}
		
		labels[0].setText("���   :");
		labels[1].setText("����   :");
		labels[2].setText("����   :");
		labels[3].setText("�ȼ�   :");
			
		
		if(id!=null){
			
				lib=dao.findById(id);
				textFields[0].setText(lib.getId());
				textFields[0].setEditable(false);
				textFields[1].setText(lib.getTitle());
				textFields[2].setText(lib.getAuthor());
				textFields[3].setText(lib.getLevel());
				
				if(lib instanceof Book){
					Book book=(Book)lib;
					labels[4].setText("�����:");
					labels[5].setText("ISBN :");
					labels[6].setText("ҳ��   :");
					textFields[4].setText(book.getPublishPlace());
					textFields[5].setText(book.getIsbn());
					textFields[6].setText(String.valueOf(book.getPages()));
				}else if(lib instanceof DVD){
					DVD dvd=(DVD)lib;
					labels[4].setText("������:");
					labels[5].setText("������:");
					labels[6].setText("ʱ��   :");
					textFields[4].setText(dvd.getPublisher());
					textFields[5].setText(dvd.getPublishYear());
					textFields[6].setText(String.valueOf(dvd.getPlayTime()));
				}else {
					Painting painting=(Painting)lib;
					labels[4].setText("�����:");
					labels[5].setText(" ��     :");
					labels[6].setText(" ��     :");
					textFields[4].setText(painting.getPublishCountry());
					textFields[5].setText(String.valueOf(painting.getWidth()));
					textFields[6].setText(String.valueOf(painting.getHeight()));
				}
				
				for(int i=0;i<labels.length;i++){
					ps[i].add(labels[i]);
					ps[i].add(textFields[i]);
					labelsAndTfs.add(ps[i]);
				}
							
				labelsAndTfs.setLayout(new GridLayout(7,1));
				this.setLayout(null);
				labelsAndTfs.setBounds(0,0,500,420);
				btn.setBounds(200,500,150,50);
				btn.setFont(new Font("",1,20));
				    
				this.add(labelsAndTfs);
				this.add(btn);
		}
				
				
				
		btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String[] ts=new String[textFields.length];
			for(int i=0;i<7;i++){
				   ts[i]=textFields[i].getText();
				   if(ts[i]==null||ts[i].trim().isEmpty()){
					  JOptionPane.showMessageDialog(null, "������е���Ϣ��д�����ٸ���");
				     return;
				  }
			}
			try{
				dao.updateByParam(ts);
				JOptionPane.showMessageDialog(null,"�༭�ɹ�");
			}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "ĳЩ��Ϣ��д��ʽ����");
					C3p0Utils.closeConnection();
				}
			}
		});
	}
		
		
		
	}
