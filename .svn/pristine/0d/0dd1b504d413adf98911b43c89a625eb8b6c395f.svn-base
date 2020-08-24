<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="<c:url value = "Views/css/style.css"/>" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<c:url value = "Views/js/user.js"/>"></script>
<title>ユーザ管理</title>
</head>
<body>
	<!-- Begin vung header -->
	<%@include file="header.jsp"%>
	<!-- End vung header -->

	<!-- Begin vung input-->
	<form
		action="<c:choose>
						<c:when
							test="${userInfoEntity.userId == 0}">addUserOK.do?key=${key }</c:when>
						<c:otherwise>editUserOK.do?key=${key }</c:otherwise>
				</c:choose>"
		method="post" name="inputform">
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">
						情報確認<br> 入力された情報をＯＫボタンクリックでＤＢへ保存してください 
					</div>
					<div style="padding-left: 100px;">&nbsp;</div>
				</th>
			</tr>
			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="1" width="70%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left">アカウント名:</td>
								<td align="left">${fn:escapeXml(userInfoEntity.loginName)}</td>
							</tr>
							<tr>
								<td class="lbl_left">グループ:</td>
								<td align="left">${fn:escapeXml(userInfoEntity.groupName)}</td>
							</tr>
							<tr>
								<td class="lbl_left">氏名:</td>
								<td align="left">${fn:escapeXml(userInfoEntity.fullName)}</td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left">${fn:escapeXml(userInfoEntity.fullNameKatana)}</td>
							</tr>
							<tr>
								<td class="lbl_left">生年月日:</td>
								<td align="left">${fn:escapeXml(userInfoEntity.birthday)}</td>
							</tr>
							<tr>
								<td class="lbl_left">メールアドレス:</td>
								<td align="left">${fn:escapeXml(userInfoEntity.email)}</td>
							</tr>
							<tr>
								<td class="lbl_left">電話番号:</td>
								<td align="left">${fn:escapeXml(userInfoEntity.tel)}</td>
							</tr>
							<tr>
								<th colspan="2"><a href="#" onclick="showJapan()">日本語能力</a></th>
							</tr>
							<tr class="japan" id="japan1">
								<td class="lbl_left">資格:</td>
								<td align="left">${fn:escapeXml(userInfoEntity.nameLevel)}</td>
							</tr>
							<tr class="japan" id="japan2">
								<td class="lbl_left">資格交付日:</td>
								<td align="left"><c:if
										test="${userInfoEntity.nameLevel ne '' }">${fn:escapeXml(userInfoEntity.startDate)}</c:if></td>
							</tr>
							<tr class="japan" id="japan3">
								<td class="lbl_left">失効日:</td>
								<td align="left"><c:if
										test="${userInfoEntity.nameLevel ne '' }">${fn:escapeXml(userInfoEntity.endDate)}</c:if></td>
							</tr>
							<tr class="japan" id="japan4">
								<td class="lbl_left">点数:</td>
								<td align="left"><c:if
										test="${userInfoEntity.nameLevel ne '' }">
										<c:if test="${userInfoEntity.total ne '0'}">${fn:escapeXml(userInfoEntity.total)}</c:if>
									</c:if></td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<div style="padding-left: 100px;">&nbsp;</div>
		<!-- Begin vung button -->
		<div style="padding-left: 45px;">
			<table border="0" cellpadding="4" cellspacing="0" width="300px">
				<tr>
					<th width="200px" align="center">&nbsp;</th>
					<td><input class="btn" type="submit" value="OK" /></td>
					<td><a
						href="<c:choose>
						<c:when
							test="${userInfoEntity.userId == 0}">addUserInput.do?type=back&key=${key }</c:when>
						<c:otherwise>editUserInput.do?type=back&id=${userInfoEntity.userId }&key=${key }</c:otherwise>
					</c:choose>"><input
							class="btn" type="button" value="戻る" /> </a></td>
				</tr>
			</table>
			<!-- End vung button -->
	</form>
	<!-- End vung input -->

	<!-- Begin vung footer -->
	<div class="lbl_footer">
		<%@include file="footer.jsp"%>
	</div>
	<!-- End vung footer -->
</body>

</html>