<%--
  Copyright 1997-2008 Day Management AG
  Barfuesserplatz 6, 4001 Basel, Switzerland
  All Rights Reserved.

  This software is the confidential and proprietary information of
  Day Management AG, ("Confidential Information"). You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with Day.

  ==============================================================================

  Default body script.

  Draws an empty HTML body.

  ==============================================================================

--%>
<%@ include file="/libs/foundation/global.jsp" %>
<body>
    <h1>hello</h1>
	<cq:include path="welcome" resourceType="irg/components/global/content/welcome"/>
    <cq:include path="content" script="greg.jsp"/>
</body>