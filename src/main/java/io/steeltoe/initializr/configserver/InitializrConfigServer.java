package io.steeltoe.initializr.configserver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@EnableConfigServer
@SpringBootApplication
public class InitializrConfigServer implements ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(InitializrConfigServer.class);

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(InitializrConfigServer.class, args);
	}

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		BuildProperties buildProperties = null;
		try {
			buildProperties = context.getBean(BuildProperties.class);
		} catch (BeansException e) {
			buildProperties = new BuildProperties(new Properties());
		}

		InputStream pin = getClass().getClassLoader().getResourceAsStream("git.properties");
		Properties gitProperties = new Properties();
		if (pin != null) {
			try {
				gitProperties.load(pin);
				pin.close();
			} catch (IOException e) {
				logger.warn("IO error processing Git properties: {}", e.getLocalizedMessage());
			}
		}
		logger.info("{}, version {} [{}]", buildProperties.getName(), buildProperties.getVersion(),
				gitProperties.getOrDefault("git.commit.id", "unknown"));
		Package springPkg = EnableConfigServer.class.getPackage();
		logger.info("{}, version {}", springPkg.getImplementationTitle(), springPkg.getImplementationVersion());
		logger.info("{}: {}", "spring.cloud.config.server.git.uri",
				env.getProperty("spring.cloud.config.server.git.uri"));
		logger.info("{}: {}", "spring.profiles.active", env.getProperty("spring.profiles.active"));
		logger.info("{}: {}", "spring.cloud.config.server.native.searchLocations",
				env.getProperty("spring.cloud.config.server.native.searchLocations"));
	}

}
