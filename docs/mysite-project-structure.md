# mysite04, 05 Package Structure

<pre>
	|---[main]
			|---[java]
			|		|---[com]
			|				|---[douzone]
			|						|
			|						|---[config]
			|						|		|---[app]
			|						|		|		|---[DBConfig]
			|						|		|		|---[MyBatisConfig]
			|						|		|							
			|						|		|---[web]
			|						|				|---[MVCConfig]
			|						|				|---[SecurityConfig]
			|						|				|---[MessageConfig]
			|						|				|---[FileuploadConfig]
			|						|		
			|						|---mysite]
			|								|---[config]
			|								|		|---[app]
			|								|		|		|---[AppConfig]
			|								|		|---[web]
			|								|				|---[WebConfig]
			|								|
			|								|---[aspecrt]
			|								|---[controller]
			|								|---[exception]
			|								|---[interceptor]
			|								|---[repository]
			|								|---[security]
			|								|---[service]
			|								|---[vo]
			|																
			|																																							
			|---[resource]
					|---[com]
							|---[douzone]
									|---[mysite]
											|---[config]
													|---[app]
													|		|---[jdbc.properties]
													|		|---[mybatis]
													|				|---[configuration]
													|				|---[mappers]
													|						|---board.xml
													|						|---user.xml
													|
													|	
													|---[web]
															|---fileuploadproperties
															|---message
																	|---message_ko.properties
																									
													
</pre>