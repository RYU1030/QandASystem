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
    public List<Answer> findAll(int questionId) {
    	Connection conn = null;
		List<Answer> answerList = new ArrayList<Answer>();

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// SELECT文を用意
			String sql = "SELECT question_id, seq, handle_name, contents, regist_timestamp FROM answers WHERE question_id = ? ORDER BY seq DESC";
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
		return answerList;
    }
}
