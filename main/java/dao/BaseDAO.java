package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BaseDAO {

	private String dbUrl;
    private String dbUser;
    private String dbPassword;

    private Connection conn;

    public BaseDAO() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            if (in == null) {
                throw new IOException("database.properties がクラスパス上に見つかりません。");
            }

            Properties props = new Properties();
            props.load(in);

            this.dbUrl = props.getProperty("db.url");
            this.dbUser = props.getProperty("db.user");
            this.dbPassword = props.getProperty("db.password");

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("設定ファイルの読み込みに失敗しました。", e);
        }
    }
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conn == null || conn.isClosed()) {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        }
        return conn;
    }
}
//} catch (SQLException e) {
			//DBが起動していない／ユーザやパスワードが違う／ネットワーク不通なら SQLException。

				//System.err.println("DB接続失敗！");

				//e.printStackTrace();

			//}catch(ClassNotFoundException e) {
			//ドライバが見つからなければ ClassNotFoundException。
				
				//System.err.println("PostgreSQL ドライバが見つかりません");

				//e.printStackTrace();

			//}
		//}
		
		//connがnullでもnullじゃなくてもreturnされる