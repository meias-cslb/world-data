package world.model.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import world.model.dao.CountryDAO;
import world.model.entity.Country;

class CountryTest {

	@Test
	void test() throws SQLException, ClassNotFoundException {
//		ドライバーの呼び出し(jarファイルの設定必須)
		Class.forName("com.mysql.cj.jdbc.Driver");

		String driver = "jdbc:mysql://localhost/world?serverTimezone=UTC";
		Connection connection = DriverManager.getConnection(driver, "root", "");

		CountryDAO dao = new CountryDAO(connection);

//		面積の大きい順でリストを取り出し
		List<Country> list = dao.findAll("surfaceArea", "desc");

//		「国名：面積(小数点第二まで)」を表示
		for(Country country : list) {
			System.out.println(String.format("%s: %.2f", country.getName(), country.getSurfaceArea()));
		}

	}

}
