<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 예제</title>
</head>
<body>
	<h1>산술연산</h1>
	<ul>
		<li>10 + 15 = ${10+15 }</li>
		<li>20 - 10 = ${20-10 }</li>
		<li>5 * 12 = ${5*12 }</li>
		<li>20 / 4 = ${20/4 }</li>
		<li>20 / 4 = ${20 div 4 }</li>
		<li>20 % 4 = ${20%4 }</li>
		<li>20 % 4 = ${20 mod 4 }</li>
	</ul>
	
	<h1>비교연산</h1>
	<ul>
		<li>a == a -> ${"a" == "a" }</li>
		<li>a == b -> ${"a" == "b" }</li>
		<li>1 == 1 -> ${1 == 1 }</li>
		<li>1 == 2 -> ${1 == 2 }</li>
		<li>a eq a -> ${"a" eq "a" }</li>
		<li>a eq b -> ${"a" eq "b" }</li>
		<li>1 ne 1 -> ${1 ne 1 }</li>
		<li>1 ne 2 -> ${1 ne 2 }</li>
		
	</ul>
	<h1>비교연산</h1>
	<table border="1">
		<tr>
			<td>+</td>
			<td>-</td>
			<td>*</td>
			<td>/</td>
			<td>%</td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td>div</td>
			<td>mod</td>
			<td></td>
		</tr>
		<tr>
			<td>==</td>
			<td>!=</td>
			<td>>=</td>
			<td><=</td>
			<td>></td>
			<td><</td>
		</tr>
		<tr>
			<td>eq</td>
			<td>ne</td>
			<td>ge</td>
			<td>le</td>
			<td>gt</td>
			<td>lt</td>
		</tr>
		<tr>
			<td>&&</td>
			<td>||</td>
			<td>!</td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>and</td>
			<td>or</td>
			<td>not</td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
</body>
</html>