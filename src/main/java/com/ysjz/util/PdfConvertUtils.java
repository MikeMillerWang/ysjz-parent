package com.ysjz.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 使用 libreoffice 将 excel 转换为 pdf
 */
@Slf4j
public class PdfConvertUtils {
    /**
     * 转换最大进程数
     */
    public static final Semaphore MAX_PROCESS_NUM_SEMAPHORE = new Semaphore(5);
    /**
     * 获取进程最长超时时常 单位秒
     */
    public static final int GET_PROCESS_MAX_WAIT_TIME = 60;
    /**
     * 转换最长超时时常 单位秒
     */
    public static final int PROCESS_CONVERT_MAX_WAIT_TIME = 60;

    public static final String WINDOWS_COMMAND = "cmd /c start soffice --headless --invisible --convert-to pdf:writer_pdf_Export %s --outdir %s";

    public static final String LINUX_COMMAND = "libreoffice --headless --invisible  --convert-to pdf:writer_pdf_Export %s --outdir %s";
    public static final String WINDOWS = "Windows";

    public static String excelToPdf(String inputFilePath) {
        String outPutFilePath = inputFilePath.substring(0, inputFilePath.lastIndexOf('.')) + ".pdf";
        String outPutFilePPath = outPutFilePath.substring(0, Math.max(outPutFilePath.lastIndexOf('\\'), outPutFilePath.lastIndexOf('/')));
        String command = buildCommand(inputFilePath, outPutFilePPath);
        try {
            boolean acquire = MAX_PROCESS_NUM_SEMAPHORE.tryAcquire(GET_PROCESS_MAX_WAIT_TIME, TimeUnit.SECONDS);
            if (acquire) {
                executeCommand(command);
            } else {
                throw new RuntimeException("文件PDF转换异常,请稍后重试");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("文件PDF转换异常,请稍后重试");
        } finally {
            MAX_PROCESS_NUM_SEMAPHORE.release();
        }
        return outPutFilePath;
    }

    /**
     * 构建转换命令
     *
     * @param inputFilePath 输入文件路径
     * @param outPutFilePath 输出文件路径
     * @return
     */
    private static String buildCommand(String inputFilePath, String outPutFilePath) {
        String osName = System.getProperty("os.name");
        return String.format(osName.contains(WINDOWS) ? WINDOWS_COMMAND : LINUX_COMMAND, inputFilePath, outPutFilePath);
    }


    /**
     * 执行转换
     *
     * @param command 命令
     */
    private static void executeCommand(String command) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            process.waitFor(PROCESS_CONVERT_MAX_WAIT_TIME, TimeUnit.SECONDS);
            int exitValue = process.exitValue();
            if (0 != exitValue) {
                throw new RuntimeException(String.format("convertOffice2PDF command:{%s}", command));
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            String format = String.format("convertOffice2PDF command:{%s}", command);
            log.error(format, e);
            throw new RuntimeException(e.getMessage());
        } finally {
            if (null != process) {
                try {
                    process.getInputStream().close();
                } catch (IOException e) {
                    log.error("process.getInputStream().close() error", e);
                }
                try {
                    process.getOutputStream().close();
                } catch (IOException e) {
                    log.error("process.getOutputStream().close() error", e);
                }
                process.destroy();
            }
        }
    }
}
