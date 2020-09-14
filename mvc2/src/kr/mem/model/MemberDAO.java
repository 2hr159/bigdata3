package kr.mem.model;
import java.sql.*;
import java.util.ArrayList;
public class MemberDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	//초기화블럭
	static {
		try {		// DriverManager
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Connection getConnect() {
		String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user="hr";
		String password="hr";
		
		try {
			conn=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	
	
	
	}
	public int memberInsert(MemverVO vo) {
		conn=getConnect();
		String SQL="insert into tblMem values(seq_num.nextval,?,?,?,?,?)";
		int cnt=-1;//-1실패
		try {
			ps=conn.prepareStatement(SQL);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPhone());
			ps.setString(3, vo.getAddr());
			ps.setDouble(4, vo.getLat());
			ps.setDouble(5, vo.getLng());
			cnt=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}
	public ArrayList<MemverVO> memberAllList() {
		ArrayList<MemverVO> list=new ArrayList<MemverVO>();
		conn=getConnect();
		String SQL="select*from tblMem order by num desc";
		
		try {
			ps=conn.prepareStatement(SQL);
			rs=ps.executeQuery();
			
			while (rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String addr = rs.getString("addr");
				double lat=rs.getDouble("lat");
				double lng=rs.getDouble("lng");
				MemverVO vo = new MemverVO(num, name, phone, addr, lat, lng);
				list.add(vo);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
		
		
		
		
	}
	public int memberDelete(int num) {
		conn=getConnect();
		String SQL="delete from tblmem where num=?";
		int cnt=-1;
		try {
			ps=conn.prepareStatement(SQL);
			ps.setInt(1, num);
			cnt = ps.executeUpdate();//1
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}
	public void dbClose() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	public MemverVO memberContent(int num) {
		MemverVO vo=null;
		conn=getConnect();
		String SQL="select*from tblMem where num=?";
		try {
			ps=conn.prepareStatement(SQL);
			ps.setInt(1, num);
			rs=ps.executeQuery();
			if(rs.next()) {
				num = rs.getInt("num");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String addr = rs.getString("addr");
				double lat=rs.getDouble("lat");
				double lng=rs.getDouble("lng");
				vo = new MemverVO(num, name, phone, addr, lat, lng);
				
			}
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return vo;
	}
	public int memberUpdate(MemverVO vo) {
		conn=getConnect();
		 String SQL="update tblMem set phone=?,addr=? where num=?";
		int cnt=-1;
	try {
		ps=conn.prepareStatement(SQL);
		ps.setString(1, vo.getPhone());
		ps.setString(2, vo.getAddr());
		ps.setInt(3, vo.getNum());
	    cnt=ps.executeUpdate(); ///???
	} catch( Exception e) {
		e.printStackTrace();
	}finally {
		dbClose();
	}
		return cnt;
	}
}
