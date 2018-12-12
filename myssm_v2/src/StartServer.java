import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StartServer {
    
    public void generator() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("C:\\IDEA\\JavaEE\\myssm_v2\\resources\\generator.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
        System.out.println("生成文件成功");
    }

    public static void main(String[] args) {
        try {
            StartServer generatorSqlmap = new StartServer();
            generatorSqlmap.generator();
        } catch (Exception e) {
            System.out.println("使用mybatis逆向工程生成失败");
            System.out.println("==========================");
            e.printStackTrace();
        }

    }

    


}
