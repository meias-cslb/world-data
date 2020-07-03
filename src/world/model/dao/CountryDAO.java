package world.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import world.model.entity.Country;

public class CountryDAO {
	private Connection connection;

//	@param connection データベース接続オブジェクト
	public CountryDAO(Connection connection) {
		this.connection = connection;
	}

//	@param sortColumn ソート文字列
//	@param direction ソート方向
//	@param sql 文字列
	private String createSql(String sortColumn, String direction) {
		String sql = "select *from country";

//		sortColumn ソート文字列＝例）name, surfacearea, population…
		if(sortColumn != null && !sortColumn.isEmpty()) {
			sql = sql + " order by " + sortColumn;
		}

//		@param direction ソート方向＝ direction = dec(大きい順) or 空白(小さい順)
		if(direction != null && !direction.isEmpty()) {
			sql = sql + " " + direction;
		}
		return sql;
	}


//	@param sortColumn ソート文字列
//	@param direction ソート方向
//	@param sql 文字列
//	@param country 国のリスト
//	@param SQLException データベース・アクセス・エラー、またはその他のエラーに関する情報を提供する例外
	public List<Country> findAll(String sortColumn, String direction) throws SQLException{
		List<Country> list = new ArrayList<Country>();

//		SQL文を作成
		String sql = this.createSql(sortColumn, direction);
//		SQL文を送るためのStatementオブジェクトの生成
		Statement statement = connection.createStatement();
//		SQL文を実行
		ResultSet resultSet = statement.executeQuery(sql);
//		ResultSet データベースの結果データ表

		while(resultSet.next()) {
			Country country = new Country();

//			国ごとにそれぞれのデータをセットし、国リストへ追加
			country.setCode(resultSet.getString("code"));
			country.setName(resultSet.getString("name"));
			country.setContinent(resultSet.getString("continent"));
			country.setRegion(resultSet.getString("region"));
			country.setSurfaceArea((BigDecimal)resultSet.getObject("surfaceArea"));
			country.setIndepYear((Integer)resultSet.getObject("indepYear"));
			country.setPopulation((Integer)resultSet.getObject("population"));
			country.setLifeExpectancy((BigDecimal)resultSet.getObject("lifeExpectancy"));
			country.setGnp((BigDecimal)resultSet.getObject("gnp"));
			country.setGnpOld((BigDecimal)resultSet.getObject("gnpOld"));
			country.setLocalName(resultSet.getString("localName"));
			country.setGovernmentForm(resultSet.getString("governmentForm"));
			country.setHeadOfState(resultSet.getString("headOfState"));
			country.setCode2(resultSet.getString("code2"));

			list.add(country);

		}

		resultSet.close();
		statement.close();

		return list;
	}
}
