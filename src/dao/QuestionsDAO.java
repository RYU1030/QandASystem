package dao;

import static constants.UrgencyMessages.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Question;

/**
 * QuestionsDAOは、DBへの接続と操作を担当するクラスです。
 * @param DRIVER_NAME ドライバ名
 * @param URL DBのURL
 * @param USER DBユーザ名
 * @param PASSWORD DBパスワード
 */

public class QuestionsDAO {
    // URL・ユーザ名・パスワードの設定
    private final static String DRIVER_NAME = "org.postgresql.Driver";
    private final static String URL = "jdbc:postgresql://localhost/QandA";
    private final static String USER = "ryunosukefukuda";
    private final static String PASSWORD = "password";

		// 質問リスト取得処理
		public List<Question> findAll() {
			Connection conn = null;
			List<Question> questionList = new ArrayList<Question>();
			try {
				Class.forName(DRIVER_NAME);
				conn = DriverManager.getConnection(URL, USER, PASSWORD);

				// SELECT文を用意
				String sql = "SELECT question_id, handle_name, title, contents, urgency, edit_delete_key, regist_timestamp, update_timestamp FROM questions ORDER BY question_id ASC";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SELECT文を実行
				ResultSet rs = pStmt.executeQuery();

				// SELECT文の結果をArrayListに格納
				while (rs.next()) {
					int questionId = rs.getInt("question_id");
					String handleName = rs.getString("handle_name");
					String title = rs.getString("title");
					String contents = rs.getString("contents");
					int urgency = rs.getInt("urgency");
					String urgencyMessage;
					if (urgency == 1) {
						urgencyMessage = URGENT;
					} else if (urgency == 2) {
						urgencyMessage = NEED_ADVICE;
					} else {
						urgencyMessage = ANYTIME;
					}
					String editDeleteKey = rs.getString("edit_delete_key");
					// 登録日時をDate型で取得
					Date registeredDate = rs.getDate("regist_timestamp");
					// 更新日時をDate型で取得
					Date updatedDate = rs.getDate("update_timestamp");
					Question question = new Question();
					question.setQuestionId(questionId);
					question.setHandleName(handleName);
					question.setTitle(title);
					question.setContents(contents);
					question.setUrgencyMessage(urgencyMessage);
					question.setEditDeleteKey(editDeleteKey);
					question.setRegisteredDate(registeredDate);
					question.setUpdatedDate(updatedDate);
					questionList.add(question);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			} finally {
				// DBから切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
			return questionList;
		}

    // 質問登録の処理
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
