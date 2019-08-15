package com.carl.exportexcle.service;

import com.carl.exportexcle.dao.TaskExecResultDao;
import com.carl.exportexcle.dao.UserInfoDao;
import com.carl.exportexcle.entity.TaskExecResultInfo;
import com.carl.exportexcle.entity.UserInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private TaskExecResultDao taskExecResultDao;

    public void getUserCountByDateAndApp(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException {

        /*String appName = "net.sp.leitingnihuo";
        String beginT = "2019-05-01 00:00:00";
        String endT = "2019-05-02 02:47:19";*/
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat begin = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat end = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat day = new SimpleDateFormat("dd");


        String appName = request.getParameter("appName");
        String beginb = request.getParameter("begin");
        String ende = request.getParameter("end");

        Date parseb = data.parse(beginb);
        Date parsee = data.parse(ende);

        String beginT = begin.format(parseb);
        String endT = end.format(parsee);


        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("用户信息");
        HSSFRow title = sheet.createRow(0);
        HSSFCell title0 = title.createCell(0);
        HSSFCell title1 = title.createCell(1);
        HSSFCell title2 = title.createCell(2);
        HSSFCell title3 = title.createCell(3);
        HSSFCell title4 = title.createCell(4);
        HSSFCell title5 = title.createCell(5);
        HSSFCell title6 = title.createCell(6);
        HSSFCell title7 = title.createCell(7);
        HSSFCell title8 = title.createCell(8);
        /*HSSFCell title9 = title.createCell(9);
        HSSFCell title10 = title.createCell(10);
        HSSFCell title11 = title.createCell(11);
        HSSFCell title12 = title.createCell(12);
        HSSFCell title13 = title.createCell(13);
        HSSFCell title14 = title.createCell(14);
        HSSFCell title15 = title.createCell(15);*/

        title0.setCellValue("日期");
        title1.setCellValue("新增用户");
        title2.setCellValue("成功用户数");
        title3.setCellValue("转化比");
        title4.setCellValue("运营商");
        title5.setCellValue("取任务的用户");
        title6.setCellValue("完成订阅用户");
        title7.setCellValue("转化比例");
        title8.setCellValue("完成offer数");
        /*title9.setCellValue("phone_info_id");
        title10.setCellValue("phone_number");
        title11.setCellValue("privileges");
        title12.setCellValue("ip");
        title13.setCellValue("country");
        title14.setCellValue("create_time");
        title15.setCellValue("recent_hs_time");*/

        Integer beginday = 0;
        Integer endday = 0;
        try {

            beginday = Integer.valueOf(day.format(format.parse(beginT)));
            endday = Integer.valueOf(day.format(format.parse(endT)));

        int days = endday.intValue() - beginday.intValue();
        for (int i = 0;i<= days;i++){
            /*Date b = begin.parse(beginTime);*/
            String format1 = begin.format(format.parse(beginT));
            Date b = begin.parse(format1);

            long be = b.getTime()+60*60*24*1000*i;
            b.setTime(be);
            String beginTime = begin.format(be);

            String format2 = end.format(format.parse(endT));
            Date e = end.parse(format2);
            long en = e.getTime()+60*60*24*1000*i;
            e.setTime(en);
            String endTime = end.format(en);
            String riqi = data.format(b);

            List<UserInfo> userCount = userInfoDao.getUserCountByDateAndApp(appName,beginTime,endTime);
            /*Integer rightCountByDate = userInfoDao.getRightCountByDate(beginTime,endTime);

            Integer taskUserCountByDate = userInfoDao.gotTaskUserCountByDate(beginTime,endTime);*/
            int addusercount = userCount.size();

            ArrayList<UserInfo> userInfos = new ArrayList<>();
            for (UserInfo info : userCount) {
                if(info.getIsLoyalty()==1){
                    userInfos.add(info);
                }
            }
            int getrightcount = userInfos.size();

            List<TaskExecResultInfo> userInfos1 = taskExecResultDao.gotTaskUserCountByDate(beginTime, endTime);
            int gottaskcount = userInfos1.size();
            ArrayList<TaskExecResultInfo> userInfos2 = new ArrayList<>();
            for (TaskExecResultInfo userInfo : userInfos1) {
                if(userInfo.getTaskRunningState()==1){
                    userInfos2.add(userInfo);
                }
            }
            int taskdone = userInfos2.size();
            Integer offerCountByDate = userInfoDao.gotOfferCountByDate(beginTime,endTime);

       /* for (int i = 0; i < list.size(); i++) {
            UserInfo user = list.get(i);*/
            HSSFRow row = sheet.createRow(i + 1);
            HSSFCell cell0 = row.createCell(0);
            HSSFCell cell1 = row.createCell(1);
            HSSFCell cell2 = row.createCell(2);
            HSSFCell cell3 = row.createCell(3);
            HSSFCell cell4 = row.createCell(4);
            HSSFCell cell5 = row.createCell(5);
            HSSFCell cell6 = row.createCell(6);
            HSSFCell cell7 = row.createCell(7);
            HSSFCell cell8 = row.createCell(8);
           /* HSSFCell cell9 = row.createCell(9);
            HSSFCell cell10 = row.createCell(10);
            HSSFCell cell11 = row.createCell(11);
            HSSFCell cell12 = row.createCell(12);
            HSSFCell cell13 = row.createCell(13);
            HSSFCell cell14 = row.createCell(14);
            HSSFCell cell15 = row.createCell(15);
*/
            DecimalFormat df=new DecimalFormat("0.00");

            cell0.setCellValue(riqi);
            cell1.setCellValue(addusercount);
            cell2.setCellValue(getrightcount);
            cell3.setCellValue(df.format((float)getrightcount/addusercount));
            cell4.setCellValue("运营商");
            cell5.setCellValue(gottaskcount);
            cell6.setCellValue(taskdone);
            cell7.setCellValue(df.format((float)taskdone/gottaskcount));
            cell8.setCellValue(offerCountByDate);
           /* cell9.setCellValue(user.getPhoneInfoId());
            cell10.setCellValue(user.getPhoneNumber());
            cell11.setCellValue(user.getPrivileges());
            cell12.setCellValue(user.getIp());
            cell13.setCellValue(user.getCountry());
*/
           /* String creaatetime = format.format(user.getCreateTime());
            String hstime = format.format(user.getCreateTime());
            cell14.setCellValue(creaatetime);
            cell15.setCellValue(hstime);*/
        }
        /*}*/
        } catch (ParseException e) {
            e.printStackTrace();
        }
            String fileName = new String("attachment;filename=新增用户表.xls".getBytes(StandardCharsets.UTF_8),StandardCharsets.ISO_8859_1);
            ServletOutputStream outputStream = response.getOutputStream();
            //清空缓存
            response.reset();
            //定义浏览器响应表头，顺带定义下载名，比如students
            response.setHeader("Content-disposition", fileName);
            //定义下载的类型，标明是excel文件
            response.setContentType("application/vnd.ms-excel");
            //这时候把创建好的excel写入到输出流
            workbook.write(outputStream);
            outputStream.close();
    }


  /*  public void getRightCountByDate(HttpServletResponse response,String beginTime,String endTime) throws IOException{

        List<UserInfo> list = userInfoDao.getRightCountByDate( beginTime, endTime);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("用户信息");
        HSSFRow title = sheet.createRow(0);
        HSSFCell title0 = title.createCell(0);
        HSSFCell title1 = title.createCell(1);
        HSSFCell title2 = title.createCell(2);
        HSSFCell title3 = title.createCell(3);
        HSSFCell title4 = title.createCell(4);
        HSSFCell title5 = title.createCell(5);
        HSSFCell title6 = title.createCell(6);
        HSSFCell title7 = title.createCell(7);
        HSSFCell title8 = title.createCell(8);
        HSSFCell title9 = title.createCell(9);
        HSSFCell title10 = title.createCell(10);
        HSSFCell title11 = title.createCell(11);
        HSSFCell title12 = title.createCell(12);
        HSSFCell title13 = title.createCell(13);
        HSSFCell title14 = title.createCell(14);
        HSSFCell title15 = title.createCell(15);

        title0.setCellValue("用户id");
        title1.setCellValue("用户名");
        title2.setCellValue("app名");
        title3.setCellValue("ch_no");
        title4.setCellValue("sh_v");
        title5.setCellValue("state");
        title6.setCellValue("loyalty");
        title7.setCellValue("session_id");
        title8.setCellValue("session_es_time");
        title9.setCellValue("phone_info_id");
        title10.setCellValue("phone_number");
        title11.setCellValue("privileges");
        title12.setCellValue("ip");
        title13.setCellValue("country");
        title14.setCellValue("create_time");
        title15.setCellValue("recent_hs_time");

        SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd HH:mm:ss");

        for (int i = 0; i < list.size(); i++) {
            UserInfo user = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            HSSFCell cell0 = row.createCell(0);
            HSSFCell cell1 = row.createCell(1);
            HSSFCell cell2 = row.createCell(2);
            HSSFCell cell3 = row.createCell(3);
            HSSFCell cell4 = row.createCell(4);
            HSSFCell cell5 = row.createCell(5);
            HSSFCell cell6 = row.createCell(6);
            HSSFCell cell7 = row.createCell(7);
            HSSFCell cell8 = row.createCell(8);
            HSSFCell cell9 = row.createCell(9);
            HSSFCell cell10 = row.createCell(10);
            HSSFCell cell11 = row.createCell(11);
            HSSFCell cell12 = row.createCell(12);
            HSSFCell cell13 = row.createCell(13);
            HSSFCell cell14 = row.createCell(14);
            HSSFCell cell15 = row.createCell(15);

            cell0.setCellValue(user.getId());
            cell1.setCellValue(user.getName());
            cell2.setCellValue(user.getAppName());
            cell3.setCellValue(user.getChannelNo());
            cell4.setCellValue(user.getShellVersion());
            cell5.setCellValue(user.getState());
            cell6.setCellValue(user.getIsLoyalty());
            cell7.setCellValue(user.getSessionId());
            cell8.setCellValue(user.getSessionEstablishTime());
            cell9.setCellValue(user.getPhoneInfoId());
            cell10.setCellValue(user.getPhoneNumber());
            cell11.setCellValue(user.getPrivileges());
            cell12.setCellValue(user.getIp());
            cell13.setCellValue(user.getCountry());

            String creaatetime = format.format(user.getCreateTime());
            String hstime = format.format(user.getCreateTime());
            cell14.setCellValue(creaatetime);
            cell15.setCellValue(hstime);
        }

        String fileName = new String("attachment;filename=新增用户表.xls".getBytes(StandardCharsets.UTF_8),StandardCharsets.ISO_8859_1);
        ServletOutputStream outputStream = response.getOutputStream();
        //清空缓存
        response.reset();
        //定义浏览器响应表头，顺带定义下载名，比如students
        response.setHeader("Content-disposition", fileName);
        //定义下载的类型，标明是excel文件
        response.setContentType("application/vnd.ms-excel");
        //这时候把创建好的excel写入到输出流
        workbook.write(outputStream);
        outputStream.close();
    }


    public void gotTaskUserCountByDate(HttpServletResponse response,String beginTime,String endTime) throws IOException{

        List<UserInfo> list = userInfoDao.gotTaskUserCountByDate(beginTime, endTime);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("用户信息");
        HSSFRow title = sheet.createRow(0);
        HSSFCell title0 = title.createCell(0);
        HSSFCell title1 = title.createCell(1);
        HSSFCell title2 = title.createCell(2);
        HSSFCell title3 = title.createCell(3);
        HSSFCell title4 = title.createCell(4);
        HSSFCell title5 = title.createCell(5);
        HSSFCell title6 = title.createCell(6);
        HSSFCell title7 = title.createCell(7);
        HSSFCell title8 = title.createCell(8);
        HSSFCell title9 = title.createCell(9);
        HSSFCell title10 = title.createCell(10);
        HSSFCell title11 = title.createCell(11);
        HSSFCell title12 = title.createCell(12);
        HSSFCell title13 = title.createCell(13);
        HSSFCell title14 = title.createCell(14);
        HSSFCell title15 = title.createCell(15);

        title0.setCellValue("用户id");
        title1.setCellValue("用户名");
        title2.setCellValue("app名");
        title3.setCellValue("ch_no");
        title4.setCellValue("sh_v");
        title5.setCellValue("state");
        title6.setCellValue("loyalty");
        title7.setCellValue("session_id");
        title8.setCellValue("session_es_time");
        title9.setCellValue("phone_info_id");
        title10.setCellValue("phone_number");
        title11.setCellValue("privileges");
        title12.setCellValue("ip");
        title13.setCellValue("country");
        title14.setCellValue("create_time");
        title15.setCellValue("recent_hs_time");

        SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd HH:mm:ss");

        for (int i = 0; i < list.size(); i++) {
            UserInfo user = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            HSSFCell cell0 = row.createCell(0);
            HSSFCell cell1 = row.createCell(1);
            HSSFCell cell2 = row.createCell(2);
            HSSFCell cell3 = row.createCell(3);
            HSSFCell cell4 = row.createCell(4);
            HSSFCell cell5 = row.createCell(5);
            HSSFCell cell6 = row.createCell(6);
            HSSFCell cell7 = row.createCell(7);
            HSSFCell cell8 = row.createCell(8);
            HSSFCell cell9 = row.createCell(9);
            HSSFCell cell10 = row.createCell(10);
            HSSFCell cell11 = row.createCell(11);
            HSSFCell cell12 = row.createCell(12);
            HSSFCell cell13 = row.createCell(13);
            HSSFCell cell14 = row.createCell(14);
            HSSFCell cell15 = row.createCell(15);

            cell0.setCellValue(user.getId());
            cell1.setCellValue(user.getName());
            cell2.setCellValue(user.getAppName());
            cell3.setCellValue(user.getChannelNo());
            cell4.setCellValue(user.getShellVersion());
            cell5.setCellValue(user.getState());
            cell6.setCellValue(user.getIsLoyalty());
            cell7.setCellValue(user.getSessionId());
            cell8.setCellValue(user.getSessionEstablishTime());
            cell9.setCellValue(user.getPhoneInfoId());
            cell10.setCellValue(user.getPhoneNumber());
            cell11.setCellValue(user.getPrivileges());
            cell12.setCellValue(user.getIp());
            cell13.setCellValue(user.getCountry());

            String creaatetime = format.format(user.getCreateTime());
            String hstime = format.format(user.getCreateTime());
            cell14.setCellValue(creaatetime);
            cell15.setCellValue(hstime);
        }

        String fileName = new String("attachment;filename=新增用户表.xls".getBytes(StandardCharsets.UTF_8),StandardCharsets.ISO_8859_1);
        ServletOutputStream outputStream = response.getOutputStream();
        //清空缓存
        response.reset();
        //定义浏览器响应表头，顺带定义下载名，比如students
        response.setHeader("Content-disposition", fileName);
        //定义下载的类型，标明是excel文件
        response.setContentType("application/vnd.ms-excel");
        //这时候把创建好的excel写入到输出流
        workbook.write(outputStream);
        outputStream.close();
    }


    public void gotOfferCountByDate(HttpServletResponse response,String beginTime,String endTime) throws IOException{
        List<UserInfo> list = userInfoDao.gotOfferCountByDate(beginTime, endTime);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("用户信息");
        HSSFRow title = sheet.createRow(0);
        HSSFCell title0 = title.createCell(0);
        HSSFCell title1 = title.createCell(1);
        HSSFCell title2 = title.createCell(2);
        HSSFCell title3 = title.createCell(3);
        HSSFCell title4 = title.createCell(4);
        HSSFCell title5 = title.createCell(5);
        HSSFCell title6 = title.createCell(6);
        HSSFCell title7 = title.createCell(7);
        HSSFCell title8 = title.createCell(8);
        HSSFCell title9 = title.createCell(9);
        HSSFCell title10 = title.createCell(10);
        HSSFCell title11 = title.createCell(11);
        HSSFCell title12 = title.createCell(12);
        HSSFCell title13 = title.createCell(13);
        HSSFCell title14 = title.createCell(14);
        HSSFCell title15 = title.createCell(15);

        title0.setCellValue("用户id");
        title1.setCellValue("用户名");
        title2.setCellValue("app名");
        title3.setCellValue("ch_no");
        title4.setCellValue("sh_v");
        title5.setCellValue("state");
        title6.setCellValue("loyalty");
        title7.setCellValue("session_id");
        title8.setCellValue("session_es_time");
        title9.setCellValue("phone_info_id");
        title10.setCellValue("phone_number");
        title11.setCellValue("privileges");
        title12.setCellValue("ip");
        title13.setCellValue("country");
        title14.setCellValue("create_time");
        title15.setCellValue("recent_hs_time");

        SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd HH:mm:ss");

        for (int i = 0; i < list.size(); i++) {
            UserInfo user = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            HSSFCell cell0 = row.createCell(0);
            HSSFCell cell1 = row.createCell(1);
            HSSFCell cell2 = row.createCell(2);
            HSSFCell cell3 = row.createCell(3);
            HSSFCell cell4 = row.createCell(4);
            HSSFCell cell5 = row.createCell(5);
            HSSFCell cell6 = row.createCell(6);
            HSSFCell cell7 = row.createCell(7);
            HSSFCell cell8 = row.createCell(8);
            HSSFCell cell9 = row.createCell(9);
            HSSFCell cell10 = row.createCell(10);
            HSSFCell cell11 = row.createCell(11);
            HSSFCell cell12 = row.createCell(12);
            HSSFCell cell13 = row.createCell(13);
            HSSFCell cell14 = row.createCell(14);
            HSSFCell cell15 = row.createCell(15);

            cell0.setCellValue(user.getId());
            cell1.setCellValue(user.getName());
            cell2.setCellValue(user.getAppName());
            cell3.setCellValue(user.getChannelNo());
            cell4.setCellValue(user.getShellVersion());
            cell5.setCellValue(user.getState());
            cell6.setCellValue(user.getIsLoyalty());
            cell7.setCellValue(user.getSessionId());
            cell8.setCellValue(user.getSessionEstablishTime());
            cell9.setCellValue(user.getPhoneInfoId());
            cell10.setCellValue(user.getPhoneNumber());
            cell11.setCellValue(user.getPrivileges());
            cell12.setCellValue(user.getIp());
            cell13.setCellValue(user.getCountry());

            String creaatetime = format.format(user.getCreateTime());
            String hstime = format.format(user.getCreateTime());
            cell14.setCellValue(creaatetime);
            cell15.setCellValue(hstime);
        }

        String fileName = new String("attachment;filename=新增用户表.xls".getBytes(StandardCharsets.UTF_8),StandardCharsets.ISO_8859_1);
        ServletOutputStream outputStream = response.getOutputStream();
        //清空缓存
        response.reset();
        //定义浏览器响应表头，顺带定义下载名，比如students
        response.setHeader("Content-disposition", fileName);
        //定义下载的类型，标明是excel文件
        response.setContentType("application/vnd.ms-excel");
        //这时候把创建好的excel写入到输出流
        workbook.write(outputStream);
        outputStream.close();
    }
*/






}
