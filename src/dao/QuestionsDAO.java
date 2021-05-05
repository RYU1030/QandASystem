package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Question;

public class QuestionsDAO {

    // URL・ユーザ名・パスワードの設定
    private final static String DRIVER_NAME = "org.postgresql.Driver";
    private final static String URL = "jdbc:postgresql://localhost/QandA";
    private final static String USER = "ryunosukefukuda";
    private final static String PASSWORD = "password";

    public boolean create(Question question) {
    	Connection conn = null;  // コネクションオブジェクト
    	try {
			// JDBCドライバを読み込む
			try {
				Class.forName(DRIVER_NAME);
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
    		// DBへ接続
    		conn = DriverManager.getConnection(URL, USER, PASSWORD);

    		// INSERT文を用意
    		String sql = "INSERT INTO questions (question_id, handle_name, title, contents, urgency, edit_delete_key, regist_timestamp, update_timestamp) VALUES (nextval('question_id_seq'), ?, ?, ?, ?, ?, Now(), Now())";
    		PreparedStatement pstmt = conn.prepareStatement(sql);

    		pstmt.setString(1, question.getHandleName());
    		pstmt.setString(2, question.getTitle());
    		pstmt.setString(3, question.getContents());
    		pstmt.setInt(4, question.getUrgency());
    		pstmt.setString(5, question.getEditDeleteKey());

    		int result = pstmt.executeUpdate();
    		if (result != 1) {
    			return false;
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	} finally {
    		// DB切断
    		if (conn != null) {
    			try {
    				conn.close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	return false;
    }
}
