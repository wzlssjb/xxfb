package com.shengvideo;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.shengvideo.datasources.DynamicDataSourceConfig;
import com.shengvideo.modules.push.server.listener.ServerExceptionListener;
import com.shengvideo.modules.push.service.ClientManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import com.shengvideo.modules.push.server.initializer.*;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
public class AdApplication extends SpringBootServletInitializer {

	@Value("${push.server.port}")
	private Integer port;

	@Value("${push.server.hostName}")
	private String hostName;

	@Autowired
	private ClientManage clientManage;

	public static void main(String[] args) {
		SpringApplication.run(AdApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AdApplication.class);
	}

	@Bean
	public SocketIOServer socketIOServer()
	{
		Configuration config = new Configuration();
		config.setPort(port);
		config.setHostname(hostName);
		config.setSocketConfig(new SocketConfig());
		config.setWorkerThreads(100);
		config.setAuthorizationListener(data -> true);
		config.setExceptionListener(new ServerExceptionListener(clientManage));
		SocketIOServer server  = new SocketIOServer(config);
		CustomSocketIOChannelInitializer customSocketIOChannelInitializer = new CustomSocketIOChannelInitializer(config);
		server.setPipelineFactory(customSocketIOChannelInitializer);
		return server;
	}
//	private CorsConfiguration buildConfig() {
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.addAllowedOrigin("*");
//		corsConfiguration.addAllowedHeader("*");
//		corsConfiguration.addAllowedMethod("*");
//		return corsConfiguration;
//	}

	/**
	 * 跨域过滤器
	 * @return
	 */
//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", buildConfig()); // 4
//		return new CorsFilter(source);
//	}
}
