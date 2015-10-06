@RequestMapping(value = "/testRequestHeader")  基本用法同RequestParam
	public String testRequestHeader(@RequestHeader(value = "Accept-Language") String la) {
		System.out.println("testRequestHeader Accept Language:"+la);
		return "login";
	}