package com.dnp.web.controller;

import java.io.*;
import java.util.*;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dnp.util.Def;
import com.dnp.util.JsonUtil;
import com.dnp.web.service.WD_ImplementManageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Auther Tao
 * @date:2016/10/11 15:56
 * 上传
 */

@Controller
@RequestMapping("/WD_Uploading")
public class WD_Uploading {
    @Autowired
    WD_ImplementManageService wd_implementManageService;
//    @Resource
//    Map<String, String> deviceInfoMap;

    @RequestMapping(value = "/fileupload", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String upload(HttpServletRequest request, Model model) throws IOException, ServletException {

        HashMap<String, Object> ret = new HashMap<>();
        //获取id
        try {
            int numImplementID = Integer.parseInt(request.getParameter("numImplementID"));
            // String photo = request.getParameter("photo");
            //判断 是否有url----有删除
            String urls = wd_implementManageService.getuploadUrl(numImplementID);
            if (urls !=null && !urls.equals("")) {
                System.out.println(urls);
                File uploadurl = new File(urls);
                String oldurl = uploadurl.toString();
                oldurl = oldurl.replaceAll("//","\\\\");
                // oldurl = oldurl.replaceAll("'","");

                new File(oldurl).delete();
            }


            String ctxPath = "/resources/"+numImplementID;
            File file = new File(ctxPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            Collection<Part> parts = request.getParts();
            int id = 0;
            if (parts != null && !"".equals(parts)) {

                for (Part part : parts) {


                        String fileName = getFileName(part);
                        if (!fileName.contains("; name=\"numImplementID")&& !fileName.contains("; name=\"photo ")){
                          //; name="numImplementID           ; name="photo     ; name="photo   ; name="photo

                            File Files = new File(file + "/" + fileName);
                            String url = Files.toString();

                            System.out.print(url);
                            InputStream inputStream = part.getInputStream();
                            FileOutputStream outputStream = new FileOutputStream(Files);
                            IOUtils.copy(inputStream, outputStream);//将两个流对接
                            url = url.replaceAll("\\\\","//");
                            System.out.println(url);
                            // String varplan="'"+url+"'";
                            wd_implementManageService.updateUrl(url,numImplementID);


                            //关闭资源
                            inputStream.close();
                            outputStream.close();
                        }

                }
            }
            ret.put("type", Def.AlertType.success);
            ret.put("message", "操作成功");
        } catch (NumberFormatException e) {
            ret.put("type", Def.AlertType.alert);
            ret.put("message", "操作失败");
            e.printStackTrace();
        } catch (IOException e) {
            ret.put("type", Def.AlertType.alert);
            ret.put("message", "操作失败");
            e.printStackTrace();
        } catch (ServletException e) {
            ret.put("type", Def.AlertType.alert);
            ret.put("message", "操作失败");
            e.printStackTrace();
        } finally {
            return JsonUtil.toJson(ret);
        }

        //把你要的数据放进去，key 和 value，直接返回map，主要<span style="font-size: 1em; line-height: 1.5;">@ResponseBody，直接返回的是json</span>



    }
    /**
     * @Auther Tao
     * @date:2016/10/11 11:17
     * 获取上传文件的名称
     */
    private String getFileName(Part part) {

        // UUID uuid = UUID.randomUUID();
        // System.out.println(uuid);
        String header = part.getHeader("Content-Disposition");
        String submittedFileName = part.getSubmittedFileName();
        String fileName = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
        header.lastIndexOf("\"");
        return fileName;
    }
    /**
     * @Auther Tao  下载
     * @date:2016/10/13 11:40
     */

    @RequestMapping(value = "/download")
    public void download(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {

        int numImplementID = Integer.parseInt(request.getParameter("numImplementID"));

        //判断 是否有url----
        String urls = wd_implementManageService.getuploadUrl(numImplementID);
        //if(s == null || s.length() <= 0);
        if (urls ==null || urls.length()<=0) {
            return ;
        }
        String temp =urls;
        temp.replaceAll("//","/");
        String fileName=temp.substring(temp.lastIndexOf("/")+1);//名字
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        response.setHeader( "Content-Disposition", "attachment;filename=" + new String( fileName.getBytes("utf-8"), "ISO8859-1" ) );
        try {
            InputStream inputStream = new FileInputStream(temp.replaceAll("//","/"));

            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            // 这里主要关闭。
            os.close();

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  返回值要注意，要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
    }



}
