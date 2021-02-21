package com.yuandian.basics.com.yuandian.test.es;

import com.yuandian.basics.entity.BusinessDetails;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EsBulkTest {

    /***
     *
     */
    public BulkProcessor getBulkProcessor(TransportClient client){
        BulkProcessor bulkProcessor = BulkProcessor.builder(
                client,
                new BulkProcessor.Listener() {
                    @Override
                    public void beforeBulk(long executionId,
                                           BulkRequest request) {
                        System.out.println("批量操作开始！");
                    }

                    @Override
                    public void afterBulk(long executionId,
                                          BulkRequest request,
                                          BulkResponse response) {
                        response.status();
                        System.out.println("批量操作结束！"); }

                    @Override
                    public void afterBulk(long executionId,
                                          BulkRequest request,
                                          Throwable failure) {
                        System.out.println("批量操作结束---！"); }
                })
                .setBulkActions(100)
                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
                .setFlushInterval(TimeValue.timeValueSeconds(5))
                .setConcurrentRequests(1)
                .setBackoffPolicy(
                        BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                .build();
        return bulkProcessor;
    }

    /**
     * 测试批量插入数据
     */
    @Test
    public void testBulkData(){
        TransportClientTest clientTest = new TransportClientTest();
        TransportClient client = clientTest.getClient();
        BulkProcessor bulkProcessor = this.getBulkProcessor(client);
        for (Long i =0L; i<1000L; i++){
            BusinessDetails data = new BusinessDetails();
            data.setId(i);
            data.setBusinessCode("busi_code"+i);
            data.setBusinessName("话费查询");
            data.setBusinessProcessCode("1003"+i);
            data.setDealValidity(Integer.valueOf(i.toString()));
            data.setSessionId("12313");
            data.setSystemId("1231");
            data.setSystemname("营业厅网台");
            data.setUserIndetify("userid001");
            Map<String,Object> map=obj2Map(data);
            bulkProcessor.add(new IndexRequest("detail","body").source(map));
        }
        bulkProcessor.flush();
        bulkProcessor.close();
        System.out.println("123");

    }


    private  Map<String, Object> obj2Map(Object obj) {

        Map<String, Object> map = new HashMap<String, Object>();
        // System.out.println(obj.getClass());
        // 获取f对象对应类中的所有属性域
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            varName = varName.toLowerCase();//将key置为小写，默认为对象的属性
            try {
                // 获取原来的访问控制权限
                boolean accessFlag = fields[i].isAccessible();
                // 修改访问控制权限
                fields[i].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                Object o = fields[i].get(obj);
                if (o != null)
                    map.put(varName, o.toString());
                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return map;
    }
}
