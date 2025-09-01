package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

	private Connection conn;

	//コンストラクタでDB接続情報を引数で受け取りフィールドで保持する

	public TransactionManager(Connection conn) {

		this.conn = conn;

		try {
			//オートコミットモードをオフに設定（トランザクションの開始）
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//トランザクションをコミット（＝処理を確定させる）
	//DB接続がない場合は例外を投げる
	public void commit() throws SQLException {
		// データベースに処理を確定させる
		if (conn != null) {
			conn.commit();
			System.out.println("トランザクションが正常にコミットされました。");
		} else {
			System.err.println("トランザクションが開始されていません");
		}
	}

	public void rollback() throws SQLException {
		// データベースの処理を巻き戻す
		if (conn != null) {
			conn.rollback();
			System.out.println("トランザクションが正常にロールバックされました。");
		} else {
			System.err.println("トランザクションが開始されていません");
		}
	}

	//トランザクションを終了し、接続を閉じる。
	public void close() throws SQLException {
		if (conn != null) {
			conn.close();
			conn = null;
			System.out.println("データベース接続を閉じました。");
		}
	}
}