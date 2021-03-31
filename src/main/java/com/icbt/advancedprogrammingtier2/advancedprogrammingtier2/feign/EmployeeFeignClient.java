package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.FeignConfigure;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.Constants;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.EmployeeDAO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@FeignClient(url = Constants.TIER1_URL, value = Constants.TIER1_URL , configuration = FeignConfigure.class)
@Component
public interface EmployeeFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/employee/employee-id/{employeeId}", consumes = "application/json")
    public EmployeeDAO getEmployeeById(@PathVariable("employeeId") Long employeeId);

    @RequestMapping(method = RequestMethod.POST, value = "/employee", consumes = "application/json")
    public EmployeeDAO saveEmployee(@RequestBody EmployeeDAO employee);

    @RequestMapping(method = RequestMethod.GET, value = "/employee/all-employees", consumes = "application/json")
    public List<EmployeeDAO> getAllEmployees();

    @RequestMapping(method = RequestMethod.GET, value = "/employee/user/username/{username}", consumes = "application/json")
    public EmployeeDAO getUserByUsername(@PathVariable("username")  String username);
}
