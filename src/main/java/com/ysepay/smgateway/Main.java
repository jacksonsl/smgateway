package com.ysepay.smgateway;

import org.apache.log4j.Logger;
import org.restexpress.RestExpress;
import org.restexpress.util.Environment;

import com.ysepay.smgateway.serialization.SerializationProvider;
import com.ysepay.smgateway.utils.SpringContextUtil;

/**
 * 主函数
 * @author sunlei
 * @date 2017年2月21日
 */
public class Main
{
	private static final Logger LOGGER = Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception
	{
		// 设置默认的序列化
		RestExpress.setDefaultSerializationProvider(new SerializationProvider());
		// 加载环境配置
		String[] params = {};
		Configuration config = Environment.load(params, Configuration.class);
		RestExpress server = new RestExpress()
		    .setName(config.getName())// 服务名
		    .setPort(config.getPort());// 服务端口

		Routes.define(server);// 设置服务路由

		if (config.getWorkerCount() > 0)
		{
			server.setIoThreadCount(config.getWorkerCount());
		}

		if (config.getExecutorThreadCount() > 0)
	    {
	    	server.setExecutorThreadCount(config.getExecutorThreadCount());
	    }

		// 初始化spring容器
		SpringContextUtil.initSpringConfig();
		
		mapExceptions(server);
		server.bind();
		LOGGER.info("[短信网关] 服务启动成功.");
		server.awaitShutdown();
	}

	/**
     * @param server
     */
    private static void mapExceptions(RestExpress server)
    {
//    	server
//    	.mapException(ItemNotFoundException.class, NotFoundException.class)
//    	.mapException(DuplicateItemException.class, ConflictException.class)
//    	.mapException(ValidationException.class, BadRequestException.class);
    }
    
}
