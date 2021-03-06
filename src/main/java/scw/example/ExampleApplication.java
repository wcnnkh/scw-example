package scw.example;

import scw.beans.annotation.Bean;
import scw.boot.support.MainApplication;
import scw.db.DB;
import scw.env.Sys;
import scw.mvc.annotation.Controller;
import scw.sqlite.SQLiteDB;
import scw.web.cors.Cors;
import scw.web.cors.CorsRegistry;
import scw.web.resource.DefaultStaticResourceLoader;
import scw.web.resource.StaticResourceLoader;

@Controller
public class ExampleApplication {

	/**
	 * 启动后访问http://localhost:8080/hello
	 * @see ExampleApplication#helloworld()
	 * 
	 * 因为导入了scw-app-admin-web模块，所以可以访问默认的后台管理系统(http://localhost:8080/admin)
	 * @param args
	 */
	public static void main(String[] args) {
		MainApplication.run(ExampleApplication.class, args);
	}
	
	/**
	 * 示例Controller
	 * @return
	 */
	@Controller(value="hello")
	public String helloworld(){
		return "hello world";
	}

	/**
	 * 使用内嵌的sqlite为示例数据库
	 * 
	 * @return
	 */
	@Bean
	public DB getDB() {
		return new SQLiteDB(Sys.env.getWorkPath()
				+ "/scw-app-example.db");
	}

	/**
	 * 注册跨域路径
	 * 
	 * @return
	 */
	@Bean
	public CorsRegistry getCorsRegistry() {
		CorsRegistry corsRegistry = new CorsRegistry();
		corsRegistry.addMapping("/*", Cors.DEFAULT);
		return corsRegistry;
	}
	
	/**
	 * 注册静态资源
	 * @return
	 */
	@Bean
	public StaticResourceLoader getStaticResourceLoader(){
		DefaultStaticResourceLoader resourceLoader = new DefaultStaticResourceLoader();
		resourceLoader.addMapping("/html/", "*.html");
		return resourceLoader;
	}
}
