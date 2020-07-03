<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="world.model.entity.Country" %>
<%@ page import="java.util.List" %>

<%
	List<Country> list = (List<Country>)request.getAttribute("list");
	String column = (String)request.getAttribute("column");
	String direction = (String)request.getAttribute("direction");
	String[] columns = {
			"code", "name", "continent", "region", "surfaceArea", "indepYear", "population",
			"lifeExpectancy", "gnp", "gnpOld", "localName", "governmentForm", "headOfState", "code2"
	};

	String[] columnNames = {
			"国コード", "国名", "州", "地域", "面積", "独立年", "人口",
			"平均寿命", "国民総生産", "旧 国民総生産", "現地名", "政治体制", "元首", "国コード2"
	};
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>世界の国々</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body lang="ja">
	<form method="get" action="${pageContext.request.contextPath}/table">
		<div id="forms">
			<div>
				項目：
				<select name="column">
					<option value=""></option>
					<%
						for(int i =0; i < columns.length; i++) {
							String currentColumn = columns[i];
							String currentColumnName = columnNames[i];

							if(column != null && currentColumn.equals(column)){
					%>
								<option value="<%= currentColumn %>" selected><%= currentColumnName %></option>
					<%
							} else {
					%>
								<option value="<%= currentColumn %>"><%= currentColumnName %></option>
					<%
							}
						}
					%>
				</select>
			</div>

			<div>
				並び順：
				<%
					if(direction != null && direction.equals("desc")) {
				%>
						<input type="radio" name="direction" value="">昇順
						<input type="radio" name="direction" value="desc" checked>降順
				<%
					} else {
				%>
						<input type="radio" name="direction" value="" checked>昇順
						<input type="radio" name="direction" value="desc">降順
				<%
					}
				%>
			</div>
			<div>
				<input type="submit" value="並び替え" id="sort">
			</div>
		</div>
	</form>

	<table>
		<tr>
			<%
				for(String name : columnNames) {
			%>
					<th><%= name %></th>
			<%
				}
			%>
		</tr>

		<%
			for(Country country : list) {
		%>
		<tr>
			<td><%= country.getCode() %></td>
			<td><%= country.getName() %></td>
			<td><%= country.getContinent() %></td>
			<td><%= country.getRegion() %></td>
			<td><%= country.getSurfaceArea() %></td>
			<td><%= country.getIndepYear() %></td>
			<td><%= country.getPopulation() %></td>
			<td><%= country.getLifeExpectancy() %></td>
			<td><%= country.getGnp() %></td>
			<td><%= country.getGnpOld() %></td>
			<td><%= country.getLocalName() %></td>
			<td><%= country.getGovernmentForm() %></td>
			<td><%= country.getHeadOfState() %></td>
			<td><%= country.getCode2() %></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>