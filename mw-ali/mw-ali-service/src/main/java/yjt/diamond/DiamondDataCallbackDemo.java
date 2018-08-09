package yjt.diamond;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedDataBinder;

import com.alibaba.boot.diamond.annotation.DiamondListener;
import com.alibaba.boot.diamond.listener.DiamondDataCallback;

/**
 * 通过 @DiamondListener注解，监听相关的配置项
 * 详见http://gitlab.alibaba-inc.com/middleware-container/pandora-boot/wikis/spring-boot-diamond
 *
 * @author chengxu
 */
@DiamondListener(dataId = "com.taobao.middleware:configFromListener.properties")
public class DiamondDataCallbackDemo implements DiamondDataCallback {

    @Autowired
    private ConfigBean configBean;

    private String dataCahe;

    public String getReceivedData() {
        return dataCahe;
    }

    @Override
    public void received(String data) {
        try {
            dataCahe = data;
            Properties properties = new Properties();
            properties.load(new ByteArrayInputStream(data.getBytes()));
            System.out.println("received from diamond listener: " + properties);

            // 把properties的值注入到ConfigBean里
            new RelaxedDataBinder(configBean).bind(new MutablePropertyValues(properties));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
