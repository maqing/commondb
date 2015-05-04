
<%@page import="java.net.URLEncoder"%><%
 String going_to = (String)session.getAttribute("GOING_TO");
 
 if(going_to != null) {
 // response.sendRedirect(going_to);
  }
 %>
 <script type="text/javascript">
 url ="<%=going_to%>";
 url = encodeURI(url);
 <% if (!going_to.equals("")) { %>
 window.location=url;
 <% } %>
 </script>
 