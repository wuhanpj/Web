package netty.time.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import netty.time.Web.NettyClient.TimeClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner
{
	@Autowired
	private TimeClient tc;
	
    public static void main( String[] args )
    {
    	// 连接Server
    	SpringApplication.run(App.class, args);
    }
    
	@Override
	public void run(String... args) throws Exception {
		tc.startChannel();
	}
    
}
