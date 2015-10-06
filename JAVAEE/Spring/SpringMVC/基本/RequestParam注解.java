	@RequestMapping(value = "/testParams")
	public String testParams(@RequestParam(value = "username") String un,
			@RequestParam(value = "age") int age) {             //这里不传age的话是要报错的
		System.out.println("testParams username:"+un+" ,age:"+age);
		return "login";
	}
	
	@RequestMapping(value = "/testParams")
	public String testParams(@RequestParam(value = "username") String un,
			@RequestParam(value = "age",required=false) Integer age) {   //将基本数据类型改为封装类，required=false
		System.out.println("testParams username:"+un+" ,age:"+age);
		return "login";
	}
	但是
	@RequestMapping(value = "/testParams")
	public String testParams(@RequestParam(value = "username") String un,
			@RequestParam(value = "age",required=false) int age) {   //不传age是要报错的
		System.out.println("testParams username:"+un+" ,age:"+age);
		return "login";
	}
	改为
	@RequestMapping(value = "/testParams")
	public String testParams(@RequestParam(value = "username") String un,
			@RequestParam(value = "age",required=false,defaultValue="0") int age) {   //加一个默认值就不会报错了
		System.out.println("testParams username:"+un+" ,age:"+age);
		return "login";
	}


	
	<br>
	<a href="testParams?username=cpp&age=10">testParam</a>
	<br />