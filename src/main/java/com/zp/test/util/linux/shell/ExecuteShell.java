package com.zp.test.util.linux.shell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * java执行linux脚本
 * 
 * test.sh
 * echo $1
 * echo $2
 * chmod 777 /路径/test.sh   赋予脚本可执行权限
 * @author lenovo
 *
 */
public class ExecuteShell {

    private static final String DIR = "/usr/local";

    public static void main(String[] args) throws Exception {
        ProcessBuilder pb =
                //参数是需要程序和参数
                new ProcessBuilder(DIR + "/" + "test.sh", DIR, "test1231");
        Process ps = pb.start();
        System.out.println("开始执行脚本文件....");
        try {
            ps.waitFor(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("执行脚本超时100秒....");
        }
        //获取输出
        BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        ps.destroyForcibly();
    }

}
