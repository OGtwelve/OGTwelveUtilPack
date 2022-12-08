package cn.com.ogtwelve.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 多条执行CMD命令使用方式:
 * 1.新建一个ProcShell对象;
 * 2.写一条String包含多条CMD命令行; 例如: String cmd = 路径1 + 路径2;
 * 3.调用ProcShell对象的execByTimeoutShell(), 在括号中放入第二步的String即可;
 */
@Slf4j
public class ProcShell {
    static String describe = "多条执行CMD命令使用方式: " +
            "1.新建一个ProcShell对象;" +
            "2.写一条String包含多条CMD命令行; 例如: String cmd = 路径1 + 路径2;" +
            "3.调用ProcShell对象的execByTimeoutShell(), 在括号中放入第二步的String即可";

    //private String charsetName = "UTF-8";
    private String charsetName = "GBK";
    private String logFilePath = "./log/";
    private Long timeout = (long) (1000 * 60 * 20);
    private boolean isSuccess = true;

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public String getCharsetName() {
        return charsetName;
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    private static class Worker extends Thread {
        private final Process process;
        private Integer exit;

        private Worker(Process process) {
            this.process = process;
        }

        public void run() {
            try {
                exit = process.waitFor();
            } catch (InterruptedException ignore) {
                return;
            }
        }
    }

    public boolean execByTimeout(String commandstr) {
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            log.info("执行cmd命令：{}", commandstr);
            process = runtime.exec(commandstr);
            outputLog(process, true, false);

            Worker worker = new Worker(process);

            worker.start();
            worker.join(this.timeout);

            if (worker.exit != null) {
                if (isSuccess) {
                    writeComLog("Process out :ִ执行成功...");
                } else {
                    writeComLog("Process out :ִ执行过程中有错误发生...");
                }
                return isSuccess;
            } else {
                writeComLog("Process err :ִ" + commandstr);

                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }

        return false;
    }

    public boolean execByTimeoutByDir(String commandstr, String dir) {
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            String env = " cmd /c path " + dir + ";%path% & ";
            commandstr = env + commandstr;
            log.info("执行cmd命令：{}", commandstr);
            process = runtime.exec(commandstr);
            outputLog(process, true, false);

            Worker worker = new Worker(process);

            worker.start();
            worker.join(this.timeout);

            if (worker.exit != null) {
                if (isSuccess) {
                    writeComLog("Process out :ִ执行成功...");
                } else {
                    writeComLog("Process out :ִ执行过程中有错误发生...");
                }
                return isSuccess;
            } else {
                writeComLog("Process err :ִ" + commandstr);

                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }

        return false;
    }

    public static boolean isDone = false;

    public boolean execByTimeoutShell(String commandstr) {
        System.out.print(commandstr);
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            process = runtime.exec(commandstr);
            outputLog(process, true, false);

            Worker worker = new Worker(process);
            worker.start();
            worker.join(this.timeout);

            if (worker.exit != null) {
                if (isSuccess) {
                    writeComLog("Process out :执行成功...");
                } else {
                    writeComLog("Process out :执行过程中有错误发生...");
                }
                return isSuccess;
            } else {
                writeComLog("Process err :" + commandstr);

                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }

        return false;
    }

    public boolean exec(String commandstr) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(commandstr);

            outputLog(process, true, false);

            process.waitFor();
            return isDone;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

    /**
     * 日志输出回调
     */
    @SuppressWarnings("static-access")
    public synchronized void writeComLog(String str) {
        Calendar c = GregorianCalendar.getInstance();
        String datetime = null;
        try {

            datetime = "" + c.get(c.YEAR) + "-" + fillZero(1 + c.get(c.MONTH) + "", 2) + "-"
                    + fillZero("" + c.get(c.DAY_OF_MONTH), 2) + " " + fillZero("" + c.get(c.HOUR), 2) + ":"
                    + fillZero("" + c.get(c.MINUTE), 2) + ":" + fillZero("" + c.get(c.SECOND), 2);

            str = "[" + datetime + "] " + str;
            log.info(str);

            writeLog(str);

        } catch (Exception e) {
            log.error("Error");
        }
    }

    private void outputLog(final Process pr, boolean printOutput, boolean printError) {
        if (printOutput) {
            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {

                    BufferedReader br_in = null;
                    try {
                        br_in = new BufferedReader(new InputStreamReader(pr.getInputStream(), charsetName));
                        String buff = null;
                        String lastBuff = null;
                        while ((buff = br_in.readLine()) != null) {

                            if (buff != null && !buff.equals(lastBuff)) {
                                lastBuff = buff;
                                if (buff.contains("Done")) {
                                    isDone = true;
                                }
                                writeComLog("Process out :" + buff);
                            }

                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {
                            }
                        }
                        br_in.close();
                    } catch (IOException ioe) {
                        log.error("Exception caught printing process output.");
//						ioe.printStackTrace(); 
                    } finally {
                        try {
                            br_in.close();
                        } catch (Exception ex) {
                        }
                    }

                }

            }, 100);
        }

        if (printError) {
            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    BufferedReader br_err = null;

                    try {
                        br_err = new BufferedReader(new InputStreamReader(pr.getErrorStream(), charsetName));
                        String buff = null;
                        String lastBuff = null;
                        while ((buff = br_err.readLine()) != null) {

                            if (buff != null && !buff.equals(lastBuff)) {
                                lastBuff = buff;
                                writeComLog("Process err :" + buff);
                                isSuccess = false;
                                // break;
                            }
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {
                            }

                        }
                        br_err.close();
                    } catch (IOException ioe) {
                        System.out.println("Exception caught printing process error.");
                        ioe.printStackTrace();
                    } finally {
                        try {
                            br_err.close();
                        } catch (Exception ex) {
                        }
                    }
                }

            }, 100);
        }
    }

    private void writeLog(String str) {
        if (logFilePath == null) {
            return;
        }

        BufferedWriter bufOut = null;

        try {
            File f = new File(logFilePath);
            if (f.exists() == true) {
                bufOut = new BufferedWriter(new FileWriter(f, true));
            } else {
                bufOut = new BufferedWriter(new FileWriter(f));
            }
            bufOut.write(str + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufOut != null) {
                try {
                    bufOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeLog(String logPath, String str) {
        if (logPath == null) {
            return;
        }

        BufferedWriter bufOut = null;

        try {
            File f = new File(logPath);
            if (f.exists() == true) {
                bufOut = new BufferedWriter(new FileWriter(f, true));
            } else {
                bufOut = new BufferedWriter(new FileWriter(f));
            }
            bufOut.write(str + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufOut != null) {
                try {
                    bufOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 左边填充0
     *
     * @param str
     * @param len
     * @return
     */
    static String fillZero(String str, int len) {
        int tmp = str.length();
        int t;
        String str1 = str;
        if (tmp >= len)
            return str1;
        t = len - tmp;
        for (int i = 0; i < t; i++)
            str1 = "0" + str1;
        return str1;
    }

}