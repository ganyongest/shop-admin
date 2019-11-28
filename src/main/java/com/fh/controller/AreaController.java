package com.fh.controller;

import com.fh.common.ServerResponse;
import com.fh.model.Area;
import com.fh.service.area.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Api(tags = "area_manager")
@Controller
@RequestMapping("area/")
public class AreaController {
    @Autowired
 private AreaService areaService;

    @RequestMapping("index")
    public String index(){
        return "area/list";
    }



    @ApiOperation(value = "根据id查询该节点下的子节点",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pid",value="该节点id",required=true,paramType="query")
    })
    @RequestMapping("queryListByPid")
    @ResponseBody
    public ServerResponse queryListByPid(Integer pid){
       List<Area> list =  areaService.queryListByPid(pid);
       return ServerResponse.success(list);
    }





    @ApiOperation(value = "增加地区",httpMethod="POST")
    @RequestMapping("addArea")
    @ResponseBody
    public ServerResponse addArea(Area area){
          areaService.addArea(area);
        return ServerResponse.success();
    }
    @ApiOperation(value = "查询所有地区",httpMethod="POST")
    @RequestMapping("queryList")
    @ResponseBody
    public ServerResponse queryList(){
        List<Area> list =  areaService.queryList();
        return ServerResponse.success(list);
    }
    @ApiOperation(value = "删除地区",httpMethod="POST")
    @RequestMapping("deleteArea")
    @ResponseBody
    public ServerResponse deleteArea(@RequestParam("idList[]") List idList){
        areaService.deleteArea(idList);
        return ServerResponse.success();
    }
    @ApiOperation(value = "修改地区",httpMethod="POST")
    @RequestMapping("updateArea")
    @ResponseBody
    public ServerResponse updateArea(Area area){
        areaService.updateArea(area);
        return ServerResponse.success();
    }

}
