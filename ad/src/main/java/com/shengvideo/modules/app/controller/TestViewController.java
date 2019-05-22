package com.shengvideo.modules.app.controller;

import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.usermodel.Picture;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/app")
public class TestViewController {
    @GetMapping("view/excel")
    public void excelView(String url, HttpServletResponse response) {
        url.replaceAll("%20", "\\+");//转换加号
        xlsToHtml(url,response);
    }

    @GetMapping("view/ppt")
    public void pptView(String url, HttpServletResponse response) {
        url.replaceAll("%20", "\\+");//转换加号
        pptToHtml(url,response);
    }

    private int xlsToHtml(String url,HttpServletResponse response) {
        int rv = 0;
        url.replaceAll("%20", "\\+");//转换加号
        String fileType = url.substring(url.lastIndexOf(".") + 1, url.length());
        //word路径
        String wordPath = "/Users/wz/upload";

        try {
            URL oaUrl = new URL(url);
            HttpURLConnection httpConn = (HttpURLConnection) oaUrl.openConnection();
            InputStream input = httpConn.getInputStream();
//            InputStream input = new FileInputStream(wordPath + wordName);
            HSSFWorkbook excelBook = new HSSFWorkbook(input);

            ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
            excelToHtmlConverter.processWorkbook(excelBook);
            List pics = excelBook.getAllPictures();
            if (pics != null) {
                for (int i = 0; i < pics.size(); i++) {
                    Picture pic = (Picture) pics.get(i);
                    try {
                        pic.writeImageContent(new FileOutputStream(wordPath + pic.suggestFullFileName()));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            Document htmlDocument = excelToHtmlConverter.getDocument();
//            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            OutputStream outStream = response.getOutputStream();
            DOMSource domSource = new DOMSource(htmlDocument);
            StreamResult streamResult = new StreamResult(outStream);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
            outStream.close();

//            String content = new String(outStream.toByteArray(), "utf-8");
//            String uuid = "123";
//            FileUtils.writeStringToFile(new File(wordPath, uuid + ".html"), content, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return rv;
        }
        rv = 1;
        return rv;

    }

    /**
     * ppt转html
     * @param response
     * @return
     */
    private int pptToHtml(String url,HttpServletResponse response){
        int rv = 0;
//        String path = presentationDto.getWordPath();
        //word路径
        String wordPath = "/Users/wz/upload";
        //文件夹名
        String folderName = new Random(10).nextInt() + "";
        List<String> imgList = new ArrayList<>();

        File folder = new File(wordPath + File.separator + folderName);
        try {
            folder.mkdirs();
            URL oaUrl = new URL(url);
            HttpURLConnection httpConn = (HttpURLConnection) oaUrl.openConnection();
            InputStream is = httpConn.getInputStream();
            SlideShow ppt = new SlideShow(is);
            is.close();
            Dimension pgsize = ppt.getPageSize();
            org.apache.poi.hslf.model.Slide[] slide = ppt.getSlides();
            for (int i = 0; i < slide.length; i++) {
                TextRun[] truns = slide[i].getTextRuns();
                for ( int k=0;k<truns.length;k++){
                    RichTextRun[] rtruns = truns[k].getRichTextRuns();
                    for(int l=0;l<rtruns.length;l++){
                        rtruns[l].setFontIndex(1);
                        rtruns[l].setFontName("宋体");
                    }
                }
                BufferedImage img = new BufferedImage(pgsize.width,pgsize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                graphics.setPaint(Color.BLUE);
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
                slide[i].draw(graphics);

                // 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径
                String imgName = File.separator + folderName + File.separator +"pict_"+ (i + 1) + ".jpeg";

                FileOutputStream out = new FileOutputStream(wordPath + imgName);
                javax.imageio.ImageIO.write(img, "jpeg", out);
                out.close();

                imgList.add(File.separator + "upload" + imgName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return rv;
        } catch (IOException e) {
            e.printStackTrace();
            return rv;
        }
        rv = createHtml(response,imgList);
        return rv;
    }

    /**
     * ppt转html时生成html
     * @param response	upload根目录
     * @param imgList	所有幻灯片路径
     * @param response	项目访问路径
     * @return
     */
    private int createHtml(HttpServletResponse response,List<String> imgList){

        StringBuilder sb = new StringBuilder("<!doctype html><html><head><meta charset='utf-8'><title>无标题文档</title></head><body>");
        if (imgList != null && !imgList.isEmpty()) {
            for (String img : imgList) {
                sb.append("<img src='" + img + "' /><br>");
            }
        }
        sb.append("</body></html>");
        try {
            OutputStream outStream = response.getOutputStream();
            outStream.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }



}


