package com.shengvideo.common.utils;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.shengvideo.common.exception.RRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ConnectException;

public class OfficeUtils {
    private static final int wdFormatPDF = 17;
    private static final int xlTypePDF = 0;
    private static final int ppSaveAsPDF = 32;
    private static final int ppSaveAsMP4 = 39;
    static Logger log = LoggerFactory.getLogger(OfficeUtils.class);
    public static String pptConveter(File file) {
        ComThread.InitSTA();
        ActiveXComponent activexcomponent = new ActiveXComponent(
                "PowerPoint.Application");
        System.out.println("正在转换      "+file.getAbsolutePath()+"");
        boolean flag = false;
        String savePath=file.getAbsolutePath().replaceAll(".ppt$","");
        try {
            Dispatch dispatch = activexcomponent.getProperty("Presentations")
                    .toDispatch();
            Dispatch dispatch1 = Dispatch.call(dispatch, "Open", file.getAbsolutePath(),
                    new Variant(-1), new Variant(-1), new Variant(0))
                    .toDispatch();
            Dispatch.call(dispatch1, "SaveAs", savePath, new Variant(11));  //5:pot  6:rtf 7:pps 11:pptx
            Variant variant = new Variant(-1);
            Dispatch.call(dispatch1, "Close");
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("转换出错 file:" + file.getAbsolutePath());
            throw new RRException("文件转换出错，请检查文件格式");
        } finally {
            activexcomponent.invoke("Quit", new Variant[0]);
            ComThread.Release();
            ComThread.quitMainSTA();
        }
        log.info("转换完毕 file:" + file.getAbsolutePath());
        return savePath;
    }

    public static String wordConveter(File file) {
        // 启动word应用程序(Microsoft Office Word 2003)
        String savePath=file.getAbsolutePath().replaceAll(".doc$","docx");

        ActiveXComponent app = new ActiveXComponent("Word.Application");
        System.out.println("正在转换     "+file.getAbsolutePath()+"");
        try {
            // 设置word应用程序不可见
            app.setProperty("Visible", new Variant(false));
            // documents表示word程序的所有文档窗口，（word是多文档应用程序）
            Dispatch docs = app.getProperty("Documents").toDispatch();
            // 打开要转换的word文件
            Dispatch doc = Dispatch.invoke(
                    docs,
                    "Open",
                    Dispatch.Method,
                    new Object[] { file.getAbsolutePath(), new Variant(false),
                            new Variant(true) }, new int[1]).toDispatch();

            // 作为type格式保存到临时文件
            // *Variant(0):doc
            // *Variant(1):dot
            // *Variant(2-5)，Variant(7):txt
            // *Variant(6):rft
            // *Variant(8)，Variant(10):htm
            // *Variant(9):mht
            // *Variant(11)，Variant(19-22):xml
            // *Variant(12):docx
            // *Variant(13):docm
            // *Variant(14):dotx
            // *Variant(15):dotm
            // *Variant(16)、Variant(24):docx
            // *Variant(17):pdf
            // *Variant(18):xps
            // *Variant(23):odt
            // *Variant(25):与Office2003与2007的转换程序相关，执行本程序后弹出一个警告框说是需要更高版本的 Microsoft

            int type=12;
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {
                    savePath + "x", new Variant(type) }, new int[1]);
            // 关闭word文件
            Dispatch.call(doc, "Close", new Variant(false));
        } catch (Exception e) {
            log.info("转换出错 file:" + file.getAbsolutePath());
            throw new RRException("转换出错,请检查文件格式");
        } finally {
            // 关闭word应用程序
            app.invoke("Quit", new Variant[] {});
        }
        log.info("转换完毕 file:" + file.getAbsolutePath());
        return savePath;
    }


    public static boolean convert2Video(String inputFile, String videoFile) {
        String suffix = getFileSufix(inputFile);
        File file = new File(inputFile);
        if (!file.exists()) {
            return false;
        }
        String pdfFile = inputFile.replace(suffix,"pdf");
        if (suffix.equals("doc") || suffix.equals("docx") || suffix.equals("ppt") || suffix.equals("pptx") ||
                suffix.equals("xls") || suffix.equals("xlsx")|| suffix.equals("txt")) {
            if (office2PDF(inputFile, pdfFile)){
                try {
                    return pdf2swf(pdfFile,videoFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new  RRException("文件转换失败，请检查格式! error" + e.getMessage());
                }
            }else {
               return false;
            }

        }
//        else if (suffix.equals("ppt") || suffix.equals("pptx")) {
//            if (ppt2PDF(inputFile, pdfFile)){
//                try {
//                    return pdf2swf(pdfFile,videoFile);
//                } catch (IOException e) {
//                    throw new  RRException("文件转换失败，请检查格式! error" + e.getMessage());
//                }
//            }else {
//                return false;
//            }
////            if (suffix.equals("ppt")){
////                return ppt2MP4(pptConveter(new File(inputFile)),videoFile);
////            }
////            return ppt2MP4(inputFile,videoFile);
//        } else if (suffix.equals("xls") || suffix.equals("xlsx")) {
//            if (excel2PDF(inputFile, pdfFile)){
//                try {
//                    return pdf2swf(pdfFile,videoFile);
//                } catch (IOException e) {
//                    throw new  RRException("文件转换失败，请检查格式! error" + e.getMessage());
//                }
//            }else {
//                return false;
//            }
//        }
        else if (suffix.equals("pdf")) {
            try {
                return pdf2swf(inputFile,videoFile);
            } catch (IOException e) {
                e.printStackTrace();
                throw new  RRException("文件转换失败，请检查格式! error" + e.getMessage());
            }

        } else {
            return false;
        }
    }
    public static String getFileSufix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(splitIndex + 1);
    }
    // word转换为pdf
    public static boolean word2PDF(String inputFile, String pdfFile) {
        try {
            // 打开word应用程序
            ActiveXComponent app = new ActiveXComponent("Word.Application");
            // 设置word不可见
            app.setProperty("Visible", false);
            // 获得word中所有打开的文档,返回Documents对象
            Dispatch docs = app.getProperty("Documents").toDispatch();
            // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
            Dispatch doc = Dispatch.call(docs, "Open", inputFile, false, true)
                    .toDispatch();
            // 调用Document对象的SaveAs方法，将文档保存为pdf格式
            /*
             * Dispatch.call(doc, "SaveAs", pdfFile, wdFormatPDF
             * //word保存为pdf格式宏，值为17 );
             */
            Dispatch.call(doc, "ExportAsFixedFormat", pdfFile, wdFormatPDF);// word保存为pdf格式宏，值为17
            // 关闭文档
            Dispatch.call(doc, "Close", false);
            // 关闭word应用程序
            app.invoke("Quit", 0);
            return true;
        } catch (Exception e) {
            throw new  RRException("文件转换失败，请检查格式! error" + e.getMessage());
        }
    }
    // excel转换为pdf
    public static boolean excel2PDF(String inputFile, String pdfFile) {
        try {
            ActiveXComponent app = new ActiveXComponent("Excel.Application");
            app.setProperty("Visible", false);
            Dispatch excels = app.getProperty("Workbooks").toDispatch();
            Dispatch excel = Dispatch.call(excels, "Open", inputFile, false,
                    true).toDispatch();
            Dispatch.call(excel, "ExportAsFixedFormat", xlTypePDF, pdfFile);
            Dispatch.call(excel, "Close", false);
            app.invoke("Quit");
            return true;
        } catch (Exception e) {
            throw new  RRException("文件转换失败，请检查格式! error" + e.getMessage());
        }
    }
//     ppt转换为pdf
    public static boolean ppt2PDF(String inputFile, String pdfFile) {
        try {
            ActiveXComponent app = new ActiveXComponent(
                    "PowerPoint.Application");
            // app.setProperty("Visible", msofalse);
//            app.setProperty("Visible", false);
            Dispatch ppts = app.getProperty("Presentations").toDispatch();

            Dispatch ppt = Dispatch.call(ppts, "Open", inputFile, true,// ReadOnly
                    true,// Untitled指定文件是否有标题
                    false// WithWindow指定文件是否可见
            ).toDispatch();

//            Dispatch.call(ppt, "ExportAsFixedFormat", pdfFile, ppSaveAsPDF);
            Dispatch.callN(ppt, "SaveAs",  new Variant(pdfFile));

//            Dispatch.call(ppt, "Close");
            Dispatch.call(ppt, "Close");

            app.invoke("Quit");
            return true;
        } catch (Exception e) {
            throw new  RRException("文件转换失败，请检查格式! error" + e.getMessage());
        }
    }

    public static synchronized boolean pdf2swf(String fileDir, String swfFile) throws IOException {
        //文件路径
        String filePath = fileDir.substring(0, fileDir.lastIndexOf("/"));
        //文件名，不带后缀
//        String fileName = fileDir.substring((filePath.length() + 1), fileDir.lastIndexOf("."));
        Process pro = null;
        if (isWindowsSystem() || OSinfo.isMacOS() || OSinfo.isMacOSX()) {
            //如果是windows系统
            //命令行命令
            String cmd =  "pdf2swf " + fileDir + " -o "  + swfFile;
            //Runtime执行后返回创建的进程对象
            pro = Runtime.getRuntime().exec(cmd);
        } else {
            //如果是linux系统,路径不能有空格，而且一定不能用双引号，否则无法创建进程
            String[] cmd = new String[3];
            cmd[0] = "pdf2swf";
            cmd[1] = fileDir;
            cmd[2] = swfFile;
            //Runtime执行后返回创建的进程对象
            pro = Runtime.getRuntime().exec(cmd);
        }
        //非要读取一遍cmd的输出，要不不会flush生成文件（多线程）
        new DoOutput(pro.getInputStream()).start();
        new DoOutput(pro.getErrorStream()).start();
        try {
            //调用waitFor方法，是为了阻塞当前进程，直到cmd执行完
            pro.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new  RRException("文件转换失败，请检查格式! error" + e.getMessage());
        }
        return true;
    }

    /**
     * 判断是否是windows操作系统
     * @author iori
     * @return
     */
    private static boolean isWindowsSystem() {
        String p = System.getProperty("os.name");
        return p.toLowerCase().indexOf("windows") >= 0 ? true : false;
    }

    /**
     * 多线程内部类
     * 读取转换时cmd进程的标准输出流和错误输出流，这样做是因为如果不读取流，进程将死锁
     * @author iori
     */
    private static class DoOutput extends Thread {
        public InputStream is;

        //构造方法
        public DoOutput(InputStream is) {
            this.is = is;
        }

        public void run() {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.is));
            String str = null;
            try {
                //这里并没有对流的内容进行处理，只是读了一遍
                while ((str = br.readLine()) != null){
                    log.info("pdf2swf:" + str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static boolean ppt2MP4(String inputFile, String pdfFile) {
        try {
//            ActiveXComponent app = new ActiveXComponent(
//                    "PowerPoint.Application");
            ActiveXComponent app = new ActiveXComponent(
                    "PowerPoint.Application");
//            app.setProperty("Visible", false);
            Dispatch ppts = app.getProperty("Presentations").toDispatch();
            Dispatch ppt = Dispatch.call(ppts, "Open", inputFile, true,// ReadOnly
                    true,
                    false
            ).toDispatch();
//            Dispatch.call(ppt, "SaveAs", pdfFile, );
              Dispatch.call(ppt, "SaveAs", pdfFile, new Variant(ppSaveAsMP4));
            //	Dispatch.call(ppt, "SaveAs", pdfFile, new Variant(PPT_SAVEAS_PDF));
//            	Dispatch.invoke(ppt, "SaveAs2", Dispatch.Method, new Object[] { pdfFile, ppSaveAsMP4 }, new int[1]);
            //	Dispatch.invoke(ppt, "SaveAs", Dispatch.Method, new Object[] { new Variant(PPT_SAVEAS_PDF) }, new int[1]);

            Dispatch.call(ppt, "Close");
            app.invoke("Quit");
            return true;
        } catch (Exception e) {
            throw new RRException("转换失败，请检查文件 error:" + e.getMessage());
        }
    }

    /**
     * 将Office文档转换为PDF. 运行该函数需要用到OpenOffice, OpenOffice下载地址为
     * http://www.openoffice.org/
     *
     * <pre>
     * 方法示例:
     * String sourcePath = "F:\\office\\source.doc";
     * String destFile = "F:\\pdf\\dest.pdf";
     * Converter.office2PDF(sourcePath, destFile);
     * </pre>
     *
     * @param sourceFile
     *            源文件, 绝对路径. 可以是Office2003-2007全部格式的文档, Office2010的没测试. 包括.doc,
     *            .docx, .xls, .xlsx, .ppt, .pptx等. 示例: F:\\office\\source.doc
     * @param destFile
     *            目标文件. 绝对路径. 示例: F:\\pdf\\dest.pdf
     * @return 操作成功与否的提示信息. 如果返回 -1, 表示找不到源文件, 或url.properties配置错误; 如果返回 0,
     *         则表示操作成功; 返回1, 则表示转换失败
     */
    public static boolean office2PDF(String sourceFile, String destFile) {
        File inputFile = new File(sourceFile);
        if (!inputFile.exists()) {
            throw new RRException("文件转换出错！ error:找不到上传文件");
        }

        // 如果目标路径不存在, 则新建该路径
        File outputFile = new File(destFile);
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }

//        String cmd = "soffice -headless -accept=\"socket,host=localhost,port=8100;urp;\" -nofirststartwizard";
//
//        try {
//            Runtime.getRuntime().exec(cmd);
//        } catch (IOException e) {
//            throw new RRException("文件转换出错！ error:" + e.getMessage());
//        }
        // connect to an OpenOffice.org instance running on port 8100
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(
                "127.0.0.1", 8100);
        try {
            connection.connect();
        } catch (ConnectException e) {
            e.printStackTrace();
            throw new RRException("文件转换出错！ error:" + e.getMessage());
        }

        // convert
        DocumentConverter converter = new OpenOfficeDocumentConverter(
                connection);
        converter.convert(inputFile, outputFile);

        // close the connection
        connection.disconnect();

        return true;
    }



}
