package com.shixzh.java.interview.solution;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: ZhaoShixiang <br>
 * @date: 2018/10/30 15:04
 */
public class AccessControlByIP {

    private static Map<String, List<Long>> accessDatas = new ConcurrentHashMap<>();

    /**
     * 判断该次请求是否通过
     *
     * @param name
     * @return boolean
     * @throws Exception ex
     * @method: procAccessControl <br>
     * @version 1.0.0 <br>
     * @author: ZhaoShixiang <br>
     * @date: 2018/10/30 17:58
     */
    public boolean procAccessControl(String name) {
        Customer customer = selectCustomerByName(name);
        if (customer.isInWhiteListOrNot()) {
            return true;
        }
        int count = selectAccessCountFromRedis(customer.getIp());
        if (customer.getLevel() == 1) {
            if (count <= 10000) {
                addIpToRedis(customer.getIp());
                return true;
            }
        } else if (customer.getLevel() == 2) {
            if (count <= 2000) {
                addIpToRedis(customer.getIp());
                return true;
            }
        } else if (customer.getLevel() == 3) {
            if (count <= 100) {
                addIpToRedis(customer.getIp());
                return true;
            }
        }
        return false;
    }

    private void addIpToRedis(String ip) {
        // todo
    }

    private int selectAccessCountFromRedis(String ip) {
        return 0;
    }

    private Customer selectCustomerByName(String name) {
        return null;
    }

    public static void main(String[] args) {

    }
}

class Customer {
    private String name;
    private int level;
    private boolean inWhiteListOrNot;
    private String ip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isInWhiteListOrNot() {
        return inWhiteListOrNot;
    }

    public void setInWhiteListOrNot(boolean inWhiteListOrNot) {
        this.inWhiteListOrNot = inWhiteListOrNot;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", inWhiteListOrNot=" + inWhiteListOrNot +
                ", ip='" + ip + '\'' +
                '}';
    }
}