package constants;

/*
 * UrgencyMessagesは、質問一覧ページで表示する緊急度を示す文字列を定数として用意するためのクラスです。
 */

public class UrgencyMessages {
	// privateコンストラクタでインスタンス生成を抑止
	private UrgencyMessages(){}

	// urgencyの数値に基づき、緊急度を示す文言を定数で定義する
	public static final String URGENT = "急いでいます";
	public static final String NEED_ADVICE = "困っています";
	public static final String ANYTIME = "いつでも";
}
