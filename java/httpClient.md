~~~
<dependency>
	<groupId>org.apache.httpcomponents</groupId>
	<artifactId>httpclient</artifactId>
	<version>4.5.6</version>
</dependency>

get:
CloseableHttpClient httpclient = HttpClients.createDefault();
RequestConfig requestConfig = RequestConfig.custom()
        .setConnectTimeout(5000)   //设置连接超时时间
        .setConnectionRequestTimeout(5000) // 设置请求超时时间
        .setSocketTimeout(5000)
        .setRedirectsEnabled(true)//默认允许自动重定向
        .build();
HttpGet httpGet = new HttpGet("http://localhost:8086/Spring-test/test");
httpGet.setConfig(requestConfig);
try {
	CloseableHttpResponse response1 = httpclient.execute(httpGet);
	System.out.println(response1.getStatusLine().getStatusCode());//200 404
	System.out.println(EntityUtils.toString(response1.getEntity()));//responsebody
	httpclient.close();
} catch (IOException e) {
	e.printStackTrace();
}
~~~
