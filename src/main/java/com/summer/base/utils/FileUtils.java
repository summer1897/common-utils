package com.summer.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Intellij IDEA
 *
 * @Projcet common-utils
 * @Author duhao
 * @Date 2018/1/12
 * @Time 11:12
 * @Description 文件操作类
 */

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static final String BYTE = "byte";
    public static final String KB = "KB";
    public static final long SIZE_KB = 1024;
    public static final String MB = "MB";
    public static final long SIZE_MB = SIZE_KB * 1024;
    public static final String GB = "GB";
    public static final long SIZE_GB = SIZE_MB * 1024;
    public static final String TB = "TB";
    public static final long SIZE_TB = SIZE_GB * 1024;
    public static final String PB = "PB";
    public static final long SIZE_PB = SIZE_TB * 1024;
    public static final String EB = "EB";
    public static final long SIZE_EB = SIZE_PB * 1024;
    public static final String YB = "YB";
    public static final long SIZE_YB = SIZE_EB * 1024;
    public static final String ZB = "ZB";
    public static final long SIZE_ZB = SIZE_YB * 1024;
    public static final String BB = "BB";
    public static final long SIZE_BB = SIZE_ZB * 1024;

    private static String FILE_SEPARATOR = System.getProperty("file.separator");


    private static double formatNumber(double number,int scale) {
        BigDecimal bd = new BigDecimal(number);
        return bd.divide(new BigDecimal(1),scale,RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 格式化文件大小，以用户可读方式返回文件大小信息
     * @param size 最大支持PB级别转换
     * @return {@link String}
     */
    public static String formatFileSize(double size) {
        if (size < 0) {
            return StringUtils.concat("0",BYTE);
        } else if (size < SIZE_KB) {
            return StringUtils.concat(size + "",BYTE);
        } else if(size < SIZE_MB) {
            double tmpSize = formatNumber((size * 1.0) / SIZE_KB,2);
            return StringUtils.concat(tmpSize + "",KB);
        } else if (size < SIZE_GB) {
            double tmpSize = formatNumber((size * 1.0) / SIZE_MB,2);
            return StringUtils.concat(tmpSize + "",MB);
        } else if (size < SIZE_TB) {
            double tmpSize = formatNumber((size * 1.0) / SIZE_GB,2);
            return StringUtils.concat(tmpSize + "",GB);
        } else if (size < SIZE_PB) {
            double tmpSize = formatNumber((size * 1.0) / SIZE_TB,2);
            return StringUtils.concat(tmpSize + "",TB);
        } else if (size < SIZE_EB) {
            double tmpSize = formatNumber((size * 1.0) / SIZE_PB,2);
            return StringUtils.concat(tmpSize + "",PB);
        } else if (size < SIZE_YB) {
            double tmpSize = formatNumber((size * 1.0) / SIZE_EB,2);
            return StringUtils.concat(tmpSize + "",EB);
        } else if (size < SIZE_ZB) {
            double tmpSize = formatNumber((size * 1.0) / SIZE_YB,2);
            return StringUtils.concat(tmpSize + "",YB);
        } else if (size < SIZE_BB) {
            double tmpSize = formatNumber((size * 1.0) / SIZE_YB,2);
            return StringUtils.concat(tmpSize + "",YB);
        }
        return "超出可度量范围";
    }

    /**
     * 获取文件名后缀
     * @param fileName
     * @return {@link String}
     */
    public static String getExtension(String fileName) {
        String ext = null;
        if (StringUtils.isNotEmpty(fileName)) {
            int index = fileName.lastIndexOf(".");
            if (index >= 0) {
                ext = fileName.substring(index + 1);
            }
        }
        return ext;
    }

    public static String resolvePath(String rootPath,String...paths) {
        StringBuilder sb = new StringBuilder();
        if (ObjectUtils.isNotNull(paths) && paths.length > 0) {
            if (StringUtils.isNotEmpty(rootPath)) {
                sb.append(rootPath).append(FILE_SEPARATOR);
            }
            for (String path : paths) {
                sb.append(path).append(FILE_SEPARATOR);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String getRealFilePath(String path) {
        return path.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);
    }

    public static String getHttpURLPath(String path) {
        return path.replace("\\", "/");
    }

    public static void mkdir(String path) {
        if (StringUtils.isNotEmpty(path)) {
            Path p = Paths.get(path);
            if (!Files.exists(p)) {
                try {
                    Files.createDirectories(p);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void mkdir(String...paths) {
        String finalPath = FileUtils.resolvePath("",paths);
        mkdir(finalPath);
    }

    public static void mkFile(String file) {
        if (StringUtils.isNotEmpty(file)) {
            Path path = Paths.get(file);
            if (Files.notExists(path)) {
                try {
                    Files.createFile(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void mkFile(String fileName,String...paths) {
        mkdir(paths);
        String path = FileUtils.resolvePath(null, paths);
        String finalFile = FileUtils.resolvePath(path,fileName);
        mkFile(finalFile);
    }

    public static void delete(String path) {
        if (StringUtils.isNotEmpty(path)) {
            Path path1 = Paths.get(path);
            try {
                Files.deleteIfExists(path1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void delete(String...paths) {
        String finalPath = resolvePath(null,paths);
        delete(finalPath);
    }

    private static void close(InputStream is) {
        if (ObjectUtils.isNotNull(is)) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void close(OutputStream os) {
        if (ObjectUtils.isNotNull(os)) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//       mkFile("hello.txt","F:","upload","sayHello");
//        delete("F:","upload","sayHello","hello.txt");
        Path path = Paths.get("F", "upload");
        Path fileName = path.getFileName();
        System.out.println(fileName.getName(0));
    }

}
