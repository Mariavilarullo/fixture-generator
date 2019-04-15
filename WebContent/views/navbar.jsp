
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/navbarStyle.css" />
<nav class="navbar navbar-inverse" color="black">
  <div class="container-fluid">
    <div class="navbar-header">
     	<img class="logo" alt="logo" src='<c:url value="/img/logo.jpeg" />' style="width: 50px;">
    </div>
    <ul class="nav navbar-nav">
          <li><a href='<c:url value="/home.htm" />'>Home</a></li>
  		  <li><a href='<c:url value="/login.htm" />'><fmt:message key="label.login" /></a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <c:if test="${(not empty user.username)}"><li style="float:right"><a href='<c:url value="/logout.htm" />'><span class="glyphicon glyphicon-log-out"></span> Log out</a></li></c:if>
   		<c:if test="${(not empty user.username)}"><li style="float:right"><a href='<c:url value="/account.htm" />'><span class="glyphicon glyphicon-user"></span> <fmt:message key="label.account" /></a></li></c:if>        
    </ul>
  </div>
</nav>
