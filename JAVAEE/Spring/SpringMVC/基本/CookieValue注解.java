CookieValue注解

	@RequestMapping(value = "/testCookieValue")
	public String testRequestHeader(@CookieValue(value = "lastLoginTime") Long time) { //读取cookie值
		System.out.println("testCookieValue："+time);  
		return "login";
	}
	
	
	<script type="text/javascript">
		document.cookie="lastLoginTime="+new Date().getTime();
	</script>
	<a href="testCookieValue">testCookieValue</a>
	<br />
	