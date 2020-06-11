// DAO(Data Access Object)

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.MemberDTO;

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	// 컨넥션풀에서 컨넥션을 구해오는 메소드
	private Connection getConnection() throws Exception{
		Context init = new InitialContext();
  		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
  		return ds.getConnection();
	}
	
	public int insert(MemberDTO member) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = getConnection();
			String sql = "insert into member0609 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getJumin1());
			pstmt.setString(5, member.getJumin2());
			pstmt.setString(6, member.getMailid());
			pstmt.setString(7, member.getDomain());
			pstmt.setString(8, member.getTel1());
			pstmt.setString(9, member.getTel2());
			pstmt.setString(10, member.getTel3());
			pstmt.setString(11, member.getPhone1());
			pstmt.setString(12, member.getPhone2());
			pstmt.setString(13, member.getPhone3());
			pstmt.setString(14, member.getPost());
			pstmt.setString(15, member.getAddress());
			pstmt.setString(16, member.getGender());
			pstmt.setString(17, member.getHobby());
			pstmt.setString(18, member.getIntro());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) { try {pstmt.close();}catch(Exception e){ e.printStackTrace();}}
			if(con != null) { try {con.close();}catch(Exception e){ e.printStackTrace();}}
		}
			
		return result;
	}
	
	// ID 중복검사
	public int idcheck(String id) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "select * from member0609 where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {					// 중복 ID
				result = 1;
			}else {							// 사용 가능한 ID
				result = -1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { try {rs.close();}catch(Exception e){ e.printStackTrace();}}
			if(pstmt != null) { try {pstmt.close();}catch(Exception e){ e.printStackTrace();}}
			if(con != null) { try {con.close();}catch(Exception e){ e.printStackTrace();}}
		}
		
		return result;
	}
	
	// 로그인(회원 인증)
	public int memberAuth(String id, String passwd) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select * from member0609 where id=? and passwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {			// 인증 성공
				result = 1;
			}else {					// 인증 실패
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) { try {rs.close();}catch(Exception e){ e.printStackTrace();}}
			if(pstmt != null) { try {pstmt.close();}catch(Exception e){ e.printStackTrace();}}
			if(con != null) { try {con.close();}catch(Exception e){ e.printStackTrace();}}
		}
		
		return result;
	}
	
	// 회원 정보 구하기
	public MemberDTO getMember(String id) {
		MemberDTO member = new MemberDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			String sql = "select * from member0609 where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getNString("name"));
				member.setJumin1(rs.getString("jumin1"));
				member.setJumin2(rs.getString("jumin2"));
				member.setMailid(rs.getString("mailid"));
				member.setDomain(rs.getString("domain"));
				member.setTel1(rs.getString("tel1"));
				member.setTel2(rs.getString("tel2"));
				member.setTel3(rs.getString("tel3"));
				member.setPhone1(rs.getString("phone1"));
				member.setPhone2(rs.getString("phone2"));
				member.setPhone3(rs.getString("phone3"));
				member.setPost(rs.getString("post"));
				member.setAddress(rs.getString("address"));
				member.setGender(rs.getString("gender"));
				member.setHobby(rs.getString("hobby"));
				member.setIntro(rs.getString("intro"));
				member.setRegister(rs.getTimestamp("register"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { try {rs.close();}catch(Exception e){ e.printStackTrace();}}
			if(pstmt != null) { try {pstmt.close();}catch(Exception e){ e.printStackTrace();}}
			if(con != null) { try {con.close();}catch(Exception e){ e.printStackTrace();}}
		}
		
		return member;
	}
	
	// 회원정보 수정
	public int update(MemberDTO member) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "update member0609 set name=?, jumin1=?, jumin2=?,"
					+ " mailid=?, domain=?, tel1=?, tel2=?, tel3=?,"
					+ " phone1=?, phone2=?, phone3=?, post=?, address=?,"
					+ " gender=?, hobby=?, intro=? where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getJumin1());
			pstmt.setString(3, member.getJumin2());
			pstmt.setString(4, member.getMailid());
			pstmt.setString(5, member.getDomain());
			pstmt.setString(6, member.getTel1());
			pstmt.setString(7, member.getTel2());
			pstmt.setString(8, member.getTel3());
			pstmt.setString(9, member.getPhone1());
			pstmt.setString(10, member.getPhone2());
			pstmt.setString(11, member.getPhone3());
			pstmt.setString(12, member.getPost());
			pstmt.setString(13, member.getAddress());
			pstmt.setString(14, member.getGender());
			pstmt.setString(15, member.getHobby());
			pstmt.setString(16, member.getIntro());
			pstmt.setString(17, member.getId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) { try {pstmt.close();}catch(Exception e){ e.printStackTrace();}}
			if(con != null) { try {con.close();}catch(Exception e){ e.printStackTrace();}}			
		}
		return result;
	}
	// 회원탈퇴
	public int delete(String id) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			String sql = "delete from member0609 where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) { try {pstmt.close();}catch(Exception e){ e.printStackTrace();}}
			if(con != null) { try {con.close();}catch(Exception e){ e.printStackTrace();}}			
		}
		return result;
	}
	
	

}
