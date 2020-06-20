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
 * 得到相关类别的所有记录
 * @param sort
 * @return
 */
	public ResultSet getLibraryResultSet(String sort){
		Connection con=null;
		ResultSet rs=null;
		String sql = null;
		if(sort.equals("Book")){
			sql="select id as 编号,title as 标题 ,author as 作者 ,level as 等级,publishPlace as 出版地,isbn as ISBN,pages as 页数  from book";
		}else if(sort.equals("DVD")){
			sql="select id as 编号,title as 标题 ,author as 作者 ,level as 等级,publisher as 出版人,publishYear as 出版年,playTime as 时长  from dvd";
		}else if(sort.equals("Painting")){
			sql="select id as 编号,title as 标题 ,author as 作者 ,level as 等级,publishCountry as 出版国,width as 长,height as 宽  from painting";
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
	 * 通过id查找数据
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
					       sql="select id as 编号,title as 标题 ,author as 作者 ,level as 等级,publishPlace as 出版地,isbn as ISBN,pages as 页数  from book where id=?"; 
				           break;
				 case 'B':
					       sql="select id as 编号,title as 标题 ,author as 作者 ,level as 等级,publisher as 出版人,publishYear as 出版年,playTime as 时长  from dvd where id=?";
				           break;
				 case 'C':
					      sql="select id as 编号,title as 标题 ,author as 作者 ,level as 等级,publishCountry as 出版国,width as 长,height as 宽  from painting where id=?";
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
	     * 通过标题查找数据,三种数据的ResultSet
	     * @param title
	     * @return
	     */
		public List<ResultSet> findResultSetByTitle(String title){
			Connection con=null;
			List<ResultSet> list=new ArrayList<ResultSet>();
			try{
				con=C3p0Utils.getConnection();
				String sql="select id as 编号,title as 标题 ,author as 作者 ,"
					    	+ "level as 等级,publishPlace as 出版地,isbn as ISBN,"
					    	+ "pages as 页数  from book where title like ?";
				String param="%"+title+"%";
				NewProxyPreparedStatement ps=(NewProxyPreparedStatement) con.prepareStatement(sql);
				ps.setString(1, param);
				list.add(ps.executeQuery());
				
				sql="select id as 编号,title as 标题 ,author as 作者 ,"
						+ "level as 等级,publisher as 出版人,publishYear as 出版年,"
						+ "playTime as 时长  from dvd where title like ?";
				ps=(NewProxyPreparedStatement) con.prepareStatement(sql);
				ps.setString(1, param);
			   list.add(ps.executeQuery());
		
		       sql="select id as 编号,title as 标题 ,author as 作者 ,"
						+ "level as 等级,publishCountry as 出版国,width as 长,"
						+ "height as 宽  from painting where title like ?";
		        ps=(NewProxyPreparedStatement) con.prepareStatement(sql);
				ps.setString(1, param);
				list.add(ps.executeQuery());
		 
		  }catch(Exception e){
			 e.printStackTrace();
			}
			return list;
		}
	
	/**
	 * 通过id删除记录
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
		 * 得到图书总数
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
		 * 得到视频总数
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
	 * 得到图画总数
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
	 * 判断id是否存在
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
		 * 添加记录
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
		 * 通过id查找
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
	 * 通过参数类别编辑数据 
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
