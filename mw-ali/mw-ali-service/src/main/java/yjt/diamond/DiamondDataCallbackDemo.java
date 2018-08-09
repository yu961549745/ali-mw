package yjt.diamond;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedDataBinder;

import com.alibaba.boot.diamond.annotation.DiamondListener;
import com.alibaba.boot.diamond.listener.DiamondDataCallback;
import org.springframework.boot.json.GsonJsonParser;

/**
 * @author 鱼泡
 */
@DiamondListener(dataId = "yjt_diamond_test", groupId = "yjt")
public class DiamondDataCallbackDemo implements DiamondDataCallback {

    private ConfigBean configBean = new ConfigBean();
    private RelaxedDataBinder binder = new RelaxedDataBinder(configBean);
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfigBean getConfigBean() {
        return configBean;
    }

    @Override
    public void received(String data) {
        try {
            Properties properties = new Properties();
            properties.load(new StringReader(data));
            System.err.println("received from diamond listener: " + properties);
            binder.bind(new MutablePropertyValues(properties));
            System.err.println(gson.toJson(configBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
