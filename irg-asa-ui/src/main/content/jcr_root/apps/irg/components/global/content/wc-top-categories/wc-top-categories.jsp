<%@include file="/apps/irg/components/global/global.jsp" %>

<%@page import="com.irg.asa.bl.components.generic.entity.GenericConfiguration, 
                com.irg.asa.wcs.domain.Category" %>

<%
sling.getService(WCService.class);
%>

<jsp:useBean id="categoryController" class="com.irg.asa.wcs.controller.CategoryController"></jsp:useBean>
<jsp:useBean id="genericConfiguration" class="com.irg.asa.bl.components.generic.entity.GenericConfiguration">
    <jsp:setProperty name="genericConfiguration" property="request" value="<%= request %>" />
    <jsp:setProperty name="genericConfiguration" property="page" value="<%= currentPage %>" />
    <jsp:setProperty name="genericConfiguration" property="resolver" value="<%= resourceResolver %>" />
    <jsp:setProperty name="genericConfiguration" property="pageManager" value="<%= pageManager %>" />
    <jsp:setProperty name="genericConfiguration" property="pageContext" value="<%= pageContext %>" />
    <jsp:setProperty name="genericConfiguration" property="properties" value="${properties}" />
    <jsp:setProperty name="genericConfiguration" property="slingHelper" value="<%= sling %>" />
</jsp:useBean>

<c:set var="isEditMode" value="<%= (WCMMode.fromRequest(request) == WCMMode.EDIT || WCMMode.fromRequest(request) == WCMMode.DESIGN) %>" />
<c:set var="topCategories" value="<%= categoryController.getTopCategories(genericConfiguration) %>" />

<c:choose>
	<c:when test="${not empty topCategories}">
		<div id="top-cats">
		    <h1>Top categories</h1>
			<ul>
				<c:forEach var="category" items="${topCategories}" varStatus="indexLoop">
					<li>${category.name}</li>
				</c:forEach>
			</ul>
		</div>
	</c:when>
	<c:otherwise>
		<c:if test="${isEditMode}">
			<h1>No top categories found</h1>
		</c:if>
	</c:otherwise>
</c:choose>

   