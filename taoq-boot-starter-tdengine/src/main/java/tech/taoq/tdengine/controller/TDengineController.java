package tech.taoq.tdengine.controller;

import tech.taoq.tdengine.domain.param.CreateDbParam;
import tech.taoq.tdengine.service.TDengineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * TDengineController
 *
 * @author keqi
 */
@RestController
@RequestMapping("/tdengine")
public class TDengineController {

    @Autowired
    private TDengineService tDengineService;

    @PostMapping("/createDb")
    public void createDb(@RequestBody CreateDbParam param) {
        tDengineService.createDb(param);
    }

    @DeleteMapping("/deleteDb/{dbName}")
    public void deleteDb(@PathVariable String dbName) {
        tDengineService.deleteDb(dbName);
    }

    @GetMapping("/showDatabases")
    public List<Map<String, Object>> showDatabases() {
        return tDengineService.showDatabases();
    }
}
