package dao;

import java.sql.Connection;
import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.impl.NewProxyPreparedStatement;

import domain.Book;
import domain.DVD;
import domain.Library;
import domain.Painting;

public class Dao {
	
/**
 * �õ�����������м�¼
 * @param sort
 * @return
 */
	public ResultSet getLibraryResultSet(String sort){
		Connection con=null;
		ResultSet rs=null;
		String sql = null;
		if(sort.equals("Book")){
			sql="select id as ���,title as ���� ,author as ���� ,level as �ȼ�,publishPlace as �����,isbn as ISBN,pages as ҳ��  from book";
		}else if(sort.equals("DVD")){
			sql="select id as ���,title as ���� ,author as ���� ,level as �ȼ�,publisher as ������,publishYear as ������,playTime as ʱ��  from dvd";
		}else if(sort.equals("Painting")){
			sql="select id as ���,title as ���� ,author as ���� ,level as �ȼ�,publishCountry as �����,width as ��,height as ��  from painting";
		}
		try{
			con=C3p0Utils.getConnection();
			rs=con.prepareStatement(sql).executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
	  return rs;
	}
	
	
	
	/**
	 * ͨ��id��������
	 * @param id
	 * @return
	 */
		public ResultSet findResultSetById(String id){
			Connection con=null;
			ResultSet rs=null;
			
			try{
				con=C3p0Utils.getConnection();
				String sql=null;
				switch(id.charAt(0)){
				 case 'A':
					       sql="select id as ���,title as ���� ,author as ���� ,level as �ȼ�,publishPlace as �����,isbn as ISBN,pages as ҳ��  from book where id=?"; 
				           break;
				 case 'B':
					       sql="select id as ���,title as ���� ,author as ���� ,level as �ȼ�,publisher as ������,publishYear as ������,playTime as ʱ��  from dvd where id=?";
				           break;
				 case 'C':
					      sql="select id as ���,title as ���� ,author as ���� ,level as �ȼ�,publishCountry as �����,width as ��,height as ��  from painting where id=?";
				          break;
				}
	        	NewProxyPreparedStatement ps=(NewProxyPreparedStatement) con.prepareStatement(sql);
				ps.setString(1, id);
				rs=ps.executeQuery();
			}catch(Exception e){
			    e.printStackTrace();
			}
				return rs;
		}

	    /**
	     * ͨ�������������,�������ݵ�ResultSet
	     * @param title
	     * @return
	     */
		public List<ResultSet> findResultSetByTitle(String title){
			Connection con=null;
			List<ResultSet> list=new ArrayList<ResultSet>();
			try{
				con=C3p0Utils.getConnection();
				String sql="select id as ���,title as ���� ,author as ���� ,"
					    	+ "level as �ȼ�,publishPlace as �����,isbn as ISBN,"
					    	+ "pages as ҳ��  from book where title like ?";
				String param="%"+title+"%";
				NewProxyPreparedStatement ps=(NewProxyPreparedStatement) con.prepareStatement(sql);
				ps.setString(1, param);
				list.add(ps.executeQuery());
				
				sql="select id as ���,title as ���� ,author as ���� ,"
						+ "level as �ȼ�,publisher as ������,publishYear as ������,"
						+ "playTime as ʱ��  from dvd where title like ?";
				ps=(NewProxyPreparedStatement) con.prepareStatement(sql);
				ps.setString(1, param);
			   list.add(ps.executeQuery());
		
		       sql="select id as ���,title as ���� ,author as ���� ,"
						+ "level as �ȼ�,publishCountry as �����,width as ��,"
						+ "height as ��  from painting where title like ?";
		        ps=(NewProxyPreparedStatement) con.prepareStatement(sql);
				ps.setString(1, param);
				list.add(ps.executeQuery());
		 
		  }catch(Exception e){
			 e.printStackTrace();
			}
			return list;
		}
	
	/**
	 * ͨ��idɾ����¼
	 * @param id
	 * @param sort
	 * @return
	 */
	public int deleteById(String id){
		QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
		int count = -1;
		try{
			  String sort=null;
			  switch(id.charAt(0)){
			    case 'A':
			    	      sort="book";
			    	      break;
			    case 'B':
			    	      sort="dvd";
			    	      break;
			    case 'C': 
			    	      sort="painting";
			    	      break;
			  }
			String sql = "delete from "+sort+" where id=?";
			count=qr.update(sql,id);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			C3p0Utils.closeConnection();
		}
		return count;
	}


		/**
		 * �õ�ͼ������
		 * @return
		 */
		public int getBookCount(){
			QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
			int count=-1;
			try{
				String sql="select count(*) from book";
				count=((Number) qr.query(sql,new ScalarHandler())).intValue();
			}catch(Exception e){
			}finally{
				C3p0Utils.closeConnection();
			}
			return count;
		}


		/**
		 * �õ���Ƶ����
		 * @return
		 */
		public int getDVDCount(){
			int count=-1;
			QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
			try{
				String sql="select count(*) from dvd";
				count=((Number) qr.query(sql,new ScalarHandler())).intValue();
				
			}catch(Exception e){
			}finally{
				C3p0Utils.closeConnection();
			}
			return count;
		}
	
	
	/**
	 * �õ�ͼ������
	 * @return
	 */
	public int getPaintingCount(){
		int count=-1;
		QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
		try{
			String sql="select count(*) from painting";
			count=((Number) qr.query(sql,new ScalarHandler())).intValue();	
		}catch(Exception e){
			
		}finally{
			C3p0Utils.closeConnection();
		}
		return count;
	}
	
	
	/**
	 * �ж�id�Ƿ����
	 * @param id
	 * @return
	 */
	public boolean idExist(String id){
		QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
		String sql1="select * from book where id=?";
		String sql2="select * from dvd where id=?";
		String sql3="select * from painting where id=?";
		try{
			Library lib=qr.query(sql1,new BeanHandler<>(Book.class),id);
			if(lib==null){
				lib=qr.query(sql2,new BeanHandler<>(DVD.class),id);
			}
			if(lib==null){
				lib=qr.query(sql3,new BeanHandler<>(Painting.class),id);
			}
			if(lib==null){
				return false;
			}
				
		}catch(Exception e){}
		finally{
			C3p0Utils.closeConnection();
		}
		return true;
	}

	
		/**
		 * ��Ӽ�¼
		 * @param ts
		 * @param selectSort
		 * @throws SQLException 
		 */
		public void addByParamAndSort(String[] ts,String selectSort) throws NumberFormatException{
			String sql="insert "+selectSort+" values(?,?,?,?,?,?,?)";
			QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
			try{
				if(selectSort.equals("Book")){
					  Object[] param={ts[0],ts[1],ts[2],ts[3],ts[4],ts[5],Integer.parseInt(ts[6])};
					  qr.update(sql, param);
				  }
				 else if(selectSort.equals("DVD")){
					  Object[] param={ts[0],ts[1],ts[2],ts[3],ts[4],ts[5],Double.parseDouble(ts[6])};
					  qr.update(sql, param);
				 }else if(selectSort.equals("Painting")){
					  Object[] param={ts[0],ts[1],ts[2],ts[3],ts[4],Integer.parseInt(ts[5]),Integer.parseInt(ts[6])};
					  qr.update(sql, param);
				 }
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				C3p0Utils.closeConnection();
			}
		}

		/**
		 * ͨ��id����
		 * @param id
		 * @return
		 */
		public Library findById(String id){
			Library lib=null;
			QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
			try{
				if(id.charAt(0)=='A'){
					String sql="select*from book where id=?";
					lib=qr.query(sql,new BeanHandler<>(Book.class),id);
				}
				else if(id.charAt(0)=='B'){
					String sql="select*from dvd where id=?";
					lib=qr.query(sql,new BeanHandler<>(DVD.class),id);
				}else{
					String sql="select*from painting where id=?";
					lib=qr.query(sql,new BeanHandler<>(Painting.class),id);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				C3p0Utils.closeConnection();
			}
			return lib;
		}

	/**
	 * ͨ���������༭���� 
	 * @param ts
	 * @param sort
	 * @throws SQLException 
	 */
	public void updateByParam(String[] ts)throws NumberFormatException{
		QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
		try{
			if(ts[0].charAt(0)=='A'){
				  Object[] param={ts[1],ts[2],ts[3],ts[4],ts[5],Integer.parseInt(ts[6]),ts[0]};
				  String sql="update book set title=?,author=?,level=?,publishPlace=?,isbn=?,pages=? where id=?";
				  qr.update(sql, param);
			  }
			  else if(ts[0].charAt(0)=='B'){
				  Object[] param={ts[1],ts[2],ts[3],ts[4],ts[5],Double.parseDouble(ts[6]),ts[0]};
				  String sql="update dvd set title=?,author=?,level=?,publisher=?,publishYear=?,playTime=? where id=?";
				  qr.update(sql, param);
			  }else if(ts[0].charAt(0)=='C'){
				  Object[] param={ts[1],ts[2],ts[3],ts[4],Integer.parseInt(ts[5]),Integer.parseInt(ts[6]),ts[0]};
				  String sql="update painting set title=?,author=?,level=?,publishCountry=?,width=?,height=? where id=?";
				  qr.update(sql, param);
			 }
		}catch(SQLException e){
			 e.printStackTrace();
		 }finally{
			 C3p0Utils.closeConnection();
		 }
	}
}
