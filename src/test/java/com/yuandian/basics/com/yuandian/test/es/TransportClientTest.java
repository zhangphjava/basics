package com.yuandian.basics.com.yuandian.test.es;

import com.sun.org.apache.xml.internal.security.utils.resolver.implementations.ResolverLocalFilesystem;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;

public class TransportClientTest {

    /**
     *
     */
    private TransportClient client;

    private final static String article="article";
    private final static String content="content";
    private static String host = "192.168.1.98";
    private static int port = 9300;
    private static Settings settings = null;
    public static Settings indexSettings = null;

    String indexName = "detail";

    public void setSettings(){
        settings = Settings.builder()
                .put("cluster.name", "bpm-database")
                .build();
    }

    public TransportClient getClient(){
        try {
            this.initClient();
        }catch (Exception e ){
            e.printStackTrace();
        }
        return this.client;
    }

    @Before
    public void setIndexSettings(){
        indexSettings = Settings.builder()
                .put("number_of_replicas", "0") //副本数
                .put("number_of_shards", "2") //分片数
                .put("max_result_window", "100000")//返回最大记录数
                .put("refresh_interval", "30s")//刷新时间
                .put("routing.allocation.total_shards_per_node","4") //单节点最大分片数
                .build();
    }

    @Before
    public void initClient() throws Exception{
        setSettings();
        if (settings != null){
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));
        }


    }

//    @After
    public void closeClient(){
        if(client != null){
            client.close();
        }
    }

    @Test
    public void testCreateIndex(){
        CreateIndexRequestBuilder indexRequestBuilder = client.admin().indices().prepareCreate(indexName);
        indexRequestBuilder.setSettings(indexSettings);
        XContentBuilder mapping = getMappings();
        indexRequestBuilder.addMapping("detail",mapping);
        indexRequestBuilder.execute().actionGet();
        System.out.println("完成");
    }

    public XContentBuilder getMappings(){
        XContentBuilder mapping = null;
        try {
            mapping = XContentFactory.jsonBuilder().startObject()
                    .startObject("detail")
                    .startObject("_all").field("enabled",false).endObject()
                    .startObject("properties")
                    .startObject("id").field("index",true).field("type","long").endObject()
                    .startObject("dealValidity").field("index",true).field("type","integer").endObject()
                    .startObject("sessionId").field("type","keyword").endObject()
                    .startObject("userIndetify").field("type","keyword").endObject()
                    .startObject("systemId").field("type","keyword").endObject()
                    .startObject("systemname").field("type","keyword").endObject()
                    .startObject("businessProcessCode").field("type","keyword").endObject()
                    .startObject("businessCode").field("type","keyword").endObject()
                    .startObject("businessName").field("type","keyword").endObject()
                    .endObject().endObject().endObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return mapping;
    }

    /***
     * 插入数据
     */
    public void insertData(){

    }

}
