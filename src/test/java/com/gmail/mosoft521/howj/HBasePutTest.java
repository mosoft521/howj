package com.gmail.mosoft521.howj;

import com.gmail.mosoft521.howj.util.HBaseUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * HBase 测试类
 * Created by babylon on 2016/11/29.
 */
public class HBasePutTest {

    @Before
    public void init() {
//        HBaseUtil.init("zkHost");
        HBaseUtil.init("slaver1:2181,slaver2:2181");
    }

    @Test
    public void testPut() {
        Put put = new Put(Bytes.toBytes("rowKey"));
        put.addColumn(Bytes.toBytes("events"), Bytes.toBytes("severity"), Bytes.toBytes(new Random().nextInt(10) + ""));
        HBase.put("logs", Arrays.asList(new Put[]{put}), true);
    }

    @Test
    public void testGet() {
//        Result result = HBaseUtil.getRow("logs", HBase.generateRowkey("rowKey"));
        Result result = HBaseUtil.getRow("logs", "rowKey".getBytes());
        HBaseUtil.formatRow(result.raw());
    }

    @Test
    public void testCreateTable() {
        try {
            HBaseUtil.createTable("logs", new String[]{"events"}, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
