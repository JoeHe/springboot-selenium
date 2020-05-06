package com.hjs.automation.springBootSelenium.utils;


import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;


public class YmlUtils {

    private final static DumperOptions OPTIONS = new DumperOptions();

    static {
        //将默认读取的方式设置为块状读取
        OPTIONS.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    }

    public static void addIntoYml(File dest, String key, Object value) throws IOException {
        Yaml yaml = new Yaml(OPTIONS);
        //载入当前yml文件
        LinkedHashMap<String, Object> dataMap = yaml.load(new FileReader(dest));
        //如果yml内容为空,则会引发空指针异常,此处进行判断
        if (null == dataMap) {
            dataMap = new LinkedHashMap<>();
        }
        dataMap.put(key, value);
        //将数据重新写回文件
        yaml.dump(dataMap, new FileWriter(dest));
    }

    public static Object getFromYml(File source, String key) throws IOException {
        Yaml yaml = new Yaml(OPTIONS);
        //载入文件
        LinkedHashMap<String, Object> dataMap = yaml.load(new FileReader(source));
        //获取当前key下的值(如果存在多个节点,则value可能为map,自行判断)
        return dataMap.get(key);
    }

}
