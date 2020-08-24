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
							test="${userInfoEntity.userId == 0}">addUserValidate.do?type=validate</c:when>
						<c:otherwise>editUserValidate.do?type=validate&id=${userInfoEntity.userId}</c:otherwise>
					</c:choose>"
		method="post" name="inputform">
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">会員情報編集</div>
				</th>
			</tr>
			<c:forEach items="${listError}" var="error">
				<tr>
					<td class="errMsg">
						<div style="padding-left: 120px">${error}&nbsp;</div>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="0" width="100%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left"><font color="red">*</font> アカウント名:</td>
								<td align="left"><input class="txBox" type="text"
									name="loginName"
									<c:if test="${userInfoEntity.userId != 0}">readonly</c:if>
									value="${fn:escapeXml(userInfoEntity.loginName)}" size="15"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> グループ:</td>
								<td align="left"><select name="groupId">
										<option value="0">選択してください</option>
										<c:forEach items="${listMstGroup}" var="group">
											<option value="${group.getGroupId() }"
												<c:if test="${userInfoEntity.groupId == group.groupId}">selected</c:if>>
												${group.getGroupName() }</option>
										</c:forEach>
								</select> <span>&nbsp;&nbsp;&nbsp;</span></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="fullName"
									value="${fn:escapeXml(userInfoEntity.fullName)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="fullNameKatana"
									value="${fn:escapeXml(userInfoEntity.fullNameKatana)}"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 生年月日:</td>
								<c:set var="birthday"
									value="${fn:split(userInfoEntity.birthday, '/')}"></c:set>
								<td align="left"><select name="yearofbirth"
									id="yearofbirth"
									onchange="changeYear('yearofbirth', 'monthofbirth', 'dayofbirth')">
										<c:forEach items="${listYear}" var="year">
											<option value="${year }"
												<c:if test="${year ==  birthday[0]}">selected</c:if>>${year }</option>
										</c:forEach>
								</select>年 <select name="monthofbirth" id="monthofbirth"
									onchange="changeMonth(this, 'yearofbirth', 'dayofbirth');">
										<c:forEach items="${listMonth}" var="month">
											<option value="${month }"
												<c:if test="${month ==  birthday[1]}">selected</c:if>>${month }</option>
										</c:forEach>
								</select>月 <select name="dayofbirth" id="dayofbirth">
										<c:forEach items="${listDay}" var="day">
											<option value="${day }" id="dayofbirth${day }"
												<c:if test="${day ==  birthday[2]}">selected</c:if>>${day }</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> メールアドレス:</td>
								<td align="left"><input class="txBox" type="text"
									name="email" value="${fn:escapeXml(userInfoEntity.email)}"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font>電話番号:</td>
								<td align="left"><input class="txBox" type="text"
									name="tel" value="${fn:escapeXml(userInfoEntity.tel)}"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<c:if test="${userInfoEntity.userId == 0}">
								<tr>
									<td class="lbl_left"><font color="red">*</font> パスワード:</td>
									<td align="left"><input class="txBox" type="password"
										name="password" value="" size="30"
										onfocus="this.style.borderColor='#0066ff';"
										onblur="this.style.borderColor='#aaaaaa';" /></td>
								</tr>
								<tr>
									<td class="lbl_left">パスワード（確認）:</td>
									<td align="left"><input class="txBox" type="password"
										name="passwordConfirm" value="" size="30"
										onfocus="this.style.borderColor='#0066ff';"
										onblur="this.style.borderColor='#aaaaaa';" /></td>
								</tr>
							</c:if>
							<tr>
								<th align="left" colspan="2"><a href="#"
									onclick="showJapan()">日本語能力</a></th>
							</tr>
							<tr class="japan" id="japan1">
								<td class="lbl_left">資格:</td>
								<td align="left"><select name="codeLevel">
										<option value="">選択してください</option>
										<c:forEach items="${listMstJapan}" var="mstJapan">
											<option value="${mstJapan.getCodeLevel() }"
												<c:if test="${userInfoEntity.codeLevel == mstJapan.getCodeLevel()}">selected</c:if>>
												${mstJapan.getNameLevel() }</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr class="japan" id="japan2">
								<td class="lbl_left">資格交付日:</td>
								<c:set var="startDate"
									value="${fn:split(userInfoEntity.startDate, '/')}"></c:set>
								<td align="left"><select name="startyear" id="startyear"
									onchange="changeYear('startyear', 'startmonth', 'startday')">
										<c:forEach items="${listYear}" var="year">
											<option value="${year }"
												<c:if test="${year ==  startDate[0]}">selected</c:if>>${year }</option>
										</c:forEach>
								</select>年 <select name="startmonth" id="startmonth"
									onchange="changeMonth(this, 'startyear', 'startday');">
										<c:forEach items="${listMonth}" var="month">
											<option value="${month }"
												<c:if test="${month ==  startDate[1]}">selected</c:if>>${month }</option>
										</c:forEach>
								</select>月 <select name="startday" id="startday">
										<c:forEach items="${listDay}" var="day">
											<option value="${day }" id="startday${day }"
												<c:if test="${day ==  startDate[2]}">selected</c:if>>${day }</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr class="japan" id="japan3">
								<td class="lbl_left">失効日:</td>
								<c:set var="endDate"
									value="${fn:split(userInfoEntity.endDate, '/')}"></c:set>
								<td align="left"><select name="endyear" id="endyear"
									onchange="changeYear('endyear', 'endmonth', 'endday')">
									<option value="${listYear[0]}">${listYear[0]}</option>
										<c:forEach items="${listYear}" var="year">
											<option value="${year +1}"
												<c:if test="${(year+1) ==  endDate[0]}">selected</c:if>>${year +1}</option>
										</c:forEach>
								</select>年 <select name="endmonth" id="endmonth"
									onchange="changeMonth(this, 'endyear', 'endday');">
										<c:forEach items="${listMonth}" var="month">
											<option value="${month }"
												<c:if test="${month ==  endDate[1]}">selected</c:if>>${month }</option>
										</c:forEach>
								</select>月 <select name="endday" id="endday">
										<c:forEach items="${listDay}" var="day">
											<option value="${day }" id="endday${day }"
												<c:if test="${day ==  endDate[2]}">selected</c:if>>${day }</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr class="japan" id="japan4">
								<td class="lbl_left">点数:</td>
								<td align="left"><input class="txBox" type="text"
									name="total"
									value="<c:if test="${userInfoEntity.total ne '0'}">${fn:escapeXml(userInfoEntity.total)}</c:if>"
									size="15" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
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
					<td><input class="btn" type="submit" value="確認" /></td>
					<td><a
						href="<c:choose>
						<c:when
							test="${userInfoEntity.userId == 0}">listUser.do?type=back</c:when>
						<c:otherwise>viewDetailUser.do?id=${userInfoEntity.userId}</c:otherwise>
					</c:choose>
					"><input
							class="btn" type="button" value="戻る" /></a></td>
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