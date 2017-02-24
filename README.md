# Key-logger
###OVERVIEW
* Java SE based project that allows the server to collect data (keystrokes) secretly 
	from the clients. Each client stores the date locally (in hidden file) and if the local file
	content size exceeds a limit (in Bytes) then the client send the data to the server 
	via web socket using TCP.
<hr></hr>

###DEPENDENCIES
* JNativeHook - https://github.com/kwhat/jnativehook
