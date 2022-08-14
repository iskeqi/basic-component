package tech.taoq.oms.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.taoq.oms.domain.dto.LogFileListDto;
import tech.taoq.web.mvc.result.NoAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Api(tags = "日志文件下载")
@RestController
@RequestMapping("/oms/logFile")
public class LogFileDownload {

    @ApiOperation("查询指定路径下的文件")
    @GetMapping("/{path}")
    public List<LogFileListDto> listLogFileByApp(@PathVariable String path) {
        List<String> logFileNameList = FileUtil.listFileNames(path);
        List<LogFileListDto> dtoList = new ArrayList<>();

        for (String fileName : logFileNameList) {
            File file = new File(path + "/" + fileName);

            LogFileListDto dto = new LogFileListDto();
            dto.setFileName(fileName);
            dto.setSize(String.valueOf(file.length()));
            dto.setLastUpdateTime(DateUtil.toLocalDateTime(new Date(file.lastModified())));

            dtoList.add(dto);
        }

        return dtoList;
    }

    @ApiOperation("下载指定日志文件")
    @GetMapping("/download/{path}")
    @NoAdvice
    public void download(@PathVariable String path, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        InputStream fis = null;
        try {
            File file = new File(path);
            fis = FileUtil.getInputStream(file);

            String fileName = URLEncoder.encode(file.getName(), request.getCharacterEncoding());
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);

            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
