package com.hdgf.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hdgf.dto.AnnouncementVO;
import com.hdgf.dto.IR_Center_VO;
import com.hdgf.util.DBConnection;

import oracle.jdbc.OracleTypes;

public class IR_Center_DAO {

	private IR_Center_DAO() {
	}

	private static IR_Center_DAO instance = new IR_Center_DAO();

	public static IR_Center_DAO getInstance() {
		return instance;
	}

	Connection conn = null;

	public void write(IR_Center_VO IR_Center_VO) {

		String runSP = "{ call sp_insert_IR_Center(?, ?, ?, ?, ?) }";

		try {
			conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			// callableStatement.setInt(1, IR_Center_VO.getIR_id());
			callableStatement.setString(1, IR_Center_VO.getTitle());
			callableStatement.setString(2, IR_Center_VO.getUser_id());
			callableStatement.setString(3, IR_Center_VO.getMain_text());
			callableStatement.setInt(4, IR_Center_VO.getfile_id());
			callableStatement.executeUpdate();
			System.out.println("success");
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(IR_Center_VO IR_Center_VO) {

		String runSP = "{ call sp_update_IR_Center(?, ?, ?) }";

		try {
			conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.setInt(1, IR_Center_VO.getIR_id());
			callableStatement.setString(2, IR_Center_VO.getTitle());
			callableStatement.setString(3, IR_Center_VO.getMain_text());
			callableStatement.setInt(4, IR_Center_VO.getfile_id());
			callableStatement.executeUpdate();
			System.out.println("success");
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int delete(int IR_ID) {
		String sql = " { call sp_delete_ir_center(?) }";
		
		Connection conn = null;
		
		try {
			conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(sql);
			callableStatement.setInt(1, IR_ID);
			callableStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}

	// selectAll : dto가 담길 리스트가 필요하다.
	public ArrayList<IR_Center_VO> get_ALL_List(String search_ALL) {
		String runSP = "{ call sp_search_ALL_IR_Center(?,?,?) }";
		// 물음표 변수의 순서는 out, in. 이 순서를 바꾸려면 프로시저의 변수 순서를 바꿔주면 된다
		ArrayList<IR_Center_VO> lists = new ArrayList<>();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			ResultSet rs = null;
			callableStatement = conn.prepareCall(runSP);
			// out 파라미터 자료형 설정
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			// 파일 재추가 진행
			callableStatement.setString(2, search_ALL);// '홍길' 검색시 '홍길%' 모든 사람 나오게끔 % 붙임
			callableStatement.setString(3, search_ALL);// '홍길' 검색시 '홍길%' 모든 사람 나오게끔 % 붙임
			// 프로시져 실행
			callableStatement.executeUpdate();
			// out파라미터의 값을 돌려받는다
			rs = (ResultSet) callableStatement.getObject(1); // callableStatement실행결과를 object로 받아 downcast
			while (rs.next()) {
				// 레코드에 있는 내용을 dto에 입력
				IR_Center_VO vo = new IR_Center_VO();
				vo.setIR_id(rs.getInt("board_id"));
				/*
				 * vo.setTitle(rs.getString("Title")); vo.setUser_id(rs.getString("User_id"));
				 * vo.setWrdate(rs.getDate("Wrdate"));
				 * vo.setMain_text(rs.getString("Main_text"));
				 * vo.setfile_id(rs.getString("file_id")); vo.setVisiter(rs.getInt("Visiter"));
				 */
				// vo를 리스트에 추가
				lists.add(vo);
			}
			rs.close();
			callableStatement.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}

	// searchTitle
	public ArrayList<IR_Center_VO> get_Title_List(String search_Title) {
		String runSP = "{ call sp_search_title_IR_Center(?,?) }";
		// 물음표 변수의 순서는 out, in. 이 순서를 바꾸려면 프로시저의 변수 순서를 바꿔주면 된다
		ArrayList<IR_Center_VO> lists = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			ResultSet rs = null;
			callableStatement = conn.prepareCall(runSP);
			// out 파라미터 자료형 설정
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			// 오라클과 호환성제 때문에 demo > build path > configure build path > library에 ojdbc8.jar
			// 파일 재추가 진행
			callableStatement.setString(2, search_Title);// '홍길' 검색시 '홍길%' 모든 사람 나오게끔 % 붙임
			// 프로시져 실행
			callableStatement.executeUpdate();
			// out파라미터의 값을 돌려받는다
			rs = (ResultSet) callableStatement.getObject(1); // callableStatement실행결과를 object로 받아 downcast
			while (rs.next()) {
				// 레코드에 있는 내용을 dto에 입력
				IR_Center_VO vo = new IR_Center_VO();
				vo.setIR_id(rs.getInt("IR_id"));
				/*
				 * vo.setTitle(rs.getString("Title")); vo.setUser_id(rs.getString("User_id"));
				 * vo.setWrdate(rs.getDate("Wrdate"));
				 * vo.setMain_text(rs.getString("Main_text"));
				 * vo.setfile_id(rs.getString("file_id")); vo.setVisiter(rs.getInt("Visiter"));
				 */
				// vo를 리스트에 추가
				lists.add(vo);
			}
			rs.close();
			callableStatement.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}

	// searchMain_text
	public ArrayList<IR_Center_VO> get_Main_text_List(String search_Main_text) {
		String runSP = "{ call sp_search_Main_text_IR_Center(?,?) }";
		// 물음표 변수의 순서는 out, in. 이 순서를 바꾸려면 프로시저의 변수 순서를 바꿔주면 된다
		ArrayList<IR_Center_VO> lists = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			ResultSet rs = null;
			callableStatement = conn.prepareCall(runSP);
			// out 파라미터 자료형 설정
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			// 파일 재추가 진행
			callableStatement.setString(2, search_Main_text);// '홍길' 검색시 '홍길%' 모든 사람 나오게끔 % 붙임
			// 프로시져 실행
			callableStatement.executeUpdate();
			// out파라미터의 값을 돌려받는다
			rs = (ResultSet) callableStatement.getObject(1); // callableStatement실행결과를 object로 받아 downcast
			while (rs.next()) {
				// 레코드에 있는 내용을 dto에 입력
				IR_Center_VO vo = new IR_Center_VO();
				vo.setIR_id(rs.getInt("IR_id"));
				/*
				 * vo.setTitle(rs.getString("Title")); vo.setUser_id(rs.getString("User_id"));
				 * vo.setWrdate(rs.getDate("Wrdate"));
				 * vo.setMain_text(rs.getString("Main_text"));
				 * vo.setfile_id(rs.getString("file_id")); vo.setVisiter(rs.getInt("Visiter"));
				 */
				// vo를 리스트에 추가
				lists.add(vo);
			}
			rs.close();
			callableStatement.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}

	// 전체 list 검색
	public ArrayList<IR_Center_VO> getList() {
		String runSP = "{ call sp_search_List_ALL_IR_Center(?) }";
		// 전체데이터를 select한 결과 presult가 들어가므로 ?가 1개. presult는 오라클에서 커서에 해당.
		ArrayList<IR_Center_VO> lists = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			ResultSet rs = null;
			callableStatement = conn.prepareCall(runSP);
			// out파라미터의 자료형 설정(커서를 받아낼 데이터 타입을 생성)
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			// 프로시저 실행
			callableStatement.executeUpdate();
			// out파라미터의 값을 돌려받는다
			rs = (ResultSet) callableStatement.getObject(1); // cstmt실행결과를 object로 받아 downcast
			while (rs.next()) {
				// 레코드에 있는 내용을 vo에 입력
				IR_Center_VO vo = new IR_Center_VO();
				vo.setIR_id(rs.getInt("ir_id"));
				vo.setTitle(rs.getString("title"));
				vo.setUser_id(rs.getString("user_id"));
				vo.setVisiter(rs.getInt("visiter"));
				vo.setWrdate(rs.getDate("wrdate"));
				// vo를 리스트에 추가
				lists.add(vo);
			}
			rs.close();
			callableStatement.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}

	// 하나의 게시글을 보는 메소드
	public IR_Center_VO getIR(int IR_id) {
		String sql = "{ call sp_select_one_ir_center(?, ?) }";
		Connection conn = null;
		ResultSet rs = null;
		IR_Center_VO vo = new IR_Center_VO();
		try {
			conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(sql);
			callableStatement = conn.prepareCall(sql);
			// out파라미터의 자료형 설정(커서를 받아낼 데이터 타입을 생성)
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.setInt(2, IR_id);
			// 프로시저 실행
			callableStatement.executeUpdate();
			// out파라미터의 값을 돌려받는다
			rs = (ResultSet) callableStatement.getObject(1); // cstmt실행결과를 object로 받아 downcast
			if (rs.next()) {
				// 레코드에 있는 내용을 vo에 입력
				vo.setIR_id(rs.getInt("ir_id"));
				vo.setTitle(rs.getString("title"));
				vo.setUser_id(rs.getString("user_id"));
				vo.setWrdate(rs.getDate("wrdate"));
				vo.setMain_text(rs.getString("main_text"));
				vo.setfile_id(rs.getInt("file_id"));
				vo.setVisiter(rs.getInt("visiter"));
			}
			rs.close();
			callableStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
}