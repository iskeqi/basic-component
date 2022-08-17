package tech.taoq.oms.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RuntimeShellUtil
 *
 * @author keqi
 */
@Slf4j
public class RuntimeShellUtil {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);

    /**
     * 开启一个子进程执行 shell 命令,并读取子进程的执行输出信息
     *
     * @param command shell 命令
     * @return 执行结果
     */
    public static Result exec(String command) {
        Result result = new Result();
        result.setSuccess(true);

        Process process = null;
        try {
            // 执行命令
            process = Runtime.getRuntime().exec(command);

            // 获取运行成功时的输出
            StringJoiner successResult = new StringJoiner("\n");
            Process successProcess = process;
            EXECUTOR_SERVICE.submit(() -> {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(successProcess.getInputStream()));
                try {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        successResult.add(line);
                    }
                } catch (IOException e) {
                    log.error("read subprocess inputStream failed", e);
                } finally {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        log.error("close subprocess inputStream failed", e);
                    }
                }
            });

            // 获取运行失败时的输出
            StringJoiner errorResult = new StringJoiner("\n");
            Process errorProcess = process;
            EXECUTOR_SERVICE.submit(() -> {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorProcess.getErrorStream()));
                try {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        errorResult.add(line);
                    }
                } catch (IOException e) {
                    log.error("read subprocess errorStream failed", e);
                } finally {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        log.error("close subprocess errorStream failed", e);
                    }
                }
            });

            // 等待执行结果
            int resultCode = process.waitFor();

            if (resultCode == 0) {
                result.setSuccessResult(successResult.toString());
            } else {
                result.setSuccess(false);
                result.setErrorResult(errorResult.toString());
            }

        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage(), e);
        } finally {
            assert process != null;
            process.destroy();
        }

        return result;
    }

    public static class Result {

        /**
         * 是否成功[true:成功 false:失败]
         */
        private Boolean success;

        /**
         * 成功响应结果
         */
        private String successResult;

        /**
         * 失败响应结果
         */
        private String errorResult;

        public boolean success() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getSuccessResult() {
            return successResult;
        }

        public void setSuccessResult(String successResult) {
            this.successResult = successResult;
        }

        public String getErrorResult() {
            return errorResult;
        }

        public void setErrorResult(String errorResult) {
            this.errorResult = errorResult;
        }
    }
}
