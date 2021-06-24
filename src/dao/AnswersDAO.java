package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Answer;

/**
 * AnswersDAOは、Answersテーブルへの接続と操作を担当するクラスです。
 * @param DRIVER_NAME ドライバ名
 * @param URL DBのURL
 * @param USER DBユーザ名
 * @param PASSWORD DBパスワード
 */

public class AnswersDAO {
	// URL・ユーザ名・パスワードの設定
    private final static String DRIVER_NAME = "org.postgresql.Driver";
    private final static String URL = "jdbc:postgresql://localhost/QandA";
    private final static String USER = "ryunosukefukuda";
    private final static String PASSWORD = "password";

    // 回答リスト取得処理
    public List<Answer> findAll(int questionId) throws SQLException, ClassNotFoundException {
    	Connection conn = null;
		List<Answer> answerList = new ArrayList<Answer>();

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// SELECT文を用意
			String sql = "SELECT question_id, seq, handle_name, contents, regist_timestamp FROM answers WHERE question_id = ? ORDER BY seq ASC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, questionId);

			ResultSet rs = pstmt.executeQuery();
			// 年/月/日 時:分:秒 のフォーマットを用意
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/' 'HH:mm:ss");
			while (rs.next()) {
				int answerTo = rs.getInt("question_id");
				int seq = rs.getInt("seq");
				String handleName = rs.getString("handle_name");
				String contents = rs.getString("contents");
				// DBからタイムスタンプを取得し、文字列に整形する
				Timestamp registTimestamp = rs.getTimestamp("regist_timestamp");
				String registDateTime = df.format(registTimestamp);

				Answer answer = new Answer();
				answer.setQuestionId(answerTo);
				answer.setSeq(seq);
				answer.setHanldeName(handleName);
				answer.setContents(contents);
				answer.setRegistDateTime(registDateTime);
				answerList.add(answer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} finally {
			// DBから切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw e;
				}
			}
		}
		return answerList;
    }

	// 回答登録の処理
    public boolean create(Answer answer) throws SQLException, ClassNotFoundException {
    	Connection conn = null;  // コネクションオブジェクト
    	try {
				// JDBCドライバを読み込む
				try {
					Class.forName(DRIVER_NAME);
				} catch (ClassNotFoundException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
					throw e;
				}
				// DBへ接続
				conn = DriverManager.getConnection(URL, USER, PASSWORD);

				// 回答登録に際して、postされたquestion_idに基づき該当の質問に対する次のseqを取得する
				String getMaxSeq = "SELECT MAX(seq) AS maxSeq FROM answers WHERE question_id = ?";
				PreparedStatement getMaxSeqPstmt = conn.prepareStatement(getMaxSeq);

				getMaxSeqPstmt.setInt(1, answer.getQuestionId());

				// SQL問い合わせ結果をmaxSeqRsに格納
				ResultSet maxSeqRs = getMaxSeqPstmt.executeQuery();
				//
				while (maxSeqRs.next()) {
					int maxSeq = maxSeqRs.getInt("maxSeq");
					// seq最大値+1の値を、新規登録する回答情報のseqとしてセットする
					int nextSeq = maxSeq + 1;

						// INSERT文を用意
						String sql = "INSERT INTO answers (question_id, seq, handle_name, contents, regist_timestamp) VALUES (?, ?, ?, ?, Now())";
						PreparedStatement pstmt = conn.prepareStatement(sql);

						pstmt.setInt(1, answer.getQuestionId());
						pstmt.setInt(2, nextSeq);
						pstmt.setString(3, answer.getHandleName());
						pstmt.setString(4, answer.getContents());

						int result = pstmt.executeUpdate();
						if (result != 1) {
							return false;
						}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				// DB切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw e;
					}
				}
			}
			return false;
		}
    // 回答情報削除の処理
    public boolean delete (int questionId) throws SQLException, ClassNotFoundException {
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

    		// DELETE文を用意
    		String sql = "DELETE FROM answers WHERE question_id = ?";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, questionId);
    		int result = pstmt.executeUpdate();
    		if (result != 1) {
    			return false;
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	} catch (Exception e) {
    		conn.rollback(); // 予期せぬ例外発生時はロールバックする
    		e.printStackTrace();
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
