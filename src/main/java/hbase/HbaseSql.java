package hbase;

import utils.DateUtils;

import java.io.*;

/**
 * @author H
 * @desc   输出加密后的sql到指定目录供hbase查询
 * @date 2018/3/22
 *
 */
public class HbaseSql {

    public static void main(String[] args) throws IOException {

        //C:\Users\Administrator\Desktop\phone_no.txt
        String path1="C:\\Users\\Administrator\\Desktop\\phone_no.txt";
        String inPath=path1;
        //C:\Users\Administrator\Desktop\phone_no1.txt
        String path2="C:\\Users\\Administrator\\Desktop\\phone_no1.txt";
        String outPath=path2;

        /**
         * reader 读取文件到fileWriter缓冲区方便操作
         */
        FileReader reader= new FileReader(inPath);

        FileWriter fileWriter= new FileWriter(outPath);
        /**\
         * bw 文件到br
         */
        BufferedWriter  bw = new BufferedWriter(fileWriter);

        BufferedReader br= new BufferedReader(reader);

        try {
            while (br.readLine()!=null){
                String phone_no=br.readLine();
                    /**
                     *去掉phone_no的空格
                     */
                    String phoneNoTrim=phone_no.trim();
                System.out.println(phoneNoTrim);
                    try {
                        /**
                         *对时间，电话号码进行DES加密
                         */
                        String startTime=DateUtils.getTime(5,-5);
                        String endTime=DateUtils.getTime(5,-3);
                        String month=startTime.substring(0,6);

                        String md5PhoneNo=new MD5RowKeyGenerator().generatePrefix(phoneNoTrim);

                        StringBuffer str1= new StringBuffer();
                        String idStart=str1.append(md5PhoneNo).append(phoneNoTrim).append(startTime).toString();

                        StringBuffer str2=new StringBuffer();
                        String idEnd=str2.append(md5PhoneNo).append(phoneNoTrim).append(endTime).toString();

                       String sql = "select  count(1) as GROUPCOUNT  from CD_GPRS_"+month+"  where  ID>= '" + idStart + "' and ID<= '" + idEnd + "' |sqlline.py";

                        bw.write(sql);
                        bw.newLine();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bw.flush();
        bw.close();
        reader.close();
        br.close();
    }
}
